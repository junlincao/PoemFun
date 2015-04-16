package com.cjl.poemfun.ui;

import com.cjl.poemfun.ui.activity.MainActivity;
import com.cjl.poemfun.ui.fragment.DaySuggestFragment;
import com.cjl.poemfun.ui.fragment.HallCenterFragment;
import com.cjl.poemfun.ui.fragment.NavDrawerFragment;
import com.cjl.poemfun.util.UtilModule;

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
