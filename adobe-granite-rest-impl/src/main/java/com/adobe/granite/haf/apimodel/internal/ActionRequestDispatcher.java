package com.adobe.granite.haf.apimodel.internal;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.servlets.post.PostResponse;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.granite.rest.impl.Util;

@Weave(type = MatchType.Interface)
public abstract class ActionRequestDispatcher {

	@Trace
	public void dispatchHttpRequest(SlingHttpServletRequest request, PostResponse response) {

		Util.recordRequestAttributes(request); // Adds Custom attributes

		NewRelic.getAgent().getTracedMethod().setMetricName(
				new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "dispatchHttpRequest" });

		Weaver.callOriginal();
	}

	@Trace
	public boolean handlesRequest(SlingHttpServletRequest request) {

		Util.recordRequestAttributes(request); // Adds Custom attributes

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "handlesRequest" });

		return Weaver.callOriginal();
	}
}
