package com.cjl.poemfun.di;

import android.content.Context;
import android.view.LayoutInflater;

import com.cjl.poemfun.AppApplication;
import com.cjl.poemfun.domain.PoemFunModule;
import com.cjl.poemfun.executor.ExecutorModul;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module
 * <p>
 * Module for Application
 *
 * @author CJL
 * @since 2015-04-13
 */
@Module(includes = {ExecutorModul.class, PoemFunModule.class},
        injects = {AppApplication.class},
        library = true)
public class RootModule {
    private Context mContext;

    public RootModule(Context ctx) {
        mContext = ctx;
    }

    @Provides
    Context provideContext() {
        return mContext;
    }

    @Provides
    LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(mContext);
    }

    @Provides
    @Singleton
    Bus provideEventBus() {
        return new Bus();
    }
}
