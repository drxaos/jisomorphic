package io.github.drxaos.jisomorphic.api;

import io.github.drxaos.jisomorphic.Context;
import io.github.drxaos.jisomorphic.db.Database;
import io.github.drxaos.jisomorphic.loading.Loader;

public class ApiContext extends Context {
    public Database database;

    public ApiContext(Loader loader, Database database) {
        this.loader = loader;
        this.database = database;
    }
}
