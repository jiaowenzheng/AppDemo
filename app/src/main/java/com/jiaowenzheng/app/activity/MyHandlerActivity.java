package com.jiaowenzheng.app.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.jiaowenzheng.app.R;

/**
 * Android中Handler的使用，一般都在UI主线程中执行，因此在Handler接收消息后，处理消息时，不能做一些很耗时的操作，否则将出现ANR错误。
 * Android中专门提供了HandlerThread类，来解决该类问题。HandlerThread类是一个线程专门处理Hanlder的消息，
 * 依次从Handler的队列中获取信息，逐个进行处理，保证安全，不会出现混乱引发的异常。
 * Created by wenzheng on 2015/8/12.
 */
public class MyHandlerActivity extends AppCompatActivity {

    private MyHandler mHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_handler);

        HandlerThread handlerThread = new HandlerThread("thread");
        handlerThread.start();

        mHandler = new MyHandler(handlerThread.getLooper());
        mHandler.sendEmptyMessage(1);

        new MyThread().start();

        Log.i("jiao", " Main thread id " + Thread.currentThread().getId());
    }

    class MyHandler extends Handler {

        public MyHandler(Looper looper) {
            super(looper);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            Log.i("jiao"," MyHandler handleMessage  thread id "+Thread.currentThread().getId());

        }
    }

    class  MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            mHandler.sendEmptyMessage(2);
        }
    }


}
