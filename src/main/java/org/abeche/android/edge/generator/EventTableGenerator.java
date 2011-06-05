/**
 * 
 */
package org.abeche.android.edge.generator;

import static org.abeche.android.edge.generator.meta.AnnotationMeta.at;
import static org.abeche.android.edge.generator.meta.MethodMeta.def;
import static org.abeche.android.edge.generator.meta.ModifierMeta.PROTECTED;
import static org.abeche.android.edge.generator.meta.TypeMeta.t;
import static org.abeche.android.edge.generator.meta.VariableMeta.var;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeMap;

import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.AnnotationMirror;
import javax.lang.model.element.AnnotationValue;
import javax.lang.model.element.ExecutableElement;
import javax.lang.model.element.TypeElement;
import javax.lang.model.element.VariableElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.util.ElementFilter;

import org.abeche.android.edge.event.CallBack;
import org.abeche.android.edge.event.EventHolder;
import org.abeche.android.edge.event.EventObserver;
import org.abeche.android.edge.event.EventTable;
import org.abeche.android.edge.util.ElementUtil;
import org.abeche.android.edge.util.GeneratorUtil;
import org.abeche.android.edge.util.StringUtil;


/**
 * @author abeche
 *
 */
public class EventTableGenerator {
	private Set<TypeElement> targetElements;
	private ProcessingEnvironment processingEnv;
	private Formatter out;
	private String outputPackage;
	private String generateClass;
	private static final SimpleDateFormat genDateFormat
		= new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSZ");
	private String generateDate = "";
	
	public EventTableGenerator(Set<TypeElement> targetElements
			, ProcessingEnvironment processingEnv, Formatter formatter
			, String outputPackage, String generateClass) {
		this.targetElements = targetElements;
		this.processingEnv = processingEnv;
		this.out = formatter;
		this.outputPackage = outputPackage;
		this.generateClass = generateClass;
	}

	public void generate() {
		List<EventHolder> callBackMethods = searchCallBackMethods();
		String eventTable = generateEventTable(callBackMethods);
		generateClasss(callBackMethods, eventTable);
		
	}

	private String generateEventTable(List<EventHolder> callBackMethods) {
		StringBuilder result = new StringBuilder();
		
		int eventNo = 1;
		result.append("%nMap<Integer, EventHolder> eventMap;%n%n");
		
		for (EventHolder callbackMethod : callBackMethods) {
			String declaredClass = callbackMethod.getDeclaredClassName();
			String eventName = callbackMethod.getEventName();
			String eventMethodName = callbackMethod.getEventMethodName();
			
			StringBuilder paramTypeBuffer = new StringBuilder();
			boolean isFirst = true;
			for(Class<?> paramType : callbackMethod.getEventMethodArgs()) {
				if (! isFirst) {
					paramTypeBuffer.append(", ");
				}
				paramTypeBuffer.append(paramType.getCanonicalName()).append(".class");
				isFirst = false;
			}
			String paramType = paramTypeBuffer.toString();
			int eventOrder = callbackMethod.getEventOrder();
			
			result.append("if (eventTable.containsKey(\"").append(eventName).append("\")) {%n");
			result.append(GeneratorUtil.indent("eventMap = eventTable.get(\"")).append(eventName).append("\");%n");
			result.append("} else {%n");
			result.append(GeneratorUtil.indent("eventMap = new TreeMap<Integer, EventHolder>();")).append("%n");
			result.append("}%n%n");
			
			result.append("EventHolder event").append(eventNo).append(" = new EventHolder();%n");
			result.append("event").append(eventNo).append(".setDeclaredClass(").append(declaredClass).append(".class);%n");
			result.append("event").append(eventNo).append(".setEventMethodName(\"").append(eventMethodName).append("\");%n");
			result.append("event").append(eventNo).append(".setEventMethodArgs(new Class[]{").append(paramType).append("});%n");
			result.append("event").append(eventNo).append(".setEventName(\"").append(eventName).append("\");%n");
			result.append("event").append(eventNo).append(".setEventOrder(").append(eventOrder).append(");%n%n");
			
			result.append("eventMap.put(").append(eventOrder).append(", event").append(eventNo).append(");%n");
			
			result.append("if (eventMap.size() == 1) {%n");
			result.append(GeneratorUtil.indent("eventTable.put(\""+eventName+"\", eventMap);")).append("%n");
			result.append("}%n%n");
			
			eventNo++;
		}
		
		return result.toString();
	}

