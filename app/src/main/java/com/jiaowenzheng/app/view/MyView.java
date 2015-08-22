package com.jiaowenzheng.app.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * Created by wenzheng on 2015/8/14.
 */
public class MyView extends View {


    private Paint mPaint = new Paint();

    public MyView(Context context) {
        super(context);
    }

    public MyView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);

        int width = MeasureSpec.getSize(widthMeasureSpec);
        int height = MeasureSpec.getSize(heightMeasureSpec);

        Log.i("jiao","  myView "+widthMeasureSpec+"  height "+heightMeasureSpec+"  www "+width+" hhh "+height);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint.setTypeface(Typeface.defaultFromStyle(Typeface.BOLD));
        mPaint.setColor(Color.RED);

        canvas.drawColor(Color.BLUE);
        canvas.drawRect(0,0,30,30,mPaint);
        canvas.drawText("MyView",10,40,mPaint);
    }
}

