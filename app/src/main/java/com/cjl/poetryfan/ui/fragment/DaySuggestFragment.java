package com.cjl.poetryfan.ui.fragment;

import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.cjl.poetryfan.R;
import com.cjl.poetryfan.ui.presenter.DaySuggestPresenter;
import com.cjl.poetryfan.ui.widget.MonthDayIndicater;

import java.util.Calendar;
import java.util.Date;

import javax.inject.Inject;

import butterknife.InjectView;

/**
 * 每日推荐Fragment
 *
 * @author CJL
 * @since 2015-04-16
 */
public class DaySuggestFragment extends BaseFragment {

    @Inject
    DaySuggestPresenter mPresenter;
    @InjectView(R.id.toolbar)
    Toolbar mToolbar;

    public DaySuggestFragment() {

    }

    @InjectView(R.id.vp)
    ViewPager mViewPager;
    @InjectView(R.id.indicate)
    MonthDayIndicater mIndicater;

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



        mViewPager.setAdapter(new PagerAdapter() {
            @Override
            public int getCount() {
                return 40;
            }

            @Override
            public boolean isViewFromObject(View view, Object o) {
                return view == o;
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {
                TextView tv = new TextView(getActivity());
                tv.setText("" + position);
                tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 25);
                tv.setTag(position);
                container.addView(tv);
                return tv;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
                View v = (View) object;
                container.removeView(v);
            }
        });
        mViewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(View view, float v) {
                int id = (int) view.getTag();
                if (id == mViewPager.getCurrentItem()) {
                    float pos = Math.max(-1, Math.min(1, v));
                    mIndicater.setScrollPos(pos);
                }
            }
        });
        mViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            float lastPos = 0;

            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                Calendar c = Calendar.getInstance();
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, i);
                mIndicater.setDate(c.getTime());
                Log.d("---", "onPageSelected");
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }


}
