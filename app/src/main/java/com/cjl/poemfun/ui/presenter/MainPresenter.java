package com.cjl.poemfun.ui.presenter;

import android.support.v4.app.Fragment;

import com.cjl.poemfun.util.BusEvents;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

/**
 * MainActivity presenter
 *
 * @author CJL
 * @since 2015-04-15
 */
public class MainPresenter {
    @Inject
    Bus bus;

    public interface MainView {
        void replaceFragment(Fragment fragment);
    }

    @Inject
    MainPresenter() {

    }

    private MainView mView;

    public void setView(MainView view) {
        mView = view;

        if (mView == null) {
            bus.unregister(this);
        } else {
            bus.register(this);
        }
    }

    @Subscribe
    public void onNavToFragment(BusEvents.NavDrawerItemClickEvent event) {
        if (mView != null) {
            mView.replaceFragment(event.getToFragment());
        }
    }
}
