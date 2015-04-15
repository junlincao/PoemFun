package com.cjl.poemfun.util;

import android.content.Context;

import com.cjl.poemfun.di.ContextModule;
import com.squareup.otto.Bus;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * UtilModule
 *
 * @author CJL
 * @since 2015-04-13
 */
@Module(library = true, includes = {ContextModule.class})
public class UtilModule {

    @Provides
    @Singleton
    Bus provideEventBus() {
        return new Bus();
    }

    @Provides
    @Singleton
    PreferenceUtil providePreference(Context ctx) {
        return new PreferenceUtil(ctx);
    }

}
