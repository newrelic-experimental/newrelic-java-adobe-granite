package com.day.crx.packaging.impl;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(originalName = "com.day.crx.packaging.impl.SlingInstallerSupport.Handle", type = MatchType.Interface)
public abstract class Handle_instrumentation {

	@Trace(dispatcher = true)
	public void release() {
		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "release" });

		Weaver.callOriginal();
	}
}
