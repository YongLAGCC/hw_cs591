package com.example.q22_11;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final String MyFlag = "REDSOX";  //this will be our trail of breadcrumbs for logging events.
    private static int eventCount = 0;
    private Button btnCtoF;
    private TextView txtC;
    private TextView txtF;
    private TextView txtSum;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        eventCount++;
//        Log.i(MyFlag, intToStr(eventCount) + ": Activity onCreate State Transition");
        setContentView(R.layout.activity_main);


        btnCtoF = (Button) findViewById(R.id.btnCtoF);
        txtC = (TextView) findViewById(R.id.txtC);
        txtF = (TextView) findViewById(R.id.txtF);
        txtSum = (TextView) findViewById(R.id.txtSum);

        btnCtoF.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                Toast.makeText(getBaseContext(),"This is an On Long Click Event", Toast.LENGTH_SHORT).show();
                return true;
            }
        });

        btnCtoF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String C, F;
                Double DegC;
                Double DegF;
                Double DegSum;
                C = txtC.getText().toString();

                F = txtF.getText().toString();

                DegC = Double.parseDouble(C);
                DegF = Double.parseDouble(F);
                DegSum = DegC + DegF;   //todo, dblcheck formula.
                F = DegSum.toString();
                txtSum.setText(F);
            }
        });







    }
}
