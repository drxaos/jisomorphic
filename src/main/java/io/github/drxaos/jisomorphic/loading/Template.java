package io.github.drxaos.jisomorphic.loading;

import io.github.drxaos.jisomorphic.Context;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Template {

    // Init

    Context context;

    public Template(Context context) {
        this.context = context;
    }

    // Processing

    public static final int TYPE_AFTER_ANY = 100;
    public static final int TYPE_AFTER_ALL = 200;
    public static final int TYPE_FINAL = 300;

    public static interface PostProcessor {
        void process(Template template);
    }

    Map<Integer, List<PostProcessor>> postProcessors = new HashMap<Integer, List<PostProcessor>>();

    {
        postProcessors.put(TYPE_AFTER_ANY, new ArrayList<PostProcessor>());
        postProcessors.put(TYPE_AFTER_ALL, new ArrayList<PostProcessor>());
        postProcessors.put(TYPE_FINAL, new ArrayList<PostProcessor>());
    }

    public Template addPostProcessor(int type, PostProcessor postProcessor) {
        this.postProcessors.get(type).add(postProcessor);
        return this;
    }

    public Template addResource(String url, Loader.Callback<String> callback) throws IOException {
        context.loader.requestResource(url, this, callback);
        return this;
    }

    public Template addApi(String url, Loader.Callback<String> callback) throws IOException {
        context.loader.requestApi(url, this, callback);
        return this;
    }

    public void postProcess(int type) {
        for (PostProcessor postProcessor : postProcessors.get(type)) {
            postProcessor.process(this);
        }
    }

    // Result

    String type = "plain/html";
    byte[] page = {};

    public byte[] getResult() {
        return page;
    }

    public void setPage(String page) {
        this.type = "plain/html";
        this.page = page.getBytes(Charset.defaultCharset());
    }

    public void setJson(String json) {
        this.type = "application/json";
        this.page = json.getBytes(Charset.defaultCharset());
    }

    public void setBinary(byte[] data) {
        this.type = "application/binary";
        this.page = data;
    }

    public void setBinary(byte[] data, String contentType) {
        this.type = contentType;
        this.page = data;
    }

    // Storage

    HashMap<String, Object> map = new HashMap<String, Object>();

    public Object getObject(String key, Object defaultValue) {
        return map.containsKey(key) ? map.get(key) : defaultValue;
    }

    public String getString(String key, String defaultValue) {
        Object val = map.get(key);
        if (val == null) {
            return defaultValue;
        } else if (val instanceof String) {
            return (String) val;
        } else {
            return val.toString();
        }
    }

    public Object put(String key, Object value) {
        return map.put(key, value);
    }

    public void putAll(Map<? extends String, ?> m) {
        map.putAll(m);
    }

    public Object remove(String key) {
        return map.remove(key);
    }
}
