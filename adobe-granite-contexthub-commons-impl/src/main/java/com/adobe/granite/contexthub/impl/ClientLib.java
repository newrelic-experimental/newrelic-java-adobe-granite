package com.adobe.granite.contexthub.impl;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.granite.contexthub.commons.impl.Util;
import com.adobe.granite.ui.clientlibs.ClientLibrary;

import java.util.HashMap;
import java.util.Map;


@Weave
public abstract class ClientLib {

	@Trace(dispatcher = true)
	public String getContent(ClientLibrary clientLibrary) {
		String result;
		Map<String, Object> attributes = new HashMap<>();
		NewRelic.getAgent().getTracedMethod().setMetricName(new String[] {"Custom", "AdobeGranite", getClass().getSimpleName(), "getContent"});
		if (clientLibrary !=null) {
			
			String libPath=clientLibrary.getPath();
			Util.recordValue(attributes, "libPath", libPath);
		}
		
		if ( attributes != null ) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}
		result= Weaver.callOriginal();
		
		return result;
	}
	
}
