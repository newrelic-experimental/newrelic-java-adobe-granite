package com.day.crx.packaging.impl;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.Interface)
public abstract class SlingInstallerSupport {

	@Trace
	public Handle pause() {

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "pause" });

		return Weaver.callOriginal();
	}

	@Weave(type = MatchType.Interface)
	// Inner class has to be static
	public static abstract class Handle {

	}
}
