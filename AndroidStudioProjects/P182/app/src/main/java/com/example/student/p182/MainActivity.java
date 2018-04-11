package com.example.student.p182;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView text;
    LinearLayout bck;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        text = findViewById(R.id.tx);
        bck = findViewById(R.id.lanyoung);
    }

    public void bt1Clicked(View v){
        text.setText("A");
    }
    public void bt2Clicked(View v){
        text.setText("B");
    }
    public void bt3Clicked(View v){
        text.setText("C");
    }
    public void bt4Clicked(View v){
        text.setText("D");
    }
    public void bt5Clicked(View v){
        text.setText("E");
    }
    public void bt6Clicked(View v){
        text.setText("F");
    }
    public void chngBackground(View v){
        bck.setBackgroundResource(R.drawable.bg2);
    }

}
