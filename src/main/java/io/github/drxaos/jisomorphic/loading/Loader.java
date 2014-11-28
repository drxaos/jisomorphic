package io.github.drxaos.jisomorphic.loading;

import java.io.IOException;

public interface Loader {
    public static interface Callback<T> {
        void receive(T data, Template template);
    }

    void requestResource(String path, Template template, Callback<String> callback) throws IOException;

    //void requestResourceBinary(String path, Template template, Callback<byte[]> callback) throws IOException;

    void requestApi(String path, Template template, Callback<String> callback) throws IOException;

    //void requestApiBinary(String path, Template template, Callback<byte[]> callback) throws IOException;
}
