package com.cjl.poetryfan.ui.activity;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;

import com.cjl.poetryfan.ui.IView;
import com.cjl.poetryfan.ui.presenter.BasePresenter;

import butterknife.ButterKnife;

/**
 * BaseActivity,
 * <p/>
 * 使用Dagger依赖注入，使用ButterKnife替换ButterKnife注解字段
 *
 * @author CJL
 * @since 2015-04-13
 */
public abstract class BaseActivity<T extends BasePresenter> extends ActionBarActivity implements IView {
    private T mPresenter;

    /**
     * log tag *
     */
    protected String TAG = getClass().getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        injectViews();

        mPresenter = setupPresenter();
        if (mPresenter != null) {
            mPresenter.setView(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (mPresenter != null) {
            mPresenter.setView(null);
        }
    }

    public abstract T setupPresenter();

    protected T getPresenter(){
        return mPresenter;
    }

    /**
     * 获取布局资源ID
     *
     * @return layout id
     */
    protected abstract int getLayoutRes();


    /**
     * ButterKnife 注解处理
     */
    private void injectViews() {
        ButterKnife.inject(this);
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }
}
