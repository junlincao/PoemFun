package com.cjl.poemfun.di;

import android.accounts.AccountManager;
import android.app.AlarmManager;
import android.content.Context;
import android.content.res.AssetManager;
import android.view.LayoutInflater;


import com.facebook.common.internal.Preconditions;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * com.cjl.poemfun.di
 *
 * @author CJL
 * @since 2015-04-15
 */
@Module(library = true)
public class ContextModule {
    private Context mContext;

    public ContextModule(Context ctx) {
        mContext = Preconditions.checkNotNull(ctx, "context can't be null");
    }

    @Provides
    public Context provideApplicationContext() {
        return mContext;
    }

    @Provides
    @Singleton
    public AccountManager provideAccountManager() {
        return AccountManager.get(mContext);
    }

    @Provides
    public File providePrivateFileDirectory() {
        return mContext.getFilesDir();
    }

    @Provides
    @Singleton
    public AssetManager provideAssetManager() {
        return mContext.getAssets();
    }

    @Provides
    @Singleton
    public AlarmManager provideAlarmManager() {
        return (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
    }

    @Provides
    @Singleton
    public LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(mContext);
    }
}
