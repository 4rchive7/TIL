package com.example.student.p593;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.os.AsyncTask;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText editText,editText2;
    LoginTask loginTask;
    ProgressDialog progressDialog;
    LocationTask locationTask;
    boolean flag = true;

    AlertDialog dialog;
    AlertDialog.Builder albuilder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        progressDialog = new ProgressDialog(MainActivity.this);
        albuilder = new AlertDialog.Builder(MainActivity.this);
       // dialog = new AlertDialog(MainActivity.this);
        new Thread(r).start();
    }

    Runnable r = new Runnable() {

        @Override
        public void run() {
            while(flag) {
                try {
                    Thread.sleep(30000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //좌표를 가져오기
                locationTask = new LocationTask("http://70.12.114.144/Android/location.jsp");
                locationTask.execute(12.513, 65.2134);
            }
        }
    };

    public void clickBt(View v){

        String id = editText.getText().toString();
        String pwd = editText2.getText().toString();
        if(id == null || pwd == null || id.equals("")){
            return ;
        }
        loginTask = new LoginTask("http://70.12.114.144/Android/login.jsp");
        loginTask.execute(id.trim(), pwd.trim());

    }


    class LoginTask extends AsyncTask<String, String ,String>{

        String url;
        public LoginTask(){
        }

        public LoginTask(String url){
            this.url = url;
        }


        @Override
        protected void onPreExecute() {
            progressDialog.setMessage("Login...");
            progressDialog.show();
        }

        @Override
        protected String doInBackground(String... strings) {
            String id = strings[0];
            String pwd = strings[1];

            url += "?id="+id+"&pwd="+pwd;

            HttpURLConnection conn = null;
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = null;
            try{
                URL url = new URL(this.url);
                conn = (HttpURLConnection)url.openConnection(); //이렇게 하면 연결 됨
                if(conn != null){
                    conn.setConnectTimeout(5000);
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Accept", "*/*");
                    if(conn.getResponseCode() != HttpURLConnection.HTTP_OK) {
                        return null;
                    }


                    reader = new BufferedReader(
                            new InputStreamReader(
                                    conn.getInputStream()
                            )
                    );
                    String line = null;
                    while(true){
                        line = reader.readLine();
                        if(line == null){
                            break;
                        }
                        sb.append(line);
                    }

                }
            }catch(Exception e){
                progressDialog.dismiss();
                return e.getMessage();
            }finally{
                try {
                    if(reader != null)
                        reader.close(); // 반드시 끊어주자!
                } catch (IOException e) {
                    e.printStackTrace();
                }
                conn.disconnect();
            }
            return sb.toString();
        }

        @Override
        protected void onPostExecute(String s) {
            progressDialog.dismiss();
            if(s.trim().equals("1"))
                Toast.makeText(MainActivity.this, "Login Ok", Toast.LENGTH_SHORT).show();
            else if(s.trim().equals("0"))
                Toast.makeText(MainActivity.this, "Login Fail", Toast.LENGTH_SHORT).show();
            else
                Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show();

        }
    }



    class LocationTask extends AsyncTask<Double, Void, String>{

        String url;
        public LocationTask(){
        }

        public LocationTask(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(Double... doubles) {

            double lat = doubles[0];
            double lon = doubles[1];

            url += "?lat="+lat+"&lon="+lon;

            HttpURLConnection conn = null;
            StringBuilder sb = new StringBuilder();
            try{
                URL url = new URL(this.url);
                conn = (HttpURLConnection)url.openConnection(); //이렇게 하면 연결 됨
                if(conn != null){
                    conn.setConnectTimeout(5000);
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Accept", "*/*");
                    if(conn.getResponseCode() != HttpURLConnection.HTTP_OK)
                        return null;
                }
            }catch(Exception e){
                return e.getMessage();
            }finally{
                conn.disconnect();
            }
            return "";
        }

    }

    @Override
    public void onBackPressed() {
        albuilder.setTitle("Alert");
        albuilder.setMessage("Are you Finish App ?");
        albuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                flag = false;
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finish(); //=> 너무 빨라서 flag 값 변경 안되고 넘어갈 수도 있으니 Thread.Sleep gogo!
            }
        });
        albuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                return ;
            }
        });
        dialog = albuilder.create();
        dialog.show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        flag = false;
    }
}
