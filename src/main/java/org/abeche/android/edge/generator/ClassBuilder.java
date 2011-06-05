/**
 * 
 */
package org.abeche.android.edge.generator;

import java.lang.annotation.Annotation;
import java.util.EnumSet;

import org.abeche.android.edge.generator.meta.AnnotationMeta;
import org.abeche.android.edge.generator.meta.ClassMeta;
import org.abeche.android.edge.generator.meta.MethodMeta;
import org.abeche.android.edge.generator.meta.ModifierMeta;
import org.abeche.android.edge.generator.meta.VariableMeta;


/**
 * @author abeche
 *
 */
public class ClassBuilder {
	public final ClassMeta classMeta = new ClassMeta();
	
	public ClassBuilder header(String comment) {
		classMeta.header(comment);
		return this;
	}
	
	public ClassBuilder package_(String packageName) {
		classMeta.package_(packageName);
		return this;
	}
	
	public ClassBuilder import_(String importName) {
		classMeta.import_(importName);
		return this;
	}
	
	public ClassBuilder import_(Class<?> importClass) {
		classMeta.import_(importClass);
		return this;
	}
	
	public ClassBuilder description(String comment) {
		classMeta.description(comment);
		return this;
	}
	
	public ClassBuilder annotation(Class<? extends Annotation> annotationMeta) {
		classMeta.at(annotationMeta);
		return this;
	}
	
	public ClassBuilder annotation(AnnotationMeta annotationMeta) {
		classMeta.at(annotationMeta);
		return this;
	}
	
	public ClassBuilder modifier(String modifierName) {
		classMeta.modifier(modifierName);
		return this;
	}
	
	public ClassBuilder modifier(ModifierMeta modifierName) {
		classMeta.modifier(EnumSet.of(modifierName));
		return this;
	}
	
	public ClassBuilder modifier(EnumSet<ModifierMeta> modifiers) {
		classMeta.modifier(modifiers);
		return this;
	}
	
	public ClassBuilder class_(String className) {
		classMeta.class_(className);
		return this;
	}
	
	public ClassBuilder extends_(String superClass) {
		classMeta.extends_(superClass);
		return this;
	}
	
	public ClassBuilder extends_(Class<?> superClass) {
		classMeta.extends_(superClass.getName());
		return this;
	}
	
	public ClassBuilder impliments_(String interfaceClass) {
		classMeta.impl(interfaceClass);
		return this;
	}
	
	public ClassBuilder impliments_(Class<?> interfaceClass) {
		classMeta.impl(interfaceClass.getName());
		return this;
	}
	
	public ClassBuilder feild(VariableMeta feild) {
		classMeta.feild(feild);
		return this;
	}
	
	public ClassBuilder feild(String feildType, String feildName) {
		classMeta.feild(feildType, feildName);
		return this;
	}
	
	public ClassBuilder feild(String feildType, String feildName, String feildValue) {
		classMeta.feild(feildType, feildName, feildValue);
		return this;
	}
	
	public ClassBuilder feild(String modifier, String feildType, String feildName, String feildValue) {
		classMeta.feild(modifier, feildType, feildName, feildValue);
		return this;
	}
	
	public ClassBuilder feild(EnumSet<ModifierMeta> modifier, String feildType, String feildName, String feildValue) {
		classMeta.feild(modifier, feildType, feildName, feildValue);
		return this;
	}
	
	public ClassBuilder method(MethodMeta method) {
		classMeta.method(method);
		return this;
	}
	
	@Override
	public String toString() {
		return classMeta.toString();
	}
}
