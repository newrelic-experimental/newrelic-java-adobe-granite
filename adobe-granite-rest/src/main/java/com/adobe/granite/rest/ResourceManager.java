package com.adobe.granite.rest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.sling.api.resource.Resource;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.granite.rest.Util;

@Weave(type = MatchType.Interface)
public abstract class ResourceManager {

	@Trace
	public boolean copy(Resource resource, String destUri, int depth) {

		boolean result;
		Map<String, Object> attributes = new HashMap<>();

		result = Weaver.callOriginal();

		Util.recordValue(attributes, "copyResult", result);

		if (null != destUri && !destUri.isEmpty()) {
			Util.recordValue(attributes, "destUri", destUri);
		}
		if (null != resource) {
			Util.recordValue(attributes, "resourceName", resource.getName());
			Util.recordValue(attributes, "resourcePath", resource.getPath());
			Util.recordValue(attributes, "resourceType", resource.getResourceType());
		}
		Util.recordValue(attributes, "copyDepth", depth);

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "copy" });

		return result;
	}

	@Trace
	public boolean move(Resource resource, String destUri) {

		boolean result;
		Map<String, Object> attributes = new HashMap<>();

		result = Weaver.callOriginal();

		Util.recordValue(attributes, "moveResult", result);

		if (null != destUri && !destUri.isEmpty()) {
			Util.recordValue(attributes, "destUri", destUri);
		}
		if (null != resource) {
			Util.recordValue(attributes, "resourceName", resource.getName());
			Util.recordValue(attributes, "resourcePath", resource.getPath());
			Util.recordValue(attributes, "resourceType", resource.getResourceType());
		}

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "move" });

		return result;
	}

	@Trace
	public List<Resource> find(Resource resource, String query) {

		List<Resource> result;

		Map<String, Object> attributes = new HashMap<>();

		result = Weaver.callOriginal();

		if (null != query && !query.isEmpty()) {
			Util.recordValue(attributes, "query", query);
		}
		if (null != resource) {
			Util.recordValue(attributes, "resourceName", resource.getName());
			Util.recordValue(attributes, "resourcePath", resource.getPath());
			Util.recordValue(attributes, "resourceType", resource.getResourceType());
		}

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "find" });

		return result;

	}

}
