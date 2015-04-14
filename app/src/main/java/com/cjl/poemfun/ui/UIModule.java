package com.cjl.poemfun.ui;

import com.cjl.poemfun.ui.activity.MainActivity;
import com.cjl.poemfun.ui.fragment.NavDrawerFragment;

import dagger.Module;

/**
 * UIModule
 * Created by cjl on 2015/4/15.
 */
@Module(injects = {MainActivity.class, NavDrawerFragment.class})
public class UIModule {

}
