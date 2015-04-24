package com.cjl.poetryfan.ui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * ViewPager 书页效果
 * <p/>
 * 不要设置pager 的 Left and right padding
 *
 * @author CJL
 * @since 2015-04-23
 */
public class BookLikePager extends ViewPager {
    public interface IBookLikePagerAdapter {
        /**
         * 根据ItemView 对象获取其对于在adapter中的位置
         * <p/>
         * 建议使用tag存储其位置
         *
         * @param itemView adapter 的一个itemVIew
         * @return 位置
         */
        int getPositionByItemView(View itemView);

        /**
         * 获取ItemView 相同的背景Drawable 请new一个Drawable，不要使用某个item的background
         */
        Drawable getItemViewBackground();
    }


    private int mMaxEdgeWidth;
    private int mMaxEdgeCount = 3;
    private float mScrollOffset;
    private Drawable mEdgeDrawable;

    private int mFirstOffset;
    private int mNowEdgeCount;
    private float mItemEdgeWidth;


    public BookLikePager(Context context) {
        super(context);
        init(context);
    }

    public BookLikePager(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    private void init(Context context) {
        float density = context.getResources().getDisplayMetrics().density;

        mMaxEdgeWidth = (int) (density * 25 + .5);

        setPageTransformer(false, null); // set the default PageTransformer
        setClipToPadding(false);
        resetPadding();
    }

    public void setMaxPageEdgeCount(int count) {
        if (count <= 2) {
            throw new IllegalArgumentException("count can't be less than 2");
        }
        this.mMaxEdgeCount = count;
        resetPadding();
    }

    public void setMaxPageEdgeWidth(int width) {
        mMaxEdgeWidth = width;
        resetPadding();
    }

    private void resetPadding() {
        int padding = (int) (mMaxEdgeWidth * (mMaxEdgeCount + 1) / mMaxEdgeCount / 2f);
        setPadding(padding, getPaddingTop(), padding, getPaddingBottom());
        mItemEdgeWidth = mMaxEdgeWidth * 1f / mMaxEdgeCount;
    }

    @Override
    public void setAdapter(PagerAdapter adapter) {
        if (!(adapter instanceof IBookLikePagerAdapter)) {
            throw new IllegalArgumentException("adapter must implements IBookLikePagerAdapter");
        }
        mEdgeDrawable = ((IBookLikePagerAdapter) adapter).getItemViewBackground();
        if (mEdgeDrawable == null || mEdgeDrawable.getCallback() != null) {
            throw new IllegalArgumentException("请 new 一个新的Drawable !");
        }

        super.setAdapter(adapter);

//        getAdapter().registerDataSetObserver(new DataSetObserver() {
//            @Override
//            public void onInvalidated() {
//                updateState();
//            }
//        });

        postDelayed(new Runnable() {
            @Override
            public void run() {
                Log.d("---", "mItemEdgeWidth=" + mItemEdgeWidth);
                Log.d("---", "paddingLeft=" + getPaddingLeft());
                logDx(0, 0);
                logDx(0, 1);
                logDx(0, 2);
                logDx(1, 0);
                logDx(1, 1);
                logDx(1, 2);
                logDx(2, 1);
                logDx(2, 2);
            }
        }, 1000);
    }

    private void logDx(int idx, int cIdx) {
        float x = getDefaultX(idx, cIdx);
        Log.d("---", "idx=" + idx + "; cIdx=" + cIdx + "; x=" + x);
    }

    @Override
    public void setPageTransformer(boolean reverseDrawingOrder, final PageTransformer transformer) {
        PageTransformer ptf = new PageTransformer() {
            @Override
            public void transformPage(View page, float position) {
                int idx = ((IBookLikePagerAdapter) getAdapter()).getPositionByItemView(page);
                onTransformPage(page, position, idx);

                if (transformer != null) {
                    transformer.transformPage(page, position);
                }
            }
        };

        super.setPageTransformer(reverseDrawingOrder, ptf);
    }

    /**
     * @param view   PagerAdapter 的一个itemView
     * @param offset 从屏幕左边进-1->0, 出0->-1
     *               屏幕右边进 1->0, 出0->1
     * @param idx    view 在adapter中的position
     */
    private void onTransformPage(View view, float offset, int idx) {
//        Log.d("---", "iw=" + view.getWidth() + "; w=" + getWidth() + "; pl=" + getPaddingLeft() + "; pr=" + getPaddingRight());
        view.setTranslationX(calcTranslationX(idx, offset));
    }

    /**
     * @param idx    page 在adapter 中的idx
     * @param offset 从屏幕左边进-1->0, 出0->-1
     *               屏幕右边进 1->0, 出0->1
     * @return ViewPager 偏移位置
     */
    private float calcTranslationX(int idx, float offset) {
        final int cIdx = getCurrentItem();

        float from, to;
        if (idx == cIdx + 1 && offset >= 0 && offset < 1) {
            from = getDefaultX(idx, cIdx + 1);
            to = getDefaultX(idx, cIdx);
        } else if (idx > cIdx + 1) {
            return -getPaddingRight();
        } else if (idx == cIdx) {
            if (offset <= 0) {
                if (idx == getAdapter().getCount() - 1) {
//                    from = getDefaultX(idx, idx);
//                    to = from;
                    return 0;
                } else {
                    from = getDefaultX(idx, cIdx);
                    to = getDefaultX(idx, cIdx + 1);
                }
            } else {
                if (idx == 0) {
//                    from = getDefaultX(idx, idx);
//                    to = from;
                    return 0;
                } else {
                    from = getDefaultX(idx, cIdx);
                    to = getDefaultX(idx, cIdx - 1);
                }
            }
        } else if (idx == cIdx - 1 && offset <= 0 && offset > -1) {
            from = getDefaultX(idx, cIdx - 1);
            to = getDefaultX(idx, cIdx);
        } else {
            return getPaddingLeft();
        }
        float toX = from + (to - from) * Math.abs(offset);
        float defX = -offset * getWidth();
        float res;
//        if(offset <= 0){
//            res = defX - toX;
//        } else {
            res = defX + toX;
//        }

        Log.d("---", idx + "->offset=" + offset + ";\tfrom=" + from + ";\tto=" + to + ";\tnx=" + toX + ";\ttx=" + defX + ";\tres=" + res );
        return res;
    }

    /**
     * 获取指定页应该所处的x位置(pager 静止空闲状态)
     *
     * @param idx 页id
     * @param ci  当前默认页ID
     * @return 指定页x位置
     */
    private float getDefaultX(int idx, int ci) {
        if (idx > ci) { // 大于当前显示页，应该是在屏幕右边看不见状态
            return getWidth() * (idx - ci);
        } else if (ci - idx >= mMaxEdgeCount) { // 相隔超过最大页数，在屏幕左边
            return -getWidth() * (ci - idx - mMaxEdgeCount + 1);
        } else if (ci < mMaxEdgeCount) {//小于最大页数，所有页一起居中显示，计算偏差
            float delta = getPaddingLeft();
            if (ci > 0) {
                delta -= ci * mItemEdgeWidth / 2;
            }
            return delta + idx * mItemEdgeWidth;
        } else {
            return (mMaxEdgeCount + idx - ci) * mItemEdgeWidth;
        }
    }

    private void updateState() {
        PagerAdapter mAdapter = getAdapter();
        if (mAdapter == null) {
            return;
        }
        int currentItem = getCurrentItem();


        mNowEdgeCount = Math.min(currentItem, mMaxEdgeCount) + 1;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (getAdapter() != null) {
            drawEdge();
        }
    }

    private void drawEdge() {

    }
}
