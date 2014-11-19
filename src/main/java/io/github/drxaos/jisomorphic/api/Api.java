package io.github.drxaos.jisomorphic.api;

import java.io.IOException;

abstract public class Api {

    protected ApiContext context;

    public void init(ApiContext context) {
        this.context = context;
    }

    abstract public boolean accepts(String url);

    abstract public String render(String url) throws IOException;
}
