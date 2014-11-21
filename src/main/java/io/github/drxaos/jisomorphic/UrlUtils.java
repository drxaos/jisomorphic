package io.github.drxaos.jisomorphic;

import java.util.HashMap;
import java.util.Map;

public class UrlUtils {

    public static Map<String, String> parseParams(String params, String... paramsNames) {
        HashMap<String, String> result = new HashMap<String, String>();
        String[] split = params.split("\\/");
        for (int i = 0; i < paramsNames.length; i++) {
            if (split.length > i) {
                result.put(paramsNames[i], split[i]);
            }
        }
        return result;
    }

    public static String getString(String paramName, Map<String, String> params, String defaultValue) {
        String v = params.get(paramName);
        return v == null ? defaultValue : v;
    }

    public static Integer getInt(String paramName, Map<String, String> params, Integer defaultValue) {
        String v = params.get(paramName);
        try {
            return v == null ? defaultValue : Integer.parseInt(v);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public static Float getFloat(String paramName, Map<String, String> params, Float defaultValue) {
        String v = params.get(paramName);
        try {
            return v == null ? defaultValue : Float.parseFloat(v);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

}
