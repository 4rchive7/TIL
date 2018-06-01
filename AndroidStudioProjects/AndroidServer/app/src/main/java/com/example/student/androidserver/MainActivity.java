package com.example.student.androidserver;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    TextView textView, textView2;
    EditText editText;
    String comm = "";
    Server server = null;
    MyHandler myHandler = null;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myHandler = new MyHandler();


        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        editText = findViewById(R.id.editText3);


    }
    public void clickBt(View v){
        try {
            server = new Server();
            server.start.execute();
            t.start();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
            while(true){
                comm = server.getComm();
                if(comm != null && comm.equals("running")){

                }

                if(comm != null && comm.equals("display")){

                    Message msg = new Message();
                    Bundle bundle = msg.getData();
                    bundle.putInt("speed", server.getMsg1());
                    bundle.putInt("temp", server.getMsg2());
                    myHandler.sendMessage(msg);

                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    comm = "";
                }
            }
        }
    });

    class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            Bundle bundle = msg.getData();
            textView.setText(bundle.getInt("speed"));
            textView2.setText(bundle.getInt("temp"));
        }
    }
}
