package com.cityogsadana.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.ViewGroup;

import com.cityogsadana.R;
import com.cityogsadana.activity.introduction.LevelFiveActivity;
import com.cityogsadana.activity.introduction.LevelFiveActivity_;
import com.cityogsadana.bean.UserBean;
import com.cityogsadana.prefrences.UserPref;
import com.cityogsadana.services.MyFirebaseInstanceIDService;
import com.cityogsadana.utils.Global;
import com.google.firebase.FirebaseApp;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_splash)
public class SplashActivity extends Activity {

    public static long timer = 3000;

    @ViewById(R.id.activity_splash)
    ViewGroup viewGroup;

    UserBean userBean;

    @AfterViews
    public void setData()
    {
        Global.setFont(viewGroup, Global.regular);
        Global.setCustomFont(Global.italic, findViewById(R.id.welcome_txt));

        if(Global.token==null){
            FirebaseApp.initializeApp(this);
            new MyFirebaseInstanceIDService().onTokenRefresh();
        }

        userBean = UserPref.getUser(this);
        if(userBean.getUser_id()==null){
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, LoginActivity_.class);
                    startActivity(intent);
                    finish();
                }
            }, timer);
        }else{
            Handler handler = new Handler();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(SplashActivity.this, LevelFiveActivity_.class);
                    startActivity(intent);
                    finish();
                }
            }, timer);
        }


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }
}
