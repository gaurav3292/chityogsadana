package com.cityogsadana.activity;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.cityogsadana.R;
import com.cityogsadana.bean.CommonList;
import com.cityogsadana.bean.NotificationBean;
import com.cityogsadana.bean.UserBean;
import com.cityogsadana.dialogs.ConnectionMessageDialog;
import com.cityogsadana.handler.ApiHandler;
import com.cityogsadana.interfaces.DataHandlerCallback;
import com.cityogsadana.prefrences.UserPref;
import com.cityogsadana.services.AlarmReceiver;
import com.cityogsadana.utils.Config;
import com.cityogsadana.utils.CustomJsonParams;
import com.cityogsadana.utils.Global;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.HashMap;

@EActivity(R.layout.activity_main)
public class MainActivity extends AppCompatActivity implements View.OnClickListener,DataHandlerCallback {

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
    @ViewById(R.id.owner)
    CardView owner;
    @ViewById(R.id.noti_img)
    ImageView profileTab;
    @ViewById(R.id.home_img)
    ImageView homeImg;
    @ViewById(R.id.text_notification)
    TextView countText;
    @ViewById(R.id.layout_noti)
    RelativeLayout notiLayout;

    private UserBean userBean;
    private ConnectionMessageDialog cDialog = new ConnectionMessageDialog();

    private PendingIntent pendingIntent;
    private AlarmManager manager;
    private CommonList commonList;

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
        owner.setOnClickListener(this);
        notiLayout.setOnClickListener(this);



        Intent alarmIntent = new Intent(this, AlarmReceiver.class);

        PendingIntent.getBroadcast(this, 0, alarmIntent,
                PendingIntent.FLAG_UPDATE_CURRENT).cancel();

        pendingIntent = PendingIntent.getBroadcast(this, 0, alarmIntent, 0);
        setAlarm();


    }

    private void setAlarm() {

        manager = (AlarmManager)getSystemService(Context.ALARM_SERVICE);
        int interval = 10000;

        manager.setRepeating(AlarmManager.RTC_WAKEUP, System.currentTimeMillis(), 100, pendingIntent);
       // Toast.makeText(this, "Alarm Set", Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        userBean = UserPref.getUser(this);

//        if (userBean.getUser_id() != null && userBean.getSelf_result() == null) {
//            cDialog.showSelfTest(this, "Self Test", "Start your self assessment test", "Start", false);
//        }

        checkNotificationCount();
    }

    private void checkNotificationCount() {

        CustomJsonParams customJsonParams = new CustomJsonParams();
        JSONObject params = customJsonParams.getUserData(userBean.getUser_id());
        new ApiHandler(MainActivity.this).apiResponse(MainActivity.this, Config.NOTI_LIST, params);

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


            case R.id.owner:
                cDialog.successShow(this, "Owner", "Chit Yog Sadhana is developed by Dr. S. Ajit, an ayurvedic consultant based in Australasia for 23 years, with 38 years clinical experience.","Ok", false);
                break;

            case R.id.layout_noti:
                if(commonList!=null){
                    if(commonList.getNotifications().size()>0){
                        Intent intent6 = new Intent(this,NotificationActivity_.class);
                        intent6.putExtra("data", (Serializable) commonList.getNotifications());
                        startActivity(intent6);

                        readNotification();
                    }
                }
                break;

        }
    }

    private void readNotification() {
        CustomJsonParams customJsonParams = new CustomJsonParams();
        JSONObject params = customJsonParams.getUserData(userBean.getUser_id());
        new ApiHandler(MainActivity.this).apiReadNoti(MainActivity.this, Config.READ_NOTI, params);
    }

    @Override
    public void onSuccess(HashMap<String, Object> map) {

        Gson gson = new Gson();
        JSONObject obj = (JSONObject) map.get(Config.POST_JSON_RESPONSE);
        if(obj!=null){
             commonList = gson.fromJson(obj.toString(),CommonList.class);

            int count = Integer.parseInt(commonList.getNotification_count());
            if(count>0){
                countText.setVisibility(View.VISIBLE);
                countText.setText(commonList.getNotification_count());
            }else{
                countText.setVisibility(View.GONE);
            }

        }

    }

    @Override
    public void onFailure(HashMap<String, Object> map) {

    }
}
