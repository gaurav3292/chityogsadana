package com.cityogsadana.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.error.VolleyError;
import com.cityogsadana.R;
import com.cityogsadana.activity.introduction.LevelFiveActivity_;
import com.cityogsadana.activity.introduction.LevelFiveProgramActivity_;
import com.cityogsadana.activity.introduction.LevelFourActivity_;
import com.cityogsadana.activity.introduction.LevelOneActivity_;
import com.cityogsadana.activity.introduction.LevelSixActivity;
import com.cityogsadana.activity.introduction.LevelSixActivity_;
import com.cityogsadana.activity.introduction.LevelThreeActivity_;
import com.cityogsadana.activity.introduction.LevelTwoActivity_;
import com.cityogsadana.bean.LevelBean;
import com.cityogsadana.bean.LevelResultBean;
import com.cityogsadana.bean.UserBean;
import com.cityogsadana.dialogs.ConnectionMessageDialog;
import com.cityogsadana.handler.ApiHandler;
import com.cityogsadana.interfaces.DataHandlerCallback;
import com.cityogsadana.prefrences.UserPref;
import com.cityogsadana.utils.Config;
import com.cityogsadana.utils.CustomJsonParams;
import com.cityogsadana.utils.ErrorHelper;
import com.cityogsadana.utils.Global;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONObject;

import java.util.HashMap;

@EActivity(R.layout.activity_level)
public class LevelActivity extends AppCompatActivity implements View.OnClickListener, DataHandlerCallback {

