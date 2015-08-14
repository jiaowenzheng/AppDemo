package com.jiaowenzheng.app.activity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.jiaowenzheng.app.R;
import com.jiaowenzheng.app.service.MyMessengerService;

/**
 * Created by wenzheng on 2015/8/11.
 */
public class MessengerActivity extends AppCompatActivity {

    private Button mSendBtn;
    private TextView mConnectedState;
    private LinearLayout mContainerLayout;

    private boolean isConnect = false;
    private int mA = 0;
    private Messenger mService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_messenger);

        mSendBtn = (Button) findViewById(R.id.send_msg_btn);
        mConnectedState = (TextView) findViewById(R.id.connected_state);
        mContainerLayout = (LinearLayout) findViewById(R.id.container_layout);

        bindService();

        mSendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int a = mA++;
                int b = (int) (Math.random() * 100);

                TextView tv = new TextView(MessengerActivity.this);
                tv.setText(a + " + " + b + " =  caculating ...");
                tv.setId(a);

                mContainerLayout.addView(tv);
                Message messageFrom = Message.obtain(null,MyMessengerService.MSG_SUM,a,b);
                messageFrom.replyTo = mMessenger;
                try {
                    if(isConnect){
                        mService.send(messageFrom);
                    }
                } catch (RemoteException e) {
                    e.printStackTrace();
                }



            }
        });

    }

    /**
     * 绑定服务
     */
    public void bindService(){
        Intent intent = new Intent(this, MyMessengerService.class);
//        Intent intent = new Intent();
//        intent.setAction("com.android.service.action");
        bindService(intent, connection, Context.BIND_AUTO_CREATE);
    }


    //绑定服务回调
    private ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
                isConnect = true;
                mConnectedState.setText("connected");
                mService = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            isConnect = false;
            mConnectedState.setText("disConnected");
        }


    };


    //接收消息
    private Messenger mMessenger = new Messenger(new Handler(){
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            switch (msg.what){
                case MyMessengerService.MSG_SUM:
                    TextView tv = (TextView) mContainerLayout.findViewById(msg.arg1);
                    tv.setText(tv.getText()+" => "+msg.arg2);
                    break;
            }
        }
    });

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unbindService(connection);
    }




}
