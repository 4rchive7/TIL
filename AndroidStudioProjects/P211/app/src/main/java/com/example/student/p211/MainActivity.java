package com.example.student.p211;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    EditText tx_id, tx_pw;
    Button bt_login;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeui();
    }

    public void makeui(){
        tx_id = findViewById(R.id.tx_id);
        tx_pw = findViewById(R.id.tx_pw);
        bt_login = findViewById(R.id.login);

    }
    public void clickLogin(View v){
        String id = tx_id.getText().toString(); // getText가 String이 아니여서 toString으로 바꿔줘야함
        String pw = tx_pw.getText().toString();
        tx_id.setText("");
        tx_pw.setText("");
        Intent intent = null;
        if(id.equals("qq") && pw.equals("11")){

            intent = new Intent(getApplicationContext(), success.class);
            intent.putExtra("login", id);
        }else{
            intent = new Intent(getApplicationContext(), fail.class);

        }
        startActivity(intent);
        Toast.makeText(this, id+""+pw, Toast.LENGTH_SHORT).show();
    }
}
