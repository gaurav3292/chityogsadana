package com.cityogsadana.activity.introduction;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.error.VolleyError;
import com.cityogsadana.R;
import com.cityogsadana.activity.TestActivity_;
import com.cityogsadana.bean.UserBean;
import com.cityogsadana.dialogs.ConnectionMessageDialog;
import com.cityogsadana.handler.ApiHandler;
import com.cityogsadana.interfaces.ActivitySet;
import com.cityogsadana.interfaces.DataHandlerCallback;
import com.cityogsadana.prefrences.UserPref;
import com.cityogsadana.utils.AccountChecker;
import com.cityogsadana.utils.Config;
import com.cityogsadana.utils.CustomJsonParams;
import com.cityogsadana.utils.DataEntry;
import com.cityogsadana.utils.ErrorHelper;
import com.cityogsadana.utils.Global;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

@EActivity(R.layout.activity_sub_level_four)
public class SubLevelFourActivity extends AppCompatActivity implements View.OnClickListener, ActivitySet, DataHandlerCallback {

    @ViewById(R.id.activity_sub_level_four)
    ViewGroup viewGroup;
    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.alarm)
    TextView alarm;
    @ViewById(R.id.intro)
    TextView introTxt;
    @ViewById(R.id.test)
    TextView testText;


    private String title_txt;
    private boolean is_true = false;
    private UserBean userBean;
    private ConnectionMessageDialog cDialog = new ConnectionMessageDialog();
    private DataEntry dataEntry = new DataEntry();

    @AfterViews
    public void setData() {
        Global.setFont(viewGroup, Global.regular);
        title.setText("Level 4");

        backButton.setOnClickListener(this);
        alarm.setOnClickListener(this);
        testText.setOnClickListener(this);


        if (is_true) {
            alarm.setVisibility(View.GONE);
            introTxt.setText(title_txt+"\n"+R.string.four_step_2);
            if (userBean.getLevel().getUserLevel().equalsIgnoreCase("42")) {
                if (userBean.getLevel().getTotalNumberOfDays() == 0) {
                    testText.setText("Start Routine");
                    testText.setVisibility(View.VISIBLE);
                } else {
                    testText.setText("Start Test");
                    testText.setVisibility(View.VISIBLE);
                }
            } else {
                testText.setVisibility(View.GONE);
            }


        } else {
            alarm.setVisibility(View.VISIBLE);
            introTxt.setText(title_txt+"\n"+R.string.four_step_1);


            if (userBean.getLevel().getUserLevel().equalsIgnoreCase("41")) {
                if (userBean.getLevel().getTotalNumberOfDays() == 0) {
                    testText.setVisibility(View.VISIBLE);
                    testText.setText("Start Routine");
                } else {
                    testText.setText("Start Test");
                    testText.setVisibility(View.VISIBLE);
                }
            } else {
                testText.setVisibility(View.GONE);
            }
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title_txt = getIntent().getStringExtra("title_txt");
        is_true = getIntent().getBooleanExtra("is_true", false);
        userBean = UserPref.getUser(this);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.back_button:
                onBackPressed();
                break;

            case R.id.alarm:
                setAlarm();
                break;

            case R.id.test:
                if (userBean.getLevel().getTotalNumberOfDays() == 0) {
                    cDialog.showStartRoutine(this, this, "Routine Test", "Do you want to start your routine test?", false);
                } else {
                    Date currentTime = Calendar.getInstance().getTime();
                    DateFormat df = new SimpleDateFormat("HH:mm:ss");
                    String currentTimeStr = df.format(currentTime);
                    boolean check = AccountChecker.checkTimeMorning(currentTimeStr);

                    if (check) {
                        Intent intent = new Intent(this, TestActivity_.class);
                        if (userBean.getLevel().getUserLevel().equalsIgnoreCase("41")) {
                            intent.putExtra("data", dataEntry.getLevelFour_OneList());
                            intent.putExtra("ques", Config.LevelFour_one);
                        } else {

                            intent.putExtra("data", dataEntry.getLevelFour_TwoList());
                            intent.putExtra("ques", Config.LevelFour_two);
                        }

                        intent.putExtra("title", "Level four");
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    } else {
                        cDialog.successShow(this, "Alert!", "Your test will be active at 04:30:00. (04:30 am)", "Ok", false);

                    }

                }
                break;


        }
    }

    private void setAlarm() {
        ArrayList<Integer> alarmDays = new ArrayList<Integer>();
        alarmDays.add(android.icu.util.Calendar.SATURDAY);
        alarmDays.add(android.icu.util.Calendar.SUNDAY);
        alarmDays.add(android.icu.util.Calendar.MONDAY);
        alarmDays.add(android.icu.util.Calendar.TUESDAY);
        alarmDays.add(android.icu.util.Calendar.WEDNESDAY);
        alarmDays.add(android.icu.util.Calendar.THURSDAY);
        alarmDays.add(android.icu.util.Calendar.FRIDAY);


        Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
        i.putExtra(AlarmClock.EXTRA_MESSAGE, "Time to review the day");
        i.putExtra(AlarmClock.EXTRA_DAYS, alarmDays);
        i.putExtra(AlarmClock.EXTRA_HOUR, 21);
        i.putExtra(AlarmClock.EXTRA_MINUTES, 00);
        i.putExtra(AlarmClock.EXTRA_SKIP_UI, true);
        startActivity(i);


    }

    @Override
    public void startRoutineTest() {
        Date currentDate = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateStr = df.format(currentDate);
        Global.showProgress(this);
        CustomJsonParams customJsonParams = new CustomJsonParams();
        JSONObject params = customJsonParams.startTest(userBean.getUser_id(), currentDateStr, userBean.getLevel().getUserLevel());
        new ApiHandler(SubLevelFourActivity.this).apiResponse(SubLevelFourActivity.this, Config.START_TEST, params);

    }

    @Override
    public void onSuccess(HashMap<String, Object> map) {
        Global.dialog.dismiss();
        JSONObject jsonObject = (JSONObject) map.get(Config.POST_JSON_RESPONSE);
        if (jsonObject != null) {
            Gson gson = new Gson();
            userBean = gson.fromJson(jsonObject.toString(), UserBean.class);
            UserPref.saveUser(this, userBean);
            cDialog.successShow(this, "Congratulations!", "Your test will be active tomorrow at 21:00:00. (09:00 pm)", "Ok", false);

            startNotification();
        }
    }

    private void startNotification() {
    }

    @Override
    public void onFailure(HashMap<String, Object> map) {
        Global.dialog.dismiss();
        if (map.containsKey(Config.ERROR)) {
            cDialog.successShow(this, "Error!", (String) map.get(Config.ERROR), "Ok", false);
        } else {
            VolleyError error = (VolleyError) map.get(Config.VOLLEY_ERROR);
            cDialog.successShow(this, "Error!", ErrorHelper.getErrorResponse(error), "Ok", false);

        }
    }
}
