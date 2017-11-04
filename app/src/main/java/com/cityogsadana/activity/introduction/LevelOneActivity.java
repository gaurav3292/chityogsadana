package com.cityogsadana.activity.introduction;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.cityogsadana.R;
import com.cityogsadana.activity.TestActivity_;
import com.cityogsadana.bean.UserBean;
import com.cityogsadana.dialogs.ConnectionMessageDialog;
import com.cityogsadana.dialogs.DialogDateTmePicker;
import com.cityogsadana.handler.DateTimePickerHandler;
import com.cityogsadana.interfaces.ActivitySet;
import com.cityogsadana.prefrences.UserPref;
import com.cityogsadana.utils.AccountChecker;
import com.cityogsadana.utils.DataEntry;
import com.cityogsadana.utils.Global;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@EActivity(R.layout.activity_level_one)
public class LevelOneActivity extends AppCompatActivity implements ActivitySet, DateTimePickerHandler, View.OnClickListener {

    @ViewById(R.id.activity_level_one)
    ViewGroup viewGroup;
    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.step_1_alarm)
    TextView alarm1;
    @ViewById(R.id.step_2_alarm)
    TextView alarm2;
    @ViewById(R.id.step_3_alarm)
    TextView alarm3;
    @ViewById(R.id.step_4_alarm)
    TextView alarm4;
    @ViewById(R.id.step_6_alarm)
    TextView alarm6;
    @ViewById(R.id.step_7_alarm)
    TextView alarm7;
    @ViewById(R.id.step_8_alarm)
    TextView alarm8;
    @ViewById(R.id.test)
    TextView testText;

    private UserBean userBean;
    private ConnectionMessageDialog cDialog = new ConnectionMessageDialog();
    private DataEntry dataEntry = new DataEntry();

    @AfterViews
    public void setData() {

        Global.setFont(viewGroup, Global.regular);
        title.setText("Level 1");

        testText.setVisibility(View.VISIBLE);

        if (userBean.getLevel().getTotalNumberOfDays() != null) {
            testText.setText("Start Routine");
        } else {
            testText.setText("Start Test");
        }

        backButton.setOnClickListener(this);
        alarm1.setOnClickListener(this);
        alarm2.setOnClickListener(this);
        alarm3.setOnClickListener(this);
        alarm4.setOnClickListener(this);
        alarm6.setOnClickListener(this);
        alarm7.setOnClickListener(this);
        alarm8.setOnClickListener(this);
        testText.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userBean = UserPref.getUser(this);
    }

    @Override
    public void onClick(View view) {
        DialogDateTmePicker timePickerDialog;
        switch (view.getId()) {
            case R.id.back_button:
                onBackPressed();
                break;

            case R.id.step_1_alarm:
                timePickerDialog = new DialogDateTmePicker().getInstance("time_picker", "Time to get up", this);
                timePickerDialog.show(getSupportFragmentManager(), "time_picker");

                break;

            case R.id.step_2_alarm:
                timePickerDialog = new DialogDateTmePicker().getInstance("time_picker", "Time to brush your teeth and scrape your tongue", this);
                timePickerDialog.show(getSupportFragmentManager(), "time_picker");

                break;

            case R.id.step_3_alarm:
                timePickerDialog = new DialogDateTmePicker().getInstance("time_picker", "Time for your morning de-drink", this);
                timePickerDialog.show(getSupportFragmentManager(), "time_picker");
                break;

            case R.id.step_4_alarm:
                timePickerDialog = new DialogDateTmePicker().getInstance("time_picker", "Time for nasya", this);
                timePickerDialog.show(getSupportFragmentManager(), "time_picker");
                break;

            case R.id.step_6_alarm:
                timePickerDialog = new DialogDateTmePicker().getInstance("time_picker", "Time for your massage", this);
                timePickerDialog.show(getSupportFragmentManager(), "time_picker");
                break;

            case R.id.step_7_alarm:
                timePickerDialog = new DialogDateTmePicker().getInstance("time_picker", "Time for your deep breathing", this);
                timePickerDialog.show(getSupportFragmentManager(), "time_picker");
                break;

            case R.id.step_8_alarm:
                timePickerDialog = new DialogDateTmePicker().getInstance("time_picker", "Time for your walk", this);
                timePickerDialog.show(getSupportFragmentManager(), "time_picker");
                break;

            case R.id.test:
                if (userBean.getLevel().getTotalNumberOfDays() == null) {
                    cDialog.showStartRoutine(this, this, "Routine Test", "Do you want to start your routine test?", false);
                } else {
                    Date currentTime = Calendar.getInstance().getTime();
                    DateFormat df = new SimpleDateFormat("HH:mm:ss");
                    String currentTimeStr = df.format(currentTime);
                    boolean check = AccountChecker.checkTime(currentTimeStr);

                    if(check)
                    {
                        Intent intent = new Intent(this, TestActivity_.class);
                        intent.putExtra("data",dataEntry.getLevelOneList());
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP|Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);

                    }else {
                        cDialog.successShow(this, "Alert!", "Your test will be active at 21:30:00. (09:30 pm)","Ok", false);

                    }

                }
                break;


        }
    }


    @Override
    public void setActivity() {
        onResume();
    }


    @Override
    public void startRoutineTest() {

    }
}
