package io.github.drxaos.jisomorphic.config;

import io.github.drxaos.jisomorphic.api.persistent.BooksApi;
import io.github.drxaos.jisomorphic.api.simple.HelloApi;
import io.github.drxaos.jisomorphic.api.simple.TimeApi;
import io.github.drxaos.jisomorphic.api.simple.UidApi;
import io.github.drxaos.jisomorphic.pages.books.BooksListPage;
import io.github.drxaos.jisomorphic.pages.system.StaticResourcePage;
import io.github.drxaos.jisomorphic.pages.test.TestPage;

public class Config {

    public final static Class[] RESOURCES = {

            // Tests
            TimeApi.class,
            UidApi.class,
            HelloApi.class,
            TestPage.class,

            // Books
            BooksApi.class,
            BooksListPage.class,

            // System resources
            StaticResourcePage.class,
    };

}
