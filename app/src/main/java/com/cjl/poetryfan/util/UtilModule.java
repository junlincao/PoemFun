package com.cjl.poetryfan.util;

import android.content.Context;

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
@Module
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
