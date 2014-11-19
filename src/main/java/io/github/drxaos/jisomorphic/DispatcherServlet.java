package io.github.drxaos.jisomorphic;

import io.github.drxaos.jisomorphic.api.Api;
import io.github.drxaos.jisomorphic.api.ApiContext;
import io.github.drxaos.jisomorphic.db.DatabaseImpl;
import io.github.drxaos.jisomorphic.pages.Page;
import io.github.drxaos.jisomorphic.pages.PageContext;
import io.github.drxaos.jisomorphic.templater.ServletLoader;
import io.github.drxaos.jisomorphic.templater.Template;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String location = request.getRequestURI();
        Page page = Pages.findPage(location);
        page.init(new PageContext(new ServletLoader(getServletContext()), 0));
        Template rendered = page.render(location);
        rendered.postProcess();
        response.getWriter().write(rendered.getPage());
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String location = request.getRequestURI();
        Api api = Apis.findApi(location);
        api.init(new ApiContext(new ServletLoader(getServletContext()), new DatabaseImpl()));
        String rendered = api.render(location);
        response.getWriter().write(rendered);
    }

}
