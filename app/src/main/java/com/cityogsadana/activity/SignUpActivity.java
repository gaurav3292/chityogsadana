package com.cityogsadana.activity;

import android.content.Intent;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.telephony.gsm.GsmCellLocation;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;

import com.android.volley.error.VolleyError;
import com.cityogsadana.R;
import com.cityogsadana.adapter.CountrySpinnerAdapter;
import com.cityogsadana.application.AppController;
import com.cityogsadana.bean.NotificationBean;
import com.cityogsadana.bean.UserBean;
import com.cityogsadana.dialogs.ConnectionMessageDialog;
import com.cityogsadana.handler.ApiHandler;
import com.cityogsadana.interfaces.DataHandlerCallback;
import com.cityogsadana.prefrences.UserPref;
import com.cityogsadana.services.MyFirebaseInstanceIDService;
import com.cityogsadana.utils.AccountChecker;
import com.cityogsadana.utils.Config;
import com.cityogsadana.utils.ConnectivityReceiver;
import com.cityogsadana.utils.CustomCrouton;
import com.cityogsadana.utils.CustomJsonParams;
import com.cityogsadana.utils.ErrorHelper;
import com.cityogsadana.utils.Global;
import com.google.firebase.FirebaseApp;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@EActivity(R.layout.activity_sign_up)
public class SignUpActivity extends AppCompatActivity implements ConnectivityReceiver.ConnectivityReceiverListener, View.OnClickListener, DataHandlerCallback, CompoundButton.OnCheckedChangeListener {


    @ViewById(R.id.activity_signup)
    ViewGroup viewGroup;
    @ViewById(R.id.error_check_layout)
    RelativeLayout errorLayout;
    @ViewById(R.id.text_terms)
    TextView termsTxt;
    @ViewById(R.id.text_policy)
    TextView privacyTxt;
    @ViewById(R.id.button_signup)
    TextView buttonSignUp;
    @ViewById(R.id.already_layout)
    LinearLayout alreadyLayout;
    @ViewById(R.id.close)
    ImageButton closeButton;
    @ViewById(R.id.country)
    Spinner countrySpinner;
    @ViewById(R.id.spinner_gender)
    Spinner genderSpinner;
    @ViewById(R.id.edit_name)
    EditText editName;
    @ViewById(R.id.edit_email)
    EditText editEmail;
    @ViewById(R.id.edit_mobile)
    EditText editMobile;
    @ViewById(R.id.edit_address)
    EditText editAddress;
    @ViewById(R.id.edit_password)
    EditText editPassword;
    @ViewById(R.id.edit_confirm_password)
    EditText editConfirmPassword;
    @ViewById(R.id.check)
    CheckBox checkBox;

    private CountrySpinnerAdapter countrySpinnerAdapter;
    private CountrySpinnerAdapter genderAdapter;

    private ConnectionMessageDialog cDialog = new ConnectionMessageDialog();
    private boolean isChecked = false;

