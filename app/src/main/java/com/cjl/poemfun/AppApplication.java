package com.cjl.poemfun;

import android.app.Application;

import com.cjl.poemfun.di.RootModule;

import java.util.List;

import dagger.ObjectGraph;

/**
 * Application
 * <p/>
 * 初始化Dagger
 *
 * @author CJL
 * @since 2015-04-13
 */
public class AppApplication extends Application {
    ObjectGraph objectGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        objectGraph = ObjectGraph.create(new RootModule(this));
        objectGraph.inject(this);
        objectGraph.injectStatics();
    }

    /**
     * Inject every dependency declared in the object with the @Inject annotation if the dependency
     * has been already declared in a module and already initialized by Dagger.
     *
     * @param object object to inject.
     */
    public void inject(Object object) {
        objectGraph.inject(object);
    }

    /**
     * Extend the dependency container graph will new dependencies provided by the modules passed as
     * arguments.
     *
     * @param modules used to populate the dependency container.
     */
    public ObjectGraph plus(List<Object> modules) {
        if (modules == null) {
            throw new IllegalArgumentException(
                    "You can't plus a null module, review your getModules() implementation");
        }
        return objectGraph.plus(modules.toArray());
    }
}
