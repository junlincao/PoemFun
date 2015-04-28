package com.cjl.poetryfan.executor;

import com.cjl.poetryfan.executor.Executor;
import com.cjl.poetryfan.executor.ThreadExecutor;
import com.cjl.poetryfan.executor.UIExecutor;
import com.cjl.poetryfan.executor.UIExecutorImpl;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

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
    OkHttpClient provideOkHttpClient() {
        return new OkHttpClient();
    }

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
