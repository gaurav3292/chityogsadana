package com.cityogsadana.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.cityogsadana.utils.AccountChecker;
import com.cityogsadana.utils.Config;
import com.cityogsadana.utils.ConnectivityReceiver;
import com.cityogsadana.utils.CustomCrouton;
import com.cityogsadana.utils.CustomJsonParams;
import com.cityogsadana.utils.ErrorHelper;
import com.cityogsadana.utils.Global;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

@EActivity(R.layout.activity_change_password)
public class ChangePassword extends AppCompatActivity implements DataHandlerCallback,View.OnClickListener,ConnectivityReceiver.ConnectivityReceiverListener{

    @ViewById(R.id.activity_change_password)
    ViewGroup viewGroup;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.error_check_layout)
    RelativeLayout errorLayout;
    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.save_button)
    TextView saveButton;
    @ViewById(R.id.edit_current_pass)
    EditText editCurrentPass;
    @ViewById(R.id.edit_new_pass)
    EditText editNewPass;
    @ViewById(R.id.edit_re_pass)
    EditText editRePass;

    private ConnectionMessageDialog cDialog = new ConnectionMessageDialog();
    private UserBean userBean;

    @AfterViews
    public  void setData(){

        Global.setFont(viewGroup,Global.regular);

        backButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);
        title.setText("Change Password");

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
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.anim.no_change,R.anim.exit_to_right_fast);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId())
        {
            case R.id.back_button:
              onBackPressed();
                break;

            case R.id.save_button:
                String current = editCurrentPass.getText().toString();
                String newPass = editNewPass.getText().toString();
                String rePass = editRePass.getText().toString();

                boolean check = validate(current, newPass, rePass);
                if (!check) {
                    CustomJsonParams customJsonParams = new CustomJsonParams();
                    JSONObject params = customJsonParams.getChangePass(userBean.getUser_id(),current,newPass);
                    new ApiHandler(this).apiResponse(this, Config.CHANGE_PASS, params);

                }
                break;



        }
    }

    private boolean validate(String current, String newPass, String rePass) {

        boolean error = false;
        if (current.isEmpty() || newPass.isEmpty() || rePass.isEmpty()) {
            error = true;
            if (current.isEmpty()) {
                new CustomCrouton(this, "Current password is required.", errorLayout).setInAnimation();
            } else if (newPass.isEmpty()) {
                new CustomCrouton(this, "New password is required.", errorLayout).setInAnimation();
            } else if (rePass.isEmpty()) {
                new CustomCrouton(this, "Re-enter password is required.", errorLayout).setInAnimation();
            }
        } else {

            boolean checkPasword = AccountChecker.checkPasswordlength(newPass);
            if (!checkPasword) {
                error = true;
                new CustomCrouton(this, "Password is too short.", errorLayout).setInAnimation();
            } else if (newPass.equalsIgnoreCase(current)) {
                error = true;
                new CustomCrouton(this, "Please choose your new password different from your current password.", errorLayout).setInAnimation();
            } else if (!newPass.equalsIgnoreCase(rePass)) {
                error = true;
                new CustomCrouton(this, "Password doesn't match.", errorLayout).setInAnimation();
            }
        }
        return error;
    }

    @Override
    public void onNetworkConnectionChanged(boolean isConnected) {
        if (!isConnected) {
            new CustomCrouton(this, getString(R.string.no_connection), errorLayout).setInAnimation();
        }
    }

    @Override
    public void onSuccess(HashMap<String, Object> map) {

        JSONObject jsonObject = (JSONObject) map.get(Config.POST_JSON_RESPONSE);
        if (jsonObject != null) {
            try {
                cDialog.successShowActivityDismiss(this, "Congratulations!",jsonObject.getString("msg"), "Ok", false);
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
