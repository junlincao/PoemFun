package com.cjl.poetryfan.ui.fragment;

import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.widget.Toolbar;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import butterknife.InjectView;
import com.cjl.poetryfan.R;
import com.cjl.poetryfan.ui.presenter.BasePresenter;
import com.cjl.poetryfan.ui.presenter.DaySuggestPresenter;
import com.cjl.poetryfan.ui.widget.BookLikePager;
import com.cjl.poetryfan.ui.widget.MonthDayIndicater;

import java.util.Calendar;
import java.util.Date;

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

        mViewPager.setAdapter(new MPagerAdapter());
        mViewPager.setPageTransformer(false, new ViewPager.PageTransformer() {
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
            Calendar c = Calendar.getInstance();

            @Override
            public void onPageScrolled(int i, float v, int i1) {
            }

            @Override
            public void onPageSelected(int i) {
                c.setTime(new Date());
                c.add(Calendar.DAY_OF_MONTH, i);
                mIndicater.setDate(c.getTime());

//                Log.d("---", "0->1->2->onPageSelected");
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    private class MPagerAdapter extends PagerAdapter implements BookLikePager.IBookLikePagerAdapter {

        @Override
        public int getPositionByItemView(View itemView) {
            return (int) itemView.getTag();
        }

        @Override
        public Drawable getItemViewBackground() {
            return new ColorDrawable(Color.WHITE);
        }

        @Override
        public int getCount() {
            return 5;
        }

        @Override
        public boolean isViewFromObject(View view, Object o) {
            return view == o;
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            TextView tv = new TextView(getActivity());
            tv.setText("" + position);
            tv.setTextSize(TypedValue.COMPLEX_UNIT_DIP, 300);
            tv.setTag(position);
            container.addView(tv);
            tv.setBackgroundColor(Color.WHITE);
            return tv;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            View v = (View) object;
            container.removeView(v);
        }
    }

}
