package io.github.drxaos.jisomorphic.pages;

import io.github.drxaos.jisomorphic.Dispatcher;
import io.github.drxaos.jisomorphic.templater.Loader;
import io.github.drxaos.jisomorphic.templater.Template;

import java.io.IOException;

public class Static extends Page {

    @Override
    public boolean accepts(String url) {
        return true;
    }

    @Override
    public Template render(final String url) throws IOException {
        Template template = new Template();
        context.loader.load(url, template, new Loader.Callback() {
            @Override
            public void recv(String data, Template template) {
                template.setPage(data, url);
            }
        });
        return template;
    }

    @Override
    public void animate(String url) throws IOException {
    }
}
