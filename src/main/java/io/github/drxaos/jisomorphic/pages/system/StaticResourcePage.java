package io.github.drxaos.jisomorphic.pages.system;

import io.github.drxaos.jisomorphic.loading.Loader;
import io.github.drxaos.jisomorphic.loading.Template;
import io.github.drxaos.jisomorphic.pages.Page;

import java.io.IOException;

public class StaticResourcePage extends Page {

    public StaticResourcePage() {
        url = ""; // any url
    }

    @Override
    public Template render(final String params) throws IOException {
        Template template = new Template();
        context.loader.requestResource("/" + params, template, new Loader.Callback() {
            @Override
            public void receive(String data, Template template) {
                template.setPage(data, "/" + params);
            }
        });
        return template;
    }

    @Override
    public void animate(String params) throws IOException {
    }
}
