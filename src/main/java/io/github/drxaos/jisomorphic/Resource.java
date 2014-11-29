package io.github.drxaos.jisomorphic;

import io.github.drxaos.jisomorphic.Params;
import io.github.drxaos.jisomorphic.config.Config;
import io.github.drxaos.jisomorphic.loading.Template;

import java.io.IOException;
import java.util.Map;

abstract public class Resource {

    private String url = "/" + this.getClass().getName();

    public String getUrlTemplate() {
        return url;
    }

    protected Resource(String urlTemplate) {
        this.url = urlTemplate;
    }

    abstract public void render(Params params, Template template) throws IOException;

    public void animate(Params params) throws IOException {
        // default
    }
}
