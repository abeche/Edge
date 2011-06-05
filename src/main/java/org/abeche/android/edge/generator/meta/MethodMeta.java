/**
 * 
 */
package org.abeche.android.edge.generator.meta;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.EnumSet;
import java.util.List;

import org.abeche.android.edge.util.GeneratorUtil;


/**
 * @author abeche
 *
 */
public class MethodMeta {
	private DocMeta description = new DocMeta();
	private List<AnnotationMeta> annotaions = new ArrayList<AnnotationMeta>();
	private EnumSet<ModifierMeta> modifiers = EnumSet.of(ModifierMeta.PUBLIC);
	private String genericType = "";
	private TypeMeta returnType = new TypeMeta();
	private String name = "";
	private List<VariableMeta> parameters = new ArrayList<VariableMeta>();
	private List<TypeMeta> throwables = new ArrayList<TypeMeta>();
	private String body = "";
	
	public DocMeta getDescription() {
		return description;
	}
	public void setDescription(DocMeta javadoc) {
		this.description = javadoc;
	}
	public List<AnnotationMeta> getAnnotaions() {
		return annotaions;
	}
	public void setAnnotaions(List<AnnotationMeta> annotaion) {
		this.annotaions = annotaion;
	}
	public EnumSet<ModifierMeta> getModifier() {
		return modifiers;
	}
	public void setModifier(EnumSet<ModifierMeta> modifire) {
		this.modifiers = modifire;
	}
	public String getGenericType() {
		return genericType;
	}
	public void setGenericType(String genericType) {
		this.genericType = genericType;
	}
	public TypeMeta getReturnType() {
		return returnType;
	}
	public void setReturnType(TypeMeta returnType) {
		this.returnType = returnType;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<VariableMeta> getParameters() {
		return parameters;
	}
	public void setParameters(List<VariableMeta> parameters) {
		this.parameters = parameters;
	}
	public List<TypeMeta> getThrowables() {
		return throwables;
	}
	public void setThrowables(List<TypeMeta> throwables) {
		this.throwables = throwables;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	
	public static MethodMeta def(String methodName) {
		MethodMeta result = new MethodMeta();
		result.setName(methodName);
		return result;
	}
	public List<AnnotationMeta> addAnnotation(AnnotationMeta annotation) {
		if (annotaions == null) {
			annotaions = new ArrayList<AnnotationMeta>();
		}
		annotaions.add(annotation);
		return annotaions;
	}
	public List<AnnotationMeta> addAnnotation(String annotation) {
		return addAnnotation(AnnotationMeta.at(annotation));
	}
	public List<AnnotationMeta> addAnnotation(Class<? extends Annotation> annotation) {
		return addAnnotation(AnnotationMeta.at(annotation));
	}
	public MethodMeta at(String annotation) {
		addAnnotation(annotation);
		return this;
	}
	public MethodMeta at(Class<? extends Annotation> annotation) {
		addAnnotation(annotation);
		return this;
	}
	public MethodMeta at(AnnotationMeta annotation) {
		addAnnotation(annotation);
		return this;
	}
	public MethodMeta modifier(EnumSet<ModifierMeta> modifier) {
		setModifier(modifier);
		return this;
	}
	public MethodMeta modifier(ModifierMeta modifier) {
		setModifier(EnumSet.of(modifier));
		return this;
	}
	public MethodMeta modifier(String modifier) {
		setModifier(EnumSet.of(ModifierMeta.valueOf(modifier)));
		return this;
	}
	public MethodMeta type(TypeMeta returnType) {
		setReturnType(returnType);
		return this;
	}
	public MethodMeta type(String returnTypeName) {
		setReturnType(TypeMeta.t(returnTypeName));
		return this;
	}
	public MethodMeta parameter(VariableMeta... args) {
		setParameters(Arrays.asList(args));
		return this;
	}
	public MethodMeta body(String body) {
		setBody(body);
		return this;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		result.append("%n");
		if (description != null) {
			result.append(description);
		}
		if ((annotaions != null) && (! annotaions.isEmpty())) {
			for (AnnotationMeta annotation : annotaions) {
				result.append(annotation).append("%n");
			}
		}
		if ((modifiers != null) && (! modifiers.isEmpty())) {
			for (ModifierMeta modifier : modifiers) {
				result.append(modifier).append(" ");
			}
		}
		if ((genericType != null) && (! genericType.isEmpty())) {
			result.append("<").append(genericType).append("> ");
		}
		result.append(returnType).append(" ");
		result.append(name);
		result.append("(");
		boolean isFirstAppend = true;
		for (VariableMeta parameter : parameters) {
			if (! isFirstAppend) {
				result.append(", ");
			}
			result.append(parameter);
			isFirstAppend = false;
		}
		result.append(")");
		if ((throwables != null) && (! throwables.isEmpty())) {
			result.append(" throws ");
			isFirstAppend = true;
			for (TypeMeta throwable : throwables) {
				if (! isFirstAppend) {
					result.append(", ");
				}
				result.append(throwable);
				isFirstAppend = false;
			}
			result.append(" ");
		}
		result.append("{%n").append(GeneratorUtil.indent(body)).append("%n}%n");
		return result.toString();
	}
}
