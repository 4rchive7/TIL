package com.example.student.workshop2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    LinearLayout started, container, notstarted;
    ProgressBar progressBar;
    Intent intent, intent2;
    ImageView imageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        started = findViewById(R.id.started);
        container = findViewById(R.id.container);
        notstarted = findViewById(R.id.notstarted);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100);
        imageView = findViewById(R.id.imageView);
        started.setVisibility(View.INVISIBLE);
        notstarted.setVisibility(View.INVISIBLE);
    }

    @Override
    protected void onNewIntent(Intent intent) {

        String sname = intent.getStringExtra("name");
        int time;
        int cnt = 0;
        int cnt2 = 0;

        if(sname.equals("service1")){
            time = intent.getIntExtra("time",0);
            if(time < 5){
                notstarted.setVisibility(View.VISIBLE);
            }else if(time == 5) {
                started.setVisibility(View.VISIBLE);
                notstarted.setVisibility(View.INVISIBLE);
                stopService(intent);
            }
        }
        if(sname.equals("service2")){
            cnt = intent.getIntExtra("cnt", 0);
            //Toast.makeText(this, "service2 called " + cnt, Toast.LENGTH_SHORT).show();
            if(cnt != 0)
                progressBar.setProgress(cnt);
        }
        if(sname.equals("service3")){
            Toast.makeText(this, "service3 called", Toast.LENGTH_SHORT).show();
            cnt2 = intent.getIntExtra("cnt2", 0);
            if(cnt2%2 == 0)
                imageView.setImageResource(R.drawable.bg1);
            else
                imageView.setImageResource(R.drawable.img2);
        }
    }


    public void clickBt(View v){
        if(v.getId() == R.id.start){
            //Toast.makeText(this, "startBUttonClicked", Toast.LENGTH_SHORT).show();
            intent = new Intent(getApplicationContext(), MyService.class);
            startService(intent);
        }else if(v.getId() == R.id.start2){
            //Toast.makeText(this, "startBUtton2   Clicked", Toast.LENGTH_SHORT).show();
            intent = new Intent(getApplicationContext(), MyService2.class);
            intent2 = new Intent(getApplicationContext(), MyService3.class);
            startService(intent);
            startService(intent2);
        }
    }
}
