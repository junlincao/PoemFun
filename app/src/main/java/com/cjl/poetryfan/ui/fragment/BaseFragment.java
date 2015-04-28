package com.cjl.poetryfan.ui.fragment;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.ButterKnife;
import com.cjl.poetryfan.AppApplication;
import com.cjl.poetryfan.di.AppComponent;
import com.cjl.poetryfan.di.ContextModule;
import com.cjl.poetryfan.di.DaggerAppComponent;
import com.cjl.poetryfan.ui.DaggerUIComponent;
import com.cjl.poetryfan.ui.IView;
import com.cjl.poetryfan.ui.UIComponent;
import com.cjl.poetryfan.ui.presenter.BasePresenter;

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
    private UIComponent mUIComponent;
    private AppComponent mAppComponent;

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

    abstract T setupPresenter();

    protected T getPresenter() {
        return mPresenter;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mAppComponent = ((AppApplication) getActivity().getApplicationContext()).getAppComponent();
        mUIComponent = DaggerUIComponent.builder().appComponent(mAppComponent).build();

        mPresenter = setupPresenter();
        if (mPresenter != null) {
            mPresenter.setView(this);
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        if (mPresenter != null) {
            mPresenter.setView(null);
        }
        mPresenter = null;
        mAppComponent = null;
        mUIComponent = null;
    }

    protected UIComponent getUIComponent() {
        return mUIComponent;
    }

    protected AppComponent getAppComponent() {
        return mAppComponent;
    }

    /**
     * onCreateView 返回的View的布局id
     */
    protected abstract int getFragmentLayoutResId();

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
