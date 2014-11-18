package io.github.drxaos.jisomorphic.templater;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;

public class WebLoader implements Loader {

    public String load(String path) throws IOException {
        return "aaa";
    }

}
