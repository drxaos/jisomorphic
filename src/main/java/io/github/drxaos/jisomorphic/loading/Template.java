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

    public Context getContext() {
        return context;
    }

    // Processing

    public static interface PostProcessor {
        void process(Template template);
    }

    List<PostProcessor> postProcessors=new ArrayList<PostProcessor>();

    public Template postProcessor(PostProcessor postProcessor) {
        this.postProcessors.add(postProcessor);
        return this;
    }

    public Template resource(String url, Loader.Callback<String> callback) throws IOException {
        context.loader.requestResource(url, this, callback);
        return this;
    }

    public void postProcess() {
        for (PostProcessor postProcessor : postProcessors) {
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
