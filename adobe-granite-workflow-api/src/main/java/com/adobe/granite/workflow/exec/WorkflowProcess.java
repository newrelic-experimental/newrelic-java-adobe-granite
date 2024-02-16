package com.adobe.granite.workflow.exec;

import java.util.HashMap;
import java.util.Map;

import com.adobe.granite.workflow.WorkflowSession;
import com.adobe.granite.workflow.metadata.MetaDataMap;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.granite.workflow.api.Util;

@Weave(type = MatchType.Interface)
public abstract class WorkflowProcess {

	public void execute(WorkItem item, WorkflowSession workflowSession, MetaDataMap args) {

		Map<String, Object> attributes = new HashMap<>();

		if (null != item) {
			Util.recordValue(attributes, "workItemType", item.getItemType());
			Util.recordValue(attributes, "workItemId", item.getId());
		}

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "execute" });

		Weaver.callOriginal();
	}
}
