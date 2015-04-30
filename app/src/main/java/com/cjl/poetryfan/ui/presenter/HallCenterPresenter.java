package com.cjl.poetryfan.ui.presenter;

import com.cjl.poetryfan.ui.IView;

import javax.inject.Inject;

/**
 * com.cjl.poemfun.ui.presenter
 *
 * @author CJL
 * @since 2015-04-16
 */
public class HallCenterPresenter extends BasePresenter<HallCenterPresenter.HallCenterView>{

    public interface HallCenterView extends IView{

    }

    @Inject
    HallCenterPresenter(){

    }
}
