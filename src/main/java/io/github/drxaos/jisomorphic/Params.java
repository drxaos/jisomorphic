package io.github.drxaos.jisomorphic;

import org.apache.commons.lang3.StringUtils;

import java.util.Map;

public class Params {

    public static boolean checkUrl(String url, String templateUrl) {
        return StringUtils.countMatches(url, "/") == StringUtils.countMatches(templateUrl, "/");
    }

    String url, templateUrl;
    Map<String, String> params;

    public Params(String url, String templateUrl) {
        this.url = url;
        this.templateUrl = templateUrl;
        String[] u = url.split("\\/");
        String[] t = templateUrl.split("\\/");

        for (int i = 0; i < t.length; i++) {
            if (t[i].startsWith("{") && t[i].endsWith("}")) {
                params.put(t[i].substring(1, t[i].length() - 1), u[i]);
            }
        }
    }

    public String getUrl() {
        String res = templateUrl;
        for (Map.Entry<String, String> e : params.entrySet()) {
            res = res.replace("{" + e.getKey() + "}", e.getValue());
        }
        return res;
    }

    public String getString(String key, String defaultValue) {
        String v = null;
        if (params.containsKey(key)) {
            v = params.get(key);
        }
        return v == null ? defaultValue : v;
    }

    public void setString(String key, String value) {
        params.put(key, value);
    }

    public Integer getInt(String key, Integer defaultValue) {
        String v = null;
        if (params.containsKey(key)) {
            v = params.get(key);
        }
        try {
            return v == null ? defaultValue : Integer.parseInt(v);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public void setInt(String key, Integer value) {
        params.put(key, "" + value);
    }

    public Float getFloat(String key, Float defaultValue) {
        String v = null;
        if (params.containsKey(key)) {
            v = params.get(key);
        }
        try {
            return v == null ? defaultValue : Float.parseFloat(v);
        } catch (NumberFormatException e) {
            return defaultValue;
        }
    }

    public void setFloat(String key, Float value) {
        params.put(key, "" + value);
    }

}
