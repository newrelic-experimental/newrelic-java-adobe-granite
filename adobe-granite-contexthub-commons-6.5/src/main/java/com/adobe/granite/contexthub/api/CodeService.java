package com.adobe.granite.contexthub.api;

import org.apache.sling.api.SlingHttpServletRequest;
import org.apache.sling.api.SlingHttpServletResponse;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.granite.contexthub.commons.Util;

@Weave(type = MatchType.Interface)
public abstract class CodeService {

	@Trace
	public String getKernelCode(SlingHttpServletRequest request, SlingHttpServletResponse response) {

		String result;

		if (request != null) {
			Util.recordRequestAttributes(request);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "getKernelCode" });

		result = Weaver.callOriginal();

		return result;
	}
	@Trace
	public String getUICode(SlingHttpServletRequest request, SlingHttpServletResponse response) {

		String result;

		if (request != null) {
			Util.recordRequestAttributes(request);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "getUICode" });

		result = Weaver.callOriginal();

		return result;
	}
	
	@Trace
	public String getKernelCodeETag(SlingHttpServletRequest request, SlingHttpServletResponse response) {

		String result;

		if (request != null) {
			Util.recordRequestAttributes(request);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "getKernelCodeETag" });

		result = Weaver.callOriginal();

		return result;
	}
	
	@Trace(dispatcher = true)
	public String getUICodeETag(SlingHttpServletRequest request, SlingHttpServletResponse response) {

		String result;

		if (request != null) {
			Util.recordRequestAttributes(request);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "getUICodeETag" });

		result = Weaver.callOriginal();

		return result;
	}

}
