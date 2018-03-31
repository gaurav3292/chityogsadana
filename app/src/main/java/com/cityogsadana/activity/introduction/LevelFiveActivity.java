package com.cityogsadana.activity.introduction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.error.VolleyError;
import com.cityogsadana.R;
import com.cityogsadana.bean.UserBean;
import com.cityogsadana.dialogs.ConnectionMessageDialog;
import com.cityogsadana.handler.ApiHandler;
import com.cityogsadana.interfaces.DataHandlerCallback;
import com.cityogsadana.prefrences.UserPref;
import com.cityogsadana.utils.Config;
import com.cityogsadana.utils.CustomCrouton;
import com.cityogsadana.utils.CustomJsonParams;
import com.cityogsadana.utils.ErrorHelper;
import com.cityogsadana.utils.Global;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

@EActivity(R.layout.activity_level_five)

public class LevelFiveActivity extends AppCompatActivity implements DataHandlerCallback, View.OnClickListener, CompoundButton.OnCheckedChangeListener {

    @ViewById(R.id.activity_level_five)
    ViewGroup viewGroup;
    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.prg_1)
    CheckBox prg1;
    @ViewById(R.id.prg_2)
    CheckBox prg2;
    @ViewById(R.id.prg_3)
    CheckBox prg3;
    @ViewById(R.id.prg_4)
    CheckBox prg4;
    @ViewById(R.id.error_check_layout)
    RelativeLayout errorLayout;
    @ViewById(R.id.submit_button)
    TextView submitBtn;

    private String finalValue;
    private UserBean userBean;

    private ConnectionMessageDialog cDialog = new ConnectionMessageDialog();

    @AfterViews
    public void setData() {
        Global.setFont(viewGroup, Global.regular);
        title.setText("Level 5");

        backButton.setOnClickListener(this);
        submitBtn.setOnClickListener(this);

        prg1.setOnCheckedChangeListener(this);
        prg2.setOnCheckedChangeListener(this);
        prg3.setOnCheckedChangeListener(this);
        prg4.setOnCheckedChangeListener(this);

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

            case R.id.submit_button:
                boolean check = validate();

                if (!check) {

                    Date currentDate = Calendar.getInstance().getTime();
                    DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                    String currentDateStr = df.format(currentDate);

                    Global.showProgress(this);
                    CustomJsonParams customJsonParams = new CustomJsonParams();
                    JSONObject params = customJsonParams.submitRating(userBean.getUser_id(), userBean.getLevel().getUserLevel(), finalValue, currentDateStr);
                    new ApiHandler(LevelFiveActivity.this).apiResponse(LevelFiveActivity.this, Config.SUBMIT_RATING, params);
                }

                break;

        }
    }

    private boolean validate() {
        boolean error = false;
        if (finalValue == null) {
            error = true;
            new CustomCrouton(this, "Please select one of the emotional state", errorLayout).setInAnimation();
        }
        return error;

    }

    @Override
    public void onSuccess(HashMap<String, Object> map) {
        Global.dialog.dismiss();
        JSONObject jsonObject = (JSONObject) map.get(Config.POST_JSON_RESPONSE);
        if (jsonObject != null) {
            try {
                Gson gson = new Gson();
                userBean = gson.fromJson(jsonObject.toString(), UserBean.class);
                UserPref.saveUser(this, userBean);

                cDialog.successShowLevelMenu(this, "Congratulations!", jsonObject.getString("msg"), "Ok", false);
            } catch (JSONException e) {
                e.printStackTrace();
            }

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

    @Override
    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

        switch (buttonView.getId()) {
            case R.id.prg_1:
                if (isChecked) {
                    finalValue = "1";

                    prg2.setChecked(false);
                    prg3.setChecked(false);
                    prg4.setChecked(false);
                }


                break;

            case R.id.prg_2:
                if (isChecked) {
                    finalValue = "2";


                    prg1.setChecked(false);
                    prg4.setChecked(false);
                    prg3.setChecked(false);
                }

                break;

            case R.id.prg_3:
                if (isChecked) {
                    finalValue = "3";


                    prg1.setChecked(false);
                    prg2.setChecked(false);
                    prg4.setChecked(false);
                }

                break;

            case R.id.prg_4:
                if (isChecked) {
                    finalValue = "4";


                    prg1.setChecked(false);
                    prg2.setChecked(false);
                    prg3.setChecked(false);
                }

                break;
        }

    }
}
