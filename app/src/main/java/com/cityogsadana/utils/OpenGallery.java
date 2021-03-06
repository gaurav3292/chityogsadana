package com.cityogsadana.utils;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.provider.MediaStore;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;
import androidx.core.content.ContextCompat;
import android.util.Base64;



import java.io.ByteArrayOutputStream;


public class OpenGallery {


    private Activity activity;
    private Fragment fragment;
    private static final int MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE = 2;
    private static int RESULT_LOAD_IMAGE = 1;
    private String base64Image;


    public String getBase64Image() {
        return base64Image;
    }

    public OpenGallery(Activity activity) {

        this.activity = activity;
        getPhotoGalleryPermission();
    }

    public OpenGallery(Fragment fragment) {

        this.fragment = fragment;
        getPhotoGalleryPermission();
    }

    public void getPhotoGalleryPermission() {
        if(activity!=null){
            int storageCheck = ContextCompat.checkSelfPermission(activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (storageCheck == PackageManager.PERMISSION_GRANTED) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                activity.startActivityForResult(i, RESULT_LOAD_IMAGE);

            } else {
                ActivityCompat.requestPermissions((activity),
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, "photos"},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        }else{
            int storageCheck = ContextCompat.checkSelfPermission(fragment.getActivity(),
                    Manifest.permission.WRITE_EXTERNAL_STORAGE);

            if (storageCheck == PackageManager.PERMISSION_GRANTED) {
                Intent i = new Intent(
                        Intent.ACTION_PICK,
                        MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                fragment.startActivityForResult(i, RESULT_LOAD_IMAGE);

            } else {
                fragment.requestPermissions(
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, "photos"},
                        MY_PERMISSIONS_REQUEST_WRITE_EXTERNAL_STORAGE);
            }
        }



    }

    public Bitmap getPhotoGallery(Intent data) {
        Bitmap bitmap = null;
        Uri selectedImage = data.getData();
        String[] filePathColumn = {MediaStore.Images.Media.DATA};
        Cursor cursor = null;
        if(activity!=null){
             cursor = activity.getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);
        }else{
            cursor = fragment.getActivity().getContentResolver().query(selectedImage,
                    filePathColumn, null, null, null);

        }
        cursor.moveToFirst();

        int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
        String picturePath = cursor.getString(columnIndex);

        cursor.close();

        bitmap = BitmapFactory.decodeFile(picturePath);

        Bitmap bm = BitmapFactory.decodeFile(picturePath);
        bm = Bitmap.createScaledBitmap(bm, 300, 300, false);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos); //bm is the bitmap object
        byte[] b = baos.toByteArray();
        base64Image = Base64.encodeToString(b, Base64.DEFAULT);

        return bitmap;


    }

}
