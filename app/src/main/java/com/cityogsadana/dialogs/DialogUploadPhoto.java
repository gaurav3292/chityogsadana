package com.cityogsadana.dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cityogsadana.R;
import com.cityogsadana.interfaces.MediaHandler;
import com.cityogsadana.interfaces.UploadPhotoRemoveHandler;
import com.cityogsadana.utils.Global;
import com.cityogsadana.utils.OpenCamera;
import com.cityogsadana.utils.OpenGallery;


public class DialogUploadPhoto extends BottomSheetDialogFragment implements View.OnClickListener {

    private Activity activity;
    private ViewGroup viewGroup;
    private LinearLayout takePhoto, gallery;
    private TextView headingTxt;
    public static MediaHandler mediaHandler;
    public static UploadPhotoRemoveHandler uploadPhotoRemoveHandler;
    public static Fragment fragment;
    public static String heading;


    public static DialogUploadPhoto getInstance(MediaHandler mediaHandler1, UploadPhotoRemoveHandler uploadPhotoRemoveHandler1, Fragment fragment1, String heading1) {
        mediaHandler = mediaHandler1;
        fragment = fragment1;
        heading = heading1;
        uploadPhotoRemoveHandler = uploadPhotoRemoveHandler1;
        DialogUploadPhoto dialogUploadPhoto = new DialogUploadPhoto();
        return dialogUploadPhoto;

    }

    @Override
    public void setupDialog(Dialog dialog, int style) {
        super.setupDialog(dialog, style);
        activity = getActivity();
        View contentView = View.inflate(getContext(), R.layout.dialog_upload_photo, null);
        dialog.setContentView(contentView);
        viewGroup = (ViewGroup) dialog.findViewById(R.id.dialog_upload_photo);
        takePhoto = (LinearLayout) dialog.findViewById(R.id.open_camera);
        gallery = (LinearLayout) dialog.findViewById(R.id.open_gallery);
        headingTxt = (TextView) dialog.findViewById(R.id.heading);

        Global.setFont(viewGroup, Global.regular);
        takePhoto.setOnClickListener(this);
        gallery.setOnClickListener(this);

        headingTxt.setText(heading);


        if (!uploadPhotoRemoveHandler.setCameraButton()) {
            takePhoto.setVisibility(View.GONE);

        } else {
            takePhoto.setVisibility(View.VISIBLE);
        }


    }


    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.open_camera:
                OpenCamera openCamera = new OpenCamera(activity);
                mediaHandler.setOpenCamera(openCamera);
                dismiss();
                break;

            case R.id.open_gallery:
                OpenGallery openGallery;
                if(fragment!=null){
                     openGallery = new OpenGallery(fragment);
                }else{
                     openGallery = new OpenGallery(activity);
                }

                mediaHandler.setOpenGallery(openGallery);

                dismiss();
                break;




        }
    }


}
