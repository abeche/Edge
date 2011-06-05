/**
 * 
 */
package org.abeche.android.edge.event;

import java.lang.annotation.ElementType;
import java.lang.annotation.Target;

/**
 * @author abeche
 *
 */
@Target(ElementType.METHOD)
public @interface CallBack {
	String value() default "";
	int order() default 0;
}
