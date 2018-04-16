package com.example.student.map;


import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;


public class MapsActivity extends FragmentActivity{

    MapControll mapCtrl;
    WebView web;
    ImageView img;
    RelativeLayout linearMap;
    final int REQUEST_PLACE_PICKER = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        web = findViewById(R.id.web);
        img = findViewById(R.id.img);
        linearMap = findViewById(R.id.linearMap);
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        //R.id.map아이디를 갖고 오는 것은 다른 class에서 가져올 수 없음(?)
        mapCtrl = new MapControll(mapFragment);


        vision(0);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapCtrl.loadMap();
    }

    public void vision(int comm){
        web.setVisibility(View.INVISIBLE);
        img.setVisibility(View.INVISIBLE);
        linearMap.setVisibility(View.INVISIBLE);

        if(comm == 1){
            linearMap.setVisibility(View.VISIBLE);
        }else if(comm == 2){
            img.setVisibility(View.VISIBLE);
        }else if(comm == 3){
            web.setVisibility(View.VISIBLE);
        }
    }


    public void clickBt(View v){
        if(v.getId() == R.id.bt1){
            vision(1);
        }else if(v.getId() == R.id.bt2){
            vision(2);
        }else if(v.getId() == R.id.bt3){
            vision(3);
            web.setWebViewClient(new WebViewClient());
            web.loadUrl("http://www.naver.com");
        }else if(v.getId() == R.id.iBt1){
            LatLng latLng = new LatLng(35.1152138,129.0400702);
            mapCtrl.showCurrentLocation(latLng,"Bakeries");
        }else if(v.getId() == R.id.iBt2){
            LatLng latLng = new LatLng(35.1152138,129.0400702);
            mapCtrl.showCurrentLocation(latLng,"Bars");
        }else if(v.getId() == R.id.iBt3){
            LatLng latLng = new LatLng(35.1152138,129.0400702);
            mapCtrl.showCurrentLocation(latLng,"Cafes");

        }

    }





}
