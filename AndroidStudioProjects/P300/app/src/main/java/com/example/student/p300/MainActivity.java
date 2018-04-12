package com.example.student.p300;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    EditText editText;
    TextView textView;
    Intent intent;
    ProgressBar progressBar;
    ImageView imageView;


    ProgressDialog progressDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setMax(100);
        imageView = findViewById(R.id.imageView);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setCancelable(true);
        processIntent(intent);
    }

    public void clickBt(View v){
        if(v.getId() == R.id.button) {
            String name = editText.getText().toString();
            intent = new Intent(this, MyService.class);
            intent.putExtra("command", "start");
            intent.putExtra("name", name);
            startService(intent);
        }else if(v.getId() == R.id.button2){

            progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
            progressDialog.setMessage("진행중");
            progressDialog.show();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            progressDialog.dismiss();
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        processIntent(intent);

    }

    private void processIntent(Intent intent){
        if(intent != null){
            String command = intent.getStringExtra("command");
            int cnt = intent.getIntExtra("cnt", 0);
            textView = findViewById(R.id.textView);
            textView.setText(command + "----"+ cnt);
            //여기에 뭐 들어가야하는디

            if(cnt%2 == 1)
                imageView.setImageResource(R.drawable.bg1);
            else
                imageView.setImageResource(R.drawable.bg34);

            progressBar.setProgress(
                    cnt*10
            );
            //Toast.makeText(this, ""+command+" "+cnt, Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(intent != null){
            stopService(intent);
        }
    }

    @Override
    public void onBackPressed() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("경고!");
        builder.setMessage("나갈거야?");
        builder.setIcon(R.mipmap.icon1);

        builder.setNegativeButton("아니오", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return ;
            }
        });
        builder.setNeutralButton("취소", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return;
            }
        });
        builder.setPositiveButton("예", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                finish();
            }
        });

        builder.show();


    }
}
