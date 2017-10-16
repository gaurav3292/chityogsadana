package com.cityogsadana.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.cityogsadana.R;
import com.cityogsadana.application.AppController;
import com.cityogsadana.utils.ConnectivityReceiver;
import com.cityogsadana.utils.CustomCrouton;
import com.cityogsadana.utils.Global;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

/**
 * Created by macbookpro on 15/10/17.
 */

@EActivity(R.layout.activity_forgot_password)
public class ForgotPasswordActivity extends AppCompatActivity  implements ConnectivityReceiver.ConnectivityReceiverListener,View.OnClickListener{

    @ViewById(R.id.activity_forgot_password)
    ViewGroup viewGroup;
    @ViewById(R.id.close_button)
    ImageButton closeButton;
    @ViewById(R.id.btn_send)
    Button sendButton;
    @ViewById(R.id.error_check_layout)
    RelativeLayout errorLayout;

    @AfterViews
    public void setData()
    {
        Global.setFont(viewGroup,Global.regular);

        closeButton.setOnClickListener(this);
        sendButton.setOnClickListener(this);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    protected void onResume() {
        super.onResume();
        AppController.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {
            case R.id.close_button:
                onBackPressed();
                break;

            case R.id.btn_send:
                finish();
                break;
        }


    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected) {
            new CustomCrouton(this, getString(R.string.no_connection), errorLayout).setInAnimation();
        }
    }
}
