package io.github.drxaos.jisomorphic.pages.test;

import io.github.drxaos.jisomorphic.Dispatcher;
import io.github.drxaos.jisomorphic.api.simple.HelloApi;
import io.github.drxaos.jisomorphic.api.simple.TimeApi;
import io.github.drxaos.jisomorphic.loading.Loader;
import io.github.drxaos.jisomorphic.loading.Template;
import io.github.drxaos.jisomorphic.pages.Page;
import org.teavm.dom.browser.TimerHandler;
import org.teavm.dom.browser.Window;
import org.teavm.dom.events.Event;
import org.teavm.dom.events.EventListener;
import org.teavm.dom.html.HTMLButtonElement;
import org.teavm.dom.html.HTMLDocument;
import org.teavm.dom.html.HTMLElement;
import org.teavm.jso.JS;

import java.io.IOException;

public class TestPage extends Page {

    private static String URL = "/test";

    public TestPage() {
        url = URL;
    }

    public static String makeUrl(String param1, String param2, String param3) {
        // example of url construction
        return URL + "/" + param1 + "/" + param2 + "/" + param3;
    }

    @Override
    public Template render(final String params) throws IOException {
        Template template = new Template();
        context.loader.requestResource("/templates/index.html", template, new Loader.Callback() {
            @Override
            public void receive(String data, Template template) {
                template.put("index.html", data);
            }
        });
        context.loader.requestResource("/templates/test.html", template, new Loader.Callback() {
            @Override
            public void receive(String data, Template template) {
                template.put("test.html", data);
            }
        });
        context.loader.requestApi(TimeApi.makeUrlNow(), template, new Loader.Callback() {
            @Override
            public void receive(String data, Template template) {
                template.put("time", data + " (" + params + ")");
            }
        });
        template.setPostProcessor(new Template.PostProcessor() {
            @Override
            public void process(Template template) {
                String title = (System.currentTimeMillis() % 2 == 0) ? "Some title" : "Hello World!";
                template.setPage(template.getString("index.html").replace(
                                "%TITLE%", title
                        ).replace(
                                "%BODY%", template.getString("test.html")
                                        .replace(
                                                "%TIME%", template.getString("time")
                                        )
                        ),
                        title
                );
            }
        });
        return template;
    }

    @Override
    public void animate(String params) throws IOException {
        final Window window = (Window) JS.getGlobal();
        final HTMLDocument document = window.getDocument();
        window.setTimeout(new TimerHandler() {
            @Override
            public void onTimer() {
                if (context.pid != Dispatcher.getPid()) {
                    return;
                }
                document.getElementById("loading").setAttribute("style", "");
                Template template = new Template();
                try {
                    context.loader.requestApi(TimeApi.makeUrlNow(), template, new Loader.Callback() {
                        @Override
                        public void receive(String data, Template template) {
                            Dispatcher.load(makeUrl("some", "page", data));
                        }
                    });
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }, 5000);

        final HTMLButtonElement helloButton = (HTMLButtonElement) document.getElementById("hello-button");
        helloButton.addEventListener("click", new EventListener() {
            @Override
            public void handleEvent(Event evt) {
                try {
                    sayHello();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void sayHello() throws IOException {
        final Window window = (Window) JS.getGlobal();
        final HTMLDocument document = window.getDocument();
        final HTMLButtonElement helloButton = (HTMLButtonElement) document.getElementById("hello-button");
        final HTMLElement responsePanel = document.getElementById("response-panel");

        helloButton.setDisabled(true);
        Template template = new Template();
        context.loader.requestApi(HelloApi.makeUrl(), template, new Loader.Callback() {
            @Override
            public void receive(String data, Template template) {
                template.put("data", data);
            }
        });
        template.setPostProcessor(new Template.PostProcessor() {
            @Override
            public void process(Template template) {
                HTMLElement responseElem = document.createElement("div");
                responseElem.appendChild(document.createTextNode(template.getString("data")));
                responsePanel.appendChild(responseElem);
                helloButton.setDisabled(false);
            }
        });
    }
}
