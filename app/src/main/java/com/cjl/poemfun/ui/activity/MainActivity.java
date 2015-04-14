package com.cjl.poemfun.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.TypedValue;

import com.cjl.poemfun.R;
import com.cjl.poemfun.ui.fragment.NavDrawerFragment;

import java.util.List;

import butterknife.InjectView;


public class MainActivity extends BaseActivity {

    private NavDrawerFragment mNavDrawerFragment;

    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNavDrawerFragment = (NavDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        TypedValue sTypedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimaryDark, sTypedValue, true);
        mDrawerLayout.setStatusBarBackgroundColor(sTypedValue.data);
        mNavDrawerFragment.setUp(R.id.navigation_drawer, mDrawerLayout);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected List<Object> getModules() {
        return null;
    }
}
