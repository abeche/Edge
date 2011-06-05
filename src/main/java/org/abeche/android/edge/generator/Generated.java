/**
 * 
 */
package org.abeche.android.edge.generator;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.CONSTRUCTOR;
import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.LOCAL_VARIABLE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PACKAGE;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.SOURCE;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**
 * this class porting javax.annotationGenerated.<br/>
 * because android library has'nt javax.annotationGenerated
 * @author abeche
 * @see javax.annotationGenerated
 *
 */
@Documented
@Retention(SOURCE)
@Target({PACKAGE, TYPE, ANNOTATION_TYPE, METHOD, CONSTRUCTOR, FIELD, 
        LOCAL_VARIABLE, PARAMETER})
public @interface Generated {
	String[] value();
	
	String date() default "";
	
	String comments() default "";
}
