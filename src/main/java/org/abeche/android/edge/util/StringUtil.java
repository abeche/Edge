/**
 * 
 */
package org.abeche.android.edge.util;

import java.util.List;

/**
 * @author abeche
 *
 */
public class StringUtil {
	/** for utility class */
	private StringUtil() {
	}
	
	public static String wrap(String str, String wrapStr) {
		return new StringBuilder().append(wrapStr).append(str).append(wrapStr).toString();
	}
	
	public static String join(List<?> texts, String separator) {
		StringBuilder result = new StringBuilder();
		for (Object text : texts) {
			result.append(text);
			result.append(separator);
		}
		return result.toString();
	}
	
	public static String join(String separator, Object... texts) {
		StringBuilder result = new StringBuilder();
		boolean isFirst = true;
		for (Object text : texts) {
			if (isFirst) {
				result.append(separator);
				isFirst = false;
			}
			result.append(text);
		}
		return result.toString();
	}
}
