package com.example.student.myapplication;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    EditText editText;
    TextView textView;
    TestNetwork testNetwork;
    String testUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.editText);
        textView = findViewById(R.id.textView);
        testUrl = "http://70.12.114.144/wc/test.do";
        //new Thread(r).start();

    }

    public void clicked(View v){
        testNetwork = new TestNetwork(testUrl);
        testNetwork.execute(editText.getText().toString());
    }

    //AsyncTask<A, B, C>
    //A : 외부에서 execute()로 들어오는 param(가변인자) 타입
    //B : onProgressUpdate의 params 타입
    //C : doInBackground return 타입
    public class TestNetwork extends AsyncTask<String, Void, String>{

        String url;
        TestNetwork(){}

        TestNetwork(String url){
            this.url = url;
        }

        @Override
        protected String doInBackground(String... strings) {

            String msg = strings[0];

            url += "?msg="+msg;

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
            textView.setText(s.trim());
        }
    }
}
