package com.cjl.poemfun.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;

import com.cjl.poemfun.AppApplication;

import java.util.List;

import butterknife.ButterKnife;
import dagger.ObjectGraph;

/**
 * BaseActivity,
 * <p/>
 * 使用Dagger依赖注入，使用ButterKnife替换ButterKnife注解字段
 *
 * @author CJL
 * @since 2015-04-13
 */
public abstract class BaseActivity extends ActionBarActivity {
    /**
     * log tag *
     */
    protected String TAG = getClass().getSimpleName();

    private ObjectGraph activityScopeGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        injectDependencies();
        setContentView(getLayoutRes());
        injectViews();
    }

    /**
     * Dagger 注入依赖
     *
     * @param object to inject.
     */
    public void inject(Object object) {
        if (activityScopeGraph != null) {
            activityScopeGraph.inject(object);
        } else {
            AppApplication application = (AppApplication) getApplication();
            application.inject(object);
        }
    }

    /**
     * 提供除{@link AppApplication} 中 ObjectGraph.create 提供的modules以外的
     * 当前activity需要的依赖Modules（包括用到的Fragment）
     *
     * @return 依赖Modules列表
     */
    @Nullable
    protected abstract List<Object> getExtraModules();

    /**
     * 获取布局资源ID
     *
     * @return layout id
     */
    protected abstract int getLayoutRes();

    /**
     * Dagger 添加注入依赖需要的Module
     */
    private void injectDependencies() {
        AppApplication application = (AppApplication) getApplication();
        List<Object> modules = getExtraModules();
        if (modules != null) {
            activityScopeGraph = application.plus(modules);
            inject(this);
        } else {
            application.inject(this);
        }
    }

    /**
     * ButterKnife 注解处理
     */
    private void injectViews() {
        ButterKnife.inject(this);
    }
}
