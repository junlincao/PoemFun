package com.cjl.poemfun.ui.activity;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuItem;

import com.cjl.poemfun.R;
import com.cjl.poemfun.ui.fragment.NavigationDrawerFragment;

import java.util.List;

import butterknife.InjectView;


public class MainActivity extends BaseActivity {

    private NavigationDrawerFragment mNavigationDrawerFragment;

    @InjectView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);

        TypedValue sTypedValue = new TypedValue();
        getTheme().resolveAttribute(R.attr.colorPrimaryDark, sTypedValue, true);
        mDrawerLayout.setStatusBarBackgroundColor(sTypedValue.data);
    }

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected List<Object> getModules() {
        return null;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            getMenuInflater().inflate(R.menu.main, menu);
            return true;
        }

        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
