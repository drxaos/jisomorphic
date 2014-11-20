package io.github.drxaos.jisomorphic.api.simple;

import io.github.drxaos.jisomorphic.api.Api;

import java.io.IOException;

public class HelloApi extends Api {

    private static String URL = "/api/hello";

    public HelloApi() {
        url = URL;
    }

    public static String makeUrl() {
        return URL;
    }

    @Override
    public String render(String params) throws IOException {
        int n = (int) (System.currentTimeMillis() % 10);
        switch (n) {
            case 0:
                return "Hello";
            case 1:
                return "Hallo";
            case 2:
                return "привет";
            case 3:
                return "Sveiki";
            case 4:
                return "bonjour";
            case 5:
                return "Hola";
            case 6:
                return "Ola";
            case 7:
                return "merhaba";
            case 8:
                return "здравей";
            case 9:
                return "γειά σου";
            default:
                return "Hello";
        }
    }
}
