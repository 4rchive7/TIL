package com.example.student.workshop2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {

    final String TAG = "Service1 working...";
    public MyService() {
    }

    @Override
    public void onCreate() {
        super.onCreate();

        Log.d(TAG, "Service onCreate.....");
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "service working", Toast.LENGTH_SHORT).show();
        if(intent == null){
            return Service.START_STICKY;
        }else{
            progress(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public void progress(Intent intent){

        final Intent sintent = new Intent(getApplicationContext(), MainActivity.class);
        sintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_SINGLE_TOP|
                Intent.FLAG_ACTIVITY_CLEAR_TOP);

        Toast.makeText(this, "progress working", Toast.LENGTH_SHORT).show();

        Runnable run = new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=5;i++) {

                    try {

                        Log.d(TAG, "Process : " + i);
                        sintent.putExtra("time", i);
                        sintent.putExtra("name", "service1");
                        Thread.sleep(1000);
                        startActivity(sintent);


                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            }
        };


        new Thread(run).start();
    }






}
