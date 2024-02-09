package com.day.crx.packaging.impl;

import com.day.crx.packaging.impl.SlingInstallerSupport.Handle;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(originalName = "com.day.crx.packaging.impl.SlingInstallerSupport", type = MatchType.Interface)
public abstract class SlingInstallerSupport_Instrumentation {

	@Trace(dispatcher = true)
	public Handle pause() {

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "pause" });

		return Weaver.callOriginal();
	}

}