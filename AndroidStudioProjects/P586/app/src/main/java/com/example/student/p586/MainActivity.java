package com.example.student.p586;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        textView = findViewById(R.id.textView);
        setContentView(R.layout.activity_main);
    }

    public void clickBt(View v){
        t.start();
    }

    Thread t = new Thread(new Runnable() {
        int i = 1;
        @Override
        public void run() {
            while(i <= 10){
                try {
                    Thread.sleep(500);
                    i++;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //runOn + enter => (new Run + enter) => run안에 넣어야지만 ui바꿀 수 있음
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        textView.setText(i);
                    }
                });
            }
        }
    });
}
