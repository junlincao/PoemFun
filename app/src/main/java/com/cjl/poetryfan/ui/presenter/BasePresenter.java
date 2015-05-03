package com.cjl.poetryfan.ui.presenter;

import com.cjl.poetryfan.di.AppComponent;
import com.cjl.poetryfan.ui.IView;
import com.cjl.poetryfan.ui.UIComponent;

/**
 * Presenter 基本类
 *
 * @author CJL
 * @since 2015-04-28
 */
public abstract class BasePresenter<T extends IView> {
    private T mView;
    private AppComponent mAppComponent;
    private UIComponent mUIComponent;

    protected String tag = getClass().getSimpleName();

    public void setView(T v) {
        this.mView = v;

        if (v == null) {
            mAppComponent = null;
            mUIComponent = null;
        } else {
            mAppComponent = v.getAppComponent();
            mUIComponent = v.getUIComponent();
        }
    }

    public T getView() {
        return mView;
    }


    protected AppComponent getAppComponent() {
        return mAppComponent;
    }

    protected UIComponent getUIComponent() {
        return mUIComponent;
    }
}
