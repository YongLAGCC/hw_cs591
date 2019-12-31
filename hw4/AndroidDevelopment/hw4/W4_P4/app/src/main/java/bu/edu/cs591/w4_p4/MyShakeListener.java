package bu.edu.cs591.w4_p4;

import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.util.Log;

public class MyShakeListener implements SensorEventListener {
    private static final String DEBUG_TAG = "Shake";
    private static final int SHAKE_COUNT = 2;
    private static final double SHAKE_THRESHOLD_GRAVITY = 2.7F;
    private static final int SHAKE_SLOP_TIME_MS = 500;
    private static final int SHAKE_COUNT_RESET_TIME_MS = 3000;

    private int mShakeCount = 0;
    private long mLastShake = 0;

    @Override
    public void onSensorChanged(SensorEvent event) {
        //Log.d(DEBUG_TAG,"Shaking?: " + event.toString());
        double X = event.values[0];
        double Y = event.values[1];
        double Z = event.values[2];
        double gX = X / SensorManager.GRAVITY_EARTH;
        double gY = Y / SensorManager.GRAVITY_EARTH;
        double gZ = Z / SensorManager.GRAVITY_EARTH;

        double gForce = Math.sqrt(gX * gX + gY * gY + gZ * gZ);
        if (gForce > SHAKE_THRESHOLD_GRAVITY) {
            long now = System.currentTimeMillis();
            if (mLastShake + SHAKE_SLOP_TIME_MS > now) {
                return;
            }

            if (mLastShake + SHAKE_COUNT_RESET_TIME_MS < now) {
                mShakeCount = 0;
            }

            mLastShake = now;
            mShakeCount++;
            if (mShakeCount >= SHAKE_COUNT) {
                Log.d(DEBUG_TAG,"Shaking?: " + event.toString());
                onShake();
                mShakeCount = 0;
            }
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {
        // The accuracy is not likely to change on a real device.
    }

    public void onShake() {

    }
}
