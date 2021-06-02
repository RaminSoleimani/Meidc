package com.example.medic3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.geo.BackendlessGeoQuery;
import com.backendless.geo.GeoPoint;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptor;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class tracker extends AppCompatActivity implements OnMapReadyCallback , LocationListener {

    private GoogleMap mMap;
    LocationManager locationmanger;
    String provider;
    double lat=41.3851, lng=2.1734;
    ImageButton ibSend;
    boolean isExistingPoint;
    GeoPoint exsitingPoint;
    List<GeoPoint> list;

    ImageButton getIbSend;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracker);


        ibSend=(ImageButton) findViewById(R.id.ibSend);
        String type=getIntent().getStringExtra("type");

        if(type.equals("Location"))
        {
            ibSend.setVisibility(View.GONE);
            BackendlessGeoQuery geoQuery=new BackendlessGeoQuery();
            geoQuery.addCategory("MedicApp");
            geoQuery.setIncludeMeta(true);
            Backendless.Geo.getPoints(geoQuery, new AsyncCallback<List<GeoPoint>>() {
                @Override
                public void handleResponse(List<GeoPoint> response) {

                    list=response;

                    if(list.size()!=0)
                    {
                        for(int i=0; i<list.size(); i++)
                        {
                            LatLng positionMarker=new LatLng(list.get(i).getLatitude(),list.get(i).getLatitude());
                            mMap.addMarker(new MarkerOptions().position(positionMarker).snippet(list.get(i).getMetadata("name").toString())
                                    .title(list.get(i).getMetadata("name").toString()));

                        }
                    }

                }

                @Override
                public void handleFault(BackendlessFault fault) {

                    Toast.makeText(tracker.this,fault.getMessage(), Toast.LENGTH_SHORT).show();

                }
            });

        }
        else
            {
                ibSend.setVisibility(View.GONE);
                BackendlessGeoQuery geoQuery=new BackendlessGeoQuery();
                geoQuery.addCategory("MedicApp");
                geoQuery.setIncludeMeta(true);
                Backendless.Geo.getPoints(geoQuery, new AsyncCallback<List<GeoPoint>>() {
                    @Override
                    public void handleResponse(List<GeoPoint> response) {

                        list=response;

                        if(list.size()!=0)
                        {
                            for(int i=0; i<list.size(); i++)
                            {
                                if(list.get(i).getMetadata("name").toString().equals(getIntent().getStringExtra("name")))
                                {
                                    isExistingPoint=true;
                                    exsitingPoint=list.get(i);
                                    break;
                                }

                            }
                        }

                        ibSend.setVisibility(View.VISIBLE);

                    }

                    @Override
                    public void handleFault(BackendlessFault fault) {

                        Toast.makeText(tracker.this,fault.getMessage(), Toast.LENGTH_SHORT).show();

                    }
                });


            }

        ibSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(tracker.this, "Sending Location", Toast.LENGTH_SHORT).show();
                if(!isExistingPoint)
                {
                    List<String> categories= new ArrayList<>();
                    categories.add("MedicApp");
                    Map<String,Object>  meta=new HashMap<String,Object>();
                    meta.put("name",getIntent().getStringExtra("type"));
                    meta.put("update",new Date().toString());
                    Backendless.Geo.savePoint(lat, lng, categories, meta, new AsyncCallback<GeoPoint>() {
                        @Override
                        public void handleResponse(GeoPoint response) {
                            Toast.makeText(tracker.this, "Successfully location saved", Toast.LENGTH_SHORT).show();
                            isExistingPoint=true;
                            exsitingPoint=response;

                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {
                            Toast.makeText(tracker.this, fault.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    });
                }
                else
                {
                    Backendless.Geo.removePoint(exsitingPoint, new AsyncCallback<Void>() {
                        @Override
                        public void handleResponse(Void response) {
                            List<String> categories= new ArrayList<>();
                            categories.add("MedicApp");
                            Map<String,Object>  meta=new HashMap<String,Object>();
                            meta.put("name",getIntent().getStringExtra("type"));
                            meta.put("update",new Date().toString());
                            Backendless.Geo.savePoint(lat, lng, categories, meta, new AsyncCallback<GeoPoint>() {
                                @Override
                                public void handleResponse(GeoPoint response) {
                                    Toast.makeText(tracker.this, "Successfully location saved", Toast.LENGTH_SHORT).show();
                                    isExistingPoint=true;
                                    exsitingPoint=response;

                                }

                                @Override
                                public void handleFault(BackendlessFault fault) {
                                    Toast.makeText(tracker.this, fault.getMessage(), Toast.LENGTH_SHORT).show();

                                }
                            });

                        }

                        @Override
                        public void handleFault(BackendlessFault fault) {

                        }
                    });

                }
            }
        });

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);


        locationmanger=(LocationManager)getSystemService(Context.LOCATION_SERVICE);
        Criteria criteria=new Criteria();
        provider=locationmanger.getBestProvider(criteria,false);
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED )
        {
            ActivityCompat.requestPermissions(tracker.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION},0);

        }
        else
        {
            Location location=locationmanger.getLastKnownLocation(provider);
            if(location!=null)
            {
                onLocationChanged(location);
            }

        }


    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng position = new LatLng(lat, lng);
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(position,10));
        mMap.animateCamera(CameraUpdateFactory.zoomTo(10),200,null);
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED )
        {
            ActivityCompat.requestPermissions(tracker.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION},0);

        }
        else
        {

            mMap.setMyLocationEnabled(true);
        }

        mMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);


    }

    @Override
    public void onLocationChanged(Location location) {
        lat=location.getLatitude();
        lng=location.getLongitude();

        if(mMap!=null)
        {
            LatLng position=new LatLng(lat,lng);
            mMap.addMarker(new MarkerOptions().icon(BitmapDescriptorFactory.fromResource(R.mipmap.person))
                    .anchor(0.0F, 1.0F)
                    .title("Last Location").
                    position(position));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(position));
        }


    }

    @Override
    protected void onPause() {
        super.onPause();

        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED )
        {
            ActivityCompat.requestPermissions(tracker.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION},0);

        }
        else
        {

           locationmanger.removeUpdates(this);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                Manifest.permission.ACCESS_FINE_LOCATION)!=
                PackageManager.PERMISSION_GRANTED )
        {
            ActivityCompat.requestPermissions(tracker.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                    Manifest.permission.ACCESS_FINE_LOCATION},0);

        }
        else
        {

            locationmanger.requestLocationUpdates(provider,180000,50,this);
        }



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
