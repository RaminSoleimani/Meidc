package com.example.medic3;

import android.app.Application;

import com.backendless.Backendless;

public class ApplicationClass extends Application {
    public static final String APPLICATION_ID = "5CB94B76-D1F8-FEFD-FFCE-5C67C53CC800";
    public static final String API_KEY = "DCDCD785-A94D-482A-9697-10CF9EB53FD4";
    public static final String SERVER_URL = "https://api.backendless.com";

    @Override
    public void onCreate() {
        super.onCreate();

        Backendless.setUrl( SERVER_URL );
        Backendless.initApp( getApplicationContext(),
                APPLICATION_ID,
                API_KEY );


    }
}
