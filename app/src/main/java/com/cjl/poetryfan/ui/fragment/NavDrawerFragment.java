package com.cjl.poetryfan.ui.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.cjl.poetryfan.R;
import com.cjl.poetryfan.ui.presenter.NavDrawerPresenter;
import com.cjl.poetryfan.util.PreferenceUtil;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.controller.BaseControllerListener;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;
import com.facebook.imagepipeline.image.ImageInfo;
import com.squareup.otto.Bus;

import javax.inject.Inject;

import butterknife.InjectView;
import butterknife.OnItemClick;

/**
 * navigation drawer Fragment
 */
public class NavDrawerFragment extends BaseFragment implements NavDrawerPresenter.NavDrawerView {
    /**
     * Remember the position of the selected item.
     */
    private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";

    /**
     * Per the design guidelines, you should show the drawer on launch until the user manually
     * expands it. This shared preference tracks this.
     */
    private static final String PREF_USER_LEARNED_DRAWER = "navigation_drawer_learned";

    @Inject
    NavDrawerPresenter mPresenter;
    @Inject
    PreferenceUtil mPreferenceUtil;
    @Inject
    Bus bus;

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
    private View mFragmentContainerView;

    private int mCurrentSelectedPosition = 0;
    private boolean mFromSavedInstanceState;
    private boolean mUserLearnedDrawer;
    private boolean mHasSetup;

    public NavDrawerFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mUserLearnedDrawer = mPreferenceUtil.getUserLearnedDrawer();

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

        setUp();

        startTest();
    }

    @OnItemClick(R.id.function_list)
    void onClickFunction(int position) {
        mCurrentSelectedPosition = position;
        mFunctionList.setItemChecked(position, true);

        mPresenter.onFunctionItemClick(position);
    }

    @OnItemClick(R.id.setup_list)
    void onSetupItemClick(int position) {
        mPresenter.onSetupItemClick(position);
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
    public void setUp(int fragmentId, DrawerLayout drawerLayout) {
        mFragmentContainerView = getActivity().findViewById(fragmentId);
        mDrawerLayout = drawerLayout;
        setUp();
    }

    private void setUp() {
        if (!isAdded() || mFragmentContainerView == null || mHasSetup) {
            return;
        }
        mDrawerLayout.setDrawerShadow(R.drawable.drawer_shadow, GravityCompat.START);
        mDrawerToggle = new ActionBarDrawerToggle(
                getActivity(), mDrawerLayout,
                R.string.navigation_drawer_open,
                R.string.navigation_drawer_close) {
            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                if (!isAdded()) {
                    return;
                }
                getActivity().supportInvalidateOptionsMenu();
            }

            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                if (!isAdded()) {
                    return;
                }

                if (!mUserLearnedDrawer) {
                    mUserLearnedDrawer = true;
                    mPreferenceUtil.setUserLearnedDrawer(true);
                }

                getActivity().supportInvalidateOptionsMenu(); // calls onPrepareOptionsMenu()
            }
        };

        if (!mUserLearnedDrawer && !mFromSavedInstanceState) {
            mDrawerLayout.openDrawer(mFragmentContainerView);
        }

        mDrawerToggle.syncState();

        mDrawerLayout.setDrawerListener(mDrawerToggle);
        mHasSetup = true;
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        mPresenter.setView(this);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mPresenter.setView(null);
        mPresenter = null;
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
        bus.post(event);
    }

    @Override
    public void setUserBackground(int color) {
        mUserContainer.setBackgroundColor(color);
    }



    private void startTest() {
        final Drawable def = mUserImg.getTopLevelDrawable();

        DraweeController dc = Fresco.newDraweeControllerBuilder().setOldController(mUserImg.getController())
                .setUri(Uri.parse("http://192.168.2.170:8080/data/t1.png"))
                .setControllerListener(new BaseControllerListener<ImageInfo>() {
                    @Override
                    public void onFinalImageSet(String id, ImageInfo imageInfo, Animatable animatable) {
                        Log.d("---", "onFinalImageSet->id=" + id + "; w=" + imageInfo.getWidth() + "; h=" + imageInfo.getHeight());

                        Drawable dNow = mUserImg.getTopLevelDrawable();

                        Log.d("---", "equals=" + (def == dNow));


                        mUserImg.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                Drawable dNow = mUserImg.getTopLevelDrawable();

                                Log.d("---", "delayed equals=" + (def == dNow));
                            }
                        }, 200);
                    }
                })
                .build();
        mUserImg.setController(dc);

    }

}
