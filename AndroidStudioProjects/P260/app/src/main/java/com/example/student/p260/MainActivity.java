package com.example.student.p260;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //Toast.makeText(this, ""+resultCode, Toast.LENGTH_SHORT).show();
        if(requestCode == 100 && resultCode == RESULT_OK){
                int result = data.getIntExtra("result", 0);
                textView.setText(result+"");
        }
        if(requestCode == 101 && resultCode == RESULT_OK){
            int result = data.getIntExtra("result", 0);
            textView.setText(result+"!!");
        }

    }

    public void clickBt(View v){
        if(v.getId() == R.id.button) {
            Intent intent = new Intent(getApplicationContext(), SecondActivity.class);
            intent.putExtra("num1", 1000);
            startActivityForResult(intent, 100); //startActivity(intent); 이것과 달라 호출된 activity에서 finish할 때 어떤 값을 받아오겠다는 의미
            // 100번으로 요청된 결과는 위에 override된 onActivitiResult에 저장된다.
        }
        if(v.getId() == R.id.btThird){
            Intent intent = new Intent(getApplicationContext(), ThirdActivity.class);
            intent.putExtra("num1", 1000);
            startActivityForResult(intent, 101);
        }



    }
}
