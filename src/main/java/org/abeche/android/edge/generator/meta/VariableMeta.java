/**
 * 
 */
package org.abeche.android.edge.generator.meta;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

/**
 * @author abeche
 *
 */
public class VariableMeta {
	private DocMeta description = new DocMeta();
	private List<AnnotationMeta> annotaions = new ArrayList<AnnotationMeta>();
	private EnumSet<ModifierMeta> modifiers = EnumSet.of(ModifierMeta.NONE);
	private TypeMeta type = new TypeMeta();
	private String name = "";
	private String value = "";
	
	public VariableMeta(String feildType, String feildName) {
		type.setType(feildType);
		this.name = feildName;
	}
	public VariableMeta(String feildType, String feildName, String feildValue) {
		this(feildType, feildName);
		value = feildValue;
	}
	public VariableMeta(EnumSet<ModifierMeta> modifier, String feildType
			, String feildName, String feildValue) {
		this(feildType, feildName, feildValue);
		this.modifiers = modifier;
	}
	public VariableMeta(Class<?> feildType, String feildName) {
		this(feildType.getCanonicalName(), feildName);
	}
	public VariableMeta(Class<?> feildType, String feildName, String feildValue) {
		this(feildType.getCanonicalName(), feildName, feildValue);
	}
	public VariableMeta(EnumSet<ModifierMeta> modifier, Class<?> feildType
			, String feildName, String feildValue) {
		this(feildType, feildName, feildValue);
		this.modifiers = modifier;
	}
	public static VariableMeta var(EnumSet<ModifierMeta> modifier, String feildType
			, String feildName, String feildValue) {
		return new VariableMeta(modifier, feildType, feildName, feildValue);
	}
	public static VariableMeta var(String feildType, String feildName, String feildValue) {
		return new VariableMeta(feildType, feildName, feildValue);
	}
	public static VariableMeta var(String feildType, String feildName) {
		return new VariableMeta(feildType, feildName);
	}
	public static VariableMeta var(EnumSet<ModifierMeta> modifier, Class<?> feildType
			, String feildName, String feildValue) {
		return new VariableMeta(modifier, feildType, feildName, feildValue);
	}
	public static VariableMeta var(Class<?> feildType, String feildName, String feildValue) {
		return new VariableMeta(feildType, feildName, feildValue);
	}
	public static VariableMeta var(Class<?> feildType, String feildName) {
		return new VariableMeta(feildType, feildName);
	}
	public DocMeta getDescription() {
		return description;
	}
	public void setDescription(DocMeta description) {
		this.description = description;
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
	public void setModifier(EnumSet<ModifierMeta> modifier) {
		this.modifiers = modifier;
	}
	public TypeMeta getType() {
		return type;
	}
	public void setType(TypeMeta type) {
		this.type = type;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
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
	public VariableMeta at(String annotation) {
		addAnnotation(annotation);
		return this;
	}
	public VariableMeta at(Class<? extends Annotation> annotation) {
		addAnnotation(annotation);
		return this;
	}
	public VariableMeta at(AnnotationMeta annotation) {
		addAnnotation(annotation);
		return this;
	}
	public VariableMeta modifier(EnumSet<ModifierMeta> modifier) {
		setModifier(modifier);
		return this;
	}
	public VariableMeta modifier(String modifier) {
		setModifier(EnumSet.of(ModifierMeta.valueOf(modifier)));
		return this;
	}
	public VariableMeta description(String description) {
		setDescription(DocMeta.doc(description));
		return this;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		if ((description != null) && (! description.isEmpty())) {
			result.append(description);
		}
		if ((annotaions != null) && (! annotaions.isEmpty())) {
			for (AnnotationMeta annotation : annotaions) {
				result.append(annotation).append("%n");
			}
		}
		if ((modifiers != null) && (! modifiers.isEmpty())
				&& (! modifiers.contains(ModifierMeta.NONE))) {
			for (ModifierMeta modifier : modifiers) {
				result.append(modifier).append(" ");
			}
		}
		result.append(type).append(" ");
		result.append(name);
		if ((value != null) && (! value.isEmpty())) {
			result.append(" = ").append(value).append(";");
		}
		return result.toString();
	}
}