    @ViewById(R.id.activity_level)
    ViewGroup viewGroup;
    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.level_one)
    LinearLayout level1;
    @ViewById(R.id.level_two)
    LinearLayout level2;
    @ViewById(R.id.level_three)
    LinearLayout level3;
    @ViewById(R.id.level_four)
    LinearLayout level4;
    @ViewById(R.id.level_five)
    LinearLayout level5;
    @ViewById(R.id.icon_1)
    ImageView icon1;
    @ViewById(R.id.icon_2)
    ImageView icon2;
    @ViewById(R.id.icon_3)
    ImageView icon3;
    @ViewById(R.id.icon_4)
    ImageView icon4;
    @ViewById(R.id.icon_5)
    ImageView icon5;
    @ViewById(R.id.icon_6)
    ImageView icon6;
    @ViewById(R.id.result_1)
    TextView result1;
    @ViewById(R.id.result_2)
    TextView result2;
    @ViewById(R.id.result_3)
    TextView result3;
    @ViewById(R.id.result_4)
    TextView result4;
    @ViewById(R.id.result_5)
    TextView result5;
    @ViewById(R.id.result_6)
    TextView result6;
    @ViewById(R.id.days_1)
    TextView days1;
    @ViewById(R.id.days_2)
    TextView days2;
    @ViewById(R.id.days_3)
    TextView days3;
    @ViewById(R.id.days_4)
    TextView days4;
    @ViewById(R.id.days_5)
    TextView days5;
    @ViewById(R.id.days_6)
    TextView days6;
    @ViewById(R.id.level_six)
    LinearLayout level6;

    private UserBean userBean;

    private ConnectionMessageDialog cDialog = new ConnectionMessageDialog();

    @AfterViews
    public void setData() {
        Global.setFont(viewGroup, Global.regular);

        title.setText("Levels");

        backButton.setOnClickListener(this);

        setLevels(userBean.getLevel());

    }

    private void setLevels(LevelBean level) {

        switch (level.getUserLevel()) {
            case "1":
                icon1.setImageResource(R.drawable.ic_unlocked);
                if (level.getCompletedNumberOfDays() > 0) {
                    result1.setVisibility(View.VISIBLE);
                    result1.setText("Track Progress");
                    result1.setOnClickListener(this);
                    days1.setText(level.getCompletedNumberOfDays() + "/" + level.getTotalNumberOfDays());
                } else {
                    days1.setText("Start your test");
                }
                level1.setOnClickListener(this);
                break;

            case "2":
                icon1.setImageResource(R.drawable.ic_check);
                icon2.setImageResource(R.drawable.ic_unlocked);
                days1.setText("Completed");
                if (level.getCompletedNumberOfDays() > 0) {
                    result2.setVisibility(View.VISIBLE);
                    result2.setText("Track Progress");
                    result2.setOnClickListener(this);
                    days2.setText(level.getCompletedNumberOfDays() + "/" + level.getTotalNumberOfDays());
                } else {
                    days2.setText("Start your test");
                }
                level2.setOnClickListener(this);
                break;

            case "3":
                icon1.setImageResource(R.drawable.ic_check);
                icon2.setImageResource(R.drawable.ic_check);
                icon3.setImageResource(R.drawable.ic_unlocked);
                days1.setText("Completed");
                days2.setText("Completed");
                if (level.getCompletedNumberOfDays() > 0) {
                    result3.setVisibility(View.VISIBLE);
                    result3.setText("Track Progress");
                    result3.setOnClickListener(this);
                    days3.setText(level.getCompletedNumberOfDays() + "/" + level.getTotalNumberOfDays());
                } else {
                    days3.setText("Start your test");
                }
                level3.setOnClickListener(this);
                break;

            case "41":
                icon1.setImageResource(R.drawable.ic_check);
                icon2.setImageResource(R.drawable.ic_check);
                icon3.setImageResource(R.drawable.ic_check);
                icon4.setImageResource(R.drawable.ic_unlocked);
                days1.setText("Completed");
                days2.setText("Completed");
                days3.setText("Completed");
                if (level.getCompletedNumberOfDays() > 0) {
                    result4.setVisibility(View.VISIBLE);
                    result4.setText("Track Progress");
                    result4.setOnClickListener(this);
                    days4.setText(level.getCompletedNumberOfDays() + "/" + level.getTotalNumberOfDays());
                } else {
                    days4.setText("Start your test");
                }
                level4.setOnClickListener(this);
                break;

            case "42":
                icon1.setImageResource(R.drawable.ic_check);
                icon2.setImageResource(R.drawable.ic_check);
                icon3.setImageResource(R.drawable.ic_check);
                icon4.setImageResource(R.drawable.ic_unlocked);
                days1.setText("Completed");
                days2.setText("Completed");
                days3.setText("Completed");
                if (level.getCompletedNumberOfDays() > 0) {
                    result4.setVisibility(View.VISIBLE);
                    result4.setText("Track Progress");
                    result4.setOnClickListener(this);
                    days4.setText(level.getCompletedNumberOfDays() + "/" + level.getTotalNumberOfDays());
                } else {
                    days4.setText("Start your test");
                }
                level4.setOnClickListener(this);
                break;

            case "5":
                icon1.setImageResource(R.drawable.ic_check);
                icon2.setImageResource(R.drawable.ic_check);
                icon3.setImageResource(R.drawable.ic_check);
                icon4.setImageResource(R.drawable.ic_check);
                icon5.setImageResource(R.drawable.ic_unlocked);
                days1.setText("Completed");
                days2.setText("Completed");
                days3.setText("Completed");
                days4.setText("Completed");
//                if (level.getCompletedNumberOfDays() > 0) {
//                    result5.setVisibility(View.VISIBLE);
//                    result5.setText("Track Progress");
//                    result5.setOnClickListener(this);
//                    days5.setText(level.getCompletedNumberOfDays() + "/" + level.getTotalNumberOfDays());
//                } else {
//                    days5.setText("Start your test");
//                }
                level5.setOnClickListener(this);
                break;

            case "6":
                icon1.setImageResource(R.drawable.ic_check);
                icon2.setImageResource(R.drawable.ic_check);
                icon3.setImageResource(R.drawable.ic_check);
                icon4.setImageResource(R.drawable.ic_check);
                icon5.setImageResource(R.drawable.ic_check);
                icon6.setImageResource(R.drawable.ic_unlocked);
                days1.setText("Completed");
                days2.setText("Completed");
                days3.setText("Completed");
                days4.setText("Completed");
                days5.setText("Completed");
//                if (level.getCompletedNumberOfDays() > 0) {
//                    result6.setVisibility(View.VISIBLE);
//                    result6.setText("Track Progress");
//                    days6.setText(level.getCompletedNumberOfDays() + "/" + level.getTotalNumberOfDays());
//                } else {
//                    days6.setText("Start your test");
//                }
                level6.setOnClickListener(this);
                break;

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userBean = UserPref.getUser(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.back_button:
                onBackPressed();
                break;

            case R.id.level_one:
                Intent intent = new Intent(LevelActivity.this, LevelOneActivity_.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;
            case R.id.level_two:
                Intent intent2 = new Intent(LevelActivity.this, LevelTwoActivity_.class);
                intent2.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
                break;
            case R.id.level_three:
                Intent intent3 = new Intent(LevelActivity.this, LevelThreeActivity_.class);
                intent3.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent3);
                break;
            case R.id.level_four:
                Intent intent4 = new Intent(LevelActivity.this, LevelFourActivity_.class);
                intent4.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent4);
                break;
            case R.id.level_five:
                if(userBean.getLevel().getUserLevel().equalsIgnoreCase("5")) {
                    Intent intent5 = new Intent(LevelActivity.this, LevelFiveActivity_.class);
                    intent5.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent5);
                }else {
                    Intent intent5 = new Intent(LevelActivity.this, LevelFiveProgramActivity_.class);
                    intent5.putExtra("value",userBean.getLevel().getUserLevel());
                    intent5.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent5);
                }
                break;

            case R.id.level_six:
                Intent intent6 = new Intent(LevelActivity.this, LevelSixActivity_.class);
                intent6.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent6);
                break;

            case R.id.result_1:
                showResult("1");
                break;

            case R.id.result_2:
                showResult("2");
                break;

            case R.id.result_3:
                showResult("3");
                break;

            case R.id.result_4:
                Intent intent1 = new Intent(this, ResultFourActivity_.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
                break;

            case R.id.result_5:
             //   showResult("5");
                break;


        }
    }

    private void showResult(String levelNumber) {

        Global.showProgress(this);
        CustomJsonParams customJsonParams = new CustomJsonParams();
        JSONObject params = customJsonParams.getLevelResultParams(userBean.getUser_id(), levelNumber);
        new ApiHandler(this).apiResponse(this, Config.LEVEL_RESULT, params);
    }

    @Override
    public void onSuccess(HashMap<String, Object> map) {
        Global.dialog.dismiss();
        JSONObject obj = (JSONObject) map.get(Config.POST_JSON_RESPONSE);
        if (obj != null) {
            Gson gson = new Gson();
            LevelResultBean levelResultBean = gson.fromJson(obj.toString(), LevelResultBean.class);
            Intent intent = new Intent(LevelActivity.this, LevelResultActivity_.class);
            intent.putExtra("result", levelResultBean);
            startActivity(intent);
        }

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
