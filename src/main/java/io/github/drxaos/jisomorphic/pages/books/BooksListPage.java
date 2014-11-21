package io.github.drxaos.jisomorphic.pages.books;

import io.github.drxaos.jisomorphic.api.persistent.BooksApi;
import io.github.drxaos.jisomorphic.loading.Loader;
import io.github.drxaos.jisomorphic.loading.Template;
import io.github.drxaos.jisomorphic.pages.Page;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.JSONValue;

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
        final String title = "Books List";
        Template template = new Template();
        context.loader.load("/templates/index.html", template, new Loader.ResourceCallback() {
            @Override
            public void recv(String data, Template template) {
                template.put("index.html", data);
            }
        });
        context.loader.api(BooksApi.makeUrlList(), template, new Loader.ResourceCallback() {
            @Override
            public void recv(String data, Template template) {
                template.put("list", JSONValue.parse(data));
            }
        });
        template.setPostProcessor(new Template.PostProcessor() {
            @Override
            public void process(Template template) {
                StringBuilder b = new StringBuilder();

                JSONArray list = (JSONArray) template.get("list");
                for (Object el : list) {
                    JSONObject book = (JSONObject) el;
                    b.append(book.get("id"))
                            .append(". ")
                            .append(book.get("name"))
                            .append(" (")
                            .append(book.get("pageCount"))
                            .append(")<br/>");
                }
                b.append("<a href=\"" + BooksListPage.makeUrl(0) + "\">First</a>");
                for (int i = 1; i < 5; i++) {
                    b.append("/ <a href=\"" + BooksListPage.makeUrl(i) + "\">" + i + "</a>");
                }
                template.setPage(template.getString("index.html").replace(
                        "%BODY%", b.toString()
                ).replace(
                        "%TITLE%", title
                ), title);
            }
        });
        return template;
    }

    @Override
    public void animate(String params) throws IOException {

    }
}
