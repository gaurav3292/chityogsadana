package com.cityogsadana.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.cityogsadana.R;
import com.cityogsadana.bean.UserBean;
import com.cityogsadana.dialogs.ConnectionMessageDialog;
import com.cityogsadana.prefrences.UserPref;
import com.cityogsadana.utils.Config;
import com.cityogsadana.utils.Global;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @ViewById(R.id.activity_main)
    ViewGroup viewGroup;
    @ViewById(R.id.chit_yog)
    CardView chitYogTxt;
    @ViewById(R.id.chit)
    CardView chitTxt;
    @ViewById(R.id.needs)
    CardView needsTxt;
    @ViewById(R.id.works)
    CardView worksTxt;
    @ViewById(R.id.test)
    CardView testTxt;
    @ViewById(R.id.aim)
    CardView aimTxt;
    @ViewById(R.id.noti_img)
    ImageView profileTab;
    @ViewById(R.id.home_img)
    ImageView homeImg;

    private UserBean userBean;
    private ConnectionMessageDialog cDialog = new ConnectionMessageDialog();

    @AfterViews
    public void setData() {

        Global.setFont(viewGroup, Global.regular);

        chitYogTxt.setOnClickListener(this);
        chitTxt.setOnClickListener(this);
        testTxt.setOnClickListener(this);
        aimTxt.setOnClickListener(this);
        homeImg.setOnClickListener(this);
        worksTxt.setOnClickListener(this);
        needsTxt.setOnClickListener(this);
        profileTab.setOnClickListener(this);


    }

    @Override
    protected void onResume() {
        super.onResume();
        userBean = UserPref.getUser(this);

        if (userBean.getUser_id() != null && userBean.getSelf_result() == null) {
            cDialog.showSelfTest(this, "Self Test", "Start your self assessment test", "Start", false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.home_img:
                if (userBean.getUser_id() != null) {
                    Intent profile = new Intent(this, ProfileActivity_.class);
                    profile.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(profile);
                }else {
                    Intent intent5 = new Intent(this, LoginActivity_.class);
                    intent5.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent5);
                }
                break;

            case R.id.noti_img:
                break;

            case R.id.chit_yog:
                Intent intent = new Intent(this, DescriptionActivity_.class);
                intent.putExtra("type", Config.CHIT_YOG);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.enter_friom_rignt_fast, R.anim.no_change);
                break;

            case R.id.chit:
                Intent intent1 = new Intent(this, DescriptionActivity_.class);
                intent1.putExtra("type", Config.CHIT);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
                overridePendingTransition(R.anim.enter_friom_rignt_fast, R.anim.no_change);
                break;

            case R.id.aim:
                Intent intent2 = new Intent(this, DescriptionActivity_.class);
                intent2.putExtra("type", Config.AIM);
                intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
                overridePendingTransition(R.anim.enter_friom_rignt_fast, R.anim.no_change);
                break;

            case R.id.works:
                Intent intent3 = new Intent(this, DescriptionActivity_.class);
                intent3.putExtra("type", Config.WORKS);
                intent3.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent3);
                overridePendingTransition(R.anim.enter_friom_rignt_fast, R.anim.no_change);
                break;

            case R.id.needs:
                Intent intent4 = new Intent(this, DescriptionActivity_.class);
                intent4.putExtra("type", Config.NEED);
                intent4.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent4);
                overridePendingTransition(R.anim.enter_friom_rignt_fast, R.anim.no_change);
                break;

            case R.id.test:
                if (userBean.getUser_id() != null) {
                    if (userBean.getSelf_result() == null) {
                        Intent intent5 = new Intent(this, SelfTestActivity_.class);
                        intent5.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent5);
                        overridePendingTransition(R.anim.enter_friom_rignt_fast, R.anim.no_change);

                    } else {
                        Intent intent5 = new Intent(this, LevelActivity_.class);
                        intent5.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent5);
                        overridePendingTransition(R.anim.enter_friom_rignt_fast, R.anim.no_change);
                    }
                } else {
                    Intent intent5 = new Intent(this, LoginActivity_.class);
                    intent5.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent5);
                    overridePendingTransition(R.anim.enter_friom_rignt_fast, R.anim.no_change);
                }
                break;

        }
    }
}
