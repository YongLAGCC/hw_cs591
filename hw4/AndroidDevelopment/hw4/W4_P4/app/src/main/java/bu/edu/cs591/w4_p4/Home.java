package bu.edu.cs591.w4_p4;

import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.support.v4.view.GestureDetectorCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.Toast;

public class Home extends AppCompatActivity {

    private GestureDetectorCompat mDetector;

    private SensorManager mSensorMgr;

    private MyShakeListener myShakeListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mSensorMgr = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
        myShakeListener = new MyShakeListener() {
            @Override
            public void onShake() {
                Toast.makeText(Home.this, "Shaking!!!", Toast.LENGTH_SHORT).show();
                final Animation animShake = AnimationUtils.loadAnimation(getApplicationContext(),
                        R.anim.shake);
                ImageView imageView = (ImageView) findViewById(R.id.imgHome);
                imageView.startAnimation(animShake);
            }
        };
        mSensorMgr.registerListener(myShakeListener,
                mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);

        mDetector = new GestureDetectorCompat(this, new MyGestureListener() {
            @Override
            public void onSwipeDown() {
//                Toast.makeText(Home.this, "Down", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), South.class);
                startActivity(intent);
            }

            @Override
            public void onSwipeLeft() {
//                Toast.makeText(Home.this, "Left", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), West.class);
                startActivity(intent);
            }

            @Override
            public void onSwipeUp() {
//                Toast.makeText(Home.this, "Up", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), North.class);
                startActivity(intent);
            }

            @Override
            public void onSwipeRight() {
//                Toast.makeText(Home.this, "Right", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), East.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.mDetector.onTouchEvent(event);
        return super.onTouchEvent(event);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mSensorMgr.registerListener(myShakeListener,
                mSensorMgr.getDefaultSensor(Sensor.TYPE_ACCELEROMETER),
                SensorManager.SENSOR_DELAY_GAME);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mSensorMgr.unregisterListener(myShakeListener);
    }
}
