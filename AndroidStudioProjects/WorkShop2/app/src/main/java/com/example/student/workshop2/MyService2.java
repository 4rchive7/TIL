package com.example.student.workshop2;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

public class MyService2 extends Service {
    public MyService2() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        Toast.makeText(this, "service2 working", Toast.LENGTH_SHORT).show();
        if(intent == null){
            return Service.START_STICKY;
        }else{
            process(intent);
        }
        return super.onStartCommand(intent, flags, startId);
    }

    public void process(Intent intent){

        Toast.makeText(this, "in process in Service2", Toast.LENGTH_SHORT).show();
        final Intent sintent = new Intent(getApplicationContext(), MainActivity.class);
        sintent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK |
                Intent.FLAG_ACTIVITY_SINGLE_TOP|
                Intent.FLAG_ACTIVITY_CLEAR_TOP);

//        Runnable run = new Runnable() {
//            @Override
//            public void run() {
//                for(int i=1;i<=10;i++){
//                    Toast.makeText(MyService2.this, "in Thread in service2", Toast.LENGTH_SHORT).show();
//
//                    try {
//                        sintent.putExtra("cnt", i*10);
//                        sintent.putExtra("name","service2");
//                        Thread.sleep(1000);
//                        startActivity(sintent);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//
//                }
//
//            }
//        };

        Runnable run = new Runnable() {
            @Override
            public void run() {
                for(int i=1;i<=10;i++) {
                    try {
                        sintent.putExtra("cnt", i*10);
                        sintent.putExtra("name","service2");
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
