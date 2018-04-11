package com.example.student.workshop;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUp extends AppCompatActivity {

    EditText tx_id, tx_pw;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        tx_id = findViewById(R.id.idv);
        tx_pw = findViewById(R.id.pwv);
    }

    public void clickbt(View v){
        Intent intent = null;
        if(v.getId() == R.id.signup) {
            String id = tx_id.getText().toString();
            String pw = tx_pw.getText().toString();
            tx_id.setText("");
            tx_pw.setText("");
            intent = new Intent(getApplicationContext(), MainActivity.class);
            intent.putExtra("info_id", id);
            intent.putExtra("info_pw", pw);
            startActivity(intent);
            Toast.makeText(this, id+""+pw, Toast.LENGTH_SHORT).show();
        }
    }
}
