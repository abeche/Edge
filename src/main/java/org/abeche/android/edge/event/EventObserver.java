/**
 * 
 */
package org.abeche.android.edge.event;

import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;
import java.util.Map.Entry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

/**
 * @author abeche
 *
 */
public class EventObserver {
	
	private static final String ACTION_NAME = EventObserver.class.getName() + ".ACTION_NAME";
	private static final String EVENT_ARGS = EventObserver.class.getName() + ".EVENT_ARGS";
	private static final String EVENT_ARG = "EVENT_ARG";
	private static final String LOG_TAG = "EDGE";
	private static EventTable eventTable;
	
	/**
	 * It is necessary to call it earlier than
	 * {@link EventObserver#addEventListener(Object)}.
	 * @param eventTable
	 */
	public static void init(EventTable eventTable) {
		EventObserver.eventTable = eventTable;
	}
	
	public static boolean addEventListener(Object listener) throws EventException {
		try {
			return eventTable.register(listener);
		} catch (ClassNotFoundException e) {
			throw new EventException(e);
		} catch (NoSuchMethodException e) {
			throw new EventException(e);
		}
	}

	public static void onCreate(Intent event, Bundle savedInstanceState) throws EventException {
		executeEvent(event);
	}
	
	public static void onStart(Intent event) throws EventException {
		executeEvent(event);
	}
	
	public static void onResume(Intent event) throws EventException {
		executeEvent(event);
	}
	
	public static void notify(String eventName, Object eventArg) throws EventException {
		executeEvent(eventName, eventArg);
	}
	
	public static void notify(String eventName, Object... eventArg) throws EventException {
		executeEvent(eventName, eventArg);
	}

	public static void notify(Activity activity, String eventName, Object... eventArgs)
			throws EventException {
		Map<Integer, EventHolder> eventMethods = eventTable.getCallbackMethods(eventName);
		for (Entry<Integer, EventHolder> eventMethodSet : eventMethods.entrySet()) {
			EventHolder eventMethod = eventMethodSet.getValue();
			Class<?>[] eventHolderArgs = eventMethod.getEventMethodArgs();
			int i = 0;
			for (Class<?> eventArgClass : eventHolderArgs) {
				if (! eventArgClass.isInstance(eventArgs[i])) {
					Log.d(LOG_TAG, "callback parameter dose not mutch. args="+eventArgs);
					return;
				}
				i++;
			}
			
			Class<?> eventListener;
			try {
				eventListener = eventMethod.getDeclaredClass();
			} catch (ClassNotFoundException e) {
				Log.e(LOG_TAG, "event class not found.", e);
				throw new EventException("event class not found.", e);
			}
			Intent eventIntent = new Intent(activity, eventListener);
			eventIntent.putExtra(ACTION_NAME, eventName);
			
			Bundle bundle = new Bundle();
			i = 0;
			for (Object eventArg : eventArgs) {
				putBundle(bundle, EVENT_ARG+(i+1), eventArg);
				i++;
			}
			eventIntent.putExtra(EVENT_ARGS, bundle);
			
			activity.startActivity(eventIntent);
		}
		
		executeEvent(eventName, eventArgs);
	}
	
	private static void putBundle(Bundle bundle, String key, Object value) {
		if (value instanceof Integer) {
			bundle.putInt(key, (Integer) value);
		} else if (value instanceof String) {
			bundle.putString(key, (String) value);
		} else if (value instanceof Double) {
			bundle.putDouble(key, (Double) value);
		} else if (value instanceof Long) {
			bundle.putLong(key, (Long) value);
		} else if (value instanceof Short) {
			bundle.putShort(key, (Short) value);
		} else if (value instanceof Boolean) {
			bundle.putBoolean(key, (Boolean) value);
		} else if (value instanceof Byte) {
			bundle.putByte(key, (Byte) value);
		} else if (value instanceof Float){
			bundle.putFloat(key, (Float) value);
		} else if (value instanceof CharSequence) {
			bundle.putCharSequence(key, (CharSequence) value);
		} else if (value instanceof Serializable) {
			bundle.putSerializable(key, (Serializable) value);
		} else {
			Log.d(LOG_TAG, "It is a type of the parameter of the uncorrespondence.");
		}
		
	}

	private static void executeEvent(Intent event) throws EventException {
		if (event == null) {
			return;
		}
		String eventName = event.getStringExtra(ACTION_NAME);
		if (eventName == null) {
			return;
		}
		Bundle eventArgsBundle = event.getBundleExtra(EVENT_ARGS);
		
		Object[] eventArgs;
		if (eventArgsBundle == null) {
			eventArgs = new Object[]{};
		} else {
			eventArgs = new Object[eventArgsBundle.size()];
			for (int i = 0; i < eventArgsBundle.size(); i++) {
				eventArgs[i] = eventArgsBundle.get(EVENT_ARG+(i+1));
			}
		}
		
		executeEvent(eventName, eventArgs);
	}
	
	private static void executeEvent(String eventName, Object... eventArgs) throws EventException {
		Map<Integer, EventHolder> events = eventTable.getCallbackMethods(eventName);
		if (events != null) {
			for (Entry<Integer, EventHolder> eventSet : events.entrySet()) {
				EventHolder eventValue = eventSet.getValue();
				Method eventMethod = eventValue.getEventMethod();
				if ((eventMethod == null) || (eventValue.getInstance() == null)) {
					Log.e(LOG_TAG, "event not found. event="+eventValue);
					return;
				}
				try {
					eventMethod.invoke(eventValue.getInstance(), eventArgs);
					
				} catch (IllegalArgumentException e) {
					throw new EventException("event failed", e);
				} catch (IllegalAccessException e) {
					throw new EventException("event failed", e);
				} catch (InvocationTargetException e) {
					throw new EventException("event failed", e);
				}
			}
		} else {
			Log.d(LOG_TAG
				, "event not found. not registered eventtable. eventName="+eventName);
		}
	}
}
