package com.cityogsadana.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.cityogsadana.R;
import com.cityogsadana.application.AppController;
import com.cityogsadana.utils.ConnectivityReceiver;
import com.cityogsadana.utils.CustomCrouton;
import com.cityogsadana.utils.Global;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_sign_up)
public class SignUpActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener, View.OnClickListener{


    @ViewById(R.id.activity_signup)
    ViewGroup viewGroup;
    @ViewById(R.id.error_check_layout)
    RelativeLayout errorLayout;
    @ViewById(R.id.text_terms)
    TextView termsTxt;
    @ViewById(R.id.text_policy)
    TextView privacyTxt;
    @ViewById(R.id.button_signup)
    TextView buttonSignUp;
    @ViewById(R.id.already_layout)
    LinearLayout alreadyLayout;
    @ViewById(R.id.close)
    ImageButton closeButton;

    @AfterViews
    public void setData()
    {
        Global.setFont(viewGroup,Global.regular);
        Global.setupUI(viewGroup,this);
        Global.setCustomFont(Global.bold,termsTxt,privacyTxt,findViewById(R.id.signUp_txt),buttonSignUp,findViewById(R.id.text_signin));

        termsTxt.setOnClickListener(this);
        privacyTxt.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);
        alreadyLayout.setOnClickListener(this);
        closeButton.setOnClickListener(this);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {

            case R.id.text_terms:
                break;

            case R.id.text_policy:
                break;


            case R.id.already_layout:
                finish();
                break;

            case R.id.close:
                finish();
                break;

            case R.id.button_signup:
                break;

        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        AppController.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected) {
            new CustomCrouton(this, getString(R.string.no_connection), errorLayout).setInAnimation();
        }
    }
}
