package com.cjl.poetryfan.di;

import android.accounts.AccountManager;
import android.app.AlarmManager;
import android.content.res.AssetManager;
import android.view.LayoutInflater;
import com.cjl.poetryfan.util.PreferenceUtil;
import com.facebook.imagepipeline.core.ImagePipelineConfig;
import com.squareup.okhttp.OkHttpClient;
import com.squareup.otto.Bus;
import dagger.Component;

import javax.inject.Singleton;
import java.io.File;

/**
 * for application
 *
 * @author CJL
 * @since 2015-04-28
 */
@Singleton
@Component(modules = ContextModule.class)
public interface AppComponent {

    AccountManager getAccountManager();

    @FilesDirectory
    File getFilesDir();

    AssetManager getAssetManager();

    AlarmManager getAlarmManager();

    LayoutInflater getLayoutInflater();

    ImagePipelineConfig getFrescoConfig();

    Bus getEventBus();

    OkHttpClient getOkHttpClient();

    PreferenceUtil getPreferenceUtil();
}
