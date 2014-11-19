package io.github.drxaos.jisomorphic;

import io.github.drxaos.jisomorphic.api.Api;
import io.github.drxaos.jisomorphic.api.TimeApi;

public class Apis {

    static Api[] apis = {
            new TimeApi()
    };

    /**
     * Find api by path
     *
     * @param location Current page path
     * @return Found page
     */
    public static Api findApi(String location) {
        for (Api api : apis) {
            try {
                if (api.accepts(location)) {
                    return api.getClass().newInstance();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
