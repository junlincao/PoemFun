package com.cjl.poetryfan.executor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module, 提供Executor和UIThread
 *
 * @author CJL
 * @since 2015-04-13
 */
@Module(library = true)
public class ExecutorModul {

    @Provides
    @Singleton
    Executor provideExecutor() {
        return new ThreadExecutor();
    }

    @Provides
    @Singleton
    UIThread provideUIThread() {
        return new UIThreadImpl();
    }
}
