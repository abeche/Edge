/**
 * 
 */
package org.abeche.android.edge.generator.meta;

import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.List;

import org.abeche.android.edge.util.Pair;


/**
 * @author abeche
 *
 */
public class AnnotationMeta {
	private String name;
	private List<Pair<String, String>> values = new ArrayList<Pair<String, String>>();
	
	public AnnotationMeta(String name) {
		this.name = name;
	}
	public AnnotationMeta(Class<? extends Annotation> name) {
		this(name.getCanonicalName());
	}
	public static AnnotationMeta at(String name) {
		return new AnnotationMeta(name);
	}
	public static AnnotationMeta at(Class<? extends Annotation> name) {
		return new AnnotationMeta(name);
	}
	public AnnotationMeta value(String key, String value) {
		values.add(Pair.of(key, value));
		return this;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Pair<String,String>> getValue() {
		return values;
	}
	public void setValue(List<Pair<String, String>> values) {
		this.values = values;
	}
	
	public boolean isValueEmpty() {
		return (values == null) || (values.isEmpty());
	}
	
	public boolean isNull() {
		return (name != null) || (name.isEmpty());
	}
	
	@Override
	public String toString() {
		if ((name == null) || (name.isEmpty())) {
			return "";
		}
		
		StringBuilder result = new StringBuilder();
		result.append("@").append(name);
		if (! isValueEmpty()) {
			result.append("(");
			if (values.size() == 1) {
				result.append(values.get(0).get2());
			} else {
				boolean isFirstAppend = true;
				for (Pair<String, String> value : values) {
					if (! isFirstAppend) {
						result.append(", ");
					}
					result
						.append(value.get1())
						.append("=")
						.append(value.get2());
					isFirstAppend = false;
				}
			}
			result.append(")");
		}
		return result.toString();
	}
}
