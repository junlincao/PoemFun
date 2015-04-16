package com.cjl.poemfun.ui.fragment;

import com.cjl.poemfun.R;
import com.cjl.poemfun.ui.presenter.HallCenterPresenter;

import javax.inject.Inject;

/**
 * 大厅Fragment
 *
 * @author CJL
 * @since 2015-04-16
 */
public class HallCenterFragment extends BaseFragment {
    @Inject
    HallCenterPresenter mPresenter;

    public HallCenterFragment(){

    }

    @Override
    protected int getFragmentLayoutResId() {
        return R.layout.fragment_hall_center;
    }

}
