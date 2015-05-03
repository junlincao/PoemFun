package com.cjl.poetryfan.ui.adapter;

import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.cjl.poetryfan.R;
import com.cjl.poetryfan.proto.PoetryProto;
import com.cjl.poetryfan.ui.widget.BookLikePager;
import com.facebook.common.internal.Preconditions;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

/**
 * daySuggests adapter
 * <p/>
 * Created by cjl on 2015/5/1.
 */
public class DaySuggestAdapter extends PagerAdapter implements BookLikePager.IBookLikePagerAdapter {
    private List<PoetryProto.DaySuggest> mDatas = new LinkedList<>();
    private LayoutInflater mInflater;
    private SimpleDateFormat mFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);

    public DaySuggestAdapter(LayoutInflater mInflater) {
        this.mInflater = Preconditions.checkNotNull(mInflater, "LayoutInflater can't be null!");
    }

    public void updateData(List<PoetryProto.DaySuggest> datas, boolean isLoadMore) {
        Preconditions.checkNotNull(datas, "datas is null");
        if (!isLoadMore) {
            mDatas.clear();
        }
        mDatas.addAll(0, datas);
        notifyDataSetChanged();
    }

    public Date getDateByPos(int position) {
        PoetryProto.DaySuggest ds = mDatas.get(position);
        try {
            return mFormat.parse(ds.getDate());
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int getPositionByItemView(View itemView) {
        return (Integer) itemView.getTag();
    }

    @Override
    public Drawable getItemViewBackground() {
        return mInflater.getContext().getResources().getDrawable(R.drawable.day_sug_card);
    }

    @Override
    public int getCount() {
        return mDatas.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view == object;
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position) {
        View page = mInflater.inflate(R.layout.list_daysuggest, container, false);
        page.setTag(position);
        container.addView(page);
        return page;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        View page = (View) object;
        container.removeView(page);
    }
}
