package com.example.q23;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private Button change;
    private TextView label;

    int flag = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        change = (Button) findViewById(R.id.change);
        label = (TextView) findViewById(R.id.label);

        change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(flag==0){
                    label.setBackgroundColor(getResources().getColor(R.color.red));
                }else if(flag==1){
                    label.setBackgroundColor(getResources().getColor(R.color.green));
                }else if(flag==2){
                    label.setBackgroundColor(getResources().getColor(R.color.yellow));
                }
                flag=(flag+1)%3;
            }
        });
    }
}
