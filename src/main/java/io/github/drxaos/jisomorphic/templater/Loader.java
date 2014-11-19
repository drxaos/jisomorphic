package io.github.drxaos.jisomorphic.templater;

import java.io.IOException;

public interface Loader {
    public static interface ResourceCallback {
        void recv(String data, Template template);
    }

    void load(String path, Template template, ResourceCallback callback) throws IOException;

    void api(String path, Template template, ResourceCallback callback) throws IOException;
}
