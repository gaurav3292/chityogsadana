package com.cityogsadana.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.error.VolleyError;
import com.cityogsadana.R;
import com.cityogsadana.application.AppController;
import com.cityogsadana.bean.UserBean;
import com.cityogsadana.dialogs.ConnectionMessageDialog;
import com.cityogsadana.handler.ApiHandler;
import com.cityogsadana.interfaces.DataHandlerCallback;
import com.cityogsadana.prefrences.UserPref;
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

import java.util.HashMap;

@EActivity(R.layout.activity_login)
public class LoginActivity extends AppCompatActivity implements DataHandlerCallback, ConnectivityReceiver.ConnectivityReceiverListener, View.OnClickListener {

    @ViewById(R.id.activity_login)
    ViewGroup viewGroup;
    @ViewById(R.id.error_check_layout)
    RelativeLayout errorLayout;
    @ViewById(R.id.signUp_layout)
    LinearLayout signUpLayout;
    @ViewById(R.id.forgot_pass_layout)
    TextView forgotPassLayout;
    @ViewById(R.id.edit_email)
    EditText editEmail;
    @ViewById(R.id.edit_password)
    EditText editPassword;
    @ViewById(R.id.button_login)
    TextView loginButton;

    private ConnectionMessageDialog cDialog = new ConnectionMessageDialog();


    @AfterViews
    public void setData() {
        Global.setFont(viewGroup, Global.regular);
        Global.setupUI(viewGroup, this);
        Global.setCustomFont(Global.italic, findViewById(R.id.text_signup));

        signUpLayout.setOnClickListener(this);
        forgotPassLayout.setOnClickListener(this);
        loginButton.setOnClickListener(this);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    protected void onResume() {
        super.onResume();
        AppController.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.signUp_layout:
                Intent intent = new Intent(this, SignUpActivity_.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                break;

            case R.id.forgot_pass_layout:
                Intent intent1 = new Intent(this, ForgotPasswordActivity_.class);
                intent1.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent1);
                break;

            case R.id.button_login:
                String email = editEmail.getText().toString();
                String password = editPassword.getText().toString();

                boolean check = validate(email, password);
                if (!check) {

                    CustomJsonParams customJsonParams = new CustomJsonParams();
                    JSONObject params = customJsonParams.getLogIn(email, password);
                    new ApiHandler(LoginActivity.this).apiResponse(LoginActivity.this, Config.LOGIN_IN, params);
                }
                break;
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.no_change, R.anim.exit_to_right_fast);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected) {
            new CustomCrouton(this, getString(R.string.no_connection), errorLayout).setInAnimation();
        }
    }

    private boolean validate(String email, String password) {
        boolean error = false;
        if (email.isEmpty()) {
            error = true;
            new CustomCrouton(this, "Enter your registered email address.", errorLayout).setInAnimation();
        } else if (password.isEmpty()) {
            error = true;
            new CustomCrouton(this, "Password can't be blank.", errorLayout).setInAnimation();
        }
        return error;
    }

    @Override
    public void onSuccess(HashMap<String, Object> map) {

        JSONObject jsonObject = (JSONObject) map.get(Config.POST_JSON_RESPONSE);
        if (jsonObject != null) {
            try {
                Gson gson = new Gson();
                UserBean user = gson.fromJson(jsonObject.getJSONObject("user").toString(), UserBean.class);
                UserPref.saveUser(this, user);
                cDialog.successShowHome(this, "Congratulations!", jsonObject.getString("msg"), "Ok", false);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onFailure(HashMap<String, Object> map) {
        if (map.containsKey(Config.ERROR)) {
            cDialog.successShow(this, "Error!", (String) map.get(Config.ERROR), "Ok", false);
        } else {
            VolleyError error = (VolleyError) map.get(Config.VOLLEY_ERROR);
            cDialog.successShow(this, "Error!", ErrorHelper.getErrorResponse(error), "Ok", false);

        }
    }
}
