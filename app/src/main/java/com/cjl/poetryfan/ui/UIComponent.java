package com.cjl.poetryfan.ui;

import com.cjl.poetryfan.di.AppComponent;

import dagger.Component;

/**
 * for activity and fragment etc.
 *
 * @author CJL
 * @since 2015-04-28
 */
@Component(modules = {UIModule.class}, dependencies = {AppComponent.class})
public interface UIComponent {

}
