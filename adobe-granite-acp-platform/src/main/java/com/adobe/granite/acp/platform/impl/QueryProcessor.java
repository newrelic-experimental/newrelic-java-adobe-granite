package com.adobe.granite.acp.platform.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.json.JSONArray;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.granite.acp.platform.Util;

@SuppressWarnings("deprecation")
@Weave
public abstract class QueryProcessor {

	private HttpServletRequest request = Weaver.callOriginal();
	private QueryStatement queryStatement = Weaver.callOriginal();
	private long resultCount = Weaver.callOriginal();

	@Trace(dispatcher = true)
	public void queryAndGetResults(JSONArray children) {

		Map<String, Object> attributes = new HashMap<>();

		Weaver.callOriginal();

		String statement = this.queryStatement.toString();
		String pathinfo = this.request.getPathInfo();

		Util.recordValue(attributes, "statement", statement);
		Util.recordValue(attributes, "pathInfo", pathinfo);
		Util.recordValue(attributes, "resultCount", resultCount);

		NewRelic.getAgent().getTracedMethod().setMetricName(
				new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "queryAndGetResults" });

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

	}

	@Trace(dispatcher = true)
	public void queryDirAndAssetAndGetResults(JSONArray children) {

		Map<String, Object> attributes = new HashMap<>();

		Weaver.callOriginal();

		String statement = this.queryStatement.toString();
		String pathinfo = this.request.getPathInfo();

		Util.recordValue(attributes, "statement", statement);
		Util.recordValue(attributes, "pathInfo", pathinfo);
		Util.recordValue(attributes, "resultCount", resultCount);

		NewRelic.getAgent().getTracedMethod().setMetricName(
				new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "queryDirAndAssetAndGetResults" });

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

	}
}
