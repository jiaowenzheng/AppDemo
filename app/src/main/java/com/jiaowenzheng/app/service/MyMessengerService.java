package com.jiaowenzheng.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;

/**
 * Messenger 服务
 * @author  jiaowenzheng
 */
public class MyMessengerService extends Service {

    public static final int MSG_SUM = 1;


    private Messenger mMessenger = new Messenger(new Handler(){

        @Override
        public void handleMessage(Message msg) {

            Message client = Message.obtain(msg);
            switch (msg.what){
                case MSG_SUM:
                    try {
                        Thread.sleep(1000);
                        client.what = MSG_SUM;
                        client.arg2 = msg.arg1 + msg.arg2;

                        msg.replyTo.send(client);

                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }


            super.handleMessage(msg);
        }
    });

    @Override
    public IBinder onBind(Intent intent) {
      return mMessenger.getBinder();
    }
}
