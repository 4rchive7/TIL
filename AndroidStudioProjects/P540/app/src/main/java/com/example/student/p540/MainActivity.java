package com.example.student.p540;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView text;
    MyHandler myHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        myHandler = new MyHandler();
        text = findViewById(R.id.textView);
        Toast.makeText(MainActivity.this, R.id.textView+"", Toast.LENGTH_SHORT).show();
        Toast.makeText(MainActivity.this, text+"", Toast.LENGTH_SHORT).show();
        text = findViewById(R.id.textView);
        Toast.makeText(MainActivity.this, R.id.text+"", Toast.LENGTH_SHORT).show();
    }

    public void clickBt(View v){
        t.start();
    }

    Thread t = new Thread(new Runnable() {
        int i = 1;
        @Override
        public void run() {
            while(i<=10){
                try {
                    i++;
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Message msg = new Message();
                Bundle bundle = msg.getData();
                bundle.putInt("data", i);
                myHandler.sendMessage(msg);
            }
        }
    });

    class MyHandler extends Handler{
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            //Toast.makeText(MainActivity.this, bundle.getInt("data")+"", Toast.LENGTH_SHORT).show();
            //Toast.makeText(MainActivity.this, text+"", Toast.LENGTH_SHORT).show();
            if(bundle.getInt("data") == 11){
                text.setText("ended");
            }else {
                text.setText(bundle.getInt("data") + "");
            }
        }
    }




}
