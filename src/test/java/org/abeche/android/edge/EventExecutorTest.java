/**
 * 
 */
package org.abeche.android.edge;

import java.nio.charset.Charset;
import java.util.Locale;


import org.abeche.android.edge.generator.EventListenerProcessor;
import org.seasar.aptina.unit.AptinaTestCase;

/**
 * @author abeche
 *
 */
public class EventExecutorTest extends AptinaTestCase {
	
	private static final String GEN_INTERNALEVENT_CLASS =
		"/**%n" +
		" * %n" +
		" */%n%n" +
		"package org.abeche.android.edge.gen;%n%n" +
		"import java.util.Map;%n" +
		"import java.util.TreeMap;%n" +
		"import java.lang.Override;%n" +
		"import org.abeche.android.edge.event.EventHolder;%n" +
		"import org.abeche.android.edge.event.EventTable;%n" +
		"import org.abeche.android.edge.generator.Generated;%n%n" +
		"/**%n * this generatd class.<br/>%n" +
		" * this class defined callback event and callback methods.<br/>%n" +
		" */%n" +
		"@Generated(value=\"org.abeche.android.edge.event.EventObserver\", date=\"%generateDate%\")%n" +
		"public class GenerateEventTable extends EventTable {%n%n" +
		"    /**%n" +
		"     * %n" +
		"     */%n" +
		"    @Override%n" +
		"    protected void init(Map<String, Map<Integer, EventHolder>> eventTable){%n" +
		"    %n" +
		"        Map<Integer, EventHolder> eventMap;%n" +
		"        %n" +
		"        if (eventTable.containsKey(\"\")) {%n" +
		"            eventMap = eventTable.get(\"\");%n" +
		"        } else {%n" +
		"            eventMap = new TreeMap<Integer, EventHolder>();%n" +
		"        }%n" +
		"        %n" +
		"        EventHolder event1 = new EventHolder();%n" +
		"        event1.setDeclaredClass(org.abeche.android.edge.EventExecutor.class);%n" +
		"        event1.setEventMethodName(\"execEvent1\");%n" +
		"        event1.setEventMethodArgs(new Class[]{});%n" +
		"        event1.setEventName(\"\");%n" +
		"        event1.setEventOrder(0);%n" +
		"        %n" +
		"        eventMap.put(0, event1);%n" +
		"        if (eventMap.size() == 1) {%n" +
		"            eventTable.put(\"\", eventMap);%n" +
		"        }%n" +
		"        %n" +
		"        if (eventTable.containsKey(\"event2\")) {%n" +
		"            eventMap = eventTable.get(\"event2\");%n" +
		"        } else {%n" +
		"            eventMap = new TreeMap<Integer, EventHolder>();%n" +
		"        }%n" +
		"        %n" +
		"        EventHolder event2 = new EventHolder();%n" +
		"        event2.setDeclaredClass(org.abeche.android.edge.EventExecutor.class);%n" +
		"        event2.setEventMethodName(\"execEvent2\");%n" +
		"        event2.setEventMethodArgs(new Class[]{});%n" +
		"        event2.setEventName(\"event2\");%n" +
		"        event2.setEventOrder(1);%n" +
		"        %n" +
		"        eventMap.put(1, event2);%n" +
		"        if (eventMap.size() == 1) {%n" +
		"            eventTable.put(\"event2\", eventMap);%n" +
		"        }%n" +
		"        %n" +
		"        if (eventTable.containsKey(\"event3\")) {%n" +
		"            eventMap = eventTable.get(\"event3\");%n" +
		"        } else {%n" +
		"            eventMap = new TreeMap<Integer, EventHolder>();%n" +
		"        }%n" +
		"        %n" +
		"        EventHolder event3 = new EventHolder();%n" +
		"        event3.setDeclaredClass(org.abeche.android.edge.EventExecutor.class);%n" +
		"        event3.setEventMethodName(\"execEvent3\");%n" +
		"        event3.setEventMethodArgs(new Class[]{java.lang.String.class});%n" +
		"        event3.setEventName(\"event3\");%n" +
		"        event3.setEventOrder(1);%n" +
		"        %n" +
		"        eventMap.put(1, event3);%n" +
		"        if (eventMap.size() == 1) {%n" +
		"            eventTable.put(\"event3\", eventMap);%n" +
		"        }%n" +
		"        %n" +
		"        if (eventTable.containsKey(\"event3\")) {%n" +
		"            eventMap = eventTable.get(\"event3\");%n" +
		"        } else {%n" +
		"            eventMap = new TreeMap<Integer, EventHolder>();%n" +
		"        }%n" +
		"        %n" +
		"        EventHolder event4 = new EventHolder();%n" +
		"        event4.setDeclaredClass(org.abeche.android.edge.EventExecutor.class);%n" +
		"        event4.setEventMethodName(\"execEvent3\");%n" +
		"        event4.setEventMethodArgs(new Class[]{java.lang.String.class, java.lang.String.class});%n" +
		"        event4.setEventName(\"event3\");%n" +
		"        event4.setEventOrder(2);%n" +
		"        %n" +
		"        eventMap.put(2, event4);%n" +
		"        if (eventMap.size() == 1) {%n" +
		"            eventTable.put(\"event3\", eventMap);%n" +
		"        }%n" +
		"        %n" +
		"        %n" +
		"    }%n" +
		"    %n}" +
		"%n";
	

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		setLocale(Locale.getDefault());
		setCharset(Charset.defaultCharset());
		addSourcePath("src/test/java");
	}

	public void testGenerateEventListener() throws Exception {
		try {
			EventListenerProcessor aptProcessor = new EventListenerProcessor();
			addProcessor(aptProcessor);
			
			addCompilationUnit(EventExecutor.class);
			
			compile();
			
			assertTrue(getCompiledResult());
			
			String expected = GEN_INTERNALEVENT_CLASS.replaceAll("%n", System.getProperty("line.separator"));
			String generateDate = aptProcessor.getGenerateDate();
			assertNotNull(generateDate);
			expected = expected.replaceAll("%generateDate%", generateDate);
			assertEqualsGeneratedSource(expected
				, "org.abeche.android.edge.gen.GenerateEventTable");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			throw e;
		}
	}
	
}
