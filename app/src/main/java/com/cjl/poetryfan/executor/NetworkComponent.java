package com.cjl.poetryfan.executor;

import com.cjl.poetryfan.executor.NetworkModule;
import com.cjl.poetryfan.executor.Executor;
import com.cjl.poetryfan.executor.UIExecutor;
import com.squareup.okhttp.OkHttpClient;

import javax.inject.Singleton;

import dagger.Component;

/**
 * handle network
 *
 * @author CJL
 * @since 2015-04-28
 */

@Singleton
@Component(modules = {NetworkModule.class})
public interface NetworkComponent {
    OkHttpClient getOkHttpClient();

    Executor getExecutor();

    UIExecutor getUIExecutor();
}