package com.cjl.poetryfan.ui;

/**
 * Base View, Activity or Fragment etc have Presenter must implements this
 *
 * @author CJL
 * @since 2015-04-28
 */
public interface IView {
    void showLoading();

    void hideLoading();
}
