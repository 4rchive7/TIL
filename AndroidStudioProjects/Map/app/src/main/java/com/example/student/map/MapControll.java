package com.example.student.map;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.text.Html;
import android.view.View;

import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlacePicker;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;



public class MapControll implements OnMapReadyCallback{

    private GoogleMap mMap;
    SupportMapFragment mapFragment;



    public MapControll(SupportMapFragment mapFragment) {
        this.mapFragment = mapFragment;
    }

    final int PLACE_PICKER_REQUEST = 1;
    public void loadMap(){
        PlacePicker.IntentBuilder intentBuilder = new PlacePicker.IntentBuilder();
        LatLng x1 = new LatLng(37.500468,127.0343128);
        LatLng x2 = new LatLng(37.500568,127.0343228);
        LatLngBounds bounds = new LatLngBounds(x1,x2);
        intentBuilder.setLatLngBounds(bounds);

//        try {
//            Intent intent = intentBuilder.build(MainActivity.this);
//            startActivityForResult(intent,PLACE_PICKER_REQUEST);
//        } catch (GooglePlayServicesRepairableException e) {
//            e.printStackTrace();
//        } catch (GooglePlayServicesNotAvailableException e) {
//            e.printStackTrace();
//        }
//
//
//        출처: http://littlecold2.tistory.com/1 [날뛰는 코드]


        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng loc = new LatLng( 37.500568,127.0343228 );
        mMap.addMarker(new MarkerOptions().position(loc).title("Marker in Sydney"));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(loc, 15));
    }

    public void showCurrentLocation(LatLng location, String name) {
        mMap.addMarker(new MarkerOptions().position(location).title(name));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(location, 15));
    }






}
