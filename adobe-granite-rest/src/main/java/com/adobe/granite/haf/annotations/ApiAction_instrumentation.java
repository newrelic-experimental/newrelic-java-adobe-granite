package com.adobe.granite.haf.annotations;

import java.util.HashMap;
import java.util.Map;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.Transaction;
import com.newrelic.api.agent.TransactionNamePriority;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.WeaveIntoAllMethods;
import com.newrelic.api.agent.weaver.WeaveWithAnnotation;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.granite.rest.Util;

@WeaveWithAnnotation(type = MatchType.Interface, annotationClasses = { "com.adobe.granite.haf.annotations.ApiAction" })
public abstract class ApiAction_instrumentation {

	@WeaveWithAnnotation(annotationClasses = { "com.adobe.granite.haf.annotations.ApiAction" })
	@WeaveIntoAllMethods
	@Trace(dispatcher = true)
	private static void instrumentation() {

		Map<String, Object> attributes = new HashMap<>();

		String name = null;

		ApiAction oApiAction = Weaver.getMethodAnnotation(ApiAction.class);

		if (oApiAction != null) {
			name = oApiAction.name();
		}

		if (name == null || name.isEmpty()) {
			Class<?> thisClass = ApiAction_instrumentation.class;
			name = thisClass.getSimpleName();
		}

		StackTraceElement[] traces = Thread.currentThread().getStackTrace();
		StackTraceElement first = traces[1];
		String methodName = first.getMethodName();
		String classname = first.getClassName();

		Transaction transaction = NewRelic.getAgent().getTransaction();
		if (transaction != null) {
			NewRelic.getAgent().getTracedMethod().setMetricName("Custom", "AdobeGranite", "ApiAction", classname, name,
					methodName);
			transaction.setTransactionName(TransactionNamePriority.FRAMEWORK_LOW, true, "AdobeGranite",
					new String[] { name, methodName });
		}

		if (oApiAction != null) {
			String title = oApiAction.title();
			String resourcePath = oApiAction.resourcePath();
			String method = oApiAction.method();
			String type = oApiAction.type();

			if (null != title && !title.isEmpty()) {
				Util.recordValue(attributes, "title", title);
			}

			if (null != resourcePath && !resourcePath.isEmpty()) {
				Util.recordValue(attributes, "resourcePath", resourcePath);
			}

			if (null != method && !method.isEmpty()) {
				Util.recordValue(attributes, "method", method);
			}

			if (null != name && !name.isEmpty()) {
				Util.recordValue(attributes, "name", name);
			}

			if (null != type && !type.isEmpty()) {
				Util.recordValue(attributes, "type", type);
			}

		}

	}
}
