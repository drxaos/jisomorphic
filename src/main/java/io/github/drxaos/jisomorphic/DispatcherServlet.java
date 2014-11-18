package io.github.drxaos.jisomorphic;

import io.github.drxaos.jisomorphic.context.Context;
import io.github.drxaos.jisomorphic.pages.Page;
import io.github.drxaos.jisomorphic.pages.Static;
import io.github.drxaos.jisomorphic.pages.Test;
import io.github.drxaos.jisomorphic.templater.ServletLoader;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DispatcherServlet extends HttpServlet {

    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Page[] pages = {new Test(), new Static()};

        String location = request.getRequestURI();
        for (Page page : pages) {
            try {
                if (page.accepts(location)) {
                    Context ctx = new Context();
                    ctx.loader = new ServletLoader(getServletContext());
                    page.init(ctx);
                    String rendered = page.render(location);
                    response.getWriter().write(rendered);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }
}
