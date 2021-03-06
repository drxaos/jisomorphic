package io.github.drxaos.jisomorphic;

import io.github.drxaos.jisomorphic.loading.Template;
import io.github.drxaos.jisomorphic.loading.WebLoader;
import io.github.drxaos.jisomorphic.pages.PageContext;
import net.java.html.js.JavaScriptBody;
import org.teavm.dom.browser.Window;
import org.teavm.dom.core.Element;
import org.teavm.dom.core.NodeList;
import org.teavm.dom.events.Event;
import org.teavm.dom.events.EventListener;
import org.teavm.dom.html.HTMLDocument;
import org.teavm.dom.html.HTMLLinkElement;
import org.teavm.jso.JS;

public class Dispatcher {

    private static long pid = System.currentTimeMillis();

    private static Window window = (Window) JS.getGlobal();
    private static HTMLDocument document = window.getDocument();

    public static long getPid() {
        return pid;
    }

    /**
     * Page behavior after load
     */
    public static void main(String[] args) {
        final String location = pathname();
        addRelocationTrigger();

        NodeList<Element> a = document.getElementsByTagName("a");
        for (int i = 0; i < a.getLength(); i++) {
            final HTMLLinkElement link = (HTMLLinkElement) a.item(i);
            final String href = link.getAttribute("href");
            if (href == null || href.isEmpty()) {
                continue;
            }
            link.addEventListener("click", new EventListener() {
                @Override
                public void handleEvent(Event evt) {
                    evt.preventDefault();
                    Dispatcher.load(href);
                }
            });
        }

        try {
            Resource page = Resource.findPage(location);
            WebLoader webLoader = new WebLoader();
            page.init(new PageContext(webLoader, ++pid));
            page.animate(location);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Load page when user clicks a link and push new url to history
     *
     * @param url link href
     */
    public static void load(final String url) {
        try {
            WebLoader webLoader = new WebLoader();
            webLoader.setLoaderCallback(new WebLoader.LoaderCallback() {
                @Override
                public void ready(Template template) {
                    if (pid != Dispatcher.getPid()) {
                        return;
                    }
                    loaded(url, new String(template.getResult()));
                }
            });
            Resource page = Resource.findPage(url);
            if (page == null) {
                System.out.println("Page not found: " + url);
                return;
            }
            page.init(new PageContext(webLoader, pid));
            page.render((url.length() > page.getUrl().length()) ? url.substring(page.getUrl().length() + 1) : "").addPostProcessor();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void loaded(String url, String rendered) {
        try {
            push(url, rendered);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void onPopState(String url, String title, String html) {
        load(url);
    }

    @JavaScriptBody(args = {"url", "content"}, body =
            ""
                    + "document.open();document.write(content);document.close();"
                    + "window.history.pushState({\"url\":url, \"title\":document.title, \"html\":content}, title, url);"
    )
    private static native void push(String location, String content);

    /**
     * @return Current page path
     */
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

    /**
     * Handle browser back button
     */
    @JavaScriptBody(args = {}, body = ""
            + "window.onpopstate = function(e){\n"
            + "    if(e.state){\n"
            + "        @io.github.drxaos.jisomorphic.Dispatcher::onPopState(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;)(e.state.url,e.state.title,e.state.html);\n"
            + "    }\n"
            + "};", javacall = true)
    private static native void addRelocationTrigger();

}
