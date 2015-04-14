package com.cjl.poemfun.util;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

import com.cjl.poemfun.ui.activity.MainActivity;

import dagger.Module;
import dagger.Provides;

/**
 * com.cjl.poemfun.util
 *
 * @author CJL
 * @since 2015-04-13
 */
@Module(library = true, injects = {MainActivity.class})
public class UtilModule {

//    @Provides
//    SharedPreferences provideSharedPreference(Context ctx) {
//        return getSharedPreference(ctx);
//    }
//
//    public static SharedPreferences getSharedPreference(Context ctx) {
//        return PreferenceManager.getDefaultSharedPreferences(ctx);
//    }
}
