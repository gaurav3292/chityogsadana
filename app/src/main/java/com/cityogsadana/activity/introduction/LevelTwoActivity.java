package com.cityogsadana.activity.introduction;

import android.content.Intent;
import android.os.Bundle;
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
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

@EActivity(R.layout.activity_level_two)
public class LevelTwoActivity extends AppCompatActivity implements DataHandlerCallback, ActivitySet, View.OnClickListener {

    @ViewById(R.id.activity_level_two)
    ViewGroup viewGroup;
    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.test)
    TextView testText;

    private UserBean userBean;
    private ConnectionMessageDialog cDialog = new ConnectionMessageDialog();
    private DataEntry dataEntry = new DataEntry();

    @AfterViews
    public void setData() {
        Global.setFont(viewGroup, Global.regular);


        title.setText("Level 2");

        testText.setVisibility(View.VISIBLE);

        if (userBean.getLevel().getTotalNumberOfDays() == 0 ) {
            testText.setText("Start Routine");
        } else {
            testText.setText("Start Test");
        }

        backButton.setOnClickListener(this);
        testText.setOnClickListener(this);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userBean = UserPref.getUser(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                onBackPressed();
                break;

            case R.id.test:
                if (userBean.getLevel().getTotalNumberOfDays()== 0) {
                    cDialog.showStartRoutine(this, this, "Routine Test", "Do you want to start your routine test?", false);
                } else {
                    Date currentTime = Calendar.getInstance().getTime();
                    DateFormat df = new SimpleDateFormat("HH:mm:ss");
                    String currentTimeStr = df.format(currentTime);
                    boolean check = AccountChecker.checkTimeMorning(currentTimeStr);

                    if (check) {

                        checkTestSubmittion();


                    } else {
                        cDialog.successShow(this, "Alert!", "Your test will be active at 04:30:00. (04:30 am)", "Ok", false);
                    }

                }
                break;
        }
    }

    private void checkTestSubmittion() {
        Date currentDate = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateStr = df.format(currentDate);
        Global.showProgress(this);
        CustomJsonParams customJsonParams = new CustomJsonParams();
        JSONObject params = customJsonParams.checkSubmittion(userBean.getUser_id(),currentDateStr,userBean.getLevel().getUserLevel());
        new ApiHandler(LevelTwoActivity.this).apiResponseCheck(LevelTwoActivity.this, Config.CHECK_SUBMITTION, params);
    }

    @Override
    public void startRoutineTest() {
        Date currentDate = Calendar.getInstance().getTime();
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        String currentDateStr = df.format(currentDate);
        Global.showProgress(this);
        CustomJsonParams customJsonParams = new CustomJsonParams();
        JSONObject params = customJsonParams.startTest(userBean.getUser_id(),currentDateStr,userBean.getLevel().getUserLevel());
        new ApiHandler(LevelTwoActivity.this).apiResponse(LevelTwoActivity.this, Config.START_TEST, params);

    }


    @Override
    public void onSuccess(HashMap<String, Object> map) {
        Global.dialog.dismiss();
        JSONObject jsonObject = (JSONObject) map.get(Config.POST_JSON_RESPONSE);
        if (jsonObject != null) {
            Gson gson = new Gson();
            userBean = gson.fromJson(jsonObject.toString(),UserBean.class);
            UserPref.saveUser(this,userBean);
            cDialog.successShow(this, "Congratulations!","Your test will be active tomorrow at 21:00:00. (09:00 pm)", "Ok", false);

            startNotification();
        }


        JSONObject checkObj = (JSONObject) map.get(Config.CHECK_RESPONSE);
        if(checkObj!=null){
            Intent intent = new Intent(this, TestActivity_.class);
            intent.putExtra("data", dataEntry.getlevelTwoList());
            intent.putExtra("ques",Config.LevelTwo);
            intent.putExtra("title","Level two");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
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
