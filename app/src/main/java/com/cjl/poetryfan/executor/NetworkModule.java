package com.cjl.poetryfan.executor;

import dagger.Module;
import dagger.Provides;

import javax.inject.Singleton;

/**
 * network, provide OkHttpClient, Executor, UIExecutor
 *
 * @author CJL
 * @since 2015-04-28
 */
@Module
public class NetworkModule {

    @Provides
    @Singleton
    Executor provideExecutor() {
        return new ThreadExecutor();
    }

    @Provides
    @Singleton
    UIExecutor provideUIThread() {
        return new UIExecutorImpl();
    }
}
