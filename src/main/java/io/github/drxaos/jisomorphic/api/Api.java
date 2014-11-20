package io.github.drxaos.jisomorphic.api;

import io.github.drxaos.jisomorphic.config.Config;

import java.io.IOException;

abstract public class Api {

    protected ApiContext context;

    public void init(ApiContext context) {
        this.context = context;
    }

    protected String url = "/" + this.getClass().getName();

    public String getUrl() {
        return url;
    }

    abstract public String render(String url) throws IOException;


    /**
     * Find api by path
     *
     * @param location Current page path
     * @return Found page
     */
    public static Api findApi(String location) {
        for (Api api : Config.apis) {
            try {
                if (location.startsWith(api.getUrl() + "/") || location.equals(api.getUrl())) {
                    return api.getClass().newInstance();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
