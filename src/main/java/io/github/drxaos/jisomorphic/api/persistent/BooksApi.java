package io.github.drxaos.jisomorphic.api.persistent;

import io.github.drxaos.jisomorphic.api.Api;
import io.github.drxaos.jisomorphic.db.Book;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;

public class BooksApi extends Api {

    private static String URL = "/api/books";

    public BooksApi() {
        url = URL;
    }

    public static String makeUrlList(int page) {
        return URL + "/list";
    }

    @Override
    public String render(String params) throws IOException {
        if (params.equals("list")) {
            return list();
        }
        return "error";
    }

    private String list() {
        ArrayList<Map> result = new ArrayList<Map>();
        for (Book book : Book.findBooks(context.database)) {
            result.add(book.asMap());
        }
        return JSONValue.toJSONString(result);
    }
}
