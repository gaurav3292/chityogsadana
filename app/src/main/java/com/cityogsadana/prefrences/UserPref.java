package com.cityogsadana.prefrences;

import android.content.Context;
import android.content.SharedPreferences;

import com.cityogsadana.bean.UserBean;
import com.google.gson.Gson;


import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by pc15 on 9/20/2017.
 */

public class UserPref {

    private static final String USER_INFO = "user_Info";
    private static final String USER_ID = "user_id";
    private static final String NAME = "name";
    private static final String EMAIL = "user_email";
    private static final String EMAIL_VERIFIED = "user_email_verified";
    private static final String PHONE = "user_phone";
    private static final String PROFILE_IMG = "user_image";
    private static final String ADDRESS = "user_address";
    private static final String GENDER = "user_gender";
    private static final String LEVEL = "level";
    private static final String SELF_RESULT = "self_result";


    public static void saveUser(Context context, UserBean user) {
        try {

            SharedPreferences mPrefs = context.getSharedPreferences(USER_INFO, 0);
            SharedPreferences.Editor editor = mPrefs.edit();

            String userId = user.getUser_id();
            String fullName = user.getName();
            String email = user.getEmail();
            String emailVerified = user.getIs_email_verified();
            String phone = user.getPhone();
            String profileImage = user.getProfile_pic();
            String address = user.getAddress();
            String gender = user.getGender();
            String level = user.getLevel();
            String selfTest = user.getSelf_result();

            if (userId != null) {
                editor.putString(USER_ID, userId);
            }

            if (fullName != null) {
                editor.putString(NAME, fullName);
            }
            if (email != null) {
                editor.putString(EMAIL, email);
            }
            if (emailVerified != null) {
                editor.putString(EMAIL_VERIFIED, emailVerified);
            }
            if (phone != null) {
                editor.putString(PHONE, phone);
            }
            if (profileImage != null) {
                editor.putString(PROFILE_IMG, profileImage);
            }

            if (gender != null) {
                editor.putString(GENDER, gender);
            }

            if (address != null) {
                editor.putString(ADDRESS, address);
            }

            if(level!=null){
                editor.putString(LEVEL,level);
            }
             if(selfTest!=null){
                editor.putString(SELF_RESULT,selfTest);
            }



            editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static UserBean getUser(Context context) {

        try {
            UserBean user = new UserBean();
            SharedPreferences mPref = context.getSharedPreferences(USER_INFO, 0);
            user.setUser_id(mPref.getString(USER_ID, null));
            user.setName(mPref.getString(NAME, null));
            user.setProfile_pic(mPref.getString(PROFILE_IMG, null));
            user.setPhone(mPref.getString(PHONE, null));
            user.setEmail(mPref.getString(EMAIL, null));
            user.setIs_email_verified(mPref.getString(EMAIL_VERIFIED, null));
            user.setGender(mPref.getString(GENDER, null));
            user.setAddress(mPref.getString(ADDRESS, null));
            user.setLevel(mPref.getString(LEVEL,null));
            user.setSelf_result(mPref.getString(SELF_RESULT,null));


            return user;


        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public static void deleteUserInfo(Context context) {
        try {
            SharedPreferences mPrefs = context.getSharedPreferences(USER_INFO, 0);
            SharedPreferences.Editor editor = mPrefs.edit();
            editor.clear();
            editor.commit();


        } catch (Exception e) {
            e.printStackTrace();

        }
    }


}
