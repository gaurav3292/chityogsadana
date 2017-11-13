package com.cityogsadana.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.cache.SimpleImageLoader;
import com.android.volley.error.VolleyError;
import com.android.volley.toolbox.ImageLoader;

import com.cityogsadana.R;
import com.cityogsadana.adapter.CountrySpinnerAdapter;
import com.cityogsadana.application.AppController;
import com.cityogsadana.bean.UserBean;
import com.cityogsadana.dialogs.ConnectionMessageDialog;
import com.cityogsadana.dialogs.DialogUploadPhoto;
import com.cityogsadana.handler.ApiHandler;
import com.cityogsadana.interfaces.DataHandlerCallback;
import com.cityogsadana.interfaces.MediaHandler;
import com.cityogsadana.interfaces.UploadPhotoRemoveHandler;
import com.cityogsadana.prefrences.UserPref;
import com.cityogsadana.utils.AccountChecker;
import com.cityogsadana.utils.Config;
import com.cityogsadana.utils.ConnectivityReceiver;
import com.cityogsadana.utils.CustomCrouton;
import com.cityogsadana.utils.CustomJsonParams;
import com.cityogsadana.utils.ErrorHelper;
import com.cityogsadana.utils.Global;
import com.cityogsadana.utils.OpenCamera;
import com.cityogsadana.utils.OpenGallery;
import com.github.siyamed.shapeimageview.CircularImageView;
import com.google.gson.Gson;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@EActivity(R.layout.activity_edit_profile)
public class EditProfileActivity extends AppCompatActivity implements UploadPhotoRemoveHandler, MediaHandler, DataHandlerCallback,View.OnClickListener, ConnectivityReceiver.ConnectivityReceiverListener{

    @ViewById(R.id.error_check_layout)
    RelativeLayout errorLayout;
    @ViewById(R.id.activity_edit_profile)
    ViewGroup viewGroup;
    @ViewById(R.id.title)
    TextView title;
    @ViewById(R.id.back_button)
    ImageButton backButton;
    @ViewById(R.id.edit_name)
    EditText editName;
    @ViewById(R.id.edit_mobile)
    EditText editMobile;
    @ViewById(R.id.edit_email)
    TextView editEmail;
    @ViewById(R.id.edit_address)
    EditText editAddress;
    @ViewById(R.id.spinner_gender)
    Spinner genderSpinner;
    @ViewById(R.id.edit_country)
    TextView editCountry;
    @ViewById(R.id.user_profile)
    CircularImageView userImage;
    @ViewById(R.id.open_gallery_button)
    TextView galleryButton;
    @ViewById(R.id.save_button)
    TextView saveButton;
    @ViewById(R.id.verify_email)
    TextView verifyEmail;
    @ViewById(R.id.verify_email_layout)
    LinearLayout verifyEmailLayout;



    private UserBean userBean;
    private CountrySpinnerAdapter genderAdapter;
    private ConnectionMessageDialog cDialog = new ConnectionMessageDialog();
    private SimpleImageLoader imageLoader;
    private String profileImg;
    private OpenCamera openCamera;
    private OpenGallery openGallery;
    private static int RESULT_LOAD_IMAGE = 1;
    private static final int CAMERA_CAPTURE_IMAGE_REQUEST_CODE = 100;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 2;
    private static final int MY_PERMISSIONS_REQUEST_READ_CAMERA = 1;

    @AfterViews
    public void setData()
    {
        Global.setFont(viewGroup,Global.regular);
        Global.setupUI(viewGroup,this);

        title.setText("Edit Profile");

        backButton.setOnClickListener(this);
        galleryButton.setOnClickListener(this);
        saveButton.setOnClickListener(this);
        verifyEmail.setOnClickListener(this);


        genderAdapter = new CountrySpinnerAdapter(this, getGenders());
        genderSpinner.setAdapter(genderAdapter);


        setUserData();

    }


    public List<String> getGenders() {
        List<String> genderList = new ArrayList<>();
        genderList.add("Male");
        genderList.add("Female");
        genderList.add("Other");
        return genderList;

    }
    private void setUserData() {

        editName.setText(userBean.getName());
        editMobile.setText(userBean.getPhone());
        editEmail.setText(userBean.getEmail());
        editAddress.setText(userBean.getAddress());
        editCountry.setText(userBean.getCountry());

        if (userBean.getGender().equalsIgnoreCase("M")) {
            genderSpinner.setSelection(0);
        } else if (userBean.getGender().equalsIgnoreCase("F")) {
            genderSpinner.setSelection(1);
        } else if (userBean.getGender().equalsIgnoreCase("O")) {
            genderSpinner.setSelection(2);
        }


        if (userBean.getIs_email_verified().equalsIgnoreCase(Config.YES)) {
            verifyEmail.setVisibility(View.GONE);
            verifyEmailLayout.setVisibility(View.GONE);

        } else {
            verifyEmail.setVisibility(View.VISIBLE);
            verifyEmailLayout.setVisibility(View.VISIBLE);
        }


        if (userBean.getProfile_pic() != null) {
            imageLoader.get(userBean.getProfile_pic(), ImageLoader.getImageListener(
                    userImage, R.drawable.ic_user_yog, R.drawable.ic_user_yog));
        } else {
            userImage.setImageResource(R.drawable.ic_user_yog);
        }


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userBean = UserPref.getUser(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId())
        {
            case R.id.back_button:
                onBackPressed();
                break;

            case R.id.save_button:
                String name = editName.getText().toString();
                String mobile = editMobile.getText().toString();
                String address = editAddress.getText().toString();

                boolean check = validate(name, mobile, address);
                if (!check) {

                    String gender = (String) genderSpinner.getSelectedItem();

                    Global.showProgress(this);
                    CustomJsonParams customJsonParams = new CustomJsonParams();
                    JSONObject params = customJsonParams.getProfileUpdate(userBean.getUser_id(),name,mobile,address,profileImg,gender);
                    new ApiHandler(EditProfileActivity.this).apiResponse(EditProfileActivity.this, Config.EDIT_PROFILE, params);
                }
                break;



            case R.id.open_gallery_button:
                DialogUploadPhoto dialogUploadPhoto = new DialogUploadPhoto().getInstance(this, this, null, "Profile Photo");
                dialogUploadPhoto.show(getSupportFragmentManager(), dialogUploadPhoto.getTag());
                break;

            case R.id.verify_email:
                getVerifyEmail();
                break;


        }
    }

