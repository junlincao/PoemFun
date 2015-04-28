package com.cjl.poetryfan.ui.fragment;

import com.cjl.poetryfan.R;
import com.cjl.poetryfan.ui.presenter.BasePresenter;
import com.cjl.poetryfan.ui.presenter.HallCenterPresenter;

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
    BasePresenter setupPresenter() {
        return null;
    }

    @Override
    protected int getFragmentLayoutResId() {
        return R.layout.fragment_hall_center;
    }

}
