package com.adobe.granite.distribution.core.impl;

import java.util.HashMap;
import java.util.Map;

import org.osgi.service.event.Event;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.distribution.Util;

@Weave
public abstract class DistributionToReplicationEventTransformer {

	@Trace
	public void handleEvent(Event event) {

		Map<String, Object> attributes = new HashMap<>();

		Util.recordEventProps(attributes, event);

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "handleEvent" });

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}
		Weaver.callOriginal();

	}
}
