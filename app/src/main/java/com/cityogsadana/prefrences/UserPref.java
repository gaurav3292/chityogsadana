package com.cityogsadana.prefrences;

import android.content.Context;
import android.content.SharedPreferences;

import com.cityogsadana.bean.LevelBean;
import com.cityogsadana.bean.NotificationBean;
import com.cityogsadana.bean.UserBean;


/**
 * Created by pc15 on 9/20/2017.
 */

public class UserPref {

    private static final String USER_INFO = "user_Info";
    private static final String NOTI_INFO = "noti_info";
    private static final String USER_ID = "user_id";
    private static final String NAME = "name";
    private static final String EMAIL = "user_email";
    private static final String EMAIL_VERIFIED = "user_email_verified";
    private static final String PHONE = "user_phone";
    private static final String PROFILE_IMG = "user_image";
    private static final String ADDRESS = "user_address";
    private static final String GENDER = "user_gender";
    private static final String COUNTRY = "country";
    private static final String LEVEL = "level";
    private static final String SELF_RESULT = "self_result";
    private static final String TOTAL_DAYS = "total_days";
    private static final String COMPLETED_DAYS = "completed_days";
    private static final String IS_RESULT = "is_result";
    private static final String SUB_LEVEL = "sub_level";
    private static final String IS_ExTRA_RESULT = "is_extra_result";
    private static final String IS_PAYMENT_REQUIRED = "is_payment_required";
    private static final String IS_PAYMENT_MADE = "is_payment_made";
    private static final String IS_MORNING_NOTI = "is_morning_noti";
    private static final String IS_EVE_NOTI = "is_eve_noti";


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
            String country = user.getCountry();
            LevelBean levelBean = user.getLevel();
            String selfTest = user.getSelf_result();
            String level = null, isResult = "No", subLevel = null;
            int completedDays = 0, totalDays = 0, extraResult = -1;
            boolean isPaymentRequired = false;
            boolean isPaymentMade = false;
            if (levelBean != null) {
                isPaymentMade = user.getLevel().isPaymentMade();
                isPaymentRequired = user.getLevel().isPaymentRequired();
                totalDays = user.getLevel().getTotalNumberOfDays();
                level = user.getLevel().getUserLevel();
                completedDays = user.getLevel().getCompletedNumberOfDays();
                isResult = user.getLevel().getIsResult();
                subLevel = user.getLevel().getUserSubLevel();
                extraResult = user.getLevel().getIsExtraResult();

            }


            if (userId != null) {
                editor.putString(USER_ID, userId);
            }
            if (country != null) {
                editor.putString(COUNTRY, country);
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

            if (level != null) {
                editor.putString(LEVEL, level);
            }
            if (selfTest != null) {
                if (!selfTest.equalsIgnoreCase("-1"))
                    editor.putString(SELF_RESULT, selfTest);
            }


            editor.putInt(TOTAL_DAYS, totalDays);
            editor.putInt(COMPLETED_DAYS, completedDays);
            editor.putString(IS_RESULT, isResult);
            editor.putString(SUB_LEVEL, subLevel);
            editor.putInt(IS_ExTRA_RESULT, extraResult);
            editor.putBoolean(IS_PAYMENT_REQUIRED, isPaymentRequired);
            editor.putBoolean(IS_PAYMENT_MADE, isPaymentMade);


            editor.commit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static UserBean getUser(Context context) {

        try {
            UserBean user = new UserBean();
            LevelBean levelBean = new LevelBean();
            SharedPreferences mPref = context.getSharedPreferences(USER_INFO, 0);
            user.setUser_id(mPref.getString(USER_ID, null));
            user.setName(mPref.getString(NAME, null));
            user.setProfile_pic(mPref.getString(PROFILE_IMG, null));
            user.setPhone(mPref.getString(PHONE, null));
            user.setEmail(mPref.getString(EMAIL, null));
            user.setCountry(mPref.getString(COUNTRY, null));
            user.setIs_email_verified(mPref.getString(EMAIL_VERIFIED, null));
            user.setGender(mPref.getString(GENDER, null));
            user.setAddress(mPref.getString(ADDRESS, null));

            levelBean.setUserLevel(mPref.getString(LEVEL, null));
            levelBean.setIsResult(mPref.getString(IS_RESULT, null));
            user.setSelf_result(mPref.getString(SELF_RESULT, null));
            levelBean.setTotalNumberOfDays(mPref.getInt(TOTAL_DAYS, 0));
            levelBean.setCompletedNumberOfDays(mPref.getInt(COMPLETED_DAYS, 0));
            levelBean.setUserSubLevel(mPref.getString(SUB_LEVEL, null));
            levelBean.setIsExtraResult(mPref.getInt(IS_ExTRA_RESULT, -1));
            levelBean.setPaymentRequired(mPref.getBoolean(IS_PAYMENT_REQUIRED, false));
            levelBean.setPaymentMade(mPref.getBoolean(IS_PAYMENT_MADE, false));
            user.setLevel(levelBean);


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


    public static void updateNotification(Context context, NotificationBean notificationBean) {

        try {

            SharedPreferences mPrefs = context.getSharedPreferences(NOTI_INFO, 0);
            SharedPreferences.Editor editor = mPrefs.edit();

            if (notificationBean != null) {
                boolean isMorningNoti = notificationBean.isMorningNoti();
                boolean isEveNoti = notificationBean.isEveNoti();


                editor.putBoolean(IS_MORNING_NOTI, isMorningNoti);
                editor.putBoolean(IS_EVE_NOTI, isEveNoti);

                editor.commit();


            }


        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static NotificationBean getNotificationBean(Context context) {

        try {

            NotificationBean notificationBean = new NotificationBean();
            SharedPreferences mPref = context.getSharedPreferences(NOTI_INFO, 0);

            notificationBean.setMorningNoti(mPref.getBoolean(IS_MORNING_NOTI, false));
            notificationBean.setEveNoti(mPref.getBoolean(IS_EVE_NOTI, false));

            return notificationBean;


        } catch (Exception e) {

            e.printStackTrace();
        }

        return null;
    }


}
