package com.cjl.poetryfan.di;

import android.accounts.AccountManager;
import android.app.AlarmManager;
import android.content.res.AssetManager;
import android.view.LayoutInflater;

import com.cjl.poetryfan.executor.NetworkComponent;
import com.facebook.imagepipeline.core.ImagePipelineConfig;

import java.io.File;

import dagger.Component;

/**
 * for application
 *
 * @author CJL
 * @since 2015-04-28
 */
@AppScope
@Component(modules = ContextModule.class, dependencies = NetworkComponent.class)
public interface AppComponent {

    AccountManager getAccountManager();

    @FilesDirectory
    File getFilesDir();

    AssetManager getAssetManager();

    AlarmManager getAlarmManager();

    LayoutInflater getLayoutInflater();

    ImagePipelineConfig getFrescoConfig();
}
