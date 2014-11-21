package io.github.drxaos.jisomorphic.api.simple;

import io.github.drxaos.jisomorphic.api.Api;
import io.github.drxaos.jisomorphic.loading.Loader;
import io.github.drxaos.jisomorphic.loading.Template;

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
        return hello() + "! " + id();
    }

    public String id() throws IOException {
        Template template = new Template();
        context.loader.requestApi(UidApi.makeUrlNext(), template, new Loader.Callback() {
            @Override
            public void receive(String data, Template template) {
                template.put("data", data);
            }
        });
        return template.getString("data");
    }

    public String hello() throws IOException {
        int n = (int) (System.currentTimeMillis() % 10);
        switch (n) {
            case 0:
                return "Hello";
            case 1:
                return "Hallo";
            case 2:
                return "Ahalan";
            case 3:
                return "Sveiki";
            case 4:
                return "Bonjour";
            case 5:
                return "Hola";
            case 6:
                return "Ola";
            case 7:
                return "Merhaba";
            case 8:
                return "Parev";
            case 9:
                return "Aloha";
            default:
                return "Hello";
        }
    }
}
