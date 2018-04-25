package com.example.student.network;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {


    Boolean flag;
    EditText editText, editText2;
    SendData sendData;
    TextView textView, textView2;
    RecieveData recieveData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        textView = findViewById(R.id.textView);
        textView2 = findViewById(R.id.textView2);
        flag = false;
    }

    public void clickBt(View v){
        String data1 = editText.getText().toString();
        String data2 = editText2.getText().toString();
        sendData = new SendData("http://70.12.114.144/wc/terminal/data.jsp");
        sendData.execute(data1, data2);
    }

    public void clickBt2(View v){
        if(flag == true)
            flag = false;
        else
            flag = true;
        new Thread(r).start();
    }


    class SendData extends AsyncTask<String, String, String>{

        String url;
        SendData(){     }

        SendData(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... strings) {
            String data1 = strings[0];
            String data2 = strings[1];
            url += "?comm=send&data1="+data1+"&data2="+data2;
            HttpURLConnection conn = null;
            try {
                URL url = new URL(this.url);
                conn = (HttpURLConnection)url.openConnection(); //이렇게 하면 연결 됨
                if(conn != null){
                    conn.setConnectTimeout(5000);
                    Log.d("connected", "Timeout");
                    conn.setRequestMethod("GET");
                    Log.d("connected", "get");
                    conn.setRequestProperty("Accept", "*/*");
                    Log.d("connected", "accept");
                    if(conn.getResponseCode() != HttpURLConnection.HTTP_OK)
                        return null;
                }
                Log.d("connected", "finished");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                conn.disconnect();
            }
            return null;
        }
    }


    class RecieveData extends AsyncTask<String, String, String>{


        String url;
        RecieveData(){

        }

        RecieveData(String url){
            this.url = url;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected String doInBackground(String... strings) {

            //url += "?comm=recieve";
            HttpURLConnection conn = null;
            StringBuilder sb = new StringBuilder();
            BufferedReader reader = null;
            try{
                URL url = new URL(this.url);
                conn = (HttpURLConnection)url.openConnection();
                if(conn != null){
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
            textView.setText(s);
        }
    }

    Runnable r = new Runnable() {
        @Override
        public void run() {
            while(flag) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                //좌표를 가져오기
                //recieveData = new RecieveData("http://70.12.114.144/wc/terminal/data.jsp");
                recieveData = new RecieveData("http://70.12.114.144/wc/connection.do");
                recieveData.execute();
            }
        }
    };
}
