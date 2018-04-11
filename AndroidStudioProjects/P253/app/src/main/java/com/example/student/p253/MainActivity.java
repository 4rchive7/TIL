package com.example.student.p253;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    LayoutInflater inflater;
    LinearLayout container;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeui();
    }

    private void makeui() {
        container = findViewById(R.id.container);
        inflater = (LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE); // 반드시 캐스팅 해주기!
    }

    public void clickBt(View v){

        inflater.inflate(R.layout.sub, container, true);
        TextView stv = findViewById(R.id.stv);
        stv.setText("Button Click");
        Button sbt1, sbt2;
        sbt1 = findViewById(R.id.sbt1);
        sbt2 = findViewById(R.id.sbt2);
        sbt1.setText("Sub Button 1");
        sbt2.setText("Sub Button 2");


        sbt1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sub2로 변경
                container = findViewById(R.id.container);
                inflater.inflate(R.layout.sub3, container, true);
        }
        });

        sbt2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //sub3로 변경
                inflater.inflate(R.layout.sub2,container, true);
            }
        });

    }
}
