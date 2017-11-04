package com.cityogsadana.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.RelativeLayout;

import com.android.volley.error.VolleyError;
import com.cityogsadana.R;
import com.cityogsadana.application.AppController;
import com.cityogsadana.dialogs.ConnectionMessageDialog;
import com.cityogsadana.handler.ApiHandler;
import com.cityogsadana.interfaces.DataHandlerCallback;
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

/**
 * Created by macbookpro on 15/10/17.
 */

@EActivity(R.layout.activity_forgot_password)
public class ForgotPasswordActivity extends AppCompatActivity  implements DataHandlerCallback,ConnectivityReceiver.ConnectivityReceiverListener,View.OnClickListener{

    @ViewById(R.id.activity_forgot_password)
    ViewGroup viewGroup;
    @ViewById(R.id.close_button)
    ImageButton closeButton;
    @ViewById(R.id.btn_send)
    Button sendButton;
    @ViewById(R.id.error_check_layout)
    RelativeLayout errorLayout;
    @ViewById(R.id.edit_email)
    EditText editEmail;

    private ConnectionMessageDialog cDialog = new ConnectionMessageDialog();

    @AfterViews
    public void setData()
    {
        Global.setFont(viewGroup,Global.regular);

        closeButton.setOnClickListener(this);
        sendButton.setOnClickListener(this);

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
        switch(v.getId())
        {
            case R.id.close_button:
                onBackPressed();
                break;

            case R.id.btn_send:
                String email = editEmail.getText().toString();

                if (!email.isEmpty()) {
                    boolean checkEmail = AccountChecker.checkEmail(email);
                    if (!checkEmail) {
                        new CustomCrouton(this, "Enter correct email address.", errorLayout).setInAnimation();

                    } else {
                        CustomJsonParams customJsonParams = new CustomJsonParams();
                        JSONObject params = customJsonParams.getForgotPass(email);
                        new ApiHandler(this).apiResponse(this, Config.FORGOT_PASS, params);

                    }

                } else {
                    new CustomCrouton(this, "Enter your registered email address.", errorLayout).setInAnimation();

                }
                break;
        }


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
