package io.github.drxaos.jisomorphic.templater;

import java.io.IOException;

public interface Loader {
    public static interface Callback {
        void recv(String data, Template template);
    }

    void load(String path, Template template, Callback callback) throws IOException;
}
