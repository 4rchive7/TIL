package com.example.student.p554;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    ProgressBar progressBar;
    MyTask myTask;
    Button button;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button =  findViewById(R.id.button);
        textView = findViewById(R.id.textView);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressBar = findViewById(R.id.progressBar);
        myTask = new MyTask();
    }


    public void clickBt(View v){
        myTask = new MyTask("192.168.111.100"); // 한 번을 초과하면 오류가 나기 때문에 이것을 추가해줘서 새로운 Thread를 생성한다.
        myTask.execute();
        Log.d("clickBt", "````````````````````");

    }

    class MyTask extends AsyncTask<String, Integer, Integer> { //<parameter, parameter to progressUpdate , func type>
                                                            //generics를 써주고 alt + enter implement method 누르면 자동으로 생성해줌

        int result;
        String msg;

        public MyTask(){
        }
        public MyTask(String msg){
            this.msg = msg;
        }



        @Override
        protected void onPreExecute() {
            progressBar.setMax(55);
            textView.setText("Start Thread");
            button.setEnabled(false);
            progressDialog.setTitle("Progress");
            progressDialog.setMessage("Ing ....");
            progressDialog.show();
        }



        @Override
        protected Integer doInBackground(String... Strings) { //여기가 Thread의 run 부분에 해당하는 것임
            Log.d("doInBackground", "start````````````````````" + msg);

            for(int i=0;i<=10; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                result += i;
                publishProgress(result);

            }
            Log.d("doInBackground", "end````````````````````");
            return result;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            progressBar.setProgress(values[0].intValue());

        }
        @Override
        protected void onPostExecute(Integer aVoid) {
            textView.setText("End Thread " + aVoid);
            button.setEnabled(true);
            progressDialog.dismiss();
        }
    }
}
