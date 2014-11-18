package io.github.drxaos.jisomorphic.pages;

import java.io.IOException;

public class Test extends Page {

    @Override
    public boolean accepts(String url) {
        return url.startsWith("/test/");
    }

    @Override
    public String render(String url) throws IOException {
        return context.loader.load("/index.html");
    }
}
