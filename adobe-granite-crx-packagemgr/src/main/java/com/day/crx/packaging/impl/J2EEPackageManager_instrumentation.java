package com.day.crx.packaging.impl;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.day.crx.packaging.impl.response.Response;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.granite.crx.packagemgr.Util;

@Weave(originalName = "com.day.crx.packaging.impl.J2EEPackageManager")
public abstract class J2EEPackageManager_instrumentation {


	@Trace(dispatcher = true)
	protected void doPost(Response response, String path) {

		Map<String, Object> attributes = new HashMap<>();

		HttpServletRequest req = response.getServletRequest();
		
		String theCmd = req.getParameter("cmd");

		if (theCmd != null) {

			
			Util.recordValue(attributes, "commandValue", theCmd);

		}
		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}
		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "doPost" });

		Weaver.callOriginal();
	}

}
