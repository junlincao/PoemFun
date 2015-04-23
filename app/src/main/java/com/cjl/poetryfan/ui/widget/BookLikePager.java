package com.cjl.poetryfan.ui.widget;

import android.content.Context;
import android.database.DataSetObserver;
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
 * 不要设置pager 的 Left padding, 其它的padding可以随意设置
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
    private int mMaxEdgeCount = 2;
    private float mScrollOffset;
    private Drawable mEdgeDrawable;

    private int mFirstOffset;
    private int mNowEdgeCount;


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

        mMaxEdgeWidth = (int) (density * 15 + .5);

        setPageTransformer(false, null);
    }

    public void setMaxPageEdgeCount(int count) {
        if (count <= 1) {
            throw new IllegalArgumentException("count can't be less than 1");
        }
        this.mMaxEdgeCount = count;
    }

    public void setMaxPageEdgeWidth(int width) {
        mMaxEdgeWidth = width;
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
     * @param offset 页ID减少时应大于0
     * @param idx    view 在adapter中的position
     */
    private void onTransformPage(View view, float offset, int idx) {
        if(idx == getCurrentItem()){
            if(offset < 0){
                setStickPage(view, offset);
            } else {
                setMovePage(view, offset);
            }
            updateState();
        } else if(idx == getCurrentItem() - 1){
            if(offset < 0){
                setStickPage(view, offset);
            }
        } else {
            if(offset > 0){
                setMovePage(view, offset);
            }
        }

        Log.d("---", idx + "->" + offset);
    }

    private void setMovePage(View view, float offset){
//        view.setTranslationX(getWidth() * offset);
    }

    private void setStickPage(View view, float offset){
        view.setTranslationX(-getWidth() * offset);

    }

    private void updateState() {
        PagerAdapter mAdapter = getAdapter();
        if (mAdapter == null) {
            return;
        }
        int currentItem = getCurrentItem();


        if (currentItem <= mMaxEdgeCount) {
            mFirstOffset = 0;
            int leftPadding = (int) (currentItem * 1f * mMaxEdgeWidth / mMaxEdgeCount);
            setPaddingLeft(leftPadding);
            return;
        }

        mNowEdgeCount = Math.min(currentItem, mMaxEdgeCount) + 1;
    }

    private void setPaddingLeft(int leftPadding) {
        setPadding(leftPadding, getPaddingTop(), getPaddingRight(), getPaddingBottom());
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
