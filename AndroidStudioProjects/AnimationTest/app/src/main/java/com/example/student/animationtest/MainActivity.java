package com.example.student.animationtest;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    int cluster[] = {
            R.drawable.cluster0, R.drawable.cluster1, R.drawable.cluster2, R.drawable.cluster3, R.drawable.cluster4, R.drawable.cluster5,
            R.drawable.cluster6, R.drawable.cluster7, R.drawable.cluster8, R.drawable.cluster9, R.drawable.cluster10, R.drawable.cluster11,
            R.drawable.cluster12, R.drawable.cluster13, R.drawable.cluster14, R.drawable.cluster15, R.drawable.cluster16, R.drawable.cluster17,
            R.drawable.cluster18, R.drawable.cluster19, R.drawable.cluster20, R.drawable.cluster21    };


    ImageView imageView;
    Boolean flag = true;


    int currDegree;

    ChangeCluster changeCluster;

    final int pedalReleased = 0;
    final int accelPedal = 1;
    final int breakPedal = 2;

    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = findViewById(R.id.image);
        imageView.setImageResource(cluster[3]);

        editText = findViewById(R.id.editText);
        changeCluster = new ChangeCluster();
        changeCluster.execute();



    }

    @Override
    protected void onDestroy() {
            flag = false;
            super.onDestroy();
    }

    public void clickBt(View v) throws InterruptedException {
        if(v.getId() == R.id.input){
            String rpmStr = (editText.getText()).toString();
            int rpm = Integer.parseInt(rpmStr);
            changeCluster.setRpm(rpm);
            Thread.sleep(300);
            changeCluster.setRpm(-1);
        }
    }



    public class ChangeCluster extends AsyncTask<Integer, Integer, Integer> {
        int engineStart;
        int newRpm;
        int rpmImg;
        int prevRpm;
        int prevRpmImg;
        int currRpm;
        int status;
        int rpmRate;
        int limitRpm;

        boolean using = true;
        final int rpmNone = 0;
        final int rpmUp = 1;
        final int rpmDown = 2;
        ChangeCluster(){ }

        ChangeCluster(int engineStart){
            this.engineStart = engineStart;
            newRpm = 0; currRpm = 0;;

        }

        public void setRpm(int newRpm){
            this.newRpm = newRpm;
        }

        @Override
        protected Integer doInBackground(Integer... integers) {
            Log.d("Thread", "start");
            while(flag){
                if(newRpm != -1){

                    Log.d("Thread", "currRpm1 : "+ currRpm);
                    if(newRpm > currRpm){
                        status = rpmUp;
                        rpmRate = (newRpm - currRpm)/4;
                        limitRpm = newRpm;
                    }else if(newRpm < currRpm && false){
                        rpmRate = currRpm - newRpm;
                        status = rpmDown;
                        limitRpm = newRpm;
                    }

                }


                if(status == rpmUp && currRpm <= limitRpm){
                    currRpm += rpmRate;
                    if(currRpm >= limitRpm){
                        Log.d("Thread", "currRpm : "+ currRpm +", limit : "+limitRpm);
                        currRpm = limitRpm;
                        status = rpmNone;
                    }
                    //status = rpmNone;
                }else if(status == rpmDown && currRpm >=0 && false){
                    currRpm -= rpmRate;
                    if(currRpm <= limitRpm){
                        Log.d("Thread", "currRpm : "+ currRpm +", limit : "+limitRpm);
                        currRpm = limitRpm;
                        status = rpmNone;

                    }
                    //status = rpmNone;
                }
                if(status == rpmNone){
                        currRpm -= 5;
                        if(currRpm < 0) currRpm = 0;
                }

                rpmImg = currRpm/26;
                if(rpmImg != prevRpmImg) {
                    Log.d("Thread", "Send Data to Progress");
                    prevRpmImg = rpmImg+2;
                    publishProgress(prevRpmImg);
                }

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
            return 0;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);

            Log.d("rpmImg", "onProgressUpdate: " + values[0]);

            if(values[0]>=4 && values[0]<22)
                imageView.setImageResource(cluster[values[0]]);
        }
    }
}
