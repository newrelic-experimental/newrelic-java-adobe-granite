package com.adobe.granite.workflow;

import java.util.HashMap;
import java.util.Map;

import com.adobe.granite.workflow.exec.Route;
import com.adobe.granite.workflow.exec.WorkItem;
import com.adobe.granite.workflow.exec.Workflow;
import com.adobe.granite.workflow.exec.WorkflowData;
import com.adobe.granite.workflow.model.WorkflowModel;
import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.granite.workflow.api.Util;

@Weave(type = MatchType.Interface)
public abstract class WorkflowSession {

	@Trace
	public void complete(WorkItem item, Route route) {

		Map<String, Object> attributes = new HashMap<>();

		if (null != item) {
			Util.recordValue(attributes, "workItemType", item.getItemType());
			Util.recordValue(attributes, "workItemId", item.getId());
		}

		if (null != route) {
			Util.recordValue(attributes, "routeName", route.getName());
			Util.recordValue(attributes, "routeId", route.getId());
		}

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "complete" });

		Weaver.callOriginal();

	}

	@Trace
	public Workflow startWorkflow(WorkflowModel model, WorkflowData data) {

		Map<String, Object> attributes = new HashMap<>();

		if (null != model) {
			Util.recordValue(attributes, "modelTitle", model.getTitle());
			Util.recordValue(attributes, "modelId", model.getId());
		}

		if (null != data) {
			Util.recordValue(attributes, "dataPayloadType", data.getPayloadType());
		}

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "startWorkflow" });

		return Weaver.callOriginal();

	}

	@Trace
	public Workflow startWorkflow(WorkflowModel model, WorkflowData data, Map<String, Object> metaData) {

		Map<String, Object> attributes = new HashMap<>();

		Workflow result;

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "startWorkflow" });

		if (null != model) {
			Util.recordValue(attributes, "modelTitle", model.getTitle());
			Util.recordValue(attributes, "modelId", model.getId());
		}

		if (null != data) {
			Util.recordValue(attributes, "dataPayloadType", data.getPayloadType());
		}

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		result = Weaver.callOriginal();

		if (metaData != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(metaData);
		}

		return result;
	}

	@Trace
	public void suspendWorkflow(Workflow instance) {
		Map<String, Object> attributes = new HashMap<>();

		if (null != instance) {
			Util.recordValue(attributes, "workFlowState", instance.getState());
			Util.recordValue(attributes, "workFlowId", instance.getId());
		}
		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		NewRelic.getAgent().getTracedMethod().setMetricName(
				new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "suspendWorkflow" });

		Weaver.callOriginal();
	}

	@Trace
	public void terminateWorkflow(Workflow instance) {
		Map<String, Object> attributes = new HashMap<>();

		if (null != instance) {
			Util.recordValue(attributes, "workFlowState", instance.getState());
			Util.recordValue(attributes, "workFlowId", instance.getId());
		}
		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		NewRelic.getAgent().getTracedMethod().setMetricName(
				new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "terminateWorkflow" });

		Weaver.callOriginal();
	}
}
