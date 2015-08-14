package com.jiaowenzheng.app.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;

import com.jiaowenzheng.app.MyAidlInterface;

/**
 * AIDL Service
 * @author  jiaowenzheng
 */
public class MyAIDLService extends Service {


    @Override
    public IBinder onBind(Intent intent) {
        return mBind;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i("jiao"," MyAIDLService onCreate ");
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i("jiao"," onUnbind");
        return super.onUnbind(intent);
    }

    @Override
    public void onRebind(Intent intent) {
        Log.i("jiao"," onRebind ");
        super.onRebind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.i("jiao"," onDestroy ");
    }

    @Override
    public void onStart(Intent intent, int startId) {
        super.onStart(intent, startId);
        Log.i("jiao"," onStart ");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("jiao"," onStartCommand ");
        return super.onStartCommand(intent, flags, startId);
    }

    private MyAidlInterface.Stub mBind = new MyAidlInterface.Stub() {
        @Override
        public int add(int x, int y) throws RemoteException {
            return  x + y;
        }

        @Override
        public int min(int x, int y) throws RemoteException {
            return x - y;
        }
    };
}
