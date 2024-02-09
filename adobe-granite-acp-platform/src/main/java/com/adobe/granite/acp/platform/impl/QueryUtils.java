package com.adobe.granite.acp.platform.impl;

import java.util.HashMap;
import java.util.Map;

import javax.jcr.NodeIterator;
import javax.jcr.Session;
import javax.jcr.query.RowIterator;

import com.adobe.granite.acp.platform.api.ResourceRequest;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;

import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.granite.acp.platform.Util;

@Weave
public abstract class QueryUtils {

	@Trace(dispatcher = true)
	public static NodeIterator executeQuery(Session session, String statement, String language, long queryLimit) {

		NodeIterator result;
		Map<String, Object> attributes = new HashMap<>();

		Util.recordValue(attributes, "statement", statement);
		Util.recordValue(attributes, "language", language);
		Util.recordValue(attributes, "queryLimit", queryLimit);

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", "QueryUtils", "executeQuery" });

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}
		result = Weaver.callOriginal();
		return result;
	}

	@Trace(dispatcher = true)
	public static RowIterator executeXPathQuery(String statement, ResourceRequest resourceRequest) {
		RowIterator result;
		Map<String, Object> attributes = new HashMap<>();

		Util.recordValue(attributes, "statement", statement);
		Util.recordRequestParams(attributes, resourceRequest);

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", "QueryUtils", "executeXPathQuery" });

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}
		result = Weaver.callOriginal();
		return result;
	}
}
