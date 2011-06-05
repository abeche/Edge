/**
 * 
 */
package org.abeche.android.edge.event;

import java.lang.reflect.Method;

/**
 * @author abeche
 *
 */
public class EventHolder {
	private Class<?> declaredClass;
	private String declaredClassName;
	private Object instance;
	private Method eventMethod;
	private String eventMethodName;
	private Class<?>[] eventMethodArgs;
	private String eventName;
	private int eventOrder;
	
	public String getDeclaredClassName() {
		return declaredClassName;
	}
	public void setDeclaredClassName(String declaredClassName) {
		this.declaredClassName = declaredClassName;
	}
	public synchronized Class<?> getDeclaredClass() throws ClassNotFoundException {
		if ((declaredClass == null) && (declaredClassName != null)) {
			declaredClass = Class.forName(declaredClassName);
		}
		return declaredClass;
	}
	public synchronized void setDeclaredClass(Class<?> declaredClass) {
		this.declaredClass = declaredClass;
		if (declaredClass != null) {
			this.declaredClassName = declaredClass.getCanonicalName();
		} else {
			this.declaredClassName = "";
		}
	}
	public Object getInstance() {
		return instance;
	}
	public void setInstance(Object instance) {
		this.instance = instance;
	}
	public Method getEventMethod() {
		return eventMethod;
	}
	public void setEventMethod(Method eventMethod) {
		this.eventMethod = eventMethod;
	}
	public String getEventMethodName() {
		return eventMethodName;
	}
	public void setEventMethodName(String eventMethodName) {
		this.eventMethodName = eventMethodName;
	}
	public Class<?>[] getEventMethodArgs() {
		return eventMethodArgs;
	}
	public void setEventMethodArgs(Class<?>[] eventMethodArgs) {
		this.eventMethodArgs = eventMethodArgs;
	}
	public String getEventName() {
		return eventName;
	}
	public void setEventName(String eventName) {
		this.eventName = eventName;
	}
	public int getEventOrder() {
		return eventOrder;
	}
	public void setEventOrder(int eventOrder) {
		this.eventOrder = eventOrder;
	}
	
	@Override
	public String toString() {
		return "EventHolder [declaredClass=" + declaredClassName + ", instance=" + instance
		+ ", eventMethod=" + eventMethod + ", eventMethodName="
		+ eventMethodName + "eventOrder=" + eventOrder + "]";
	}
}