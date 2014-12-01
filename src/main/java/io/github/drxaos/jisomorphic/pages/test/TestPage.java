package io.github.drxaos.jisomorphic.pages.test;

import io.github.drxaos.jisomorphic.Dispatcher;
import io.github.drxaos.jisomorphic.Params;
import io.github.drxaos.jisomorphic.api.simple.HelloApi;
import io.github.drxaos.jisomorphic.api.simple.TimeApi;
import io.github.drxaos.jisomorphic.loading.Loader;
import io.github.drxaos.jisomorphic.loading.Template;
import io.github.drxaos.jisomorphic.Resource;
import org.teavm.dom.browser.TimerHandler;
import org.teavm.dom.browser.Window;
import org.teavm.dom.events.Event;
import org.teavm.dom.events.EventListener;
import org.teavm.dom.html.HTMLButtonElement;
import org.teavm.dom.html.HTMLDocument;
import org.teavm.dom.html.HTMLElement;
import org.teavm.jso.JS;

import java.io.IOException;

public class TestPage extends Resource {

    public TestPage() {
        super("/test/{param1}/{param2}/{param3}");
    }

    @Override
    public void render(String method, final Params params, Template template) throws IOException {
        template.resource("index.html", "/static/templates/index.html");
        template.resource("test.html", "/static/templates/test.html");
        template.resource("time", new Params(TimeApi.class).set(TimeApi.PARAM_WHEN, "now").getUrl());
        template.postProcessor(new Template.PostProcessor() {
            @Override
            public void process(Template template) {
                String title = (System.currentTimeMillis() % 2 == 0) ? "Some title" : "Hello World!";
                String time = template.getString("time");
                String test = template.getString("test.html").replace("%TIME%", time);
                String page = template.getString("index.html").replace("%TITLE%", title).replace("%BODY", test);
                template.setPage(page);
            }
        });
    }

    @Override
    public void animate(String params) throws IOException {
        final Window window = (Window) JS.getGlobal();
        final HTMLDocument document = window.getDocument();
        window.setTimeout(new TimerHandler() {
            @Override
            public void onTimer() {
//                if (context.pid != Dispatcher.getPid()) {
//                    return;
//                }
                document.getElementById("loading").setAttribute("style", "");
                Template template = new Template();
                try {
                    Dispatcher.render(new Params(TimeApi.class).set(TimeApi.PARAM_WHEN,"now").getUrl(),new Template.PostProcessor(){
                        @Override
                        public void process(Template template) {
                            Dispatcher.load(new Params(TestPage.class).
                                    set(TestPage.PARAM_PARAM1,"some").
                                    set(TestPage.PARAM_PARAM2, "page").
                                    set(TestPage.PARAM_PARAM3, template.getString("", "unknown")).
                                    getUrl());
                        }
                    });
                    context.loader.requestApi(TimeApi.makeUrlNow(), template, new Loader.Callback() {
                        @Override
                        public void receive(String data, Template template) {
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
