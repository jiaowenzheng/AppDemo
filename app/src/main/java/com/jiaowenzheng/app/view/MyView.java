package com.jiaowenzheng.app.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.ViewGroup;

/**
 * TODO: document your custom view class.
 */
public class MyView extends ViewGroup {

    public MyView(Context context) {
        super(context);
    }


    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        Log.i("jiao"," widthMode "+widthMode+" heightMode "
                +heightMode+" width "+width+" height "+height);

    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

    }
}
