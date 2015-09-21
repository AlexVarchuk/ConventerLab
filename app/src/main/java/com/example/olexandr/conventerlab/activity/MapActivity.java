package com.example.olexandr.conventerlab.activity;

import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import com.example.olexandr.conventerlab.R;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.io.IOException;
import java.util.List;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mGoogleMap;
    private String mAddress;
    private String mOrganizationName;

    private Double mLongitude;
    private Double mLatitude;

    private MainActivity main;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_map);
        Intent intent = getIntent();
        mAddress = intent.getStringExtra("addressOrganization");
        mOrganizationName = intent.getStringExtra("nameOrganization");


        initMap();


    }

    private void getLocation() {
        Geocoder geocoder = new Geocoder(this);
        List<Address> addresses = null;
        try {
            addresses = geocoder.getFromLocationName(mAddress, 1);
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (addresses.size() > 0 && addresses != null) {
            mLatitude = addresses.get(0).getLatitude();
            mLongitude = addresses.get(0).getLongitude();
            Log.i("geocoder", "широта :" + mLatitude + "долгота :" + mLongitude);

        } else {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Невозможно найти адрес, или нет соединиения с интернетом", Toast.LENGTH_SHORT);
            toast.show();
        }
    }


    private void initMap() {

        final MapFragment mapFragment = (MapFragment) getFragmentManager().findFragmentById(R.id.fragmentGoogleMap);
        mapFragment.getMapAsync(this);
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mGoogleMap = googleMap;
        if (mGoogleMap == null) return;
        mGoogleMap.getUiSettings().setZoomControlsEnabled(true);
        mGoogleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
        mGoogleMap.setMyLocationEnabled(true);
        if(main.checkInternetConnection()){

            getLocation();
        }else
        {
            return;
        }
        if (mLatitude != null && mLongitude != null) {
            mGoogleMap.moveCamera(CameraUpdateFactory.newLatLngZoom(new LatLng(mLatitude, mLongitude), 14));
            LatLng latLng = new LatLng(mLatitude, mLongitude);
            mGoogleMap.addMarker(new MarkerOptions()
                    .position(latLng)
                    .title("Организация: " + mOrganizationName + " Адрес:" + mAddress));
        }


    }

}
