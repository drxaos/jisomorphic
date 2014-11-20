package io.github.drxaos.jisomorphic.api.persistent;

import io.github.drxaos.jisomorphic.api.Api;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class BooksApi extends Api {

    private static String URL = "/api/books";

    public BooksApi() {
        url = URL;
    }

    public static String makeUrlList() {
        return URL + "/list";
    }

    @Override
    public String render(String params) throws IOException {
        if (params.equals("list")) {
            return JSONValue.toJSONString(list());
        }
        return "error";
    }

    private List<String> list() {
        ArrayList<String> books = new ArrayList<String>();

        books.add("Book1");
        books.add("Book2");
        books.add("Book3");

        return books;
    }
}
