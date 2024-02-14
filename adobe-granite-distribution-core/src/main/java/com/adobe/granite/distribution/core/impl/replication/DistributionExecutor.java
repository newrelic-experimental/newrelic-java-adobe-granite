package com.adobe.granite.distribution.core.impl.replication;

import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.resource.ResourceResolver;
import org.apache.sling.distribution.DistributionRequest;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.distribution.Util;

@Weave(type = MatchType.Interface)
public abstract class DistributionExecutor {

	@Trace
	public void distribute(DistributionRequest request, ResourceResolver resourceResolver) {

		Map<String, Object> attributes = new HashMap<>();

		if (request != null) {
			Util.recordValue(attributes, "requestType", request.getRequestType());
		}

		if (resourceResolver != null) {
			Util.recordValue(attributes, "userID", resourceResolver.getUserID());

		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "distribute" });

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}
		Weaver.callOriginal();
	}

}
