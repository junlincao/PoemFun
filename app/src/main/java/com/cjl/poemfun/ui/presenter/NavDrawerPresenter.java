package com.cjl.poemfun.ui.presenter;

import android.content.Context;

/**
 * 侧边栏管理
 *
 * @author CJL
 * @since 2015-04-14
 */
public class NavDrawerPresenter {
    public interface NavDrawerView {
        /**
         * close nav drawer*
         */
        void close();

        Context getContext();
    }

    private NavDrawerView mView;

    public NavDrawerPresenter() {

    }


    public void setView(NavDrawerView view) {
        this.mView = view;
    }

    public void onNavDrawerItemClick(int position) {
        mView.close();
    }

    public void onDraw
}
