package io.github.drxaos.jisomorphic.pages;

import io.github.drxaos.jisomorphic.Context;
import io.github.drxaos.jisomorphic.loading.Loader;

public class PageContext extends Context {

    /**
     * UID for callbacks validation
     */
    public long pid;

    public PageContext(Loader loader, long pid) {
        this.loader = loader;
        this.pid = pid;
    }

}
