package com.day.cq.replication;

import java.io.InputStream;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import javax.jcr.Session;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.replication.Util;

@Weave(type = MatchType.Interface)
public abstract class ReplicationReceiver {

	@Trace
	public void receive(Session session, ReplicationAction action, InputStream in, long size, Writer writer) {
		Map<String, Object> attributes = new HashMap<>();

		if (null != action) {
			Util.recordValue(attributes, "actionType", action.getType().getName());
			Util.recordValue(attributes, "actionPath", action.getPath());
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "receive" });

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		Weaver.callOriginal();
	}

	@Trace
	public void receive(Session session, ReplicationAction action, InputStream in, long size, Writer writer,
			boolean install) {
		Map<String, Object> attributes = new HashMap<>();

		if (null != action) {
			Util.recordValue(attributes, "actionType", action.getType().getName());
			Util.recordValue(attributes, "actionPath", action.getPath());
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "receive" });

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		Weaver.callOriginal();
	}

	@Trace
	public void receive(Session session, final ReplicationAction action, InputStream in, long size, Writer writer,
			boolean install, boolean binaryLess) {

		Map<String, Object> attributes = new HashMap<>();

		if (null != action) {
			Util.recordValue(attributes, "actionType", action.getType().getName());
			Util.recordValue(attributes, "actionPath", action.getPath());
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "receive" });

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		Weaver.callOriginal();
	}

}
