package com.cityogsadana.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;
import android.view.ViewGroup;

import com.cityogsadana.R;
import com.cityogsadana.utils.Global;

import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends Activity {

    public static long timer = 1500;

    @ViewById(R.id.activity_splash)
    ViewGroup viewGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

     //   Global.setFont(viewGroup,Global.regular);

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity_.class);
                startActivity(intent);
                finish();
            }
        }, timer);
    }
}