	private void generateClasss(List<EventHolder> callBackMethods, String eventTable) {
		ClassBuilder builder = new ClassBuilder();
		
		builder
			.header("")
			.package_(outputPackage)
			.import_(Map.class)
			.import_(TreeMap.class)
			.import_(Override.class)
			.import_(EventHolder.class)
			.import_(EventTable.class)
			.import_(org.abeche.android.edge.generator.Generated.class)
			.description("this generatd class.<br/>%n" +
					" * this class defined callback event and callback methods.<br/>")
			.annotation(at(org.abeche.android.edge.generator.Generated.class.getSimpleName())
					.value("value", StringUtil.wrap(EventObserver.class.getCanonicalName(), "\""))
					.value("date", StringUtil.wrap(generateDate(), "\"")))
			.class_(generateClass)
			.extends_(EventTable.class.getSimpleName())
			.method(def("init")
				.at(Override.class.getSimpleName())
				.modifier(PROTECTED)
				.type(t(Void.class))
				.parameter(var("Map<String, Map<Integer, EventHolder>>", "eventTable"))
				.body(eventTable));
		
		out.format(builder.toString());
	}

	/**
	 * 
	 */
	public List<EventHolder> searchCallBackMethods() {
		List<EventHolder> result = new ArrayList<EventHolder>();
		
		for (TypeElement eventType : targetElements) {
			String evetListenerClass = ElementUtil.asClassName(processingEnv, eventType);
			if (evetListenerClass == null) {
				return result;
			}
			List<ExecutableElement> allMethodsElements = ElementFilter.methodsIn(
				processingEnv.getElementUtils().getAllMembers(eventType));
			for (ExecutableElement methodElement : allMethodsElements) {
				for (AnnotationMirror annotation : methodElement.getAnnotationMirrors()) {
					DeclaredType annotationType = annotation.getAnnotationType();
					if (ElementUtil.isSameType(processingEnv, annotationType, CallBack.class)) {
						EventHolder callBackMethod = extractCallback(
								evetListenerClass, methodElement, annotation);
						
						result.add(callBackMethod);
					}
				}
				
			}
		}
		
		return result;
	}

	/**
	 * @param evetListenerClassName
	 * @param methodElement
	 * @param annotation
	 * @return
	 */
	public EventHolder extractCallback(String evetListenerClassName,
			ExecutableElement methodElement, AnnotationMirror annotation) {
		EventHolder callBackMethod = new EventHolder();
		callBackMethod.setDeclaredClassName(evetListenerClassName);
		
		callBackMethod.setEventMethodName(
			methodElement.getSimpleName().toString());
		
		List<Class<?>> methodParameterTypes = new ArrayList<Class<?>>();
		for (VariableElement pramType : methodElement.getParameters()) {
			methodParameterTypes.add(ElementUtil.asClass(processingEnv, pramType));
		}
		callBackMethod.setEventMethodArgs(
			methodParameterTypes.toArray(new Class<?>[]{}));
		
		Map<? extends ExecutableElement, ? extends AnnotationValue> annotationValues
			= processingEnv.getElementUtils().getElementValuesWithDefaults(annotation);
		for (Entry<? extends ExecutableElement, ? extends AnnotationValue> entry :
			annotationValues.entrySet()) {
			if ("value".equals(entry.getKey().getSimpleName().toString())) {
				AnnotationValue eventValue = entry.getValue();
				callBackMethod.setEventName(eventValue.getValue().toString());
			} else if ("order".equals(entry.getKey().getSimpleName().toString())) {
				AnnotationValue orderValue = entry.getValue();
				int order = (Integer) orderValue.getValue();
				callBackMethod.setEventOrder(order);
			}
		}
		return callBackMethod;
	}
	
	private String generateDate() {
		synchronized (genDateFormat) {
			this.generateDate = genDateFormat.format(new Date());
			return generateDate;
		}
	}
	
	public String getGenerateDate() {
		synchronized (genDateFormat) {
			return generateDate;
		}
	}
}
