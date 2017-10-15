package com.cityogsadana.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
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

@EActivity(R.layout.activity_change_password)
public class ChangePassword extends AppCompatActivity implements View.OnClickListener,ConnectivityReceiver.ConnectivityReceiverListener{

    @ViewById(R.id.activity_change_password)
    ViewGroup viewGroup;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.error_check_layout)
    RelativeLayout errorLayout;
    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.save_button)
    TextView saveButton;


    @AfterViews
    public  void setData(){

        Global.setFont(viewGroup,Global.regular);
        Global.setCustomFont(Global.bold,title,saveButton);

        backButton.setOnClickListener(this);
        title.setText("Change Password");

    }

    @Override
    protected void onResume() {
        super.onResume();
        AppController.getInstance().setConnectivityListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.no_change,R.anim.exit_to_right_fast);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.back_button:
              onBackPressed();
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
