/**
 * 
 */
package org.abeche.android.edge.sample;

import org.abeche.android.edge.event.CallBack;
import org.abeche.android.edge.event.EventListener;


/**
 * @author abeche
 *
 */
@EventListener
public class ListenerMock {

	@CallBack
	public void event() {
	}
	
	@CallBack("event2")
	public void event2() {
	}
	
	@CallBack("event3")
	public void event3() {
	}
}
