package io.github.drxaos.jisomorphic.config;

import io.github.drxaos.jisomorphic.api.Api;
import io.github.drxaos.jisomorphic.api.persistent.BooksApi;
import io.github.drxaos.jisomorphic.api.simple.HelloApi;
import io.github.drxaos.jisomorphic.api.simple.TimeApi;
import io.github.drxaos.jisomorphic.api.simple.UidApi;
import io.github.drxaos.jisomorphic.pages.Page;
import io.github.drxaos.jisomorphic.pages.books.BooksListPage;
import io.github.drxaos.jisomorphic.pages.system.StaticResourcePage;
import io.github.drxaos.jisomorphic.pages.test.TestPage;

public class Config {

    public static Api[] apis = {
            new TimeApi(),
            new UidApi(),
            new HelloApi(),

            new BooksApi(),
    };

    public static Page[] pages = {
            new TestPage(),

            new BooksListPage(),

            new StaticResourcePage(),
    };

}
