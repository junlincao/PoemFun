package com.cjl.poemfun.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjl.poemfun.ui.activity.BaseActivity;

import butterknife.ButterKnife;

/**
 * 基本Fragment，自动注入依赖，不需要重写onCreateView。重写onViewCreated 完成初始化
 * <p>
 * attach activity 必须为BaseActivity子类
 *
 * @author CJL
 * @since 2015-04-13
 */
public abstract class BaseFragment extends Fragment {

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        //依赖注入
        ((BaseActivity) getActivity()).inject(this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayoutResId(), container, false);
        //ButterKnife 注解处理
        ButterKnife.inject(this, view);
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);
    }

    /**
     * onCreateView 返回的View的布局id
     */
    protected abstract int getFragmentLayoutResId();
}
