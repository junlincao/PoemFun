package com.cjl.poetryfan.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import java.util.Calendar;
import java.util.Date;

/**
 * 圆形的日月指示View
 *
 * @author CJL
 * @since 2015-04-21
 */
public class MonthDayIndicater extends View {

    Calendar mCalendar = Calendar.getInstance();
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mCircleColor = 0xffff9727;
    private int mTextColor = Color.WHITE;
    private int mMonthTextSize;
    private int mDayTextSize;
    private Rect mRect = new Rect();
    private float mOffset = 0;

    public MonthDayIndicater(Context context) {
        super(context);
        init(context);
    }

    public MonthDayIndicater(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MonthDayIndicater(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MonthDayIndicater(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    void init(Context ctx) {
        float density = ctx.getResources().getDisplayMetrics().density;

        mMonthTextSize = (int) (density * 22 + .5);
        mDayTextSize = (int) (density * 14 + .5);

        setDate(new Date());
    }

    /**
     * 设置当前显示页的日期
     *
     * @param d 日期
     */
    public void setDate(Date d) {
        mCalendar.setTime(d);
        invalidate();
    }

    public void setCircleColor(int color) {
        mCircleColor = color;
        invalidate();
    }

    public void setTextColor(int color) {
        mTextColor = color;
        invalidate();
    }

    public void setTextSize(int monthTextSize, int dayTextSize) {
        this.mMonthTextSize = monthTextSize;
        this.mDayTextSize = dayTextSize;

        invalidate();
    }

    /**
     * 设置滚动偏移比例
     *
     * @param pos 范围(-1, 1),日期减少时应为大于0 类同与ViewPager.PageTransformer
     *            transformPage 第二个参数
     */
    public void setScrollPos(float pos) {
        mOffset = pos;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int r = Math.min(getWidth(), getHeight()) / 2;
        final int centerX = getWidth() / 2;
        final int centerY = getHeight() / 2;
        final int dividerXL = r / 4;

        mPaint.setColor(mCircleColor);
        canvas.drawCircle(centerX, centerY, r, mPaint);

        mPaint.setColor(mTextColor);
        canvas.drawLine(centerX - dividerXL, centerY - dividerXL, centerX + dividerXL, centerY + dividerXL, mPaint);

        final long savedTime = mCalendar.getTimeInMillis(); // 保存日历时间
        final int dayNow = mCalendar.get(Calendar.DAY_OF_MONTH);
        final int monthNow = mCalendar.get(Calendar.MONTH) + 1;

        mCalendar.add(Calendar.DAY_OF_MONTH, mOffset >= 0 ? -1 : 1);
        final int toDay = mCalendar.get(Calendar.DAY_OF_MONTH);
        final int toMonth = mCalendar.get(Calendar.MONTH) + 1;

        final int padding = r / 4;
        mPaint.setTextSize(mDayTextSize);
        mRect.set(centerX - r + padding, centerY - padding, centerX, centerY + r - padding);
        drawText(dayNow, toDay, mRect, mOffset, canvas, mPaint);

        mPaint.setTextSize(mMonthTextSize);
        mRect.set(centerX, centerY - r + padding, centerX + r - padding, centerY + padding);
        drawText(monthNow, toMonth, mRect, monthNow == toMonth ? 0 : mOffset, canvas, mPaint);

        mCalendar.setTimeInMillis(savedTime); // 恢复日历时间
    }

    private void drawText(int numCenter, int numEdge, Rect r, float mOffset, Canvas c, Paint p) {
        String tCenter = String.valueOf(numCenter);
        String tEdge = String.valueOf(numEdge);

        int tCenterWidth = (int) p.measureText(tCenter);
        int tEdgeWidth = (int) p.measureText(tEdge);

        c.save();
        c.clipRect(r);

        Paint.FontMetrics fm = p.getFontMetrics();
        float y = r.top + (r.bottom - r.top - fm.bottom + fm.top) / 2 - fm.top;

        int scroll = (int) ((r.width() + tEdgeWidth) / 2f * mOffset);
        float xCenter = r.centerX() - tCenterWidth / 2 + scroll;
        float xEdge;
        if (mOffset >= 0) {
            xEdge = r.left - tEdgeWidth + scroll;
        } else {
            xEdge = r.right + scroll;
        }

        c.drawText(tCenter, xCenter, y, p);
        c.drawText(tEdge, xEdge, y, p);
        c.restore();
    }
}
