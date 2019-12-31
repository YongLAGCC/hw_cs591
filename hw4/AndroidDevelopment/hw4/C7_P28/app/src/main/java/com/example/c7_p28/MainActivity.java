package com.example.c7_p28;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import android.animation.ObjectAnimator;
import android.graphics.Path;
import android.graphics.Point;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.Display;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.PathInterpolator;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class MainActivity extends Activity
    implements GestureDetector.OnGestureListener, GestureDetector.OnDoubleTapListener {

    private TextView txtDrag;
    private GestureDetector GD;
    private RelativeLayout.LayoutParams p;
    private int startleft;
    private int starttop;
    Display display;
    private int maxx;
    private int maxy;
    int boundary = 200;
    private int textSpeed = 7000;
    private int maxSpeed = 15000;

    private static final String TAG = "MainActivity";
    //ObjectAnimator animation = ObjectAnimator.ofFloat()

    //FlingAnimation fling = new FlingAnimation(view, DynamicAnimation.SCROLL_X);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        GD = new GestureDetector(this, this);
        GD.setOnDoubleTapListener(this);
        //GD.onTouchEvent()

        txtDrag = (TextView) findViewById(R.id.txtDrag);

        display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        maxx = size.x - boundary;
        maxy = size.y - boundary;

//        txtDrag.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View view, MotionEvent motionEvent) {
//                GD.onTouchEvent(motionEvent);
//                return true;
//            }
//        });

        p = (RelativeLayout.LayoutParams)txtDrag.getLayoutParams();
        startleft = p.leftMargin;
        starttop = p.topMargin;
    }

    public void moveTextView1(float vx, float vy) throws InterruptedException{
        while(p.leftMargin > 10 && p.topMargin > 10){
            p.leftMargin -= 100;
            p.topMargin -= 100;
            txtDrag.setLayoutParams(p);
            TimeUnit.MILLISECONDS.sleep(10);
        }
        p.leftMargin = startleft;
        p.topMargin = starttop;
        txtDrag.setLayoutParams(p);

    }

    class MyAnimationListener implements Animation.AnimationListener{
        Animation animation;
        int changex;
        int changey;
        boolean reset;

        public MyAnimationListener(Animation animation, int changex, int changey, boolean reset){
            this.animation = animation;
            this.changex = changex;
            this.changey = changey;
            this.reset = reset;
        }

        public void setLoc(int changex, int changey){
            this.changex = changex;
            this.changey = changey;
        }

        @Override
        public void onAnimationStart(Animation animation) {

        }

        @Override
        public void onAnimationEnd(Animation animation) {
            if (!reset) {
                p = (RelativeLayout.LayoutParams) txtDrag.getLayoutParams();
                p.leftMargin += changex;
                p.topMargin += changey;
                //p.height = 20;
                //p.width = 50;
                txtDrag.setLayoutParams(p);
      //          Log.d(TAG, txtDrag.isEnabled());

            } else{
                txtDrag.setText("Too Fast, Reset");
                Random rand = new Random();
                int newx = rand.nextInt(maxx);
                int newy = rand.nextInt(maxy);
                p = (RelativeLayout.LayoutParams)txtDrag.getLayoutParams();
                p.leftMargin = newx;
                p.topMargin = newy;
                txtDrag.setLayoutParams(p);
                txtDrag.setText("FlingMe");
            }
        }

        @Override
        public void onAnimationRepeat(Animation animation) {

        }
    }


    public void move(int xchange, int ychange, int time, boolean reset){
        Animation animation = new TranslateAnimation(0, xchange, 0, ychange);
        //ObjectAnimator animation = new ObjectAnimator.of
        animation.setDuration(Math.max(10, time));
        //animation.setFillAfter(true);
        MyAnimationListener myAnimationListener = new MyAnimationListener(animation, xchange, ychange, reset);
        animation.setAnimationListener(myAnimationListener);

//        animation.setAnimationListener(new Animation.AnimationListener() {
//
//            @Override
//            public void onAnimationStart(Animation animation) {
//
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                p = (RelativeLayout.LayoutParams)txtDrag.getLayoutParams();
//                p.leftMargin += fx;
//                p.topMargin += fy;
//                txtDrag.setLayoutParams(p);
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//
//            }
//        });
        //animation.setRepeatCount(1);
        txtDrag.setAnimation(animation);

    }

    public void moveTextView (float vx, float vy) {
        p = (RelativeLayout.LayoutParams)txtDrag.getLayoutParams();
        int[] loc = new int[2];
        txtDrag.getLocationOnScreen(loc);
        //float y = txtDrag.getScaleY();
        //Animation animation = new TranslateAnimation(100, 0, 0, 0);
        //Animation animation = new TranslateAnimation(loc[0], loc[1], 0, 0);

        //int destix = Math.max(, )


        int xchange = 0;
        int ychange = 0;
        if (vx > 0) {
            xchange = maxx - loc[0];
        } else{
            xchange = -loc[0];
        }
        ychange = (vy>0? maxy : 0) - loc[1];

        int tx = xchange * textSpeed / (int)vx;
        int ty = ychange * textSpeed / (int)vy;

        boolean reset = false;
        if (vx > maxSpeed || vy > maxSpeed)
            reset = true;

        if (Math.abs(tx) < Math.abs(ty)){
            move(xchange, (int)(xchange * vy / vx), tx, reset);
        } else {
            move((int)(ychange * vx / vy), ychange, ty, reset);
        }


    }

    public void moveTextView2 (float vx, float vy) {
        Path path = new Path();
        path.lineTo(0, 0);
        PathInterpolator pathInterpolator = new PathInterpolator(path);
        ObjectAnimator animator = ObjectAnimator.ofFloat(txtDrag, "translationX", 100f);
        animator.setInterpolator(pathInterpolator);
        animator.start();
    }

    @Override
    public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY){
        txtDrag.setText("onFling");// + String.valueOf(velocityX) + String.valueOf(velocityY));

        moveTextView(velocityX, velocityY);
//        } catch (InterruptedException e){
//            e.printStackTrace();
//        }

//        Random rand = new Random();
//        int newx = rand.nextInt(maxx);
//        int newy = rand.nextInt(maxy);
//        p = (RelativeLayout.LayoutParams)txtDrag.getLayoutParams();
//        p.leftMargin = newx;
//        p.topMargin = newy;
//        txtDrag.setLayoutParams(p);

        return true;
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        this.GD.onTouchEvent(event);
        return super.onTouchEvent(event);
    }


    @Override
    public boolean onSingleTapConfirmed(MotionEvent e) {
        return true;
    }


    @Override
    public boolean onDoubleTap(MotionEvent e) {

        return true;
    }

    @Override
    public boolean onDoubleTapEvent(MotionEvent e) {

        return true;
    }

    @Override
    public boolean onDown(MotionEvent e) {

        return true;
    }

    @Override
    public void onShowPress(MotionEvent e) {

    }

    @Override
    public boolean onSingleTapUp(MotionEvent e) {
        return true;
    }

    @Override
    public boolean onScroll(MotionEvent e1, MotionEvent e2,
                            float distanceX, float distanceY) {


        return true;
    }

    @Override
    public void onLongPress(MotionEvent e) {

    }
}



