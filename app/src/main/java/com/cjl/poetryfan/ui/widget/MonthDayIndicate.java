package com.cjl.poetryfan.ui.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
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
public class MonthDayIndicate extends View {
    public MonthDayIndicate(Context context) {
        super(context);
        init(context);
    }

    public MonthDayIndicate(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MonthDayIndicate(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MonthDayIndicate(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context);
    }

    private StaticLayout mMonthLayout;
    private StaticLayout mDayLayout;
    Calendar mCalendar = Calendar.getInstance();
    private int mNowMonth;
    private int mNowDay;
    private Paint mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private int mCircleColor = 0xffff9727;
    private int mTextColor = Color.WHITE;

    void init(Context ctx){


    }

    public void resetDate(Date d){
        mCalendar.setTime(d);

        mNowMonth = mCalendar.get(Calendar.MONTH) + 1;
        mNowDay = mCalendar.get(Calendar.DAY_OF_MONTH);

        mCalendar.getActualMaximum(Calendar.DAY_OF_MONTH);
    }

    public void setCircleColor(int color){

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        final int r = Math.min(getWidth(), getHeight()) / 2;
        mPaint.setColor(mCircleColor);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, r, mPaint);

        mPaint.setColor(mTextColor);


    }
}
