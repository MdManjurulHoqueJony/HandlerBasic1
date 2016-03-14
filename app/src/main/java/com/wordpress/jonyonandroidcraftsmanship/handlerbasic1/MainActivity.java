package com.wordpress.jonyonandroidcraftsmanship.handlerbasic1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

public class MainActivity extends AppCompatActivity {

    private ProgressBar pbTest =null;
    private Thread myThread1=null;
    private MyHandler1 myHandler1=null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();
    }

    private void initialize() {
        pbTest = (ProgressBar) findViewById(R.id.pbTest);
        myHandler1=new MyHandler1();
        myThread1=new Thread(new MyThread1Runnable());
        myThread1.start();

    }

    private class MyThread1Runnable implements Runnable {
        @Override
        public void run() {
            for (int i = 0; i <= 100; i++) {
                Message message=Message.obtain();
                message.arg1=i;
                myHandler1.sendMessage(message);

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class MyHandler1 extends Handler {
        @Override
        public void handleMessage(Message msg) {
            pbTest.setProgress(msg.arg1);
        }
    }
}
