package com.cjl.poetryfan.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import butterknife.InjectView;
import com.cjl.poetryfan.R;
import com.cjl.poetryfan.ui.adapter.DaySuggestAdapter;
import com.cjl.poetryfan.ui.presenter.BasePresenter;
import com.cjl.poetryfan.ui.presenter.DaySuggestPresenter;
import com.cjl.poetryfan.ui.widget.BookLikePager;
import com.cjl.poetryfan.ui.widget.MonthDayIndicater;

/**
 * 每日推荐Fragment
 *
 * @author CJL
 * @since 2015-04-16
 */
public class DaySuggestFragment extends BaseFragment implements DaySuggestPresenter.DaySuggestView {

    @InjectView(R.id.toolbar)
    Toolbar mToolbar;
    @InjectView(R.id.vp)
    BookLikePager mViewPager;
    @InjectView(R.id.indicate)
    MonthDayIndicater mIndicater;

    private DaySuggestAdapter mAdapter;

    public DaySuggestFragment() {

    }

    @Override
    BasePresenter setupPresenter() {
        return getUIComponent().getDaySuggestPresenter();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int getFragmentLayoutResId() {
        return R.layout.fragment_day_suggest;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ActionBarActivity activity = (ActionBarActivity) getActivity();
        activity.setSupportActionBar(mToolbar);
        ActionBar mActionBar = activity.getSupportActionBar();
        mActionBar.setDisplayHomeAsUpEnabled(true);

        mAdapter = new DaySuggestAdapter(getAppComponent().getLayoutInflater());
        mViewPager.setAdapter(mAdapter);
        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View view, float v) {
                int id = (int) view.getTag();
                if (id == mViewPager.getCurrentItem()) {
//                    float pos = Math.max(-1, Math.min(1, v));
                    mIndicater.setScrollPos(v);
                }
            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                mIndicater.setDate(mAdapter.getDateByPos(i));
            }

            @Override
            public void onPageScrollStateChanged(int i) {
            }
        });

        ((DaySuggestPresenter) getPresenter()).loadList();
    }
}
