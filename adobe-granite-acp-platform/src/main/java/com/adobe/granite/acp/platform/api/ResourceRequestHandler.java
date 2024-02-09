package com.adobe.granite.acp.platform.api;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;

import com.adobe.granite.acp.platform.impl.ResourceResponse;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.granite.acp.platform.Util;

@Weave(type = MatchType.Interface)
public abstract class ResourceRequestHandler {

	@Trace(dispatcher = true)
	public ResourceResponse handleGet(ResourceRequest var1) {
		ResourceResponse result;

		Map<String, Object> attributes = new HashMap<>();

		String sResourcePath = null;
		String sRequestURI = null;

		result = Weaver.callOriginal();

		Util.recordRequestParams(attributes, var1);

		Util.recordValue(attributes, "reponseStatusCode", result.getStatusCode());

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "handleGet" });

		if (var1 != null) {
			sRequestURI = var1.getRequest().getRequestURI();
			sResourcePath = var1.getResource().getPath();

			Util.recordValue(attributes, "requestURI", sRequestURI);
			Util.recordValue(attributes, "resourcePath", sResourcePath);
			NewRelic.getAgent().getTransaction().setTransactionName(TransactionNamePriority.CUSTOM_HIGH, false,
					"AdobeGranite", new String[] { "handleGet", sRequestURI });
		}

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}
		NewRelic.getAgent().getLogger().log(Level.FINEST, "Custom AdobeGranite Instrumentation - Loaded");

		return result;

	}
}
