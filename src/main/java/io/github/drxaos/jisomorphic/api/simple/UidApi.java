package io.github.drxaos.jisomorphic.api.simple;

import io.github.drxaos.jisomorphic.api.Api;

import java.io.IOException;

public class UidApi extends Api {
    private static long uid = 1000000;

    private static String URL = "/api/uid";

    public UidApi() {
        url = URL;
    }

    public static String makeUrlNext() {
        return URL + "/next";
    }

    public static String makeUrlCurrent() {
        return URL + "/current";
    }

    @Override
    public String render(String params) throws IOException {
        if (params.equals("next")) {
            return "" + ++uid;
        } else if (params.equals("current")) {
            return "" + uid;
        }
        return "unknown";
    }
}
