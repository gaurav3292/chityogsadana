package com.cityogsadana.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.cityogsadana.R;
import com.cityogsadana.application.AppController;
import com.cityogsadana.bean.UserBean;
import com.cityogsadana.handler.ApiHandler;
import com.cityogsadana.interfaces.DataHandlerCallback;
import com.cityogsadana.prefrences.UserPref;
import com.cityogsadana.utils.Config;
import com.cityogsadana.utils.ConnectivityReceiver;
import com.cityogsadana.utils.CustomCrouton;
import com.cityogsadana.utils.CustomJsonParams;
import com.cityogsadana.utils.Global;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;


@EActivity(R.layout.activity_profile)
public class ProfileActivity extends AppCompatActivity implements DataHandlerCallback, ConnectivityReceiver.ConnectivityReceiverListener, View.OnClickListener {

    @ViewById(R.id.activity_profile)
    ViewGroup viewGroup;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.edit_change_pass)
    LinearLayout editChangePass;
    @ViewById(R.id.edit_logout)
    LinearLayout editLogOut;
    @ViewById(R.id.p_p_layout)
    TextView ppLayout;
    @ViewById(R.id.t_c_layout)
    TextView termsLayout;
    @ViewById(R.id.user_name)
    TextView userName;
    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.edit_profile_layout)
    LinearLayout editProfileLayout;
    @ViewById(R.id.error_check_layout)
    RelativeLayout errorLayout;
    @ViewById(R.id.user_profile)
    CircularImageView userImage;
    @ViewById(R.id.support_layout)
    TextView support;

    private UserBean userBean;
    private ImageLoader imageLoader;

    @AfterViews
    public void setData() {
        Global.setFont(viewGroup, Global.regular);
        Global.setCustomFont(Global.italic, termsLayout, ppLayout);

        title.setText("Profile");

        backButton.setOnClickListener(this);
        editChangePass.setOnClickListener(this);
        editProfileLayout.setOnClickListener(this);
        editLogOut.setOnClickListener(this);
        ppLayout.setOnClickListener(this);
        termsLayout.setOnClickListener(this);
        support.setOnClickListener(this);


        getUserData();

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userBean = UserPref.getUser(this);
    }

    private void getUserData() {

        CustomJsonParams customJsonParams = new CustomJsonParams();
        JSONObject params = customJsonParams.getUserData(userBean.getUser_id());
        new ApiHandler(ProfileActivity.this).apiResponse(ProfileActivity.this, Config.GET_USER, params);

    }



    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.back_button:
                onBackPressed();
                break;

            case R.id.edit_change_pass:
                Intent intent = new Intent(this, ChangePassword_.class);
                startActivity(intent);
                overridePendingTransition(R.anim.enter_friom_rignt_fast, R.anim.no_change);
                break;

            case R.id.edit_logout:
                UserPref.deleteUserInfo(this);
                Intent logout = new Intent(this, LoginActivity_.class);
                logout.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(logout);
                break;

            case R.id.edit_profile_layout:
                Intent profile = new Intent(this, EditProfileActivity_.class);
                profile.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(profile);
                break;

            case R.id.p_p_layout:

                Intent intent2 = new Intent(this, PolicyActivity_.class);
                intent2.putExtra("type", Config.P_P);
                intent2.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent2);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
                break;

            case R.id.t_c_layout:
                Intent intent3 = new Intent(this, PolicyActivity_.class);
                intent3.putExtra("type", Config.T_C);
                intent3.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent3);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.slide_out_bottom);
                break;

            case R.id.support_layout:
                String url = "http://www.chityogsadhana.com/contact-us/";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
                break;

        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        AppController.getInstance().setConnectivityListener(this);
        imageLoader = AppController.getInstance().getImageLoader();

        setUserData();


    }

    private void setUserData() {

        userName.setText(userBean.getName());

        if (userBean.getProfile_pic() != null) {
            imageLoader.get(userBean.getProfile_pic(), ImageLoader.getImageListener(
                    userImage, R.drawable.ic_user_yog, R.drawable.ic_user_yog));
        } else {
            userImage.setImageResource(R.drawable.ic_user_yog);
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
                Gson gson = new Gson();
                UserBean user = gson.fromJson(jsonObject.getJSONObject("user").toString(), UserBean.class);
                UserPref.saveUser(this, user);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

    }

    @Override
    public void onFailure(HashMap<String, Object> map) {

    }
}
