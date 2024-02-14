package com.adobe.granite.httpcache.api;

import java.util.HashMap;
import java.util.Map;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.granite.httpcache.api.Util;

@Weave(type = MatchType.Interface)
public class CacheStore {

	@Trace
	public CacheFile create(String key, Headers headers) {

		Map<String, Object> attributes = new HashMap<>();

		if (null != key && !key.isEmpty()) {
			Util.recordValue(attributes, "keyName", key);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "create" });

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		return Weaver.callOriginal();
	}

	@Trace
	public CacheFile lookup(String key) {

		Map<String, Object> attributes = new HashMap<>();

		if (null != key && !key.isEmpty()) {
			Util.recordValue(attributes, "keyName", key);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "lookup" });

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		return Weaver.callOriginal();
	}

	@Trace
	public boolean evict(String key) {

		Map<String, Object> attributes = new HashMap<>();

		if (null != key && !key.isEmpty()) {
			Util.recordValue(attributes, "keyName", key);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "evict" });

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		return Weaver.callOriginal();
	}

	@Trace
	public void evictAll(String host, String path) {

		Map<String, Object> attributes = new HashMap<>();

		if (null != host && !host.isEmpty()) {
			Util.recordValue(attributes, "hostName", host);
		}

		if (null != path && !path.isEmpty()) {
			Util.recordValue(attributes, "pathValue", path);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "evictAll" });

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		Weaver.callOriginal();

	}
}
