package io.github.drxaos.jisomorphic.api;

import io.github.drxaos.jisomorphic.db.Database;
import io.github.drxaos.jisomorphic.templater.Loader;

public class ApiContext {
    public Loader loader;
    public Database database;

    public ApiContext(Loader loader, Database database) {
        this.loader = loader;
        this.database = database;
    }
}
