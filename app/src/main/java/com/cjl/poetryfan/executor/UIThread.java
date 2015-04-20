package com.cjl.poetryfan.executor;

/**
 * 提交任务到UI线程执行
 *
 * @author CJL
 * @since 2015-04-13
 */
public interface UIThread {
    void post(Runnable r);
}
