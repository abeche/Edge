package org.abeche.android.edge;

import org.abeche.android.edge.event.CallBack;
import org.abeche.android.edge.event.EventListener;


@EventListener
public class EventExecutor {
	@CallBack
	public void execEvent1() {
		System.out.println("call execEvent1");
	}
	
	@CallBack(value="event2", order=1)
	public void execEvent2() {
		System.out.println("call execEvent2");
	}
	
	@CallBack(value="event3", order=1)
	public void execEvent3(String strArg) {
		System.out.println("call execEvent3: arg="+strArg);
	}
	
	@CallBack(value="event3", order=2)
	public void execEvent3(String strArg1, String strArg2) {
		System.out.println("call execEvent3: arg1="+strArg1+", arg2="+strArg2);
	}
}
