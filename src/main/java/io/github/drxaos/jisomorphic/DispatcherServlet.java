package io.github.drxaos.jisomorphic;

import io.github.drxaos.jisomorphic.config.Config;
import io.github.drxaos.jisomorphic.loading.ServletLoader;
import io.github.drxaos.jisomorphic.loading.Template;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DispatcherServlet extends HttpServlet {

    List<Resource> resources;

    Resource findPage(String url) {
        if (resources == null) {
            resources = new ArrayList<Resource>();
            for (Class resource : Config.RESOURCES) {
                try {
                    resources.add((Resource) resource.newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
        for (Resource resource : resources) {
            if (Params.checkUrl(url, resource.getUrlTemplate())) {
                return resource;
            }
        }
        return null;
    }


    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String method = request.getMethod();
        String url = request.getRequestURI();
        Resource page = findPage(url);
        Template template = new Template(new Context(new ServletLoader(getServletContext())));
        page.render(method,new Params(url, page.getUrlTemplate()),template);
        synchronized (template) {
            if(!template.isReady()){
                try {
                    template.wait(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
        response.getOutputStream().write(template.getResult());
    }

}
