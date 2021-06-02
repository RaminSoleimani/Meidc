package com.example.medic3;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.backendless.Backendless;
import com.backendless.async.callback.AsyncCallback;
import com.backendless.exceptions.BackendlessFault;
import com.backendless.geo.GeoCategory;

public class trackerpage extends AppCompatActivity {
    Button btnLocation, btnUser, btnCaregivaer, btnCaregivaer2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_trackerpage);
        Backendless.setUrl( backendlessclass.SERVER_URL );
        Backendless.initApp( getApplicationContext(),
                backendlessclass.APPLICATION_ID,
                backendlessclass.API_KEY );

        Backendless.Geo.addCategory("MedicApp", new AsyncCallback<GeoCategory>() {
            @Override
            public void handleResponse(GeoCategory response) {

            }

            @Override
            public void handleFault(BackendlessFault fault) {

                Toast.makeText(trackerpage.this,fault.getMessage(),Toast.LENGTH_SHORT).show();

            }
        });

        btnLocation=findViewById(R.id.btnLocation);
        btnUser= findViewById(R.id.btnUSER);
        btnCaregivaer=findViewById(R.id.btnCaregiver);
        btnCaregivaer2=findViewById(R.id.btnCaregiver2);
        btnLocation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED )
                {
                    ActivityCompat.requestPermissions(trackerpage.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},0);

                }
                else
                    {
                        Intent intent=new Intent(trackerpage.this,com.example.medic3.tracker.class);
                        intent.putExtra("type", "Location");
                        startActivity(intent);

                    }

            }
        });

        btnUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED )
                {
                    ActivityCompat.requestPermissions(trackerpage.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},0);

                }
                else
                {
                    Intent intent=new Intent(trackerpage.this,com.example.medic3.tracker.class);
                    intent.putExtra("type", "User");
                    startActivity(intent);

                }

            }
        });

        btnCaregivaer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED )
                {
                    ActivityCompat.requestPermissions(trackerpage.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},0);

                }
                else
                {
                    Intent intent=new Intent(trackerpage.this,com.example.medic3.tracker.class);
                    intent.putExtra("type", "Caregiver");
                    startActivity(intent);

                }

            }
        });

        btnCaregivaer2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ContextCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(getApplicationContext(),
                        Manifest.permission.ACCESS_FINE_LOCATION)!=
                        PackageManager.PERMISSION_GRANTED )
                {
                    ActivityCompat.requestPermissions(trackerpage.this, new String[] {Manifest.permission.ACCESS_FINE_LOCATION,
                            Manifest.permission.ACCESS_FINE_LOCATION},0);

                }
                else
                {
                    Intent intent=new Intent(trackerpage.this,com.example.medic3.tracker.class);
                    intent.putExtra("type", "Caregiver2");
                    startActivity(intent);

                }

            }
        });
    }
}
