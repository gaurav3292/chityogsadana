package com.cityogsadana.activity.introduction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
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

import java.util.HashMap;

@EActivity(R.layout.activity_level_five)
public class LevelFiveActivity extends AppCompatActivity implements DataHandlerCallback, View.OnClickListener {

    @ViewById(R.id.activity_level_five)
    ViewGroup viewGroup;
    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.prg_1)
    EditText prg1;
    @ViewById(R.id.prg_2)
    EditText prg2;
    @ViewById(R.id.prg_3)
    EditText prg3;
    @ViewById(R.id.prg_4)
    EditText prg4;
    @ViewById(R.id.error_check_layout)
    RelativeLayout errorLayout;

    private String finalValue;
    private UserBean userBean;

    private ConnectionMessageDialog cDialog = new ConnectionMessageDialog();

    @AfterViews
    public void setData() {
        Global.setFont(viewGroup, Global.regular);
        title.setText("Level 5");

        backButton.setOnClickListener(this);

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
                String p1 = prg1.getText().toString();
                String p2 = prg2.getText().toString();
                String p3 = prg3.getText().toString();
                String p4 = prg4.getText().toString();
                boolean check = validate(p1, p2, p3, p4);

                if (!check) {
                    if (p1.equalsIgnoreCase("1")) {
                        finalValue = "1";
                    } else if (p2.equalsIgnoreCase("1")) {
                        finalValue = "2";
                    } else if (p3.equalsIgnoreCase("1")) {
                        finalValue = "3";
                    } else if (p4.equalsIgnoreCase("1")) {
                        finalValue = "4";
                    }

                    Global.showProgress(this);
                    CustomJsonParams customJsonParams = new CustomJsonParams();
                    JSONObject params = customJsonParams.submitRating(userBean.getUser_id(), userBean.getLevel().getUserLevel(), finalValue);
                    new ApiHandler(LevelFiveActivity.this).apiResponse(LevelFiveActivity.this, Config.SUBMIT_RATING, params);
                }

                break;

        }
    }

    private boolean validate(String p1, String p2, String p3, String p4) {
        boolean error = false;
        if (p1.isEmpty() && p2.isEmpty() && p3.isEmpty() && p4.isEmpty()) {
            error = true;
            new CustomCrouton(this, "Enter all the priority values.", errorLayout).setInAnimation();
        } else if (p1.isEmpty() || p2.isEmpty() || p3.isEmpty() || p4.isEmpty()) {
            error = true;
            if (p1.isEmpty()) {
                new CustomCrouton(this, "Enter priority value for point 1.", errorLayout).setInAnimation();
            } else if (p2.isEmpty()) {
                new CustomCrouton(this, "Enter priority value for point 2.", errorLayout).setInAnimation();
            } else if (p3.isEmpty()) {
                new CustomCrouton(this, "Enter priority value for point 3.", errorLayout).setInAnimation();
            } else if (p4.isEmpty()) {
                new CustomCrouton(this, "Enter priority value for point 4.", errorLayout).setInAnimation();
            }

        } else {
            if (p1.equalsIgnoreCase(p2) && p1.equalsIgnoreCase(p3) && p1.equalsIgnoreCase(p4)) {
                error = true;
                new CustomCrouton(this, "Priority value can not be repeated.", errorLayout).setInAnimation();
            } else if (p2.equalsIgnoreCase(p1) && p2.equalsIgnoreCase(p3) && p2.equalsIgnoreCase(p4)) {
                error = true;
                new CustomCrouton(this, "Priority value can not be repeated.", errorLayout).setInAnimation();
            } else if (p3.equalsIgnoreCase(p1) && p3.equalsIgnoreCase(p2) && p3.equalsIgnoreCase(p4)) {
                error = true;
                new CustomCrouton(this, "Priority value can not be repeated.", errorLayout).setInAnimation();
            } else if (p4.equalsIgnoreCase(p1) && p3.equalsIgnoreCase(p2) && p4.equalsIgnoreCase(p3)) {
                error = true;
                new CustomCrouton(this, "Priority value can not be repeated.", errorLayout).setInAnimation();
            }

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

                cDialog.successShow(this, "Congratulations!", jsonObject.getString("msg"), "Ok", false);
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
}
