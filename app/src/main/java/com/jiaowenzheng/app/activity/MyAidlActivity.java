package com.jiaowenzheng.app.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.jiaowenzheng.app.MyAidlInterface;
import com.jiaowenzheng.app.R;
import com.jiaowenzheng.app.service.MyAIDLService;

/**
 * Created by wenzheng on 2015/8/11.
 */
public class MyAidlActivity extends AppCompatActivity implements View.OnClickListener {

    private Button mBindServiceBtn;
    private Button mUnBindServiceBtn;
    private Button mAddBtn;
    private Button mSubtractionBtn;

    private MyAidlInterface mAidlInterface;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_aidl);

        mBindServiceBtn = (Button) findViewById(R.id.bind_service_btn);
        mUnBindServiceBtn = (Button) findViewById(R.id.unbind_service_btn);
        mAddBtn = (Button) findViewById(R.id.call_back_btn_1);
        mSubtractionBtn = (Button) findViewById(R.id.call_back_btn_2);

        mBindServiceBtn.setOnClickListener(this);
        mUnBindServiceBtn.setOnClickListener(this);
        mAddBtn.setOnClickListener(this);
        mSubtractionBtn.setOnClickListener(this);


    }


    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.bind_service_btn:
                bindService();
                break;
            case R.id.unbind_service_btn:
                unBindService();
                break;
            case R.id.call_back_btn_1:
                additiveOperation();
                break;
            case R.id.call_back_btn_2:
                subtractionOperation();
                break;
        }
    }


    private ServiceConnection connection = new ServiceConnection() {

        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            mAidlInterface = MyAidlInterface.Stub.asInterface(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            mAidlInterface = null;
        }
    };


    /**
     * 绑定服务
     */
    public void bindService(){
        Intent intent = new Intent(this, MyAIDLService.class);
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }

    /**
     * 解除绑定服务
     */
    public void unBindService(){
        unbindService(connection);
    }

    public void additiveOperation(){
        try {
            if(mAidlInterface != null){
               mAddBtn.setText(mAidlInterface.add(5,5));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }

    }

    public void subtractionOperation(){
        try {
            if(mAidlInterface != null){
                mSubtractionBtn.setText(mAidlInterface.min(19,30));
            }
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }
}
