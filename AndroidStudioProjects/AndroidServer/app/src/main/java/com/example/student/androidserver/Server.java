package com.example.student.androidserver;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.TextView;

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


    int msg1, msg2;
    String comm = "";

    ServerSocket serverSocket;
    StartServer start;
    Boolean flag = true;
    ArrayList<DataOutputStream> list = new ArrayList<>();

    public Server() throws IOException {
        serverSocket = new ServerSocket(9999);
        start = new StartServer();
       Log.d("test","Server Ready...");
    }

    public int getMsg1(){
        return msg1;
    }
    public int getMsg2(){
        return msg2;
    }
    public String getComm(){
        return comm;
    }

    class StartServer extends AsyncTask<Void, Void, Void>{

        @Override
        protected Void doInBackground(Void... voids) {
            while (true) {
                Socket socket = null;
                try {
                    socket = serverSocket.accept();
                    System.out.println("NewClient Connected : " + socket.getInetAddress());
                    Receiver reciever = new Receiver(socket);
                    reciever.execute();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if(comm.equals("true"))
                    break;
            }
            return null;
        }
    }


    class SendHttp extends AsyncTask<Void, Void, Void> {
        String temp;
        String speed;
        String urls = "http://70.12.114.144/ws/main.do";

        public SendHttp() {
        }

        public SendHttp(String speed, String temp) {
            this.speed = speed;
            this.temp = temp;
        }
        @Override
        protected Void doInBackground(Void... voids) {
            urls = urls + "?speed=" + speed+"&"+"temp="+temp;
            try {
                URL url = new URL(urls);
                HttpURLConnection conn = (HttpURLConnection)url.openConnection();
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

    class Receiver extends AsyncTask<Void, Void, Void> {

        InputStream in;
        DataInputStream din;
        OutputStream out;
        DataOutputStream dout;

        public Receiver() {
        }

        public Receiver(Socket socket) throws IOException {
            in = socket.getInputStream();
            din = new DataInputStream(in);

            out = socket.getOutputStream();
            dout = new DataOutputStream(out);
            list.add(dout);
            System.out.println(list.size() + " pc are connected");
        }

        @Override
        protected Void doInBackground(Void... voids) {
            while (din != null) {
                String str = "";
                try {
                    str = din.readUTF();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    break;
                }

                if (str != null && str.equals("q")) {
                    break;
                }

                int partition = str.indexOf(',');
                String speed = str.substring(0, partition).trim();
                String temp = str.substring(partition+1, str.length()).trim();

                msg1 = Integer.parseInt(speed);
                msg2 = Integer.parseInt(temp);

                SendHttp http = new SendHttp(speed, temp);
                http.execute();
            }
            list.remove(dout); // dout은 아직 이 class 안에서 존재했기 때문에 무슨 dout인지 정해줄 필요 없음
            if (dout != null) {
                try {
                    dout.close();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
            return null;
        }

    }


}
