package com.cjl.poetryfan.util;

import android.support.v4.app.Fragment;

/**
 * otto 事件
 *
 * @author CJL
 * @since 2015-04-15
 */
public class BusEvents {
    /** 首页导航栏内容跳转事件 **/
    public static class NavDrawerItemClickEvent {
        private Fragment toFragment;

        public NavDrawerItemClickEvent(Fragment toFragment){
            this.toFragment = toFragment;
        }

        public Fragment getToFragment() {
            return toFragment;
        }

        public void setToFragment(Fragment toFragment) {
            this.toFragment = toFragment;
        }
    }


}
