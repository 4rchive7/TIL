package com.example.student.workshop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Calendar;


public class MainActivity extends AppCompatActivity {


    WebView web, anlz;
    LinearLayout si,su, ho;
    String myid="admin", mypwd="admin";
    String loginid, loginpwd;
    TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       makeui();


        textView = findViewById(R.id.timeview);
        // 쓰레드를 이용해서 시계표시

        Thread thread = new Thread() {

            @Override

            public void run() {

                while (!isInterrupted()) {

                    runOnUiThread(new Runnable() {

                        @Override

                        public void run() {
                            Calendar calendar = Calendar.getInstance();
                            int hour = calendar.get(Calendar.HOUR_OF_DAY); // 시
                            int minute = calendar.get(Calendar.MINUTE); // 분
                            int second = calendar.get(Calendar.SECOND); // 초

                            textView.setText(hour + ":" + minute + ":" + second + "\n");

                        }

                    });

                    try {

                        Thread.sleep(1000); // 1000 ms = 1초

                    } catch (InterruptedException e) {

                        e.printStackTrace();

                    }

                } // while

            } // run()

        }; // new Thread() { };



        thread.start();




    }

    public void makeui(){
        ho = findViewById(R.id.llhome);
        si = findViewById(R.id.llsignin);
        su = findViewById(R.id.llsignup);
        web = findViewById(R.id.llweb);
        anlz = findViewById(R.id.llanlz);
    }

    public void setVision(String comm){
        ho.setVisibility(View.INVISIBLE);
        si.setVisibility(View.INVISIBLE);
        su.setVisibility(View.INVISIBLE);
        web.setVisibility(View.INVISIBLE);
        anlz.setVisibility(View.INVISIBLE);

        if(comm.equals("ho")){
            ho.setVisibility(View.VISIBLE);
        }else if(comm.equals("si")){
            si.setVisibility(View.VISIBLE);
        }else if(comm.equals("su")){
            su.setVisibility(View.VISIBLE);
        }else if(comm.equals("web")){
            web.setVisibility(View.VISIBLE);
        }else if(comm.equals("anlz")){
            anlz.setVisibility(View.VISIBLE);
        }
    }


    public void clicked(View v){
        if(v.getId() == R.id.home){
            setVision("ho");
        }else  if(v.getId() == R.id.signin){
            setVision("si");
            Toast.makeText(this, "signin!!!", Toast.LENGTH_SHORT).show();

        }else  if(v.getId() == R.id.signup){
            setVision("su");

        }else  if(v.getId() == R.id.analyze){
            setVision("anlz");
            anlz.loadUrl("http://70.12.114.144/GOTozo/chart4.jsp");

        }else  if(v.getId() == R.id.web){
            setVision("web");
            web.loadUrl("http://www.naver.com");
        }

        if(v.getId() == R.id.signupinbt){
            EditText tmp;
            tmp = findViewById(R.id.editinid);
            myid = tmp.getText().toString();
            tmp = findViewById(R.id.editinpw);
            mypwd = tmp.getText().toString();
            Toast.makeText(this, "성공적으로 회원가입되었습니다.", Toast.LENGTH_SHORT).show();
            setVision("ho");
        }

        if(v.getId() == R.id.longinbt){
            EditText tmp2;
            tmp2 = findViewById(R.id.loginid);
            loginid = tmp2.getText().toString();
            tmp2 = findViewById(R.id.editText5);
            loginpwd = tmp2.getText().toString();
            if(myid.equals(loginid) && mypwd.equals(loginpwd)) {
                Toast.makeText(this, "로그인 되었습니다.", Toast.LENGTH_SHORT).show();
                setVision("ho");
            }else{
                Toast.makeText(this, "누구세요?", Toast.LENGTH_SHORT).show();
            }
        }


    }
}
