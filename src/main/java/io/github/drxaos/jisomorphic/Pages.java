package io.github.drxaos.jisomorphic;

import io.github.drxaos.jisomorphic.pages.Page;
import io.github.drxaos.jisomorphic.pages.StaticResource;
import io.github.drxaos.jisomorphic.pages.TestPage;

public class Pages {

    static Page[] pages = {
            new TestPage(),
            new StaticResource()
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
