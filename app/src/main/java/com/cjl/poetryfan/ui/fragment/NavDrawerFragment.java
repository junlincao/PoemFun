package com.cjl.poetryfan.ui.fragment;

import android.content.Context;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import butterknife.InjectView;
import butterknife.OnItemClick;
import com.cjl.poetryfan.R;
import com.cjl.poetryfan.ui.presenter.BasePresenter;
import com.cjl.poetryfan.ui.presenter.NavDrawerPresenter;
import com.facebook.drawee.view.SimpleDraweeView;
import com.squareup.otto.Bus;

import javax.inject.Inject;

/**
 * navigation drawer Fragment
 */
public class NavDrawerFragment extends BaseFragment implements NavDrawerPresenter.NavDrawerView {
    /**
     * Remember the position of the selected item.
     */
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    @InjectView(R.id.function_list)
    ListView mFunctionList;
    @InjectView(R.id.setup_list)
    ListView mSetupList;
    @InjectView(R.id.user_container)
    View mUserContainer;
    @InjectView(R.id.user_image)
    SimpleDraweeView mUserImg;

    private ActionBarDrawerToggle mDrawerToggle;
    private DrawerLayout mDrawerLayout;

    private int mCurrentSelectedPosition = 0;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;

    public NavDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserLearnedDrawer = getAppComponent().getPreferenceUtil().getUserLearnedDrawer();

        if (savedInstanceState != null) {
            mCurrentSelectedPosition = savedInstanceState.getInt(STATE_SELECTED_POSITION);
            mFromSavedInstanceState = true;
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        setHasOptionsMenu(true);
    }


    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        String[] functionList = getResources().getStringArray(R.array.function_list);
        String[] setupList = getResources().getStringArray(R.array.setup_list);

        mFunctionList.setAdapter(new ArrayAdapter<String>(
                getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1, functionList));
        mFunctionList.setItemChecked(mCurrentSelectedPosition, true);

        mSetupList.setAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1,
                android.R.id.text1, setupList));
    }

    @OnItemClick(R.id.function_list)
    void onClickFunction(int position) {
        mCurrentSelectedPosition = position;
        mFunctionList.setItemChecked(position, true);

        ((NavDrawerPresenter)getPresenter()).onFunctionItemClick(position);
    }

    @OnItemClick(R.id.setup_list)
    void onSetupItemClick(int position) {
        ((NavDrawerPresenter)getPresenter()).onSetupItemClick(position);
    }

    @Override
    BasePresenter setupPresenter() {
        return getUIComponent().getNavDrawerPresenter();
    }

    @Override
    protected int getFragmentLayoutResId() {
        return R.layout.fragment_nav_drawer;
    }

    /**
     * Users of this fragment must call this method to set up the navigation drawer interactions.
     *
     * @param fragmentId   The android:id of this fragment in its activity's layout.
     * @param drawerLayout The DrawerLayout containing this fragment's UI.
     */
    public void setUp(int fragmentId, DrawerLayout drawerLayout, Toolbar toolbar) {
        View mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(), mDrawerLayout, toolbar,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) {
                    return;
                }
                getActivity().supportInvalidateOptionsMenu();
                syncState();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) {
                    return;
                }

                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    getAppComponent().getPreferenceUtil().setUserLearnedDrawer(true);
                }

                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
                syncState();
            }
        };

        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(mFragmentContainerView);
        }

        mDrawerToggle.syncState();

        mDrawerLayout.setDrawerListener(mDrawerToggle);
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        mDrawerToggle.onConfigurationChanged(newConfig);

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return mDrawerToggle.onOptionsItemSelected(item) || super.onOptionsItemSelected(item);
    }

    @Override
    public void close() {
        if (mDrawerLayout != null) {
            mDrawerLayout.closeDrawers();
        }
    }

    @Override
    public Context getContext() {
        return getActivity();
    }

    @Override
    public void sendEvent(Object event) {
        getAppComponent().getEventBus().post(event);
    }

    @Override
    public void setUserBackground(int color) {
        mUserContainer.setBackgroundColor(color);
    }
}
