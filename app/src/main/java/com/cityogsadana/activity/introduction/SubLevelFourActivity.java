package com.cityogsadana.activity.introduction;

import android.content.Intent;
import android.os.Bundle;
import android.provider.AlarmClock;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cityogsadana.R;
import com.cityogsadana.utils.Global;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

@EActivity(R.layout.activity_sub_level_four)
public class SubLevelFourActivity extends AppCompatActivity implements View.OnClickListener {

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


    private String title_txt;
    private boolean is_true = false;

    @AfterViews
    public void setData() {
        Global.setFont(viewGroup, Global.regular);
        title.setText(title_txt);

        backButton.setOnClickListener(this);
        alarm.setOnClickListener(this);

        if (is_true) {
            alarm.setVisibility(View.GONE);
            introTxt.setText(R.string.four_step_2);

        } else {
            alarm.setVisibility(View.VISIBLE);
            introTxt.setText(R.string.four_step_1);
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        title_txt = getIntent().getStringExtra("title_txt");
        is_true = getIntent().getBooleanExtra("is_true", false);
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

        }
    }

    private void setAlarm() {
            ArrayList<Integer> alarmDays= new ArrayList<Integer>();
            alarmDays.add(android.icu.util.Calendar.SATURDAY);
            alarmDays.add(android.icu.util.Calendar.SUNDAY);
            alarmDays.add(android.icu.util.Calendar.MONDAY);
            alarmDays.add(android.icu.util.Calendar.TUESDAY);
            alarmDays.add(android.icu.util.Calendar.WEDNESDAY);
            alarmDays.add(android.icu.util.Calendar.THURSDAY);
            alarmDays.add(android.icu.util.Calendar.FRIDAY);


            Intent i = new Intent(AlarmClock.ACTION_SET_ALARM);
            i.putExtra(AlarmClock.EXTRA_MESSAGE, "Time to review the day");
            i.putExtra(AlarmClock.EXTRA_DAYS,alarmDays);
            i.putExtra(AlarmClock.EXTRA_HOUR, 21);
            i.putExtra(AlarmClock.EXTRA_MINUTES, 00);
            i.putExtra(AlarmClock.EXTRA_SKIP_UI,true);
            startActivity(i);


    }
}
