package com.cjl.poetryfan.ui.adapter;

import android.util.SparseArray;
import android.view.View;

/**
 * Common ViewHolder
 * <p/>
 * Created by cjl on 2015/5/1.
 */
public class ViewHolder {
    /**
     * @param v   parentView
     * @param id  childView res id
     * @param <T> child view type , TextView ,Button etc.
     * @return child View of v
     */
    @SuppressWarnings("unchecked")
    public <T extends View> T get(View v, int id) {
        if (v == null) {
            throw new NullPointerException("view is null");
        }

        SparseArray<View> children = (SparseArray<View>) v.getTag();
        View res;
        if (children == null) {
            children = new SparseArray<>();
            v.setTag(children);
        }

        res = children.get(id);
        if (res == null) {
            res = v.findViewById(id);
            children.put(id, res);
        }
        return (T) res;
    }
}
