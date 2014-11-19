package io.github.drxaos.jisomorphic.pages;

import io.github.drxaos.jisomorphic.context.Context;
import io.github.drxaos.jisomorphic.templater.Template;

import java.io.IOException;

abstract public class Page {

    protected Context context;

    public void init(Context context) {
        this.context = context;
    }

    abstract public boolean accepts(String url);

    abstract public Template render(String url) throws IOException;

    abstract public void animate(String url) throws IOException;
}
