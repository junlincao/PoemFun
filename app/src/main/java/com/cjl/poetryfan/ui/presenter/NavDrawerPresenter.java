package com.cjl.poetryfan.ui.presenter;

import android.content.Context;
import android.support.v4.app.Fragment;
import com.cjl.poetryfan.ui.IView;
import com.cjl.poetryfan.ui.fragment.DaySuggestFragment;
import com.cjl.poetryfan.ui.fragment.HallCenterFragment;
import com.cjl.poetryfan.util.BusEvents;

import javax.inject.Inject;

/**
 * 侧边栏管理 (NavDrawerFragment)
 *
 * @author CJL
 * @since 2015-04-14
 */
public class NavDrawerPresenter extends BasePresenter<NavDrawerPresenter.NavDrawerView> {
    public interface NavDrawerView extends IView{
        /**
         * close nav drawer*
         */
        void close();

        Context getContext();

        /**
         * send eventbus event
         *
         * @param event event
         */
        void sendEvent(Object event);

        void setUserBackground(int color);
    }

    @Inject
    public NavDrawerPresenter(){

    }

    public void onFunctionItemClick(int position) {
        // jump to function list R.array.function_list
        Fragment fragment;
        switch (position) {
            case 0:
                fragment = new DaySuggestFragment();
                break;
            case 1:
                fragment = new HallCenterFragment();
                break;
            default:
                throw new IllegalArgumentException("not implement");
        }

        getView().sendEvent(new BusEvents.NavDrawerItemClickEvent(fragment));
        getView().close();
    }

    public void onSetupItemClick(int position) {
        // jump to setup list R.array.setup_list
        getView().close();
    }

}
