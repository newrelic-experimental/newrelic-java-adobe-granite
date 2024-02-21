package com.adobe.granite.rest;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.sling.api.resource.Resource;
import org.apache.sling.api.resource.ResourceResolver;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.granite.rest.Util;

@Weave(type = MatchType.Interface)
public abstract class ApiResourceProvider {

	@Trace
	public Resource getResource(ResourceResolver resourceResolver, String path) {

		Resource result;

		Map<String, Object> attributes = new HashMap<>();

		if (null != path && !path.isEmpty()) {
			Util.recordValue(attributes, "destUri", path);

		}

		result = Weaver.callOriginal();

		if (null != result) {
			Util.recordValue(attributes, "resourceName", result.getName());
			Util.recordValue(attributes, "resourcePath", result.getPath());
			Util.recordValue(attributes, "resourceType", result.getResourceType());
		}

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "getResource" });

		return result;

	}

	@Trace
	public Iterator<Resource> listChildren(Resource parent) {

		Map<String, Object> attributes = new HashMap<>();

		if (null != parent) {
			Util.recordValue(attributes, "ParentResourceName", parent.getName());
			Util.recordValue(attributes, "ParentResourcePath", parent.getPath());
			Util.recordValue(attributes, "ParentResourceType", parent.getResourceType());
		}

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "listChildren" });

		return Weaver.callOriginal();

	}

	@Trace
	public Resource create(ResourceResolver resolver, String path, Map<String, Object> properties) {
		Resource result;

		Map<String, Object> attributes = new HashMap<>();

		if (null != path && !path.isEmpty()) {
			Util.recordValue(attributes, "path", path);

		}

		result = Weaver.callOriginal();

		if (null != result) {
			Util.recordValue(attributes, "resourceName", result.getName());
			Util.recordValue(attributes, "resourcePath", result.getPath());
			Util.recordValue(attributes, "resourceType", result.getResourceType());
		}

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		if (properties != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(properties); // will it over load ..
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "create" });

		return result;
	}

	@Trace
	public void delete(ResourceResolver resolver, String path) {

		Map<String, Object> attributes = new HashMap<>();

		if (null != path && !path.isEmpty()) {
			Util.recordValue(attributes, "path", path);

		}

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "delete" });

		Weaver.callOriginal();
	}

}
