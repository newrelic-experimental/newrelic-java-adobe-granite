package com.newrelic.instrumentation.labs.adobe.granite.httpcache.api;

import java.util.Enumeration;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public class Util {

	public static void recordValue(Map<String, Object> attributes, String key, Object value) {
		if (key != null && !key.isEmpty() && value != null) {
			attributes.put(key, value);
		}
	}

	public static void recordHttpRequest(Map<String, Object> attributes, HttpServletRequest request) {

		if (null != request) {
			recordValue(attributes, "requestURI", request.getRequestURI());
			recordValue(attributes, "method", request.getMethod());
			recordValue(attributes, "protocol", request.getProtocol());
			recordValue(attributes, "remoteAddr", request.getRemoteAddr());

			Enumeration<String> headerNames = request.getHeaderNames();
			while (headerNames.hasMoreElements()) {
				String headerName = headerNames.nextElement();
				recordValue(attributes, headerName, request.getHeader(headerName));

			}
		}

	}

}