package bu.edu.cs591.w3_p2;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final String MyFlag = "REDSOX";  //this will be our trail of breadcrumbs for logging events.
    private static int eventCount = 0;

    private static final double celsiusDownLimit = -30.00;
    private static final double celsiusUpLimit = 40.00;
    private static final double fahrenheitDownLimit = -100.00;
    private static final double fahrenheitUpLimit = 150.00;
    private static final int hotThreshold = 10;

    private Button btnCtoF;
    private TextView txtC;
    private TextView txtF;
    private TextView txtM;

    private Button btnSayHello;
    private TextView tvMsg;
    private EditText edtMsg;

    private SeekBar barCelsius;
    private SeekBar barFahrenheit;

    private double celsius2Fahrenheit(double c) {
        return c*9.0/5.0 + 32;
    }

    private double fahrenheit2Celsius(double c) {
        return (c - 32) / (1.8);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onCreate State Transition");
        setContentView(R.layout.activity_main);


//Let's find our view references

        //btnCtoF = (Button) findViewById(R.id.btnCtoF);
        txtC = (TextView) findViewById(R.id.txtC);
        txtF = (TextView) findViewById(R.id.txtF);
        txtM = (TextView) findViewById(R.id.txtMsg);

        barCelsius = (SeekBar) findViewById(R.id.barCelsius);
        barFahrenheit = (SeekBar) findViewById(R.id.barFahrenheit);
        barCelsius.setMax(100);
        barFahrenheit.setMax(100);
        barCelsius.setProgress(0);
        barFahrenheit.setProgress(0);

        barCelsius.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Double DegC = ((double)i / 100.0) * (celsiusUpLimit - celsiusDownLimit) + celsiusDownLimit;
                String C = DegC.toString();
                txtC.setText(C);

                if (DegC < hotThreshold) {
                    String coldMsg = getResources().getString(R.string.cold_msg);
                    txtM.setText(coldMsg);
                }
                else {
                    String hotMsg = getResources().getString(R.string.hot_msg);
                    txtM.setText(hotMsg);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String C = txtC.getText().toString();
                Double DegC = Double.parseDouble(C);
                Double DegF = celsius2Fahrenheit(DegC);

                int progress = (int) ((DegF - fahrenheitDownLimit) /
                        (fahrenheitUpLimit - fahrenheitDownLimit) * 100);
                barFahrenheit.setProgress(progress);

                if (progress >= 100) {
                    String F = ">=" + fahrenheitUpLimit;
                    txtF.setText(F);
                } else if (progress <= 0) {
                    String F = fahrenheitDownLimit + "<=";
                    txtF.setText(F);
                }
            }
        });

        barFahrenheit.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                Double DegF = ((double)i / 100.0) * (fahrenheitUpLimit - fahrenheitDownLimit) + fahrenheitDownLimit;
                String F = DegF.toString();
                txtF.setText(F);

                if (fahrenheit2Celsius(DegF) < hotThreshold) {
                    String coldMsg = getResources().getString(R.string.cold_msg);
                    txtM.setText(coldMsg);
                }
                else {
                    String hotMsg = getResources().getString(R.string.hot_msg);
                    txtM.setText(hotMsg);
                }
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                String F = txtF.getText().toString();
                Double DegF = Double.parseDouble(F);
                Double DegC = fahrenheit2Celsius(DegF);

                int progress = (int) ((DegC - celsiusDownLimit) /
                        (celsiusUpLimit - celsiusDownLimit) * 100);
                barCelsius.setProgress(progress);

                if (progress >= 100) {
                    String C = ">=" + celsiusUpLimit;
                    txtC.setText(C);
                } else if (progress <= 0) {
                    String C = celsiusDownLimit + "<=";
                    txtC.setText(C);
                }
            }
        });

    }

    public void GeneralClick(View v) {
        Toast.makeText(getBaseContext(), "This is Early Bound", Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.i(MyFlag, ": Activity onStop State Transition");
    }

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onRestoreInstanceState(savedInstanceState, persistentState);
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onRestoreInstanceState State Transition");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onSaveInstanceState State Transition");
        Log.i(MyFlag, "Bundling State of our views before they get destroyed");
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onRestoreInstanceState State Transition");
        Log.i(MyFlag, "Retrieving our saved state from before... ");
        super.onRestoreInstanceState(savedInstanceState);
    }

    @Override
    protected void onDestroy() {
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onDestroy State Transition");
        super.onDestroy();
    }
    //
    @Override
    protected void onResume() {
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onResume State Transition");
        super.onResume();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(MyFlag,"Logging the OnStart Activity.");

    }


    @Override
    protected void onPause() {
        super.onPause();
        Log.i(MyFlag,"Logging the onPause Activity.");
    }



    //Handy Helpers...
    public String intToStr(Integer i)
    {
        Log.i(MyFlag, "converting: " + i.toString());
        return i.toString();
    }

    public int strToInt(String S)
    {
        return Integer.parseInt(S);
    }


}


