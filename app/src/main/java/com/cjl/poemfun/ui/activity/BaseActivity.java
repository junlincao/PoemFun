package com.cjl.poemfun.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBarActivity;

import com.cjl.poemfun.di.ActivityModule;
import com.cjl.poemfun.AppApplication;

import java.util.LinkedList;
import java.util.List;

import butterknife.ButterKnife;
import dagger.ObjectGraph;

/**
 * BaseActivity,
 * <p>
 * 使用Dagger依赖注入，使用ButterKnife替换ButterKnife注解字段
 *
 * @author CJL
 * @since 2015-04-13
 */
public abstract class BaseActivity extends ActionBarActivity {

    private ObjectGraph activityScopeGraph;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        injectDependencies();
        injectViews();
    }

    /**
     * Dagger 注入依赖
     *
     * @param object to inject.
     */
    public void inject(Object object) {
        activityScopeGraph.inject(object);
    }

    /**
     * 提供当前activity需要的依赖Modules（包括用到的Fragment）
     *
     * @return 依赖Modules列表
     */
    protected abstract List<Object> getModules();

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
        AppApplication tvShowsApplication = (AppApplication) getApplication();
        List<Object> activityScopeModules = getModules();
        activityScopeModules.add(new ActivityModule(this));
        activityScopeGraph = tvShowsApplication.plus(activityScopeModules);
        inject(this);
    }

    /**
     * ButterKnife 注解处理
     */
    private void injectViews() {
        ButterKnife.inject(this);
    }
}
