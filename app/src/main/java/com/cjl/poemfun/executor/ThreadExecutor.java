package com.cjl.poemfun.executor;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

/**
 * 异步任务线程池
 *
 * @author CJL
 * @since 2015-04-13
 */
public class ThreadExecutor implements Executor {
    private static final int CORE_POOL_SIZE = 3;
    private static final int MAX_POOL_SIZE = 5;
    private static final int KEEP_ALIVE_TIME = 30;
    private static final BlockingQueue<Runnable> WORK_QUEUE = new LinkedBlockingQueue<Runnable>();

    private ThreadPoolExecutor threadPoolExecutor;

    public ThreadExecutor() {
        threadPoolExecutor = new ThreadPoolExecutor(CORE_POOL_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME, TimeUnit.SECONDS,
                WORK_QUEUE);
    }

    @Override
    public void run(Interactor interactor) {
        if (interactor == null) {
            throw new IllegalArgumentException("interactor to  can't be null");
        }
        threadPoolExecutor.submit(interactor);
    }
}
