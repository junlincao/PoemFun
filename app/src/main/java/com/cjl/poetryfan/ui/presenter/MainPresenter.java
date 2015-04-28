package com.cjl.poetryfan.ui.presenter;

import android.support.v4.app.Fragment;
import com.cjl.poetryfan.ui.IView;
import com.cjl.poetryfan.ui.fragment.DaySuggestFragment;

import javax.inject.Inject;

/**
 * MainActivity presenter
 *
 * @author CJL
 * @since 2015-04-15
 */
public class MainPresenter extends BasePresenter<MainPresenter.MainView> {

    public interface MainView extends IView {
        void replaceFragment(Fragment fragment);
    }

    @Inject
    public MainPresenter() {

    }

    @Override
    public void setView(MainView v) {
        super.setView(v);
        if (v != null) {
            //显示默认的fragment
            v.replaceFragment(getDefaultFunctionFragment());
        }
    }

    public Fragment getDefaultFunctionFragment() {
        return new DaySuggestFragment();
    }

}
