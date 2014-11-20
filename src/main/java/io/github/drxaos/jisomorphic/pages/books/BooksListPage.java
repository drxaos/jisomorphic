package io.github.drxaos.jisomorphic.pages.books;

import io.github.drxaos.jisomorphic.loading.Template;
import io.github.drxaos.jisomorphic.pages.Page;

import java.io.IOException;

public class BooksListPage extends Page {

    private static String URL = "/books/list";

    public BooksListPage() {
        url = URL;
    }

    public static String makeUrl(int page) {
        return URL + "/" + page;
    }

    @Override
    public Template render(String params) throws IOException {
        // TODO
        return null;
    }

    @Override
    public void animate(String params) throws IOException {

    }
}
