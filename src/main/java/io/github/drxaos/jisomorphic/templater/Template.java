package io.github.drxaos.jisomorphic.templater;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Template {
    public static interface PostProcessor {
        void process(Template template);
    }

    PostProcessor postProcessor;

    public void setPostProcessor(PostProcessor postProcessor) {
        this.postProcessor = postProcessor;
    }

    public void postProcess() {
        if (postProcessor != null) {
            postProcessor.process(this);
            postProcessor = null;
        }
    }

    ArrayList<Loader.ResourceCallback> callbacks = new ArrayList<Loader.ResourceCallback>();

    public boolean removeCallback(Object o) {
        return callbacks.remove(o);
    }

    public boolean addCallback(Loader.ResourceCallback callback) {
        return callbacks.add(callback);
    }

    public boolean isReady() {
        return callbacks.isEmpty();
    }


    String page = "";
    String title = "";

    public String getPage() {
        return page;
    }

    public String getTitle() {
        return title;
    }

    public void setPage(String page, String title) {
        this.page = page;
        this.title = title;
    }

    HashMap<String, String> map = new HashMap<String, String>();

    public int size() {
        return map.size();
    }

    public boolean isEmpty() {
        return map.isEmpty();
    }

    public String get(String key) {
        return map.get(key);
    }

    public boolean containsKey(String key) {
        return map.containsKey(key);
    }

    public Object put(String key, String value) {
        return map.put(key, value);
    }

    public void putAll(Map<? extends String, ? extends String> m) {
        map.putAll(m);
    }

    public Object remove(String key) {
        return map.remove(key);
    }

    public void clear() {
        map.clear();
    }

    public boolean containsValue(String value) {
        return map.containsValue(value);
    }
}
