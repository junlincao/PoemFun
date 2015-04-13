package com.cjl.poemfun.executor;

import android.os.Handler;
import android.os.Looper;

import javax.inject.Inject;

/**
 * UIThread 实现
 *
 * @author CJL
 * @since 2015-04-13
 */
public class UIThreadImpl implements UIThread {
    private Handler mHandler;

    @Inject
    UIThreadImpl() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable r) {
        mHandler.post(r);
    }
}
