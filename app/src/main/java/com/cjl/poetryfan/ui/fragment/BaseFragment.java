package com.cjl.poetryfan.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.cjl.poetryfan.ui.IView;
import com.cjl.poetryfan.ui.presenter.BasePresenter;

import butterknife.ButterKnife;

/**
 * 基本Fragment，自动注入依赖，不需要重写onCreateView。重写onViewCreated 完成初始化
 * <p/>
 * attach activity 必须为BaseActivity子类
 *
 * @author CJL
 * @since 2015-04-13
 */
public abstract class BaseFragment<T extends BasePresenter> extends Fragment implements IView {
    private T mPresenter;

    abstract T setupPresenter();

    protected T getPresenter(){
        return mPresenter;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(getFragmentLayoutResId(), container, false);
        //ButterKnife 注解处理
        ButterKnife.inject(this, view);
        mPresenter = setupPresenter();
        if (mPresenter != null) {
            mPresenter.setView(this);
        }

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        ButterKnife.reset(this);


        if (mPresenter != null) {
            mPresenter.setView(null);
        }
    }

    /**
     * onCreateView 返回的View的布局id
     */
    protected abstract int getFragmentLayoutResId();
}
