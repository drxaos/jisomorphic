package io.github.drxaos.jisomorphic.api.simple;

import io.github.drxaos.jisomorphic.api.Api;

import java.io.IOException;

public class TimeApi extends Api {

    private static String URL = "/api/time";

    public TimeApi() {
        url = URL;
    }

    public static String makeUrlNow() {
        return URL + "/now";
    }

    @Override
    public String render(String params) throws IOException {
        if (params.equals("now")) {
            return "" + System.currentTimeMillis();
        }
        return "unknown";
    }
}
