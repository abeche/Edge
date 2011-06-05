/**
 * 
 */
package org.abeche.android.edge.util;

import java.util.regex.Pattern;


/**
 * @author abeche
 *
 */
public class GeneratorUtil {
	private final static Pattern hasLineSeperator = Pattern.compile(".*%n.*");
	
	private GeneratorUtil() {
	}
	
	public static String indent(String output, int level) {
		String result  = output;
		if (level < 1) {
			throw new IllegalArgumentException("level must over 1");
		}
		if ((output == null) || (output.isEmpty())) {
			return result;
		}
		if (! hasLineSeperator.matcher(output).matches()) {
			return "    " + output;
		}
		for (int i = 0; i < level; i++) {
			result = result.replaceAll("%n", "%n    ");
		}
		return result;
	}
	
	public static String indent(String output) {
		return indent(output, 1);
	}
}
