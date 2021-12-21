package com.app.bindertry;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import android.util.Log;


public class RemoteService extends Service {
    private static final String TAG = "BinderSample.";

    MyText mMyText;

    @Override
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        initMyText();
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind,");
        return mBinder;
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.i(TAG, "onUnbind.");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestroy.");
        super.onDestroy();
    }

    private final IRemoteService.Stub mBinder = new IRemoteService.Stub() {
        @Override
        public String showText() throws RemoteException {
            return mMyText.txt;
        }

        @Override
        public MyText getMyText() throws RemoteException {
            return mMyText;
        }

        @Override
        public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
            Log.i(TAG, "onTransact.");
            return super.onTransact(code, data, reply, flags);
        }
    };
    private void initMyText(){
        mMyText = new MyText();
        mMyText.retText("binder try.");
        mMyText.retYoco();
    }

}
