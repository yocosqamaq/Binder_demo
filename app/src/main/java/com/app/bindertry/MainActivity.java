package com.app.bindertry;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.KeyEventDispatcher;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "BinderSample";
    private IRemoteService mRemoteService;

    private Button btn;
    private TextView mShowText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn = (Button)findViewById(R.id.button);
        mShowText = (TextView) findViewById(R.id.textView);
        Log.i(TAG,"onCreate.");
        mShowText.setText(R.string.textview);
        Toast.makeText(MainActivity.this, "onCreate", Toast.LENGTH_SHORT).show();
    }

    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            mRemoteService = IRemoteService.Stub.asInterface(iBinder);
            try {
                //MyText myText = mRemoteService.getMyText();
                String myShow = mRemoteService.showText();
                mShowText.setText(myShow);
                Toast.makeText(MainActivity.this, myShow, Toast.LENGTH_SHORT).show();
            } catch (RemoteException e) {
                e.printStackTrace();
            }
            Toast.makeText(MainActivity.this, "Connected", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mShowText.setText(R.string.remote_service_disconnected);
        }
    };

    public void onClick(View view){
        Intent intent = new Intent(MainActivity.this, RemoteService.class);
        intent.setAction(IRemoteService.class.getName());
        bindService(intent,mConnection, Context.BIND_AUTO_CREATE);
        mShowText.setText("binding...");
        Toast.makeText(MainActivity.this, "binding", Toast.LENGTH_SHORT).show();
    }



}