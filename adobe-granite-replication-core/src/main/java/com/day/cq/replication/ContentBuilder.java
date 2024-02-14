package com.day.cq.replication;

import java.util.Map;

import javax.jcr.Session;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.Interface)
public abstract class ContentBuilder {

	@Trace
	public ReplicationContent create(Session session, ReplicationAction action,
			ReplicationContentFactory contentFactory) {

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "create" });

		return Weaver.callOriginal();
	}

	@Trace
	public ReplicationContent create(Session session, ReplicationAction action,
			ReplicationContentFactory contentFactory, Map<String, Object> parameters) {

		
		if ( parameters != null ) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(parameters);
		}
		
		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "create" });

		return Weaver.callOriginal();
	}
}
