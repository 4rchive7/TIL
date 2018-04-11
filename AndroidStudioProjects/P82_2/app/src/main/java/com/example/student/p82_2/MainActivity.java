package com.example.student.p82_2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button bt1, bt2, bt3 ,bt4;
    int swch;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mkui();
    }

    public void mkui(){
        bt1 = findViewById(R.id.bt1);
        bt2 = findViewById(R.id.bt2);
        bt3 = findViewById(R.id.bt3);
        bt4 = findViewById(R.id.bt4);

//        bt2.setVisibility(View.INVISIBLE);
//        bt3.setVisibility(View.INVISIBLE);
//        bt4.setVisibility(View.INVISIBLE);

        bt2.setEnabled(false);
        bt3.setEnabled(false);
        bt4.setEnabled(false);
    }

    public void startbt(View v){
//        bt2.setVisibility(View.VISIBLE);
//        bt3.setVisibility(View.VISIBLE);
//        bt4.setVisibility(View.VISIBLE);
        if(swch == 0) {
            bt2.setEnabled(true);
            bt3.setEnabled(true);
            bt4.setEnabled(true);
            swch = 1;
        }else{
            bt2.setEnabled(false);
            bt3.setEnabled(false);
            bt4.setEnabled(false);
            swch = 0;
        }

    }

    public void click1bt(View v){
        Intent intent = new Intent(getApplicationContext(), Main2Activity.class); //이동하것다!
        startActivity(intent);
    }

    public void click2bt(View v){
        Intent intent = new Intent(getApplicationContext(), Main3Activity.class); //이동하것다!
        startActivity(intent);
    }

    public void click3bt(View v){
        Intent intent = new Intent(getApplicationContext(), Main4Activity.class);
        startActivity(intent);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Destroy", Toast.LENGTH_SHORT).show();
    }
}
