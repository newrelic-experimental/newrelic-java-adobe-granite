package com.adobe.granite.contexthub.api;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.sling.api.SlingHttpServletRequest;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.granite.contexthub.commons.Util;

@Weave(originalName = "com.adobe.granite.contexthub.api.ContextHubCache", type = MatchType.Interface)
public abstract class ContextHubCache_instrumentation {

	private String getContextHubPath(SlingHttpServletRequest request) {
		return Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	String getCode(SlingHttpServletRequest request, ContextHubCache.CodeType codeType) {
		String result;
		Map<String, Object> attributes = new HashMap<>();

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "getCode" });

		Util.recordRequestAttributes(request);
		Util.recordValue(attributes, "fileName", codeType.fileName());

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}
		result = Weaver.callOriginal();
		return result;
	}

	@SuppressWarnings("rawtypes")
	@Trace(dispatcher = true)
	Map getCodeMetadata(SlingHttpServletRequest request, ContextHubCache.CodeType codeType) {

		Object result = Collections.EMPTY_MAP;

		Map<String, Object> attributes = new HashMap<>();

		NewRelic.getAgent().getTracedMethod().setMetricName(
				new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "getCodeMetadata" });

		Util.recordRequestAttributes(request);

		String path = this.getContextHubPath(request);

		Util.recordValue(attributes, "path", path);

		Util.recordValue(attributes, "fileName", codeType.fileName());

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		result = Weaver.callOriginal();

		return (Map) result;
	}

	@Trace(dispatcher = true)
	void cacheCode(SlingHttpServletRequest request, ContextHubCache.CodeType codeType) {

		Map<String, Object> attributes = new HashMap<>();

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "cacheCode" });

		Util.recordRequestAttributes(request);

		String path = this.getContextHubPath(request);

		Util.recordValue(attributes, "path", path);

		Util.recordValue(attributes, "fileName", codeType.fileName());

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		Weaver.callOriginal();

	}

	@SuppressWarnings("rawtypes")
	@Trace(dispatcher = true)
	void setCodeMetadata(SlingHttpServletRequest request, ContextHubCache.CodeType codeType, Map metaData) {
	
		Map<String, Object> attributes = new HashMap<>();

		NewRelic.getAgent().getTracedMethod().setMetricName(
				new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "setCodeMetadata" });
		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}
		Weaver.callOriginal();

	}

}
