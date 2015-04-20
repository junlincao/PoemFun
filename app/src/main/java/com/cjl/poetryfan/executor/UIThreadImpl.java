package com.cjl.poetryfan.executor;

import android.os.Handler;
import android.os.Looper;

/**
 * UIThread 实现
 *
 * @author CJL
 * @since 2015-04-13
 */
public class UIThreadImpl implements UIThread {
    private Handler mHandler;

    UIThreadImpl() {
        mHandler = new Handler(Looper.getMainLooper());
    }

    @Override
    public void post(Runnable r) {
        mHandler.post(r);
    }
}
