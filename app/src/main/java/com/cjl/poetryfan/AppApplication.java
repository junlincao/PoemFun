package com.cjl.poetryfan;

import android.app.Application;

import com.cjl.poetryfan.di.AppComponent;
import com.cjl.poetryfan.di.component.DaggerAppComponent;
import com.cjl.poetryfan.di.component.DaggerNetworkComponent;
import com.cjl.poetryfan.di.ContextModule;
import com.facebook.drawee.backends.pipeline.Fresco;

/**
 * Application
 *
 * @author CJL
 * @since 2015-04-13
 */
public class AppApplication extends Application {

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();

        mAppComponent = DaggerAppComponent.builder()
                .contextModule(new ContextModule(this))
                .networkComponent(DaggerNetworkComponent.create())
                .build();

        Fresco.initialize(this, mAppComponent.getFrescoConfig());
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }
}
