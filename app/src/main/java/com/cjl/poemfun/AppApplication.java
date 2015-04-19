package com.cjl.poemfun;

import android.app.Application;
import android.util.Log;

import com.cjl.poemfun.di.ContextModule;
import com.cjl.poemfun.domain.DomainModule;
import com.cjl.poemfun.executor.ExecutorModul;
import com.cjl.poemfun.ui.UIModule;
import com.cjl.poemfun.util.UtilModule;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.util.List;

import javax.inject.Inject;

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
    @Inject
    ImagePipelineConfig mImagePipelineConfig;

    @Override
    public void onCreate() {
        super.onCreate();

        Object[] modules = new Object[]{
                new ContextModule(this),
                new DomainModule(),
                new ExecutorModul(),
                new UtilModule(),
                new UIModule()
        };

        objectGraph = ObjectGraph.create(modules);
        objectGraph.injectStatics();
        objectGraph.inject(this);

        Fresco.initialize(this, mImagePipelineConfig);
    }

    /**
     * Inject every dependency declared in the object with the @Inject
     * annotation if the dependency has been already declared in a module and
     * already initialized by Dagger.
     *
     * @param object object to inject.
     */
    public void inject(Object object) {
        objectGraph.inject(object);
    }

    /**
     * Extend the dependency container graph will new dependencies provided by
     * the modules passed as arguments.
     *
     * @param modules used to populate the dependency container.
     */
    public ObjectGraph plus(List<Object> modules) {
        if (modules == null) {
            Log.e("AppApplication", "plus null modules!");
            return objectGraph;
        }
        return objectGraph.plus(modules.toArray());
    }
}