    @AfterViews
    public void setData() {

        if(Global.token==null){
            FirebaseApp.initializeApp(this);
            new MyFirebaseInstanceIDService().onTokenRefresh();
        }

        Global.setFont(viewGroup, Global.regular);
        Global.setupUI(viewGroup, this);
        Global.setCustomFont(Global.italic, termsTxt, privacyTxt, findViewById(R.id.text_signin));

        termsTxt.setOnClickListener(this);
        privacyTxt.setOnClickListener(this);
        buttonSignUp.setOnClickListener(this);
        alreadyLayout.setOnClickListener(this);
        closeButton.setOnClickListener(this);
        checkBox.setOnCheckedChangeListener(this);

        countrySpinnerAdapter = new CountrySpinnerAdapter(this, Global.getCountries(this));
        countrySpinner.setAdapter(countrySpinnerAdapter);

        genderAdapter = new CountrySpinnerAdapter(this, getGenders());
        genderSpinner.setAdapter(genderAdapter);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.text_terms:
                Intent intent2 = new Intent(this, PolicyActivity_.class);
                intent2.putExtra("type", Config.T_C);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
                break;

            case R.id.text_policy:
                Intent intent = new Intent(this, PolicyActivity_.class);
                intent.putExtra("type", Config.P_P);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
                break;


            case R.id.already_layout:
                finish();
                break;

            case R.id.close:
                finish();
                break;

            case R.id.button_signup:

                String fullName = editName.getText().toString();
                String email = editEmail.getText().toString();
                String mobile = editMobile.getText().toString();
                String address = editAddress.getText().toString();
                String password = editPassword.getText().toString();
                String confirmPassword = editConfirmPassword.getText().toString();
                boolean check = validate(fullName, email, mobile, address, password, confirmPassword);
                if (!check) {

                    String country = (String) countrySpinner.getSelectedItem();
                    String gender = (String) genderSpinner.getSelectedItem();

                    Global.showProgress(this);
                    CustomJsonParams customJsonParams = new CustomJsonParams();
                    JSONObject params = customJsonParams.getSignupParams(fullName, email, mobile, address, password, country, gender);
                    new ApiHandler(SignUpActivity.this).apiResponse(SignUpActivity.this, Config.SIGN_UP, params);
                }
                break;


        }
    }

    private boolean validate(String fullName, String email, String mobile, String address, String password, String confirmPassword) {
        boolean error = false;

        if (fullName.isEmpty() && email.isEmpty() && mobile.isEmpty() && address.isEmpty() && password.isEmpty() && confirmPassword.isEmpty()) {

            error = true;
            new CustomCrouton(this, "All fields are required", errorLayout).setInAnimation();
        } else if (fullName.isEmpty() || email.isEmpty() || mobile.isEmpty() || address.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            error = true;
            if (fullName.isEmpty()) {
                new CustomCrouton(this, "Name is required", errorLayout).setInAnimation();
            } else if (email.isEmpty()) {
                new CustomCrouton(this, "Email is required", errorLayout).setInAnimation();
            } else if (mobile.isEmpty()) {
                new CustomCrouton(this, "Mobile number is required", errorLayout).setInAnimation();
            } else if (address.isEmpty()) {
                new CustomCrouton(this, "Address is required", errorLayout).setInAnimation();
            } else if (password.isEmpty()) {
                new CustomCrouton(this, "Password is required", errorLayout).setInAnimation();
            } else if (confirmPassword.isEmpty()) {
                new CustomCrouton(this, "Confirm Password is required", errorLayout).setInAnimation();
            }

        } else {

            boolean checkEmail = AccountChecker.checkEmail(email);
            boolean checkPassword = AccountChecker.checkPassword(password);
            boolean checkCcomfirmPassword = AccountChecker.checkConfirmPassword(password, confirmPassword);

            if (!checkEmail) {

                error = true;
                new CustomCrouton(this, "PLease enter a valid email address", errorLayout).setInAnimation();
            } else if (!checkPassword) {
                error = true;
                new CustomCrouton(this, "Password should contain eight characters including one uppercase letter, one lowercase letter, and one number or special character", errorLayout).setInAnimation();
            } else if (!checkCcomfirmPassword) {
                error = true;
                new CustomCrouton(this, "Passwords do not match", errorLayout).setInAnimation();
            } else if (isChecked == false) {
                error = true;
                new CustomCrouton(this, "Please mark the terms and conditions checkbox", errorLayout).setInAnimation();
            }


        }

        return error;

    }

    public List<String> getGenders() {
        List<String> genderList = new ArrayList<>();
        genderList.add("Male");
        genderList.add("Female");
        genderList.add("Other");
        return genderList;

    }


    @Override
    protected void onResume() {
        super.onResume();
        AppController.getInstance().setConnectivityListener(this);
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected) {
            new CustomCrouton(this, getString(R.string.no_connection), errorLayout).setInAnimation();
        }
    }

    @Override
    public void onSuccess(HashMap<String, Object> map) {
        Global.dialog.dismiss();
        JSONObject jsonObject = (JSONObject) map.get(Config.POST_JSON_RESPONSE);
        if (jsonObject != null) {
            try {
                Gson gson = new Gson();
                UserBean user = gson.fromJson(jsonObject.getJSONObject("user").toString(), UserBean.class);
                UserPref.saveUser(this, user);
                UserPref.updateNotification(this,new NotificationBean());
                //finish();
                String fullName[] = user.getName().split(" ");
                cDialog.successShowMain(this, "Welcome", "Thanks "+fullName[0]+" for registering with us ", "Let's Begin", false);
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
    public void onCheckedChanged(CompoundButton buttonView, boolean b) {
        isChecked = b;
    }
}
