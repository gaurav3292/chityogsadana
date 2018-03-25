package com.cityogsadana.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.error.VolleyError;
import com.cityogsadana.R;
import com.cityogsadana.adapter.SelfTestAdapter;
import com.cityogsadana.application.AppController;
import com.cityogsadana.bean.QuestionBean;
import com.cityogsadana.bean.UserBean;
import com.cityogsadana.dialogs.ConnectionMessageDialog;
import com.cityogsadana.handler.ApiHandler;
import com.cityogsadana.interfaces.DataHandlerCallback;
import com.cityogsadana.prefrences.UserPref;
import com.cityogsadana.utils.AccountChecker;
import com.cityogsadana.utils.Config;
import com.cityogsadana.utils.ConnectivityReceiver;
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
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@EActivity(R.layout.activity_test)
public class TestActivity extends AppCompatActivity implements DataHandlerCallback, View.OnClickListener, ConnectivityReceiver.ConnectivityReceiverListener {

    @ViewById(R.id.activity_test)
    ViewGroup viewGroup;
    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.error_check_layout)
    RelativeLayout errorLayout;
    @ViewById(R.id.list)
    RecyclerView recyclerView;
    @ViewById(R.id.button_next)
    TextView submitBtn;
    @ViewById(R.id.back_button)
    ImageButton backButton;

    private SelfTestAdapter selfTestAdapter;
    private ArrayList<QuestionBean> listData;
    private ConnectionMessageDialog cDialog = new ConnectionMessageDialog();
    private int totalNumberOfTrue = 0;
    private UserBean userBean;
    private int ques;
    private String t;


    @AfterViews
    public void setData() {
        Global.setFont(viewGroup, Global.regular);

        t = getIntent().getExtras().getString("title");
        title.setText(t);

        submitBtn.setOnClickListener(this);
        backButton.setOnClickListener(this);

        getAdapterData();
    }

    private void getAdapterData() {

        selfTestAdapter = new SelfTestAdapter(this, this, listData);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(selfTestAdapter);
        recyclerView.setNestedScrollingEnabled(false);

    }

    @Override
    protected void onResume() {
        super.onResume();
        AppController.getInstance().setConnectivityListener(this);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userBean = UserPref.getUser(this);
        listData = (ArrayList<QuestionBean>) getIntent().getSerializableExtra("data");
        ques = (int) getIntent().getSerializableExtra("ques");

    }


    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected) {
            new CustomCrouton(this, getString(R.string.no_connection), errorLayout).setInAnimation();
        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {

            case R.id.back_button:
                onBackPressed();
                break;

            case R.id.button_next:
                boolean check = validate();
                if (!check) {

                    Date currentTime = Calendar.getInstance().getTime();
                    DateFormat df1 = new SimpleDateFormat("HH:mm:ss");
                    String currentTimeStr = df1.format(currentTime);
                    boolean check1 = AccountChecker.checkTime(currentTimeStr);

                    if (check1) {
                        Date currentDate = Calendar.getInstance().getTime();
                        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
                        String currentDateStr = df.format(currentDate);
                        Global.showProgress(this);
                        CustomJsonParams customJsonParams = new CustomJsonParams();
                        JSONObject params = customJsonParams.submitTest(userBean.getUser_id(), currentDateStr, userBean.getLevel().getUserLevel(), totalNumberOfTrue, ques);
                        new ApiHandler(TestActivity.this).apiResponse(TestActivity.this, Config.SUBMIT_TEST, params);

                    } else {
                        cDialog.successShow(this, "Alert!", "Your test submit will be active at 21:00:00. (09:00 pm)", "Ok", false);

                    }
                }
                break;

        }
    }

    private boolean validate() {
        boolean error = false;
        int totalTrue = 0;
        List<QuestionBean> questionBeanList = selfTestAdapter.getData();
        for (int i = 0; i < questionBeanList.size(); i++) {

            QuestionBean queeBean = questionBeanList.get(i);
            if (queeBean.getAnswers() == null) {
                error = true;
                new CustomCrouton(this, "Please mark all answers", errorLayout).setInAnimation();
                break;

            } else {
                if (queeBean.getAnswers().equalsIgnoreCase(Config.TRUE)) {
                    totalTrue++;
                }
            }
        }

        totalNumberOfTrue = totalTrue;

        return error;
    }

    @Override
    public void onSuccess(HashMap<String, Object> map) {
        Global.dialog.dismiss();
        JSONObject jsonObject = (JSONObject) map.get(Config.POST_JSON_RESPONSE);
        if (jsonObject != null) {
            Gson gson = new Gson();
            UserBean userBean1 = gson.fromJson(jsonObject.toString(), UserBean.class);

            if (userBean1.getLevel().getUserLevel().equalsIgnoreCase(userBean.getLevel().getUserLevel())) {
                userBean = UserPref.getUser(this);
                if(userBean.getLevel().getIsExtraResult()==0 || userBean.getLevel().getIsExtraResult()>=1){
                    userBean1.getLevel().setIsExtraResult(2);
                }
                UserPref.saveUser(this, userBean1);
                userBean = UserPref.getUser(this);
                if( userBean.getLevel().getUserLevel().equalsIgnoreCase("1") && userBean.getLevel().getIsExtraResult()==1){

                    try {
                        userBean.getLevel().setIsExtraResult(2);
                        UserPref.saveUser(this, userBean);
                        cDialog.successShowExtra(this, "Congratulations!", jsonObject.getString("msg"), "Ok", false);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }else {

                    try {
                        cDialog.successShowHome(this, "Congratulations!", jsonObject.getString("msg"), "Ok", false);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                userBean = gson.fromJson(jsonObject.toString(), UserBean.class);
                userBean.getLevel().setIsExtraResult(-1);
                UserPref.saveUser(this, userBean);

                try {
                    cDialog.successShowLevelMenu(this, "Congratulations!", jsonObject.getString("msg"), "Ok", false);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
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
    public void onBackPressed() {

        cDialog.successBack(this, "Error!", "Going back from test will reset your answers you have to fill up your answers again", "Ok", false);

    }
}
