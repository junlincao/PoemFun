package com.cjl.poetryfan.ui;

import com.cjl.poetryfan.di.AppComponent;
import com.cjl.poetryfan.di.UIScope;
import com.cjl.poetryfan.ui.presenter.DaySuggestPresenter;
import com.cjl.poetryfan.ui.presenter.MainPresenter;
import com.cjl.poetryfan.ui.presenter.NavDrawerPresenter;
import dagger.Component;

/**
 * for activity and fragment etc.
 *
 * @author CJL
 * @since 2015-04-28
 */
@UIScope
@Component(dependencies = {AppComponent.class})
public interface UIComponent {
    MainPresenter getMainPresenter();

    DaySuggestPresenter getDaySuggestPresenter();

    NavDrawerPresenter getNavDrawerPresenter();
}
