package com.cjl.poetryfan.ui;

import com.cjl.poetryfan.ui.activity.MainActivity;
import com.cjl.poetryfan.ui.fragment.DaySuggestFragment;
import com.cjl.poetryfan.ui.fragment.HallCenterFragment;
import com.cjl.poetryfan.ui.fragment.NavDrawerFragment;
import com.cjl.poetryfan.util.UtilModule;

import dagger.Module;

/**
 * UIModule Created by cjl on 2015/4/15.
 */
@Module(includes = {UtilModule.class},
        injects = {
                MainActivity.class,
                NavDrawerFragment.class,
                DaySuggestFragment.class,
                HallCenterFragment.class
        },
        library = true)
public class UIModule {

}
