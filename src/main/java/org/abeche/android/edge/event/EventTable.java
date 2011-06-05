/**
 * 
 */
package org.abeche.android.edge.event;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import android.util.Log;


/**
 * @author abeche
 *
 */
public class EventTable {
	private final Map<String, Map<Integer, EventHolder>> eventTable
		= new HashMap<String, Map<Integer, EventHolder>>();
	private final static String LOG_TAG = "EVT_TBL"; 
	
	public EventTable() {
		init(eventTable);
	}
	
	/**
	 * @param eventTable
	 */
	protected void init(Map<String, Map<Integer, EventHolder>> eventTable) {
		
//		Map<Integer, EventHolder> eventMap;
//		
//		if (eventTable.containsKey("event1")) {
//			eventMap = eventTable.get("event1");
//		} else {
//			eventMap = new TreeMap<Integer, EventHolder>();
//		}
//		
//		EventHolder event1 = new EventHolder();
//		event1.setDeclaredClass(org.abeche.android.edge.EventExecutor.class);
//		event1.setEventMethodName("eventMethod1");
//		event1.setEventMethodArgs(new Class[]{java.lang.String.class});
//		event1.setEventName("event1");
//		event1.setEventOrder(1);
//		
//		eventMap.put(event1.getEventOrder(), event1);
//		if (eventMap.size() == 1) {
//			eventTable.put("event1", eventMap);
//		}
	}
	
	/**
	 * @param key
	 * @return methods of annotation with {@link CallBack}
	 */
	public Map<Integer, EventHolder> getCallbackMethods(String key) {
		return eventTable.get(key);
	}
	
	/**
	 * @param listener
	 * @param result
	 * @return
	 * @throws NoSuchMethodException 
	 * @throws ClassNotFoundException 
	 */
	public boolean register(Object listener)
			throws NoSuchMethodException, ClassNotFoundException {
		boolean result = false;
		Class<?> listenerClass = listener.getClass();
		for (Entry<String, Map<Integer, EventHolder>> eventEntry : eventTable.entrySet()) {
			Map<Integer, EventHolder> eventNameEntry
				= eventEntry.getValue();
			for (Entry<Integer, EventHolder> eventSet : eventNameEntry.entrySet()) {
				EventHolder event = eventSet.getValue();
				Class<?> declaredClass = event.getDeclaredClass();
				if (declaredClass != null
						&& declaredClass.isInstance(listener)
						&& event.getInstance() == null) {
					try {
						event.setInstance(listener);
						event.setEventMethod(
							listenerClass.getMethod(
								event.getEventMethodName()
								, event.getEventMethodArgs()));
						Log.d(LOG_TAG, "register event:"+event);
					} catch (SecurityException e) {
						throw e;
					} catch (NoSuchMethodException e) {
						throw e;
					}
					result = true;
				} else {
					Log.d(LOG_TAG, "event listener not found. event="+event);
				}
			}
		}
		return result;
	}
}
