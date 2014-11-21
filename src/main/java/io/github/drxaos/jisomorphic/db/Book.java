package io.github.drxaos.jisomorphic.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Book {
    long id;
    String name;
    int pageCount;

    Book(long id, String name, int pageCount) {
        this.id = id;
        this.name = name;
        this.pageCount = pageCount;
    }

    public Book(String name, int pageCount) {
        this.name = name;
        this.pageCount = pageCount;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public static List<Book> findBooks(Database database) {
        ArrayList<Book> books = new ArrayList<Book>();

//        books.add(new Book(1, "Napoleon: A Life", 976));
//        books.add(new Book(2, "Big Driver", 198));
//        books.add(new Book(3, "Girl on a Wire", 386));
        books.add(new Book(3, "A", 4));

        return books;
    }

    public Map<String, Object> asMap() {
        HashMap<String, Object> result = new HashMap<String, Object>();
        result.put("id", id);
        result.put("name", name);
        result.put("pageCount", pageCount);
        return result;
    }
}
