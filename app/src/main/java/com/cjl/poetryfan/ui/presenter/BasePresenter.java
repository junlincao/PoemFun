package com.cjl.poetryfan.ui.presenter;

import com.cjl.poetryfan.ui.IView;

/**
 * Presenter 基本类
 *
 * @author CJL
 * @since 2015-04-28
 */
public abstract class BasePresenter <T extends IView> {
    private T mView;
    public void setView(T v){
        this.mView = v;
    }

    public T getView(){
        return mView;
    }
}
