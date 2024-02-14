package com.day.crx.packmgr.impl;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.Session;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.granite.crx.packagemgr.Util;

@Weave(type = MatchType.BaseClass)
public abstract class AbstractServlet {

	@Trace
	protected void doService(HttpServletRequest httpRequest, HttpServletResponse httpResponse, Session session) {

		Map<String, Object> attributes = new HashMap<>();

		if (null != httpRequest) {
			String requestURL = httpRequest.getRequestURL().toString();

			Util.recordValue(attributes, "requestURL", requestURL);
		}

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}
		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "doService" });

		Weaver.callOriginal();
	}
}
