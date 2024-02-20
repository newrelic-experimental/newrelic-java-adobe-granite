package com.adobe.granite.asset.api;

import java.util.HashMap;
import java.util.Map;

import com.newrelic.api.agent.NewRelic;
import com.newrelic.api.agent.Trace;
import com.newrelic.api.agent.weaver.MatchType;
import com.newrelic.api.agent.weaver.Weave;
import com.newrelic.api.agent.weaver.Weaver;
import com.newrelic.instrumentation.labs.adobe.granite.asset.api.Util;

@Weave(type = MatchType.Interface)
public abstract class AssetManager {

	@Trace
	public Asset createAsset(String path) {

		Map<String, Object> attributes = new HashMap<>();

		Asset result = null;

		if (null != path && !path.isEmpty()) {
			Util.recordValue(attributes, "path", path);

		}

		result = Weaver.callOriginal();

		if (null != result) {
			Util.recordValue(attributes, "assetResourceType", result.getResourceType());
			Util.recordValue(attributes, "assetName", result.getName());
			Util.recordValue(attributes, "assetID", result.getIdentifier());

		}

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "createAsset" });

		return result;

	}

	@Trace
	public void removeAsset(String assetPath) {

		Map<String, Object> attributes = new HashMap<>();

		if (null != assetPath && !assetPath.isEmpty()) {
			Util.recordValue(attributes, "assetPath", assetPath);

		}

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "removeAsset" });

		Weaver.callOriginal();
	}

	@Trace
	public void copyAsset(String assetPath, String destAbsPath) {

		Map<String, Object> attributes = new HashMap<>();

		if (null != assetPath && !assetPath.isEmpty()) {
			Util.recordValue(attributes, "assetPath", assetPath);

		}

		if (null != destAbsPath && !destAbsPath.isEmpty()) {
			Util.recordValue(attributes, "destAbsPath", destAbsPath);

		}

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "copyAsset" });

		Weaver.callOriginal();
	}

	@Trace
	public void moveAsset(String assetPath, String destAbsPath) {

		Map<String, Object> attributes = new HashMap<>();

		if (null != assetPath && !assetPath.isEmpty()) {
			Util.recordValue(attributes, "assetPath", assetPath);

		}

		if (null != destAbsPath && !destAbsPath.isEmpty()) {
			Util.recordValue(attributes, "destAbsPath", destAbsPath);

		}

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "moveAsset" });

		Weaver.callOriginal();
	}

	@Trace
	public Asset getAsset(String assetPath) {
		Map<String, Object> attributes = new HashMap<>();

		Asset result = null;

		if (null != assetPath && !assetPath.isEmpty()) {
			Util.recordValue(attributes, "assetPath", assetPath);

		}

		result = Weaver.callOriginal();

		if (null != result) {
			Util.recordValue(attributes, "assetResourceType", result.getResourceType());
			Util.recordValue(attributes, "assetName", result.getName());
			Util.recordValue(attributes, "assetID", result.getIdentifier());

		}

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		NewRelic.getAgent().getTracedMethod()
				.setMetricName(new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "getAsset" });

		return result;
	}

	@Trace
	public Asset getAssetByIdentifier(String id) {

		Map<String, Object> attributes = new HashMap<>();

		Asset result = null;

		if (null != id && !id.isEmpty()) {
			Util.recordValue(attributes, "assetId", id);

		}

		result = Weaver.callOriginal();

		if (null != result) {
			Util.recordValue(attributes, "assetResourceType", result.getResourceType());
			Util.recordValue(attributes, "assetName", result.getName());
			Util.recordValue(attributes, "assetPath", result.getPath());

		}

		if (attributes != null) {
			NewRelic.getAgent().getTracedMethod().addCustomAttributes(attributes);
		}

		NewRelic.getAgent().getTracedMethod().setMetricName(
				new String[] { "Custom", "AdobeGranite", getClass().getSimpleName(), "getAssetByIdentifier" });

		return result;
	}

}
