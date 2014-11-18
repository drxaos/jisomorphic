package io.github.drxaos.jisomorphic.pages;

import io.github.drxaos.jisomorphic.context.Context;

import java.io.IOException;

abstract public class Page {

    protected Context context;

    public void init(Context context) {
        this.context = context;
    }

    abstract public boolean accepts(String url);

    abstract public String render(String url) throws IOException;
}
