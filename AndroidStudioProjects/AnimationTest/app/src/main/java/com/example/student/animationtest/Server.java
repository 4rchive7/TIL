package com.example.student.animationtest;

import android.os.AsyncTask;
import android.util.Log;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;

public class Server {

    final int accelPedal = 0;
    final int breakPedal = 1;
    final int distance = 2;
    final int sleep = 3;

    Variable var;


    int msg1;
    int msg2;
    String comm = "";
    Socket socket = null;

    int carState[] = new int[10];

    ServerSocket serverSocket;
    StartServer start;
    Sender sender;
    Boolean flag = true;
    ArrayList<DataOutputStream> list = new ArrayList<>();

    public Server() throws IOException {
        serverSocket = new ServerSocket(9999);
        Log.d("SERVER]", "Success to get Socket, Port : 9999");
        start = new StartServer();
        Log.d("test","Server Ready...");
    }

    public int getMsg1(){
        return msg1;
    }
    public int getMsg2(){
        return msg2;
    }
    public String getComm() { return comm; }

    class StartServer extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                Log.d("SERVER]", "Ready to Accept");
                socket = serverSocket.accept();
                Log.d("SERVER]", "accept Complete");
                Receiver receiver = new Receiver(socket);
                Log.d("SERVER]","Receiver Started");
                receiver.start();
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

    }

    public void sendMsg(int id, int value){
        Sender sender = null;
        try {
            if(socket != null)
                sender = new Sender(socket);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(socket != null) {
            sender.setSendMsg(id, value);
            sender.start();
        }
    }

    class Sender extends Thread {

        OutputStream out;
        DataOutputStream dout;
        String sendMsg;

        public Sender() {
        }


        public Sender(Socket socket) throws IOException{
            out = socket.getOutputStream();
            dout = new DataOutputStream(out);
            Log.d("Receiver Class]", "initializing.... complete");
        }

        public void setSendMsg(int id, int value){
            this.sendMsg = id + "," + value;
        }

        @Override
        public void run() {
            if(dout != null){
                try {
                    Log.d("Sender]", "run: !!!!!!!!");
                    dout.writeUTF(sendMsg);
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }




    class Receiver extends Thread {

        InputStream in;
        DataInputStream din;
        OutputStream out;
        DataOutputStream dout;
        boolean httpStart = false;

        public Receiver() {
        }


        public Receiver(Socket socket) throws IOException{
            in = socket.getInputStream();
            din = new DataInputStream(in);

            out = socket.getOutputStream();
            dout = new DataOutputStream(out);
            list.add(dout);
            httpStart = false;
            Log.d("Receiver Class]", "initializing.... complete");


        }


        @Override
        public void run() {
            while (din != null) {
                httpStart = true;
                String str;
                try {
                    str = din.readUTF();
                } catch (IOException e) {
                    str = null;
                    //Log.d("Receiver Class]", " din.readUTF() fail");
                    continue;
                }
                comm = null;

                if(str == null) continue;

                int partition = str.indexOf(',');
                String device = str.substring(0, partition).trim();
                String info = str.substring(partition+1, str.length()).trim();

                msg1 = Integer.parseInt(device);
                msg2 = Integer.parseInt(info);

                Log.d("Receiver Class]", msg1 + ", "+msg2);
                comm = "change";

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                SendHttp sendHttp = new SendHttp();
                sendHttp.execute();
            }
            if (dout != null) {
                try {
                    dout.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    class SendHttp extends AsyncTask<Void, Void, Void> {
        String info;
        String device;
        String urls = "http://70.12.114.14/Server/logAdd.do?CARID=2176";

        public SendHttp() {
        }

        public SendHttp(String device, String info) {
            this.device = device;
            this.info = info;
        }

        @Override
        protected Void doInBackground(Void... voids) {

            for(int i=1;i<7;i++){
                urls += ("&" + var.setAttributeName[i] + "=" + carState[i]);
            }
            Log.d("urls : ", urls);



            try {
                URL url = new URL(urls);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setConnectTimeout(5000);
                conn.getInputStream();
                System.out.println("Http Ok");
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("Http Error");
            }


            return null;
        }
    }
}
