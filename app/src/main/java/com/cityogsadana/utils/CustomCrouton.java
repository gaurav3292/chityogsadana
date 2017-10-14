package com.cityogsadana.utils;

import android.app.Activity;
import android.os.Handler;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.cityogsadana.R;


public class CustomCrouton {

    private static final String NULL_PARAMETERS_ARE_NOT_ACCEPTED = "Null parameters are not accepted";
    private Activity activity;
    private Animation inAnimation;
    private Animation outAnimation;
    private ViewGroup layout;
    private String text;

    public CustomCrouton(Activity activity, String text, ViewGroup layout) {
        if ((activity == null) || (text == null)  || (layout == null)) {
            throw new IllegalArgumentException(NULL_PARAMETERS_ARE_NOT_ACCEPTED);
        }
        this.activity = activity;
        this.text = text;
        this.layout = layout;
    }

    public void setInAnimation(){

        Global.setText(layout,text);
        layout.setVisibility(View.VISIBLE);

         inAnimation = AnimationUtils.loadAnimation(activity,
                R.anim.slide_down);
        inAnimation.setFillAfter(true);
        layout.startAnimation(inAnimation);

        setOutAnimation();
    }

    public void setOutAnimation(){
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                outAnimation = AnimationUtils.loadAnimation(activity,
                        R.anim.slide_up);
                layout.startAnimation(outAnimation);
                layout.setVisibility(View.GONE);
            }
        },4000);

    }
}
