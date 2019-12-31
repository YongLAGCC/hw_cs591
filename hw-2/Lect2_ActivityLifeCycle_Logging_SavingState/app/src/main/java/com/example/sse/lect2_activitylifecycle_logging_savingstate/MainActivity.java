package com.example.sse.lect2_activitylifecycle_logging_savingstate;

import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {

    private static final String MyFlag = "REDSOX";  //this will be our trail of breadcrumbs for logging events.
    private static int eventCount = 0;


    private Button btnCtoF;
    private EditText txtC;
    private EditText txtF;


    private Button btnSayHello;
    private TextView tvMsg;
    private EditText edtMsg;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        eventCount++;
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onCreate State Transition");        setContentView(R.layout.activity_main);


//Let's find our view references

        btnCtoF = (Button) findViewById(R.id.btnCtoF);
        txtC = (EditText) findViewById(R.id.txtC);
        txtF = (EditText) findViewById(R.id.txtF);


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
                Double DegC, DegF;
                C = txtC.getText().toString();
                DegC = Double.parseDouble(C);
                DegF = DegC*9.0/5.0 + 32;   //todo, dblcheck formula.
                F = DegF.toString();
                txtF.setText(F);

                if (DegC < 10)
                  Toast.makeText(getBaseContext(),"Boy it's Cold", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(getBaseContext(),"Boy it's Toasty...", Toast.LENGTH_LONG).show();

            }
        });




        //-----
        //part 2
        btnSayHello = (Button) findViewById(R.id.btnSayHello);
        tvMsg = (TextView) findViewById(R.id.tvMsg);
        edtMsg = (EditText) findViewById(R.id.edtMsg);

        btnSayHello.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tvMsg.setText("Hello");
                edtMsg.setText("Hello");
            }
        });




//        btnSayHello.setOnLongClickListener(new View.OnLongClickListener() {
//            @Override
//            public boolean onLongClick(View v) {
//                tvMsg.setText("Go Pats!");
//                edtMsg.setText("Go Pats!");
//                return true;
//            }
//        });

    }

    public void GeneralClick(View v) {
Toast.makeText(getBaseContext(), "This is Early Bound", Toast.LENGTH_LONG).show();
    }

//    @Override
//    protected void onPause() {
//        super.onPause();
//        Log.i(MyFlag, ": Activity onPause State Transition");
//    }
//
//    @Override
//    protected void onStop() {
//        super.onStop();
//        Log.i(MyFlag, ": Activity onStop State Transition");
//    }
//
//    public void onButtonClick(View v) {
//        return;
//    }



    //Useful Notes:
        // ctrl-O is a shortcut to override base methods
        // Alt-Ins is a shortcut to overriding base methods and more.

//    @Override
//    protected void onPause() {
//        eventCount++;
//        Log.i(MyFlag, intToStr(eventCount) + ": Activity onPause State Transition");
//        super.onPause();
//    }
//
//
//    @Override
//    protected void onStart() {
//        eventCount++;
//        Log.i(MyFlag, intToStr(eventCount) + ": Activity onStart State Transition");
//        super.onStart();
//    }
//
//    @Override
//    public void onRestoreInstanceState(Bundle savedInstanceState, PersistableBundle persistentState) {
//        super.onRestoreInstanceState(savedInstanceState, persistentState);
//        eventCount++;
//        Log.i(MyFlag, intToStr(eventCount) + ": Activity onRestoreInstanceState State Transition");
//    }
//
//    @Override
//    protected void onSaveInstanceState(Bundle outState) {
//        eventCount++;
//        Log.i(MyFlag, intToStr(eventCount) + ": Activity onSaveInstanceState State Transition");
//        Log.i(MyFlag, "Bundling State of our views before they get destroyed");
//        super.onSaveInstanceState(outState);
//    }
//
//    @Override
//    protected void onRestoreInstanceState(Bundle savedInstanceState) {
//        eventCount++;
//        Log.i(MyFlag, intToStr(eventCount) + ": Activity onRestoreInstanceState State Transition");
//        Log.i(MyFlag, "Retrieving our saved state from before... ");
//        super.onRestoreInstanceState(savedInstanceState);
//    }
//
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
        Log.i(MyFlag, intToStr(eventCount) + ": Activity onRestoreInstanceState State Transition");
        super.onResume();
    }

//
//    @Override
//    protected void onStart() {
//        super.onStart();
//        Log.i(MyFlag, "onStart: ");
//    }

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

