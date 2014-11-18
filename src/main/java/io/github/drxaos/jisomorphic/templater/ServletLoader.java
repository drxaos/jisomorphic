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

    public String load(String path) throws IOException {
        InputStream input = servletContext.getResourceAsStream(path);
        if (input == null) {
            return null;
        }
        return IOUtils.toString(input);
    }

}
