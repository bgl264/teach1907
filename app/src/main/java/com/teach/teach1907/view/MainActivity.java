package com.teach.teach1907.view;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.teach.teach1907.base.BaseActivity;

public class MainActivity extends BaseActivity {

    static Handler mHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            if (msg.what == 1){
                Log.e("睚眦", String.valueOf(msg.obj) );
            }
        }
    };
    private static MyThread mThread;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mThread = new MyThread();
        mThread.start();
    }

    private static int a = 1;
    static class MyThread extends Thread{
        @Override
        public void run() {
            super.run();
            while (true){
                try {
                    Message message = new Message();
                    message.obj = a;
                    message.what = 1;
                    if (mHandler != null)mHandler.sendMessage(message);
                    else break;
                    a+=1;
                    sleep(1000);
                } catch (InterruptedException pE) {
                    pE.printStackTrace();
                }
            }
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        showLog("m:onRestart");
    }

    @Override
    protected void onStart() {
        super.onStart();
        showLog("m:onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
        showLog("m:onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        showLog("m:onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        showLog("m:onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        showLog("m:onDestroy");
        mHandler.removeCallbacksAndMessages(null);
        mHandler = null;
//        mThread.interrupt();
        mThread = null;
    }
}
