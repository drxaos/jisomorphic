package io.github.drxaos.jisomorphic.loading;

import java.io.IOException;

public interface Loader {
    public static interface Callback {
        void receive(String data, Template template);
    }

    void requestResource(String path, Template template, Callback callback) throws IOException;

    void requestApi(String path, Template template, Callback callback) throws IOException;
}