    private void getVerifyEmail() {
        Global.showProgress(this);
        CustomJsonParams customJsonParams = new CustomJsonParams();
        JSONObject params = customJsonParams.getVerifyEmail(userBean.getUser_id(),userBean.getEmail());
        new ApiHandler(EditProfileActivity.this).apiVerifyEmail(EditProfileActivity.this, Config.VERIFY_EMAIL, params);

    }


    private boolean validate(String fullName, String mobile, String address) {
        boolean error = false;

        if (fullName.isEmpty()  && mobile.isEmpty() && address.isEmpty()) {

            error = true;
            new CustomCrouton(this, "All fields are required", errorLayout).setInAnimation();
        } else if (fullName.isEmpty() || mobile.isEmpty() || address.isEmpty()) {
            error = true;
            if (fullName.isEmpty()) {
                new CustomCrouton(this, "Name is required", errorLayout).setInAnimation();
            } else if (mobile.isEmpty()) {
                new CustomCrouton(this, "Mobile number is required", errorLayout).setInAnimation();
            } else if (address.isEmpty()) {
                new CustomCrouton(this, "Address is required", errorLayout).setInAnimation();
            }
        }

        return error;

    }


    @Override
    protected void onResume() {
        super.onResume();
        AppController.getInstance().setConnectivityListener(this);
        imageLoader = AppController.getInstance().getImageLoader();
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
                //finish();
                cDialog.successShowHome(this, "Congratulations!", jsonObject.getString("msg"), "Ok", false);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        JSONObject obj = (JSONObject) map.get(Config.VERIFY_EMAIL_JSON_RESPONSE);
                if(obj != null)
                {
                    try {
                        cDialog.successShow(this, "Congratulations!",obj.getString("msg"), "Ok", false);

                    }catch (JSONException e)
                    {
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
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {

            userImage.setVisibility(View.VISIBLE);
            userImage.setImageBitmap(openGallery.getPhotoGallery(data));
            profileImg = openGallery.getBase64Image();

        }
        if (requestCode == CAMERA_CAPTURE_IMAGE_REQUEST_CODE) {
            if (resultCode == RESULT_OK) {
                // successfully captured the image
                // display it in image view
                userImage.setVisibility(View.VISIBLE);
                userImage.setImageBitmap(openCamera.previewCapturedImage());
                profileImg = openCamera.getBase64Image();

            } else if (resultCode == RESULT_CANCELED) {
                // user cancelled Image capture

                Toast.makeText(getApplicationContext(),
                        "User cancelled image capture", Toast.LENGTH_SHORT)
                        .show();
            } else {
                // failed to capture image
                Toast.makeText(getApplicationContext(),
                        "Sorry! Failed to capture image", Toast.LENGTH_SHORT)
                        .show();
            }
        } else {
            Log.d("result code", "onActivityResult:" + requestCode + ":" + resultCode + ":" + data);
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_READ_CAMERA: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    int storageCheck = ContextCompat.checkSelfPermission(this,
                            Manifest.permission.WRITE_EXTERNAL_STORAGE);

                    if (storageCheck == PackageManager.PERMISSION_GRANTED) {

                        openCamera.getPhoto();
                    } else {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, "camera"},
                                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                    }
                }
            }

            break;
            case MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE: {

                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    if (permissions.length > 1) {
                        if (permissions[1].toLowerCase().contains("camera")) {
                            openCamera.getCamera();
                        } else {
                            openGallery.getPhotoGalleryPermission();
                        }
                    } else {
                        if (permissions[0].toLowerCase().contains("camera")) {
                            openCamera.getCamera();
                        } else {
                            openGallery.getPhotoGalleryPermission();
                        }
                    }


                } else {

                    String per = permissions[0];

                    if (per.contains("CAMERA")) {

                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, "camera"},
                                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                    } else {
                        ActivityCompat.requestPermissions(this,
                                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, per},
                                MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
                    }

                }
            }
            break;

        }

    }

    @Override
    public void setOpenCamera(OpenCamera openCamera) {
        this.openCamera = openCamera;
    }

    @Override
    public void setOpenGallery(OpenGallery openGallery) {
        this.openGallery = openGallery;
    }

    @Override
    public boolean setCameraButton() {
        return true;
    }

}
