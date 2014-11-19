package io.github.drxaos.jisomorphic.templater;

import io.github.drxaos.jisomorphic.Apis;
import io.github.drxaos.jisomorphic.api.Api;
import io.github.drxaos.jisomorphic.api.ApiContext;
import io.github.drxaos.jisomorphic.db.DatabaseImpl;
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
    public void load(String path, Template template, ResourceCallback callback) throws IOException {
        InputStream input = servletContext.getResourceAsStream(path);
        callback.recv(input == null ? "" : IOUtils.toString(input), template);
    }

    @Override
    public void api(String path, final Template template, final ResourceCallback callback) throws IOException {
        Api api = Apis.findApi(path);
        api.init(new ApiContext(new ServletLoader(servletContext), new DatabaseImpl()));
        String result = api.render(path);
        callback.recv(result == null ? "" : result, template);
    }
}
