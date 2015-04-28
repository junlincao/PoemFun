package com.cjl.poetryfan.executor;

import android.os.Handler;
import android.os.Looper;

/**
 * UIExecutor 实现
 *
 * @author CJL
 * @since 2015-04-13
 */
public class UIExecutorImpl implements UIExecutor {
    private Handler mHandler;

    public UIExecutorImpl() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable r) {
        mHandler.post(r);
    }
}
