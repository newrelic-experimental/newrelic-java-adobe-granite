package com.newrelic.instrumentation.labs.adobe.granite.eventing.api;

import java.util.Map;

import org.apache.poi.ss.formula.functions.T;

import com.adobe.granite.eventing.api.AemCloudEvent;

public class Util {

	public static void recordValue(Map<String, Object> attributes, String key, Object value) {
		if (key != null && !key.isEmpty() && value != null) {
			attributes.put(key, value);
		}
	}

	public static void recordEvent(Map<String, Object> attributes, AemCloudEvent<T> event) {

		if (null != event) {
			Util.recordValue(attributes, "eventId", event.getId());
			Util.recordValue(attributes, "eventSource", event.getSource());
			Util.recordValue(attributes, "eventType", event.getType());
			Util.recordValue(attributes, "eventDataContentType", event.getDatacontenttype());
		}

	}

}