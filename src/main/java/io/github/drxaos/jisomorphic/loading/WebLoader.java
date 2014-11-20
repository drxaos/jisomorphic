package io.github.drxaos.jisomorphic.loading;

import org.teavm.dom.ajax.ReadyStateChangeHandler;
import org.teavm.dom.ajax.XMLHttpRequest;
import org.teavm.dom.browser.Window;
import org.teavm.jso.JS;

import java.io.IOException;

public class WebLoader implements Loader {

    public static interface LoaderCallback {
        void ready(Template template);
    }

    LoaderCallback loaderCallback;

    public LoaderCallback getLoaderCallback() {
        return loaderCallback;
    }

    public void setLoaderCallback(LoaderCallback loaderCallback) {
        this.loaderCallback = loaderCallback;
    }

    private static Window window = (Window) JS.getGlobal();

    private void request(String path, final Template template, final ResourceCallback callback, boolean post) throws IOException {
        final XMLHttpRequest xhr = window.createXMLHttpRequest();
        xhr.setOnReadyStateChange(new ReadyStateChangeHandler() {
            @Override
            public void stateChanged() {
                if (xhr.getReadyState() == XMLHttpRequest.DONE) {
                    String data = xhr.getResponseText();
                    // TODO cache data if not post
                    callback.recv(data, template);
                    template.removeCallback(callback);
                    if (template.isReady()) {
                        template.postProcess();
                        if (loaderCallback != null) {
                            loaderCallback.ready(template);
                            loaderCallback = null;
                        }
                    }
                }
            }
        });
        xhr.open(post ? "POST" : "GET", path);
        xhr.send();
        template.addCallback(callback);
    }

    @Override
    public void load(String path, final Template template, final ResourceCallback callback) throws IOException {
        request(path, template, callback, false);

    }

    @Override
    public void api(String path, final Template template, final ResourceCallback callback) throws IOException {
        request(path, template, callback, true);
    }
}
