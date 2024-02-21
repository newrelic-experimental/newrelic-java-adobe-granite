package com.adobe.granite.haf.annotations;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.WeaveIntoAllMethods;
import com.newrelic.api.agent.weaver.WeaveWithAnnotation;

@WeaveWithAnnotation(type = MatchType.Interface, annotationClasses = { "com.adobe.granite.haf.annotations.ApiQuery" })
public abstract class ApiQuery_instrumentation {

	@WeaveWithAnnotation(annotationClasses = { "com.adobe.granite.haf.annotations.ApiQuery" })
	@WeaveIntoAllMethods
	@Trace
	private static void instrumentation() {

		StackTraceElement[] traces = Thread.currentThread().getStackTrace();
		StackTraceElement first = traces[1];
		String methodName = first.getMethodName();
		String classname = first.getClassName();

		NewRelic.getAgent().getTracedMethod().setMetricName("Custom", "AdobeGranite", "ApiAction", classname,
				methodName);

	}
}
