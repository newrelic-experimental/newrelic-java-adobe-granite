package com.day.cq.replication;

import java.util.HashMap;
import java.util.Map;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.replication.Util;

@Weave(type = MatchType.Interface)
public abstract class TransportHandler {

	@Trace
	public ReplicationResult deliver(TransportContext ctx, ReplicationTransaction tx) {

		Map<String, Object> attributes = new HashMap<>();

		if (null != ctx) {
			Util.recordValue(attributes, "ctxName", ctx.getName());
		}

		if (tx != ctx) {
			Util.recordValue(attributes, "rtxName", tx.toString());
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "deliver" });

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}
		return Weaver.callOriginal();
	}
}
