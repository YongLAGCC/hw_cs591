package com.example.googlemapsgoogleplaces;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;


public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    //Check version throw error of 9001
    private static final int ERROR_DIALOG_REQUEST = 9001;

    private boolean mLocationPermissionGranted = false;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }





}
