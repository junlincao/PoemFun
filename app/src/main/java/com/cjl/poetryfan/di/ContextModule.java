package com.cjl.poetryfan.di;

import android.accounts.AccountManager;
import android.app.AlarmManager;
import android.content.Context;
import android.content.res.AssetManager;
import android.view.LayoutInflater;


import com.cjl.poetryfan.di.FilesDirectory;
import com.facebook.common.internal.Preconditions;
import com.facebook.imagepipeline.backends.okhttp.OkHttpImagePipelineConfigFactory;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.squareup.okhttp.OkHttpClient;

import java.io.File;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * ApplicationContext Module
 *
 * @author CJL
 * @since 2015-04-15
 */
@Module
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
    public AccountManager provideAccountManager() {
        return AccountManager.get(mContext);
    }

    @Provides
    @FilesDirectory
    public File providePrivateFileDirectory() {
        return mContext.getFilesDir();
    }

    @Provides
    public AssetManager provideAssetManager() {
        return mContext.getAssets();
    }

    @Provides
    public AlarmManager provideAlarmManager() {
        return (AlarmManager) mContext.getSystemService(Context.ALARM_SERVICE);
    }

    @Provides
    public LayoutInflater provideLayoutInflater() {
        return LayoutInflater.from(mContext);
    }

    @Provides
    ImagePipelineConfig provideImagePipeline(Context ctx, OkHttpClient client) {
        return OkHttpImagePipelineConfigFactory.newBuilder(ctx, client)
                .build();
    }
}
