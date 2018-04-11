package com.example.student.p221;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Resources res;
    ImageView imgV;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        res = getResources();
        makeui();
    }

    public void makeui(){
        imgV = findViewById(R.id.imgV);
    }

    public void clickBt(View v){
        //View 객체는 무엇인가? - 클릭된 것이 어느것인지 알 수 있게 해주는 것이다.

        BitmapDrawable bitmap = null;

        if(v.getId() == R.id.bt1)
            bitmap = (BitmapDrawable) res.getDrawable(R.drawable.bg5);
        else if(v.getId() == R.id.bt2)
            bitmap = (BitmapDrawable) res.getDrawable(R.drawable.bg6);
        else if(v.getId() == R.id.bt3)
            bitmap = (BitmapDrawable) res.getDrawable(R.drawable.bg7);
        imgV.setImageDrawable(bitmap);
    }


}
