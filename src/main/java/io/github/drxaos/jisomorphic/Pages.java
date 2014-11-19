package io.github.drxaos.jisomorphic;

import io.github.drxaos.jisomorphic.pages.Page;
import io.github.drxaos.jisomorphic.pages.Static;
import io.github.drxaos.jisomorphic.pages.Test;

public class Pages {

    static Page[] pages = {
            new Test(),
            new Static()
    };

    /**
     * Find page by path
     *
     * @param location Current page path
     * @return Found page
     */
    public static Page findPage(String location) {
        for (Page page : pages) {
            try {
                if (page.accepts(location)) {
                    return page.getClass().newInstance();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
