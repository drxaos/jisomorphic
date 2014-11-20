package io.github.drxaos.jisomorphic.pages;

import io.github.drxaos.jisomorphic.config.Config;
import io.github.drxaos.jisomorphic.loading.Template;

import java.io.IOException;

abstract public class Page {

    protected PageContext context;

    public void init(PageContext context) {
        this.context = context;
    }

    protected String url = "/" + this.getClass().getName();

    public String getUrl() {
        return url;
    }

    abstract public Template render(String params) throws IOException;

    abstract public void animate(String params) throws IOException;


    /**
     * Find page by path
     *
     * @param location Current page path
     * @return Found page
     */
    public static Page findPage(String location) {
        for (Page page : Config.pages) {
            try {
                if (location.startsWith(page.getUrl() + "/") || location.equals(page.getUrl())) {
                    return page.getClass().newInstance();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
