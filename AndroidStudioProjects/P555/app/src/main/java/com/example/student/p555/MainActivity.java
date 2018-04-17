package com.example.student.p555;

import android.app.AlertDialog;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.concurrent.ExecutionException;

public class MainActivity extends AppCompatActivity {
    EditText editText, editText2;
    TextView textView;
    ProgressDialog progressDialog;
    LoginTask loginTask;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        textView = findViewById(R.id.textView);
        button = findViewById(R.id.button);
        progressDialog = new ProgressDialog(MainActivity.this);

        progressDialog.setTitle("이거 되는거냐?");
        progressDialog.setCancelable(false);
    }

    public void clickLogin(View v) throws ExecutionException, InterruptedException {

        loginTask = new LoginTask();
        String id = editText.getText().toString();
        String pwd = editText2.getText().toString();
        String result = "";

        result = loginTask.execute(id,pwd).get();



    }

    class LoginTask extends AsyncTask<String,Void ,String>{

        @Override
        protected void onPreExecute() {
            button.setEnabled(false);
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String id = strings[0], pwd = strings[1];
            String result = "";

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            if(id.equals("qq") && pwd.equals("11")){
                result = "1";
            }else{
                result = "0";
            }
            return result;
        }

        @Override
        protected void onPostExecute(String s) {

            AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
            button.setEnabled(true);
            progressDialog.dismiss();
            if(s.equals("1")){
                textView.setText("Login Ok");
                dialog.setTitle("Alert");
                dialog.setMessage("Login Ok");
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editText.setText("");
                        editText2.setText("");
                        return;
                    }
                });
                AlertDialog alert = dialog.create();
                alert.show()
            }else{
                textView.setText("Login Fail");
                dialog.setTitle("Alert");
                dialog.setMessage("Login Fail");
                dialog.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        editText.setText("");
                        editText2.setText("");
                        return;
                    }
                });
                AlertDialog alert = dialog.create();
                alert.show()
            }

        }

    }
}
