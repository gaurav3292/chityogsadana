package com.cityogsadana.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cityogsadana.R;
import com.cityogsadana.utils.Config;
import com.cityogsadana.utils.Global;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;


@EActivity(R.layout.activity_profile)
public class ProfileActivity extends AppCompatActivity implements View.OnClickListener{

    @ViewById(R.id.activity_profile)
    ViewGroup viewGroup;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.edit_change_pass)
    LinearLayout editChangePass;
    @ViewById(R.id.edit_logout)
    LinearLayout editLogOut;
    @ViewById(R.id.p_p_layout)
    TextView ppLayout;
    @ViewById(R.id.t_c_layout)
    TextView termsLayout;
    @ViewById(R.id.user_name)
    TextView userName;
    @ViewById(R.id.edit_profile_layout)
    LinearLayout editProfileLayout;

    @AfterViews
    public void setData()
    {
        Global.setFont(viewGroup,Global.regular);
        Global.setCustomFont(Global.bold,termsLayout,ppLayout,findViewById(R.id.title),userName);

        backButton.setOnClickListener(this);
        editChangePass.setOnClickListener(this);
        editProfileLayout.setOnClickListener(this);
        editLogOut.setOnClickListener(this);
        ppLayout.setOnClickListener(this);
        termsLayout.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch(v.getId())
        {

            case R.id.back_button:
                onBackPressed();
                break;

            case R.id.edit_change_pass:
                Intent intent = new Intent(this, ChangePassword_.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter_friom_rignt_fast, R.anim.no_change);
                break;

            case R.id.edit_logout:
                break;

            case R.id.edit_profile_layout:
                break;

            case R.id.p_p_layout:

                Intent intent2 = new Intent(this, PolicyActivity_.class);
                intent2.putExtra("type", Config.P_P);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
                break;

            case R.id.t_c_layout:
                Intent intent3 = new Intent(this, PolicyActivity_.class);
                intent3.putExtra("type", Config.T_C);
                intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent3);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
                break;

        }
    }
}
