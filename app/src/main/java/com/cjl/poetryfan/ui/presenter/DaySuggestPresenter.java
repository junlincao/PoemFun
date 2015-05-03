package com.cjl.poetryfan.ui.presenter;

import com.cjl.poetryfan.ui.IView;
import com.squareup.okhttp.Request;
import com.squareup.otto.Bus;
import com.squareup.otto.Subscribe;

import javax.inject.Inject;

/**
 * DaySuggestFragment
 *
 * @author CJL
 * @since 2015-04-16
 */
public class DaySuggestPresenter extends BasePresenter<DaySuggestPresenter.DaySuggestView> {

    public interface DaySuggestView extends IView {

    }

    private Bus mBus;

    @Override
    public void setView(DaySuggestView v) {
        super.setView(v);

        if (v == null) {
            if (mBus != null) {
                mBus.unregister(this);
                mBus = null;
            }
        } else {
            mBus = getAppComponent().getEventBus();
            mBus.register(this);
        }
    }

    @Inject
    public DaySuggestPresenter() {

    }

    public void loadList() {

    }

    public void loadDayData() {

//        Request request = new Request.Builder().tag(tag)

    }

    @Subscribe
    public void onLoadResult() {

    }

}
