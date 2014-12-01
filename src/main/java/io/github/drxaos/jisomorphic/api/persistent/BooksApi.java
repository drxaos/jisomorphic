package io.github.drxaos.jisomorphic.api.persistent;

import io.github.drxaos.jisomorphic.UrlUtils;
import io.github.drxaos.jisomorphic.api.Api;
import io.github.drxaos.jisomorphic.db.Book;
import org.json.simple.JSONValue;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class BooksApi extends Api {

    private static String URL = "/api/books";

    public BooksApi() {
        url = URL;
    }

    public static String makeUrlList(int page) {
        return URL + "/list/" + page;
    }

    @Override
    public String render(String params) throws IOException {
        UrlUtils.Params p = UrlUtils.parseParams(params);
        if (p.getString(0, "").equals("list")) {
            Integer page = p.getInt(1, 1);
            return list(page);
        }
        return "error";
    }

    private String list(int page) {
        ArrayList<Book> books = new ArrayList<Book>();

        books.add(new Book(page * 3 + 1, "Napoleon: A Life", 976));
        books.add(new Book(page * 3 + 2, "Big Driver", 198));
        books.add(new Book(page * 3 + 3, "Girl on a Wire", 386));

        ArrayList<Map> result = new ArrayList<Map>();
        for (Book book : books) {
            result.add(book.asMap());
        }
        return JSONValue.toJSONString(result);
    }
}
