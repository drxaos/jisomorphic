package io.github.drxaos.jisomorphic.pages;

import io.github.drxaos.jisomorphic.templater.Template;

import java.io.IOException;

abstract public class Page {

    protected PageContext context;

    public void init(PageContext context) {
        this.context = context;
    }

    abstract public boolean accepts(String url);

    abstract public Template render(String url) throws IOException;

    abstract public void animate(String url) throws IOException;
}
