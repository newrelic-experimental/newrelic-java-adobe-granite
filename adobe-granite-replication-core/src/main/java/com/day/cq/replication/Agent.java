package com.day.cq.replication;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;

@Weave(type = MatchType.Interface)
public abstract class Agent {

	@Trace(dispatcher = true)
	public void replicate(final CompositeReplicationAction compositeReplicationAction,
			ReplicationContent replicationContent, final ReplicationOptions replicationOptions) {

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "replicate" });

		Weaver.callOriginal();

	}

	@Trace(dispatcher = true)
	public void replicate(ReplicationAction replicationAction, ReplicationContent replicationContent,
			ReplicationOptions replicationOptions) {

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "replicate" });

		Weaver.callOriginal();
	}

}
