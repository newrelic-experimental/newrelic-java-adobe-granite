package com.adobe.granite.workflow.exec;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.granite.workflow.api.Util;

@Weave(type = MatchType.Interface)
public abstract class WorkflowExternalProcessProxy {

	@Trace
	public Serializable execute(String externalProcessName, WorkItem workItem, WorkflowSession workflowSession,
			MetaDataMap metaDataMap) {

		Map<String, Object> attributes = new HashMap<>();

		if (null != workItem) {
			Util.recordValue(attributes, "workItemType", workItem.getItemType());
			Util.recordValue(attributes, "workItemId", workItem.getId());
		}

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "execute" });

		return Weaver.callOriginal();

	}

	@Trace
	public void handleResult(String externalProcessName, Serializable serializable, WorkItem workItem,
			WorkflowSession workflowSession, MetaDataMap metaDataMap) {

		Map<String, Object> attributes = new HashMap<>();

		if (null != workItem) {
			Util.recordValue(attributes, "workItemType", workItem.getItemType());
			Util.recordValue(attributes, "workItemId", workItem.getId());
		}

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "handleResult" });

		Weaver.callOriginal();

	}
}
