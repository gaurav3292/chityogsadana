package com.cityogsadana.activity.introduction;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
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
import com.cityogsadana.utils.CustomJsonParams;
import com.cityogsadana.utils.ErrorHelper;
import com.cityogsadana.utils.Global;
import com.cityogsadana.utils.PaypalHelper;
import com.google.gson.Gson;
import com.paypal.android.sdk.payments.PaymentActivity;
import com.paypal.android.sdk.payments.PaymentConfirmation;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

@EActivity(R.layout.activity_level_six)
public class LevelSixActivity extends AppCompatActivity implements View.OnClickListener,DataHandlerCallback {

    @ViewById(R.id.activity_level_six)
    ViewGroup viewGroup;
    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.yes_button)
    TextView yesButton;
    @ViewById(R.id.no_button)
    TextView noButton;

    private UserBean userBean;
    private ConnectionMessageDialog cDialog = new ConnectionMessageDialog();
    private JSONObject paypalObj;

    @AfterViews
    public void setData() {
        Global.setFont(viewGroup, Global.regular);
        title.setText("Level 6");


        backButton.setOnClickListener(this);
        yesButton.setOnClickListener(this);
        noButton.setOnClickListener(this);
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

            case R.id.yes_button:
                if(userBean.getLevel().isPaymentMade()==true){
                    cDialog.successShowMain(this, "Alert!", "You have already made the payment for this level", "Ok", false);

                }else{
                    PaypalHelper.getInstance();
                    PaypalHelper.startPayPalService(this, this, "35");
                }

                break;

            case R.id.no_button:
                cDialog.successShowMain(this,"Thank You","For your support and effort. Please continue following the advice you have received as these daily practices will help you connect to your Chit.","Ok",false);
                break;

        }
    }

    private void updatePaymentOnServer(JSONObject paypalObj) {
        Global.showProgress(this);
        CustomJsonParams customJsonParams = new CustomJsonParams();
        JSONObject params = customJsonParams.updatePaymentParams(userBean, paypalObj);
        new ApiHandler(this).apiPaymentResponse(this, Config.UPDATE_PAYMENT, params);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PaypalHelper.REQUEST_CODE_PAYMENT) {
            if (resultCode == Activity.RESULT_OK) {
                PaymentConfirmation confirm =
                        data.getParcelableExtra(PaymentActivity.EXTRA_RESULT_CONFIRMATION);
                if (confirm != null) {
                    try {
                        JSONObject jsonObject = confirm.toJSONObject();
                        Log.i(PaypalHelper.TAG, jsonObject.toString(4));
                        Log.i(PaypalHelper.TAG, confirm.getPayment().toJSONObject().toString(4));

                        paypalObj = new JSONObject();
                        paypalObj.put("create_time", jsonObject.getJSONObject("response").getString("create_time"));
                        paypalObj.put("id", jsonObject.getJSONObject("response").getString("id"));
                        paypalObj.put("state", jsonObject.getJSONObject("response").getString("state"));

                        Log.d("Paypal", "PaymentConfirmation info received from PayPal");

                        updatePaymentOnServer(paypalObj);


                    } catch (JSONException e) {
                        Log.e(PaypalHelper.TAG, "an extremely unlikely failure occurred: ", e);
                    }
                }
            } else if (resultCode == Activity.RESULT_CANCELED) {
                Log.i(PaypalHelper.TAG, "The user canceled.");
            } else if (resultCode == PaymentActivity.RESULT_EXTRAS_INVALID) {
                Log.i(
                        PaypalHelper.TAG,
                        "An invalid Payment or PayPalConfiguration was submitted. Please see the docs.");
            }


        }
    }

    @Override
    public void onSuccess(HashMap<String, Object> map) {

        JSONObject payObj = (JSONObject) map.get(Config.PAYMENT_RESPONSE);

        if (payObj != null) {
            CustomJsonParams customJsonParams = new CustomJsonParams();
            JSONObject params = customJsonParams.getUserData(userBean.getUser_id());
            new ApiHandler(this).apiUserResponse(this, Config.GET_USER, params);

        }

        JSONObject userObj = (JSONObject) map.get(Config.USER_RESPONSE);
        if (userObj != null) {
            Global.dialog.dismiss();
            try {
                Gson gson = new Gson();
                UserBean user = gson.fromJson(userObj.getJSONObject("user").toString(), UserBean.class);
                UserPref.saveUser(this, user);

                userBean = UserPref.getUser(this);

                cDialog.successShowMain(this, "Congratulations!", "Your payment information has been successfully saved", "Ok", false);

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
    protected void onDestroy() {
        super.onDestroy();
        PaypalHelper.stopPayPalService(this, this);
    }
}
