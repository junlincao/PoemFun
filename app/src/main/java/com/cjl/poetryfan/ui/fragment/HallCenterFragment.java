package com.cjl.poetryfan.ui.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.cjl.poetryfan.R;
import com.cjl.poetryfan.ui.presenter.BasePresenter;
import com.cjl.poetryfan.ui.presenter.HallCenterPresenter;

import javax.inject.Inject;

import butterknife.InjectView;

/**
 * 大厅Fragment
 *
 * @author CJL
 * @since 2015-04-16
 */
public class HallCenterFragment extends BaseFragment implements HallCenterPresenter.HallCenterView{


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
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
