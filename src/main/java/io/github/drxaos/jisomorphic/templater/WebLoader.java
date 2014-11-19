package io.github.drxaos.jisomorphic.templater;

import io.github.drxaos.jisomorphic.Dispatcher;
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

    @Override
    public void load(String path, final Template template, final Callback callback) throws IOException {
        final long pid = Dispatcher.getPid();
        final XMLHttpRequest xhr = window.createXMLHttpRequest();
        xhr.setOnReadyStateChange(new ReadyStateChangeHandler() {
            @Override
            public void stateChanged() {
                if (xhr.getReadyState() == XMLHttpRequest.DONE) {
                    String data = xhr.getResponseText();
                    // TODO cache data
                    callback.recv(data, template);
                    template.removeCallback(callback);
                    if (template.isReady()) {
                        template.postProcess();
                        loaderCallback.ready(template);
                    }
                }
            }
        });
        xhr.open("GET", path);
        xhr.send();
        template.addCallback(callback);
    }
}
