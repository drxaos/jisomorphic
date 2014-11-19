package io.github.drxaos.jisomorphic.pages;

import io.github.drxaos.jisomorphic.Dispatcher;
import io.github.drxaos.jisomorphic.templater.Loader;
import io.github.drxaos.jisomorphic.templater.Template;
import org.teavm.dom.browser.TimerHandler;
import org.teavm.dom.browser.Window;
import org.teavm.dom.html.HTMLDocument;
import org.teavm.jso.JS;

import java.io.IOException;

public class TestPage extends Page {

    @Override
    public boolean accepts(String url) {
        return url.startsWith("/test/");
    }

    @Override
    public Template render(String url) throws IOException {
        Template template = new Template();
        context.loader.load("/templates/index.html", template, new Loader.ResourceCallback() {
            @Override
            public void recv(String data, Template template) {
                template.put("index.html", data);
            }
        });
        context.loader.load("/templates/test.html", template, new Loader.ResourceCallback() {
            @Override
            public void recv(String data, Template template) {
                template.put("test.html", data);
            }
        });
        context.loader.api("/time/now", template, new Loader.ResourceCallback() {
            @Override
            public void recv(String data, Template template) {
                template.put("time", data);
            }
        });
        template.setPostProcessor(new Template.PostProcessor() {
            @Override
            public void process(Template template) {
                String title = (System.currentTimeMillis() % 2 == 0) ? "Some title" : "Hello World!";
                template.setPage(template.get("index.html")
                                .replace("%TITLE%", title)
                                .replace("%BODY%", template.get("test.html")
                                                .replace("%TIME%", template.get("time"))
                                ),
                        title
                );
            }
        });
        return template;
    }

    @Override
    public void animate(String url) throws IOException {
        final Window window = (Window) JS.getGlobal();
        HTMLDocument document = window.getDocument();
        window.setTimeout(new TimerHandler() {
            @Override
            public void onTimer() {
                if (context.pid != Dispatcher.getPid()) {
                    return;
                }
                Template template = new Template();
                try {
                    context.loader.api("/time/now", template, new Loader.ResourceCallback() {
                        @Override
                        public void recv(String data, Template template) {
                            Dispatcher.load("/test/new/page/opened/" + data);
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 5000);
    }
}
