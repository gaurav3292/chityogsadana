package com.cityogsadana.activity.introduction;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.error.VolleyError;
import com.cityogsadana.R;
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
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;

@EActivity(R.layout.activity_program_detail)
public class ProgramDetailActivity extends AppCompatActivity implements DataHandlerCallback,View.OnClickListener {


    @ViewById(R.id.activity_program_detail)
    ViewGroup viewGroup;
    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.done_button)
    TextView doneButton;
    @ViewById(R.id.intro_text)
    TextView introText;

    private UserBean userBean;
    private String subLevel, value;
    private ConnectionMessageDialog cDialog = new ConnectionMessageDialog();

    @AfterViews
    public void setData() {
        Global.setFont(viewGroup, Global.regular);
        title.setText("Program");

        backButton.setOnClickListener(this);
        doneButton.setOnClickListener(this);

        setLevelData(value, subLevel);

        if (subLevel.equalsIgnoreCase(userBean.getLevel().getUserSubLevel())) {
            doneButton.setVisibility(View.VISIBLE);
        } else {
            doneButton.setVisibility(View.GONE);
        }


    }

    private void setLevelData(String value, String subLevel) {

        switch (value) {

            case "51":
                if (subLevel.equalsIgnoreCase("1")) {
                    introText.setText(R.string.p1_51);
                } else {
                    introText.setText(R.string.p2_51);
                }

                break;

            case "52":
                if (subLevel.equalsIgnoreCase("1")) {
                    introText.setText(R.string.p1_52);
                } else {
                    introText.setText(R.string.p2_52);
                }
                break;

            case "53":
                if (subLevel.equalsIgnoreCase("1")) {
                    introText.setText(R.string.p1_53);
                } else {
                    introText.setText(R.string.p2_53);
                }
                break;

            case "54":
                if (subLevel.equalsIgnoreCase("1")) {
                    introText.setText(R.string.p1_54);
                } else {
                    introText.setText(R.string.p2_54);
                }
                break;


        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userBean = UserPref.getUser(this);
        value = (String) getIntent().getSerializableExtra("value");
        subLevel = (String) getIntent().getSerializableExtra("subLevel");

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.back_button:
                onBackPressed();
                break;

            case R.id.done_button:
                Date currentDate = Calendar.getInstance().getTime();
                DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                String currentDateStr = df.format(currentDate);
                Global.showProgress(this);
                CustomJsonParams customJsonParams = new CustomJsonParams();
                JSONObject params = customJsonParams.submitProgram(userBean.getUser_id(),currentDateStr,value);
                new ApiHandler(ProgramDetailActivity.this).apiResponse(ProgramDetailActivity.this, Config.SUBMIT_PROGRAM, params);


                break;

        }
    }

    @Override
    public void onSuccess(HashMap<String, Object> map) {
        Global.dialog.dismiss();
        JSONObject jsonObject = (JSONObject) map.get(Config.POST_JSON_RESPONSE);
        if (jsonObject != null) {
            Gson gson = new Gson();
            userBean = gson.fromJson(jsonObject.toString(),UserBean.class);
            UserPref.saveUser(this,userBean);
            try {
                cDialog.successShow(this, "Congratulations!",jsonObject.getString("msg"), "Ok", false);
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
