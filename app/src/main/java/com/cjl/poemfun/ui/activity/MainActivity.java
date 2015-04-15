package com.cjl.poemfun.ui.activity;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.util.TypedValue;

import com.cjl.poemfun.R;
import com.cjl.poemfun.ui.fragment.NavDrawerFragment;
import com.cjl.poemfun.ui.presenter.MainPresenter;
import com.cjl.poemfun.util.PreferenceUtil;

import java.util.List;

import javax.inject.Inject;

import butterknife.InjectView;

public class MainActivity extends BaseActivity implements MainPresenter.MainView {

    @Inject
    MainPresenter mPresenter;

    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        NavDrawerFragment mNavDrawerFragment = (NavDrawerFragment) getSupportFragmentManager()
                .findFragmentById(R.id.navigation_drawer);

        TypedValue sTypedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimaryDark, sTypedValue, true);
        mDrawerLayout.setStatusBarBackgroundColor(sTypedValue.data);
        mNavDrawerFragment.setUp(R.id.navigation_drawer, mDrawerLayout);

        mPresenter.setView(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPresenter.setView(null);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected List<Object> getExtraModules() {
        return null;
    }

    @Override
    public void replaceFragment(Fragment fragment) {
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment).commit();
    }

    @Inject
    PreferenceUtil preferenceUtil;

    @Override
    public void onBackPressed() {
        super.onBackPressed();

        preferenceUtil.getUserLearnedDrawer();
    }
}
