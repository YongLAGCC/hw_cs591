package com.example.c3_p29;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private EditText txtItem1;
    private EditText txtItem2;
    private EditText txtItem3;
    private EditText txtItem4;

    private Button btnCalculate;
    private EditText txtResult;

    private Calculator calculator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtItem1 = (EditText) findViewById(R.id.txtItem1);
        txtItem2 = (EditText) findViewById(R.id.txtItem2);
        txtItem3 = (EditText) findViewById(R.id.txtItem3);
        txtItem4 = (EditText) findViewById(R.id.txtItem4);
        btnCalculate = (Button) findViewById(R.id.btnCalculate);
        txtResult = (EditText) findViewById(R.id.txtResult);

        calculator = new Calculator();

        btnCalculate.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                Integer chicken, vegetable, potato, ice;
                chicken = tryParse(txtItem1.getText().toString());
                vegetable = tryParse(txtItem2.getText().toString());
                potato = tryParse(txtItem3.getText().toString());
                ice = tryParse(txtItem4.getText().toString());

                if (chicken == null || vegetable == null || potato == null || ice == null){
                    Toast.makeText(getBaseContext(), "Wrong input type", Toast.LENGTH_SHORT).show();
                    return;
                }

                int total = calculator.calculate(chicken, vegetable, potato, ice);

                txtResult.setText(String.valueOf(total));

            }
        });

    }

    private Integer tryParse(String s){
        try{
            return Integer.parseInt(s);
        }catch (NumberFormatException e){
            return null;
        }
    }
}
