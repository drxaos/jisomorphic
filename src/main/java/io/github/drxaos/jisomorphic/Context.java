package io.github.drxaos.jisomorphic;

import io.github.drxaos.jisomorphic.loading.Loader;

public class Context {
    public Loader loader;

    public Context(Loader loader) {
        this.loader = loader;
    }

    public Loader getLoader() {
        return loader;
    }

}
