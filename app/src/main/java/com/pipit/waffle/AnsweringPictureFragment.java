package com.pipit.waffle;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Outline;
import android.os.Bundle;
import android.app.Fragment;
import android.support.v7.widget.CardView;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;

import com.facebook.rebound.Spring;
import com.facebook.rebound.SpringConfig;
import com.facebook.rebound.SpringListener;
import com.facebook.rebound.SpringSystem;

/**
 * Created by Kyle on 11/19/2014.
 */
public class AnsweringPictureFragment extends Fragment implements SpringListener {

    private static double TENSION = 800;
    private static double DAMPER = 20; //friction

    private CardView mImageToAnimate;
    private CardView mImageToAnimate2;
    private SpringSystem mSpringSystem;
    private Spring mSpring;

    private boolean mMovedUp = false;
    private float mOrigY;
    private float mOrigX;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        // Outline

        View v = inflater.inflate(R.layout.mode_selection_fragment, container, false);

        mImageToAnimate = (CardView) v.findViewById(R.id.card_view);
        mImageToAnimate2 = (CardView) v.findViewById(R.id.card_view2);



        mSpringSystem = SpringSystem.create();

        mSpring = mSpringSystem.createSpring();
        mSpring.addListener(this);

        SpringConfig config = new SpringConfig(TENSION, DAMPER);
        mSpring.setSpringConfig(config);

        Resources r = getResources();
        final float px = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 8, r.getDisplayMetrics());
        //mSpring.setEndValue(mImageToAnimate.getX());

         //CardView movement
        final View.OnTouchListener tl = new View.OnTouchListener() {
            public float offsetX;
            public float offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int theAction = event.getAction();
                switch (theAction) {
                    case MotionEvent.ACTION_DOWN:
                        // Button down

                        mOrigX = v.getX();
                        mOrigY = v.getY();
                        offsetX = v.getX() - event.getRawX();
                        offsetY = v.getY() - event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // Button moved

                        float newX = event.getRawX() + offsetX;
                        float newY = event.getRawY() + offsetY;
                        //WindowManager wm = (WindowManager) v.getSystemService(Context.WINDOW_SERVICE);
                        // Display display = wm.getDefaultDisplay();
                        //DisplayMetrics metrics = new DisplayMetrics();
                        // display.getMetrics(metrics);
                        //int width = metrics.widthPixels;
                        //int height = metrics.heightPixels;
                        //if (!(newX < 20))
                            v.setX(newX);
                        //v.setY(newY);
                        break;
                    case MotionEvent.ACTION_UP:
                        // Button up





                        float xValue = v.getX();
                        float yValue = v.getY();

                        float dist = px - xValue;
                        TranslateAnimation anim = new TranslateAnimation(0, dist, 0, 0);
                        anim.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {

                                mImageToAnimate.setX(px);
                                //mSpring.setEndValue(100);

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        //anim.setFillAfter(true);
                        anim.setFillEnabled(true);

                        // magical custom formula for creating appropriate slide speeds varying by travel distance
                        long dur = (long) ((dist/10) * (dist/10))/2;
                        if(dur > 500)
                            dur = 500;
                        if(dur < 150)
                            dur = 150;

                        anim.setDuration(dur);
                        mImageToAnimate.startAnimation(anim);





                        break;
                    default:
                        break;
                }
                return true;
            }
        };

        final View.OnTouchListener tl2 = new View.OnTouchListener() {
            public float offsetX;
            public float offsetY;

            @Override
            public boolean onTouch(View v, MotionEvent event) {
                int theAction = event.getAction();
                switch (theAction) {
                    case MotionEvent.ACTION_DOWN:
                        // Button down

                        mOrigX = v.getX();
                        mOrigY = v.getY();
                        offsetX = v.getX() - event.getRawX();
                        offsetY = v.getY() - event.getRawY();
                        break;
                    case MotionEvent.ACTION_MOVE:
                        // Button moved

                        float newX = event.getRawX() + offsetX;
                        float newY = event.getRawY() + offsetY;
                        //WindowManager wm = (WindowManager) v.getSystemService(Context.WINDOW_SERVICE);
                        // Display display = wm.getDefaultDisplay();
                        //DisplayMetrics metrics = new DisplayMetrics();
                        // display.getMetrics(metrics);
                        //int width = metrics.widthPixels;
                        //int height = metrics.heightPixels;
                        //if (!(newX < 20))
                        v.setX(newX);
                        //v.setY(newY);
                        break;
                    case MotionEvent.ACTION_UP:
                        // Button up





                        float xValue = v.getX();
                        float yValue = v.getY();

                        float dist = px - xValue;
                        TranslateAnimation anim = new TranslateAnimation(0, dist, 0, 0);
                        anim.setAnimationListener(new Animation.AnimationListener() {
                            @Override
                            public void onAnimationStart(Animation animation) {

                            }

                            @Override
                            public void onAnimationEnd(Animation animation) {

                                mImageToAnimate2.setX(px);
                                //mSpring.setEndValue(100);

                            }

                            @Override
                            public void onAnimationRepeat(Animation animation) {

                            }
                        });
                        //anim.setFillAfter(true);
                        anim.setFillEnabled(true);
                        long dur = (long) ((dist/10) * (dist/10))/2;
                        if(dur > 500)
                            dur = 500;
                        if(dur < 150)
                            dur = 150;

                        anim.setDuration(dur);
                        mImageToAnimate2.startAnimation(anim);





                        break;
                    default:
                        break;
                }
                return true;
            }
        };

        mImageToAnimate.setOnTouchListener(tl);
        mImageToAnimate2.setOnTouchListener(tl2);



        return v;
    }


    @Override
    public void onSpringUpdate(Spring spring) {
        float value = (float) spring.getCurrentValue();

       mImageToAnimate.setX(value);
    }

    @Override
    public void onSpringAtRest(Spring spring) {

    }

    @Override
    public void onSpringActivate(Spring spring) {

    }

    @Override
    public void onSpringEndStateChange(Spring spring) {

    }

}