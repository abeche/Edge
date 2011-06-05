/**
 * 
 */
package org.abeche.android.edge.generator.meta;

import javax.annotation.Generated;

/**
 * @author abeche
 *
 */
@Generated(value="org.abeche.android.edge.event.EventObserver", date="", comments="")
public class TypeMeta {
	private String type = "";

	public TypeMeta() {
	}
	
	public TypeMeta(String type) {
		this();
		this.type = type;
	}
	
	public static TypeMeta t(String type) {
		return new TypeMeta(type);
	}
	
	public static TypeMeta t(Class<?> type) {
		String typeString = "";
		if (Void.class.equals(type)) {
			typeString = "void";
		} else if (Integer.class.equals(type)) {
			typeString = "int";
		} else if (Long.class.equals(type)) {
			typeString = "long";
		} else if (Double.class.equals(type)) {
			typeString = "double";
		} else if (Float.class.equals(type)) {
			typeString = "float";
		} else {
			typeString = type.getCanonicalName();
		}
		return new TypeMeta(typeString);
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	public boolean isNull() {
		return ((type == null) || type.isEmpty());
	}
	
	@Override
	public String toString() {
		return type;
	}
}
