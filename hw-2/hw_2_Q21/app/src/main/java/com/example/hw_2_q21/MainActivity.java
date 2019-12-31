package com.example.hw_2_q21;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String MyFlag = "REDSOX";  //this will be our trail of breadcrumbs for logging events.
    private static int eventCount = 0;


    private Button btnCtoF;
    private TextView txtOne;
    private TextView txtTwo;
    private TextView txtSum;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}
