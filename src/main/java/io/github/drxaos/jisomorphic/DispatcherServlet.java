package io.github.drxaos.jisomorphic;

import io.github.drxaos.jisomorphic.api.Api;
import io.github.drxaos.jisomorphic.api.ApiContext;
import io.github.drxaos.jisomorphic.db.Database;
import io.github.drxaos.jisomorphic.loading.ServletLoader;
import io.github.drxaos.jisomorphic.loading.Template;
import io.github.drxaos.jisomorphic.pages.Page;
import io.github.drxaos.jisomorphic.pages.PageContext;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        Page page = Page.findPage(url);
        page.init(new PageContext(new ServletLoader(getServletContext()), 0));
        Template rendered = page.render((url.length() > page.getUrl().length()) ? url.substring(page.getUrl().length() + 1) : "");
        rendered.postProcess();
        response.getWriter().write(rendered.getPage());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String url = request.getRequestURI();
        Api api = Api.findApi(url);
        api.init(new ApiContext(new ServletLoader(getServletContext()), new Database()));
        String rendered = api.render((url.length() > api.getUrl().length()) ? url.substring(api.getUrl().length() + 1) : "");
        response.getWriter().write(rendered);
    }

}
