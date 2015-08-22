package com.jiaowenzheng.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.jiaowenzheng.app.R;

/**
 * TODO: document your custom view class.
 */
public class MyViewGroup extends ViewGroup {

    public MyViewGroup(Context context) {
        super(context);
    }


    public MyViewGroup(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }


    public void init(){


        Button button = new Button(getContext());
        button.setText("这是个button");
        addView(button);

        TextView textView = new TextView(getContext());
        textView.setText("这是一个TextView");
        addView(textView);

        ImageView imageView = new ImageView(getContext());
        imageView.setImageResource(R.drawable.ic_array_pressed);
        addView(imageView);

        MyView view = new MyView(getContext());
        addView(view);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        Log.i("jiao", " widthMode " + widthMode + " heightMode "
                + heightMode + " width " + width + " height " + height);
        setMeasuredDimension(width, height);

        for (int i = 0; i < getChildCount(); i++) {
            View child = getChildAt(i);   //获得每个对象的引用
            child.measure(50, 50);   //简单的设置每个子View对象的宽高为 50px , 50px
            //或者可以调用ViewGroup父类方法measureChild()或者measureChildWithMargins()方法

        }
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int childrenCount = getChildCount();

        int startLeft = 0;
        int startTop = 10;

        for(int i = 0;i < childrenCount;i++){
            View children = getChildAt(i);
            children.layout(startLeft,startTop,startLeft + children.getMeasuredWidth(),
                    startTop + children.getMeasuredHeight());

            startLeft = startLeft + children.getMeasuredWidth() + 10;

        }

    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);
    }
}
