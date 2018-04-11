package com.example.student.p227;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class MainActivity extends AppCompatActivity {
    WebView wv;
    LinearLayout ll;
    ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        makeui();
    }

    public void makeui(){
        wv = findViewById(R.id.wv);
        ll = findViewById(R.id.ll);
        iv = findViewById(R.id.iv);

        wv.setWebViewClient(new WebViewClient());
        wv.getSettings().setJavaScriptEnabled(true);
        //브라우저를 탑제한것과 같음
        wv.setVisibility(View.INVISIBLE);
        ll.setVisibility(View.INVISIBLE);
        iv.setVisibility(View.INVISIBLE);
    }

    public void clickbt(View v){
        if(v.getId() == R.id.bt1){

            ll.setVisibility(View.INVISIBLE);
            iv.setVisibility(View.INVISIBLE);
            wv.setVisibility(View.VISIBLE);
            wv.loadUrl("http://www.naver.com");


        }else if(v.getId() == R.id.bt2){

            wv.setVisibility(View.INVISIBLE);
            iv.setVisibility(View.INVISIBLE);
            ll.setVisibility(View.VISIBLE);

        }else if(v.getId() == R.id.bt3){

            wv.setVisibility(View.INVISIBLE);
            ll.setVisibility(View.INVISIBLE);
            iv.setVisibility(View.VISIBLE);
        }
    }
}
