package com.example.student.p246;

import android.content.res.Resources;
import android.graphics.drawable.BitmapDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    Resources res;
    ImageView imgtop, imgbottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        res = getResources();
        mkui();
    }

    public void mkui(){
        imgtop = findViewById(R.id.imgvtop);
        imgbottom = findViewById(R.id.imgvbottom);
    }

    public void btclicked(View v){

        BitmapDrawable bitmap = null;

        if(v.getId() == R.id.btDown){
            bitmap =  (BitmapDrawable) res.getDrawable(R.drawable.bg4);
            imgbottom.setImageDrawable(bitmap);
            imgtop.setImageDrawable(null);

        }
        else if(v.getId() == R.id.btUp){
            bitmap =  (BitmapDrawable) res.getDrawable(R.drawable.bg4);
            imgtop.setImageDrawable(bitmap);
            imgbottom.setImageDrawable(null);
        }
    }
}
