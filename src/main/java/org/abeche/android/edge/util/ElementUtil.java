/**
 * 
 */
package org.abeche.android.edge.util;

import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.util.Types;
import javax.tools.Diagnostic.Kind;

/**
 * @author abeche
 *
 */
public class ElementUtil {
	private ElementUtil() {
	}
	
	public static Class<?> asClass(ProcessingEnvironment processingEnv, Element typeElement) {
		Class<?> result = null;
		try {
			String typeName = typeElement.asType().toString();
			result = Class.forName(typeName);
		} catch (ClassNotFoundException e) {
			Messager messager = processingEnv.getMessager();
			messager.printMessage(Kind.ERROR, e.toString());
		}
		return result;
	}

	public static Class<?> asClass(ProcessingEnvironment processingEnv, TypeElement typeElement) {
		Class<?> result = null;
		try {
			String typeName = typeElement.getQualifiedName().toString();
			result = Class.forName(typeName);
		} catch (ClassNotFoundException e) {
			Messager messager = processingEnv.getMessager();
			messager.printMessage(Kind.ERROR, e.toString());
		}
		return result;
	}

	public static String asClassName(ProcessingEnvironment processingEnv, TypeElement typeElement) {
		String result = null;
		if (typeElement != null) {
			result = typeElement.getQualifiedName().toString();
		}
		return result;
	}
	
	public static TypeMirror asType(ProcessingEnvironment processingEnv, Class<?> targetClass) {
		return processingEnv.getElementUtils()
			.getTypeElement(targetClass.getCanonicalName()).asType();
	}

	public static boolean isSameType(ProcessingEnvironment processingEnv, DeclaredType annotationType,
			Class<?> targetClass) {
		Types typeUtil = processingEnv.getTypeUtils();
		TypeMirror erasureAnnotationType = typeUtil.erasure(annotationType);
		TypeMirror erasureCallBackType = typeUtil.erasure(asType(processingEnv, targetClass));
		
		return erasureAnnotationType.equals(erasureCallBackType);
	}
	
	
}
