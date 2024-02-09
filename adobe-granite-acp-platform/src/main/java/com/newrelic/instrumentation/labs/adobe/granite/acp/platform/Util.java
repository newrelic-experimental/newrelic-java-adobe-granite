package com.newrelic.instrumentation.labs.adobe.granite.acp.platform;

import java.util.Enumeration;
import java.util.Map;

import com.adobe.granite.acp.platform.api.ResourceRequest;

public class Util {


	public static void recordRequestParams(Map<String, Object> attributes, ResourceRequest request) {

		Enumeration<String> requestParams;

		if(request != null) {

			requestParams = request.getRequest().getAttributeNames();

			while (requestParams.hasMoreElements()) {
				String key = requestParams.nextElement();
				attributes.put("RequesAttr-"+key, request.getRequest().getAttribute(key));
			}

		}
	}

	public static void recordValue(Map<String,Object> attributes, String key, Object value) {
		if(key != null && !key.isEmpty() && value != null) {
			attributes.put(key, value);
		}
	}

}