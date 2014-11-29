package io.github.drxaos.jisomorphic.api;

import io.github.drxaos.jisomorphic.Params;
import io.github.drxaos.jisomorphic.config.Config;
import io.github.drxaos.jisomorphic.loading.Template;

import java.io.IOException;

abstract public class Api {

    protected String url = "/" + this.getClass().getName();

    public String getUrl() {
        return url;
    }

    abstract public String render(Params params, Template template) throws IOException;
}
