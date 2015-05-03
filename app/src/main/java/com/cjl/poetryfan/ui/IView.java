package com.cjl.poetryfan.ui;

import com.cjl.poetryfan.di.AppComponent;

/**
 * Base View, Activity or Fragment etc have Presenter must implements this
 *
 * @author CJL
 * @since 2015-04-28
 */
public interface IView {
    void showLoading();

    void hideLoading();

    AppComponent getAppComponent();

    UIComponent getUIComponent();
}
