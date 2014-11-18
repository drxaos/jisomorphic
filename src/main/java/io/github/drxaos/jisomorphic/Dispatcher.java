package io.github.drxaos.jisomorphic;

import io.github.drxaos.jisomorphic.context.Context;
import io.github.drxaos.jisomorphic.pages.Page;
import io.github.drxaos.jisomorphic.pages.Static;
import io.github.drxaos.jisomorphic.pages.Test;
import io.github.drxaos.jisomorphic.templater.WebLoader;
import net.java.html.js.JavaScriptBody;
import org.teavm.dom.browser.TimerHandler;
import org.teavm.dom.browser.Window;
import org.teavm.dom.core.Element;
import org.teavm.dom.core.NodeList;
import org.teavm.dom.html.HTMLDocument;
import org.teavm.jso.JS;

import javax.servlet.http.HttpServlet;

public class Dispatcher extends HttpServlet {

    private static Window window = (Window) JS.getGlobal();
    private static HTMLDocument document = window.getDocument();

    public static void main(String[] args) {
        NodeList<Element> a = document.getElementsByTagName("a");
        // TODO onClick -> load(href);

        addRelocationTrigger();

        window.setTimeout(new TimerHandler() {
            @Override
            public void onTimer() {
                load("/test/new/page/opened");
            }
        }, 5000);
    }

    public static void load(String url) {
        Page[] pages = {new Test(), new Static()};

        String location = pathname();
        for (Page page : pages) {
            try {
                if (page.accepts(location)) {
                    Context ctx = new Context();
                    ctx.loader = new WebLoader();
                    page.init(ctx);
                    String rendered = page.render(location);
                    push(url, rendered);
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


    }

    @JavaScriptBody(args = {"url", "content"}, body =
            ""
                    + "window.history.pushState({\"data\":\"smth\"}, \"Title1\", url);"
                    + "document.open();document.write(content);document.close();"
    )
    private static native void push(String location, String content);

    @JavaScriptBody(args = {}, body =
            ""
                    + "var h;"
                    + "if (!!window && !!window.location && !!window.location.pathname)\n"
                    + "  h = window.location.pathname;\n"
                    + "else "
                    + "  h = null;"
                    + "return h;\n"
    )
    private static native String pathname();

    // TODO
    @JavaScriptBody(args = {"callback"}, body = "" +
            "window.onpopstate = function(e){\n" +
            "    if(e.state){\n" +
            "        document.getElementById(\"content\").innerHTML = e.state.html;\n" +
            "        document.title = e.state.pageTitle;\n" +
            "    }\n" +
            "};" +
            "return callback." +
            "@org.teavm.html4j.test.B::bar(" +
            "Lorg/teavm/html4j/test/A;)(_global_)", javacall = true)
    private static native String addRelocationTrigger();

}
