/**
 * 
 */
package org.abeche.android.edge.event;

/**
 * @author abeche
 *
 */
public class EventException extends Exception {
	/**
	 * Serial ID
	 */
	private static final long serialVersionUID = 1L;
	
	public EventException() {
		super();
	}
	public EventException(String msg, Exception cause) {
		super(msg, cause);
	}
	public EventException(Exception cause) {
		super(cause);
	}
}
