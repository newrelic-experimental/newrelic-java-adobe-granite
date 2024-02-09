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

	// failed to get definition using Weaver.callOriginal()
	// Hence hard coded values used here from the implementation for a private enum definition.
	private static enum CMD {
		upload, create, delete, dryrun, validate, addOnValidate, preview, contents, build, rewrap, install, uninstall,
		replicate, nop;
	}

	@Trace(dispatcher = true)
	protected void doPost(Response response, String path) {

		Map<String, Object> attributes = new HashMap<>();

		HttpServletRequest req = response.getServletRequest();

		J2EEPackageManager_instrumentation.CMD cmd;

		if (null != req.getParameter("cmd")) {

			cmd = J2EEPackageManager_instrumentation.CMD.valueOf(req.getParameter("cmd"));
			Util.recordValue(attributes, "commandValue", cmd);

		}
		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}
		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "doPost" });

		Weaver.callOriginal();
	}

}
