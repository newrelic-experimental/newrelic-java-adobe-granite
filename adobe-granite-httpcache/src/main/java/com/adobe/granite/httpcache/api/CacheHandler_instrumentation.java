package com.adobe.granite.httpcache.api;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.granite.httpcache.api.Util;

@Weave(originalName = "com.adobe.granite.httpcache.api.CacheFile", type = MatchType.Interface)
public class CacheHandler_instrumentation {

	@Trace
	public CacheHandler.TRI_STATE onReceive(HttpServletRequest request) {

		Map<String, Object> attributes = new HashMap<>();

		Util.recordHttpRequest(attributes, request);

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "onReceive" });

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		return Weaver.callOriginal();
	}

	@Trace
	public CacheHandler.TRI_STATE onDeliver(String key, Headers headers, HttpServletResponse response) {

		Map<String, Object> attributes = new HashMap<>();

		if (null != key && !key.isEmpty()) {
			Util.recordValue(attributes, "keyName", key);
		}

		long expires = headers.getDateHeader("Expires");

		Util.recordValue(attributes, "headerExpires", expires);

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "onDeliver" });

		return Weaver.callOriginal();
	}

	@Trace
	public CacheHandler.TRI_STATE onFetch(int status, Headers headers, HttpServletResponse response) {

		Map<String, Object> attributes = new HashMap<>();

		Util.recordValue(attributes, "status", status);

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "onFetch" });

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}
		return Weaver.callOriginal();
	}
}
