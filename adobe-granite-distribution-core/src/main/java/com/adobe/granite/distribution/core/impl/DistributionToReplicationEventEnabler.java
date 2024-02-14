package com.adobe.granite.distribution.core.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.servlets.post.Modification;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.distribution.Util;

@Weave
public abstract class DistributionToReplicationEventEnabler {

	@Trace
	public void process(SlingHttpServletRequest request, List<Modification> modifications) {

		Map<String, Object> attributes = new HashMap<>();

		Util.recordRequestAttributes(request);

		int iCount = 0;

		for (Modification modification : modifications) {
			String path = modification.getSource();
			String desitination = modification.getDestination();
			if (null != path && !path.isEmpty()) {
				Util.recordValue(attributes, "path" + ++iCount, path);
			}

			if (null != desitination && !desitination.isEmpty()) {
				Util.recordValue(attributes, "desitination" + ++iCount, desitination);
			}
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "process" });

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}
		Weaver.callOriginal();

	}
}
