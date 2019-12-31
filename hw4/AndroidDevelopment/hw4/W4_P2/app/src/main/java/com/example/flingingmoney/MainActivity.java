package com.example.flingingmoney;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity
        implements GestureDetector.OnGestureListener,
        GestureDetector.OnDoubleTapListener {

    private EditText euro;
    private TextView dollar;
    private TextView yen;
    private TextView florin;
    private TextView rupi;
    private GestureDetector GD;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        euro = (EditText) findViewById(R.id.euro);
        dollar = (TextView) findViewById(R.id.dollar);
        yen = (TextView) findViewById(R.id.yen);
        florin = (TextView) findViewById(R.id.florin);
        rupi = (TextView) findViewById(R.id.rupi);

        GD = new GestureDetector(this, this);
        GD.setOnDoubleTapListener(this);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.GD.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    public boolean onDown(MotionEvent e) {
        return false;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY) {
        if (e1.getY() - e2.getY() > 0){
            euro.setText(String.valueOf(Double.parseDouble(euro.getText().toString()) + 0.05));
        } else if (e1.getY() - e2.getY() < 0){
            euro.setText(String.valueOf(Double.parseDouble(euro.getText().toString()) - 0.05));
        }
        dollar.setText(String.valueOf(Double.parseDouble(euro.getText().toString())*1.09));
        yen.setText(String.valueOf(Double.parseDouble(euro.getText().toString())*117.80));
        florin.setText(String.valueOf(Double.parseDouble(euro.getText().toString())*1.96));
        rupi.setText(String.valueOf(Double.parseDouble(euro.getText().toString())*76.98));

        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
        if (e1.getY() - e2.getY() > 0){
            euro.setText(String.valueOf(Double.parseDouble(euro.getText().toString()) + 1));
        } else if (e1.getY() - e2.getY() < 0){
            euro.setText(String.valueOf(Double.parseDouble(euro.getText().toString()) - 1));
        }
        dollar.setText(String.valueOf(Double.parseDouble(euro.getText().toString())*1.09));
        yen.setText(String.valueOf(Double.parseDouble(euro.getText().toString())*117.80));
        florin.setText(String.valueOf(Double.parseDouble(euro.getText().toString())*1.96));
        rupi.setText(String.valueOf(Double.parseDouble(euro.getText().toString())*76.98));

        return true;
    }

    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTap(MotionEvent e) {
        return false;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {
        return false;
    }
}
