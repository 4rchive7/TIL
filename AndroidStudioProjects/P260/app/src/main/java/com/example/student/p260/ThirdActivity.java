package com.example.student.p260;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class ThirdActivity extends AppCompatActivity {

    Intent intent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        intent = getIntent();
        int num1 = intent.getIntExtra("num1", 0);
        num1 *= 2000;
        intent.putExtra("result", num1);
    }

    public void clickBt(View v){

        setResult(RESULT_OK, intent); // 200으로 넘어감
        finish(); // activity를 종료할 때 싸용됨
    }
}
