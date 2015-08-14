package com.jiaowenzheng.app;

import java.util.List;

/**
 * Created by wenzheng on 2015/8/13.
 */
public class ListItem {

    private int drawableId;
    private List<String> childrenList;

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public List<String> getChildrenList() {
        return childrenList;
    }

    public void setChildrenList(List<String> childrenList) {
        this.childrenList = childrenList;
    }
}
