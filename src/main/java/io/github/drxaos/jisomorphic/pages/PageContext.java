package io.github.drxaos.jisomorphic.pages;

import io.github.drxaos.jisomorphic.templater.Loader;

public class PageContext {
    public Loader loader;
    public long pid;

    public PageContext(Loader loader, long pid) {
        this.loader = loader;
        this.pid = pid;
    }

}
