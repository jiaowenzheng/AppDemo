package com.jiaowenzheng.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jiaowenzheng.app.activity.ListViewActivity;
import com.jiaowenzheng.app.activity.MessengerActivity;
import com.jiaowenzheng.app.activity.MyAidlActivity;
import com.jiaowenzheng.app.activity.MyHandlerActivity;
import com.jiaowenzheng.app.activity.MyViewGroupActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{

    private Button mMessengerBtn;
    private Button mAidlBtn;
    private Button mHandlerBtn;
    private Button mListBtn;
    private Button mViewDrawBtn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mMessengerBtn = (Button) findViewById(R.id.messenger_btn);
        mAidlBtn = (Button) findViewById(R.id.aidl_btn);
        mHandlerBtn = (Button) findViewById(R.id.handler_btn);
        mListBtn = (Button) findViewById(R.id.list_view_btn);
        mViewDrawBtn = (Button) findViewById(R.id.view_draw_btn);

        mMessengerBtn.setOnClickListener(this);
        mAidlBtn.setOnClickListener(this);
        mHandlerBtn.setOnClickListener(this);
        mListBtn.setOnClickListener(this);
        mViewDrawBtn.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.messenger_btn:
                startActivity(new Intent(this, MessengerActivity.class));
                break;
            case R.id.aidl_btn:
                startActivity(new Intent(this, MyAidlActivity.class));
                break;
            case R.id.handler_btn:
                startActivity(new Intent(this,MyHandlerActivity.class));
                break;
            case R.id.list_view_btn:
                startActivity(new Intent(this, ListViewActivity.class));
                break;
            case R.id.view_draw_btn:
                startActivity(new Intent(this, MyViewGroupActivity.class));
                break;

        }
    }

    public void test(){
        new Handler().post(new Runnable() {
            @Override
            public void run() {

            }
        });
    }
}
