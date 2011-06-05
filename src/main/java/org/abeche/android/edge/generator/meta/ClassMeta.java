/**
 * 
 */
package org.abeche.android.edge.generator.meta;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;

import org.abeche.android.edge.util.GeneratorUtil;


/**
 * @author abeche
 *
 */
public class ClassMeta {
	private DocMeta header = new DocMeta();
	private List<String> packages = new ArrayList<String>();
	private List<String> imports = new ArrayList<String>();
	private DocMeta description = new DocMeta();
	private List<AnnotationMeta> annotaions = new ArrayList<AnnotationMeta>();
	private EnumSet<ModifierMeta> modifiers = EnumSet.of(ModifierMeta.PUBLIC);
	private TypeMeta name = new TypeMeta();
	private TypeMeta superClass = new TypeMeta();
	private List<TypeMeta> implInterfaces = new ArrayList<TypeMeta>();
	private List<VariableMeta> feilds = new ArrayList<VariableMeta>();
	private List<MethodMeta> methods = new ArrayList<MethodMeta>();
	public DocMeta getHeader() {
		return header;
	}
	public void setHeader(DocMeta header) {
		this.header = header;
	}
	public List<String> getPackages() {
		return packages;
	}
	public void setPackages(List<String> packages) {
		this.packages = packages;
	}
	public List<String> getImports() {
		return imports;
	}
	public void setImports(List<String> imports) {
		this.imports = imports;
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
	public void setAnnotaions(List<AnnotationMeta> annotaions) {
		this.annotaions = annotaions;
	}
	public EnumSet<ModifierMeta> getModifier() {
		return modifiers;
	}
	public void setModifier(EnumSet<ModifierMeta> modifier) {
		this.modifiers = modifier;
	}
	public TypeMeta getName() {
		return name;
	}
	public void setName(TypeMeta name) {
		this.name = name;
	}
	public TypeMeta getSuperClass() {
		return superClass;
	}
	public void setSuperClass(TypeMeta superClass) {
		this.superClass = superClass;
	}
	public List<TypeMeta> getImplInterfaces() {
		return implInterfaces;
	}
	public void setImplInterfaces(List<TypeMeta> implInterfaces) {
		this.implInterfaces = implInterfaces;
	}
	public List<VariableMeta> getFeilds() {
		return feilds;
	}
	public void setFeilds(List<VariableMeta> feilds) {
		this.feilds = feilds;
	}
	public List<MethodMeta> getMethods() {
		return methods;
	}
	public void setMethods(List<MethodMeta> methods) {
		this.methods = methods;
	}
	
	public DocMeta addheader(String comment) {
		if (header == null) {
			header = new DocMeta();
		}
		header.setComment(header.getComment() + comment);
		return header;
	}
	public DocMeta addheader(DocMeta comment) {
		if (header == null) {
			header = new DocMeta();
		}
		header.setComment(header.getComment() + comment.getComment());
		return header;
	}
	public ClassMeta header(String comment) {
		addheader(comment);
		return this;
	}
	public ClassMeta header(DocMeta comment) {
		addheader(comment);
		return this;
	}
	public List<String> addPackage(String packageName) {
		if (packages == null) {
			packages = new ArrayList<String>();
		}
		packages.add(packageName);
		return packages;
	}
	public ClassMeta package_(String packageName) {
		addPackage(packageName);
		return this;
	}
	public List<String> addImport(String importName) {
		if (imports == null) {
			imports = new ArrayList<String>();
		}
		imports.add(importName);
		return imports;
		
	}
	public ClassMeta import_(String importName) {
		addImport(importName);
		return this;
	}
	public ClassMeta import_(Class<?> importClass) {
		return import_(importClass.getCanonicalName());
	}
	public ClassMeta description(String comment) {
		setDescription(DocMeta.doc(comment));
		return this;
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
	public ClassMeta at(String annotation) {
		addAnnotation(annotation);
		return this;
	}
	public ClassMeta at(Class<? extends Annotation> annotation) {
		addAnnotation(annotation);
		return this;
	}
	public ClassMeta at(AnnotationMeta annotation) {
		addAnnotation(annotation);
		return this;
	}
	public ClassMeta modifier(String modifierName) {
		setModifier(EnumSet.of(ModifierMeta.valueOf(modifierName)));
		return this;
	}
	public ClassMeta modifier(EnumSet<ModifierMeta> modifier) {
		setModifier(modifier);
		return this;
	}
	public ClassMeta class_(String className) {
		setName(TypeMeta.t(className));
		return this;
	}
	public ClassMeta extends_(String superClass) {
		setSuperClass(TypeMeta.t(superClass));
		return this;
	}
	public ClassMeta extends_(TypeMeta superClass) {
		setSuperClass(superClass);
		return this;
	}
	public List<TypeMeta> addInterface(TypeMeta interfaceName) {
		if (implInterfaces == null) {
			implInterfaces = new ArrayList<TypeMeta>();
		}
		implInterfaces.add(interfaceName);
		return implInterfaces;
	}
	public List<TypeMeta> addInterface(String interfaceName) {
		return addInterface(TypeMeta.t(interfaceName));
	}
	public ClassMeta impl(TypeMeta interfaceName) {
		addInterface(interfaceName);
		return this;
	}
	public ClassMeta impl(String interfaceName) {
		addInterface(interfaceName);
		return this;
	}
	public List<VariableMeta> addFeild(VariableMeta feild) {
		if (feilds == null) {
			feilds = new ArrayList<VariableMeta>();
		}
		feilds.add(feild);
		return feilds;
	}
	public List<VariableMeta> addFeild(String feildType, String feildName) {
		if (feilds == null) {
			feilds = new ArrayList<VariableMeta>();
		}
		feilds.add(VariableMeta.var(feildType, feildName));
		return feilds;
	}
	public List<VariableMeta> addFeild(String feildType, String feildName, String feildValue) {
		if (feilds == null) {
			feilds = new ArrayList<VariableMeta>();
		}
		feilds.add(VariableMeta.var(feildType, feildName, feildValue));
		return feilds;
	}
	public List<VariableMeta> addFeild(String modifier, String feildType, String feildName, String feildValue) {
		if (feilds == null) {
			feilds = new ArrayList<VariableMeta>();
		}
		feilds.add(VariableMeta.var(
				EnumSet.of(ModifierMeta.valueOf(modifier)), feildType, feildName, feildValue));
		return feilds;
	}
	public List<VariableMeta> addFeild(EnumSet<ModifierMeta> modifier, String feildType, String feildName, String feildValue) {
		if (feilds == null) {
			feilds = new ArrayList<VariableMeta>();
		}
		feilds.add(VariableMeta.var(modifier, feildType, feildName, feildValue));
		return feilds;
	}
	public ClassMeta feild(VariableMeta feild) {
		addFeild(feild);
		return this;
	}
	public ClassMeta feild(String feildType, String feildName) {
		addFeild(feildType, feildName);
		return this;
	}
	public ClassMeta feild(String feildType, String feildName, String feildValue) {
		addFeild(feildType, feildName, feildValue);
		return this;
	}
	public ClassMeta feild(String modifier, String feildType, String feildName, String feildValue) {
		addFeild(modifier, feildType, feildName, feildValue);
		return this;
	}
	public ClassMeta feild(EnumSet<ModifierMeta> modifier, String feildType, String feildName, String feildValue) {
		addFeild(modifier, feildType, feildName, feildValue);
		return this;
	}
	public List<MethodMeta> addMethod(MethodMeta method) {
		if (methods == null) {
			methods = new ArrayList<MethodMeta>();
		}
		methods.add(method);
		return methods;
	}
	public ClassMeta method(MethodMeta method) {
		addMethod(method);
		return this;
	}
	
	@Override
	public String toString() {
		StringBuilder result = new StringBuilder();
		
		result.append(header).append("%n");
		for (String packagename : packages) {
			result.append("package ").append(packagename).append(";%n%n");
		}
		for (String importName : imports) {
			result.append("import ").append(importName).append(";%n");
		}
		if (description != null) {
			result.append("%n").append(description);
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
		result.append("class ").append(name).append(" ");
		if ((superClass != null) && (! superClass.isNull())) {
			result.append("extends ").append(superClass).append(" ");
		} else if ((implInterfaces != null) && (! implInterfaces.isEmpty())) {
			result.append("impliments ");
			boolean isFirstAppend = true;
			for (TypeMeta interfaceName : implInterfaces) {
				if (! isFirstAppend) {
					result.append(", ");
				}
				result.append(interfaceName);
				isFirstAppend = false;
			}
		}
		result.append("{%n");
		if ((feilds != null) && (! feilds.isEmpty())) {
			for (VariableMeta feild : feilds) {
				result.append(GeneratorUtil.indent(feild.toString())).append("%n");
			}
		}
		if ((methods != null) && (! methods.isEmpty())) {
			for (MethodMeta method : methods) {
				result.append(GeneratorUtil.indent(method.toString())).append("%n");
			}
		}
		result.append("}%n");
		return result.toString();
	}
}
