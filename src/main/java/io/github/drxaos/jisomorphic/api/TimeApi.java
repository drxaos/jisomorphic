package io.github.drxaos.jisomorphic.api;

import java.io.IOException;

public class TimeApi extends Api {
    @Override
    public boolean accepts(String url) {
        return url.startsWith("/time/");
    }

    @Override
    public String render(String url) throws IOException {
        if (url.equals("/time/now")) {
            return "" + System.currentTimeMillis();
        }
        return "unknown";
    }
}
