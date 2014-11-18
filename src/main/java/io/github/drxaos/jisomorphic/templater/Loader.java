package io.github.drxaos.jisomorphic.templater;

import java.io.IOException;

public interface Loader {
    public String load(String path) throws IOException;
}
