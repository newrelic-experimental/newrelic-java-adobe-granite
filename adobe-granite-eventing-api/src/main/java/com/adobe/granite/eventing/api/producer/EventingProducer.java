package com.adobe.granite.eventing.api.producer;

import java.util.HashMap;
import java.util.Map;

import com.adobe.granite.eventing.api.AemCloudEvent;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.granite.eventing.api.Util;

@Weave(type = MatchType.Interface)
public abstract class EventingProducer {

	@Trace
	public <T> void send(AemCloudEvent<T> event) {

		Map<String, Object> attributes = new HashMap<>();

		if (null != event) {
			Util.recordValue(attributes, "eventId", event.getId());
			Util.recordValue(attributes, "eventSource", event.getSource());
			Util.recordValue(attributes, "eventType", event.getType());
			Util.recordValue(attributes, "eventDataContentType", event.getDatacontenttype());
		}

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "send" });

		Weaver.callOriginal();
	}

	@Trace
	public <T> void sendAsync(AemCloudEvent<T> event) {
		Map<String, Object> attributes = new HashMap<>();

		if (null != event) {
			Util.recordValue(attributes, "eventId", event.getId());
			Util.recordValue(attributes, "eventSource", event.getSource());
			Util.recordValue(attributes, "eventType", event.getType());
			Util.recordValue(attributes, "eventDataContentType", event.getDatacontenttype());
		}

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "send" });

		Weaver.callOriginal();
	}

}
