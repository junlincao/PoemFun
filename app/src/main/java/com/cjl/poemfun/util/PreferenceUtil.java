package com.cjl.poemfun.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * SharedPreferences 操作类
 *
 * @author CJL
 * @since 2015-04-15
 */
public class PreferenceUtil {
    private SharedPreferences mSp;

    static final String USER_LEARNED_DRAWER = "USER_LEARNED_DRAWER";

    PreferenceUtil(Context ctx) {
        mSp = PreferenceManager.getDefaultSharedPreferences(ctx);
    }

    public void setUserLearnedDrawer(boolean learned) {
        mSp.edit().putBoolean(USER_LEARNED_DRAWER, learned).apply();
    }

    public boolean getUserLearnedDrawer() {
        return mSp.getBoolean(USER_LEARNED_DRAWER, false);
    }
}
