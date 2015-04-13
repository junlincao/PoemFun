package com.cjl.poemfun.ui.widget;

import android.content.Context;
import android.graphics.Rect;
import android.support.v4.view.ViewCompat;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * FullWindowLinearLayout
 *
 * @author CJL
 * @since 2015-03-11
 */
public class InsetsLinearLayout extends LinearLayout {

    public InsetsLinearLayout(Context context) {
        this(context, null);
    }

    public InsetsLinearLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public InsetsLinearLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected boolean fitSystemWindows(Rect insets) {
        ViewCompat.requestApplyInsets(this);
        setPadding(insets.left, 0, insets.right, insets.bottom);
        return true;
    }
}