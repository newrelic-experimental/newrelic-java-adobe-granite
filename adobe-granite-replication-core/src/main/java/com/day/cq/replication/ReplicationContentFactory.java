package com.day.cq.replication;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.replication.Util;

@Weave(type = MatchType.Interface)
public abstract class ReplicationContentFactory {

	@Trace(dispatcher = true)
	public ReplicationContent create(ReplicationContentFacade facade) {

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "create" });

		return Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	public ReplicationContent create(String mimeType, File file, boolean isTemp) {

		Map<String, Object> attributes = new HashMap<>();

		if (null != file) {
			Util.recordValue(attributes, "filePath", file.getAbsolutePath());
		}

		if (null != mimeType && !mimeType.isEmpty()) {
			Util.recordValue(attributes, "mimeType", mimeType);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "create" });

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		return Weaver.callOriginal();
	}

	@Trace(dispatcher = true)
	public ReplicationContent create(String mimeType, File file, long lastModified, boolean isTemp) {

		Map<String, Object> attributes = new HashMap<>();

		if (null != file) {
			Util.recordValue(attributes, "filePath", file.getAbsolutePath());
		}

		if (null != mimeType && !mimeType.isEmpty()) {
			Util.recordValue(attributes, "mimeType", mimeType);
		}
		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "create" });

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		return Weaver.callOriginal();
	}
}
