package com.example.student.p553;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageView imageView, imageView2, imageView3;
    MyHandler myHandler;
    int addr[] = {R.drawable.m1,
            R.drawable.m2,
            R.drawable.m3,
            R.drawable.m4,
            R.drawable.m5,
            R.drawable.m6,
            R.drawable.m7,
            R.drawable.m8,
            R.drawable.m9
    };
    Intent intent;
//    int addr[] = {R.drawable.img1,
//            R.drawable.img2,
//            R.drawable.img3,
//            R.drawable.img4,
//            R.drawable.img5,
//            R.drawable.img6,
//            R.drawable.img7,
//            R.drawable.img8
//    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        imageView = findViewById(R.id.imageView);
        imageView2 = findViewById(R.id.imageView2);
        imageView3 = findViewById(R.id.imageView3);
        myHandler = new MyHandler();
    }

    public void clickBt(View v){
        intent = new Intent(this, MyService.class);
        Thread t1 = new Thread(r1);
        Thread t2 = new Thread(r2);
        if(v.getId() == R.id.stop){
            intent.putExtra("comm", 1);
        }
        startService(intent);
        t1.start();
        t2.start();
    }

    public void clickBt2(View v){
        intent = new Intent(this, MyService.class);
        intent.putExtra("comm", 1);
        startService(intent);
    }
    @Override
    protected void onNewIntent(Intent intent) {
        processIntent(intent);

    }
    private void processIntent(Intent intent){
        if(intent != null){
            int index = intent.getIntExtra("data", 0);
            imageView3.setImageResource(addr[index]);
        }
    }


    Runnable r2 = new Runnable() {
        @Override
        public void run() {
            for(int i=0;i<9;i++){
                final int index = i;
                Message msg = new Message();
                Bundle bundle = msg.getData();
                bundle.putInt("data", addr[index]);
                myHandler.sendMessage(msg);
                try {
                    Thread.sleep(700);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    };

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            Bundle bundle = msg.getData();
            int index = bundle.getInt("data");
            imageView2.setImageResource(index);
        }
    }


    Runnable r1 = new Runnable() {
        @Override
        public void run() {
            for (int i = 0; i < 9; i++) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final int index = i;

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        imageView.setImageResource(addr[index]);
                    }
                });
            }
        }
    };

}

