package com.cjl.poetryfan.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.Build;
import android.text.StaticLayout;
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
    private int mNowMonth;
    private int mNowDay;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mCircleColor = 0xffff9727;
    private int mTextColor = Color.WHITE;
    private int mMonthTextSize;
    private int mDayTextSize;
    private Rect mRect = new Rect();

    private final int mMonthCount = 12;
    private float mOffset = 0;

    /**
     * 本月总天数*
     */
    private int mDayCount;
    /**
     * 本月的前一个月总天数*
     */
    private int mLastMonthDayCount;


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

        mMonthTextSize = (int) (density * 18 + .5);
        mDayTextSize = (int) (density * 14 + .5);

        resetDate(new Date());
    }

    public void resetDate(Date d) {
        mCalendar.setTime(d);

        mNowMonth = mCalendar.get(Calendar.MONTH) + 1;
        mNowDay = mCalendar.get(Calendar.DAY_OF_MONTH);

        mDayCount = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
        mCalendar.add(Calendar.MONTH, -1);
        mLastMonthDayCount = mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);

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

//        mRect.set();

    }

    private void drawTextInRect(String t1, String t2, Rect r, float mOffset, Canvas c, Paint p){
        int t1Width = (int) p.measureText(t1);
        int t2Width = (int) p.measureText(t2);

        c.save();
        c.clipRect(r);



        c.restore();
    }
}
