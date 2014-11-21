package io.github.drxaos.jisomorphic.loading;

import io.github.drxaos.jisomorphic.api.Api;
import io.github.drxaos.jisomorphic.api.ApiContext;
import io.github.drxaos.jisomorphic.db.Database;
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
    public void requestResource(String path, Template template, Callback callback) throws IOException {
        InputStream input = servletContext.getResourceAsStream(path);
        callback.receive(input == null ? "" : IOUtils.toString(input), template);
    }

    @Override
    public void requestApi(String path, final Template template, final Callback callback) throws IOException {
        Api api = Api.findApi(path);
        api.init(new ApiContext(new ServletLoader(servletContext), new Database()));
        String result = api.render((path.length() > api.getUrl().length()) ? path.substring(api.getUrl().length() + 1) : "");
        callback.receive(result == null ? "" : result, template);
    }
}
