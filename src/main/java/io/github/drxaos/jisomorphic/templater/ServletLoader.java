package io.github.drxaos.jisomorphic.templater;

import org.apache.commons.io.IOUtils;

import javax.servlet.ServletContext;
import java.io.IOException;
import java.io.InputStream;

public class ServletLoader implements Loader {

    ServletContext servletContext;

    public ServletLoader(ServletContext servletContext) {
        this.servletContext = servletContext;
    }

    @Override
    public void load(String path, Template template, Callback callback) throws IOException {
        InputStream input = servletContext.getResourceAsStream(path);
        callback.recv(input == null ? "" : IOUtils.toString(input), template);
    }
}
