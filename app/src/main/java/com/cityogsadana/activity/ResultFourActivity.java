package com.cityogsadana.activity;

import android.content.Intent;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.error.VolleyError;
import com.cityogsadana.R;
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

@EActivity(R.layout.activity_result_four)
public class ResultFourActivity extends AppCompatActivity implements DataHandlerCallback, View.OnClickListener {

    @ViewById(R.id.activity_result_four)
    ViewGroup viewGroup;
    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.level_4_1)
    LinearLayout level4_1;
    @ViewById(R.id.level_4_2)
    LinearLayout level4_2;
    @ViewById(R.id.result_41)
    TextView result41;
    @ViewById(R.id.result_42)
    TextView result42;

    private UserBean userBean;
    private ConnectionMessageDialog cDialog = new ConnectionMessageDialog();

    @AfterViews
    public void setData() {
        Global.setFont(viewGroup, Global.regular);
        title.setText("Level 4 Result");

        if (userBean.getLevel().getUserLevel().equalsIgnoreCase("41")) {
            level4_1.setOnClickListener(this);
            result41.setVisibility(View.VISIBLE);
            result42.setVisibility(View.GONE);

        } else {
            level4_2.setOnClickListener(this);
            result42.setVisibility(View.VISIBLE);
            result41.setVisibility(View.GONE);
        }


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

            case R.id.level_4_1:
                showResult("41");
                break;

            case R.id.level_4_2:
                showResult("42");
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
            Intent intent = new Intent(this, LevelResultActivity_.class);
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
