package com.cjl.poetryfan.ui.fragment;

import android.os.Bundle;

import com.cjl.poetryfan.R;
import com.cjl.poetryfan.ui.presenter.DaySuggestPresenter;

import javax.inject.Inject;

/**
 * 每日推荐Fragment
 *
 * @author CJL
 * @since 2015-04-16
 */
public class DaySuggestFragment extends BaseFragment {

    @Inject
    DaySuggestPresenter mPresenter;

    public DaySuggestFragment(){

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getFragmentLayoutResId() {
        return R.layout.fragment_day_suggest;
    }


}
