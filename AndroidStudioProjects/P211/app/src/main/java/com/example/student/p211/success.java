package com.example.student.p211;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class success extends AppCompatActivity {

    TextView ok_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_success);
        ok_id= findViewById(R.id.ok_id);
        Intent intent = getIntent();
        String id = intent.getStringExtra("login");
        ok_id.setText(id);
    }
}
