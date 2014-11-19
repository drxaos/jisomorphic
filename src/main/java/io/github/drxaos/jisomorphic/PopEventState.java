package io.github.drxaos.jisomorphic;

import org.teavm.jso.JSObject;
import org.teavm.jso.JSProperty;

public interface PopEventState extends JSObject {
    @JSProperty
    String getHtml();

    @JSProperty
    String getUrl();

    @JSProperty
    String getTitle();
}
