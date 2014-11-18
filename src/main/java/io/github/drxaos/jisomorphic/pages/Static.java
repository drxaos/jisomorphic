package io.github.drxaos.jisomorphic.pages;

import java.io.IOException;

public class Static extends Page {

    @Override
    public boolean accepts(String url) {
        return url.startsWith("/teavm/") || url.equals("/favicon.ico");
    }

    @Override
    public String render(String url) throws IOException {
        if (url.startsWith("/teavm/")) {
            return context.loader.load("/js" + url);
        }
        if (url.equals("/favicon.ico")) {
            return context.loader.load("/images/favicon.ico");
        }
        return null;
    }
}
