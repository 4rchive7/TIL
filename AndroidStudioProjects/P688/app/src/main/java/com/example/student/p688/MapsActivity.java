package com.example.student.p688;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    public void clickBt(View v) {

        if (v.getId() == R.id.button) {
            requestMyLocation();
        } else if (v.getId() == R.id.button2) {

            LatLng latLng = new LatLng(35.1152138, 129.0400702);
            showCurrentLocation(latLng);

        } else if (v.getId() == R.id.button3) {
            LatLng latLng = new LatLng(35.1653428, 126.9070116);
            showCurrentLocation(latLng);
        }

    }


    private void requestMyLocation() {
        //이 함수는 manager 중심으로 해석해보면 GPS, NETWORK 모두 사용한다.
        //GPS가 실패하면 Network로 이용해서
        //어떻게든 위치를 표시하겠다 뭐 이런 뜻이지
        //심지어 중간에는 lastLocation으로 GPS 못잡으면 마지막 위치를 잡겠다고 함
        LocationManager manager =
                (LocationManager) getSystemService(Context.LOCATION_SERVICE);

        try {
            long minTime = 10000; //10초 기다림
            float minDistance = 0;
            manager.requestLocationUpdates(
                    LocationManager.GPS_PROVIDER, // <= 점심시간에 밖에 나가서 해보자!
                    minTime,
                    minDistance,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            LatLng curPoint = new LatLng(location.getLatitude(), location.getLongitude());
                            showCurrentLocation(curPoint);
                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {

                        }

                        @Override
                        public void onProviderDisabled(String provider) {

                        }
                    }
            );

            //아래에는 GPS 실패시 마지막으로 접속했던 위치 정보를 뿌린다.
            Location lastLocation = manager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (lastLocation != null) {
                LatLng curPoint = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                showCurrentLocation(curPoint);
            }


            manager.requestLocationUpdates(
                    LocationManager.NETWORK_PROVIDER,
                    minTime,
                    minDistance,
                    new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            LatLng curPoint = new LatLng(location.getLatitude(), location.getLongitude());
                            showCurrentLocation(curPoint);
                        }

                        @Override
                        public void onStatusChanged(String provider, int status, Bundle extras) {

                        }

                        @Override
                        public void onProviderEnabled(String provider) {

                        }

                        @Override
                        public void onProviderDisabled(String provider) {

                        }
                    }
            );
        } catch (SecurityException e) {
            e.printStackTrace();
        }
    }


    private void showCurrentLocation(LatLng latLng) {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.addMarker(new MarkerOptions().position(latLng));
        mMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 15)); //지도를 이동 시킨다 v:는 숫자가 작을 수록 광범위 19 => 작은 범위
    }


    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(37.500568,127.0343228);
        mMap.addMarker(new MarkerOptions().position(sydney).title("MyLoc"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
        requestMyLocation();
    }
}
