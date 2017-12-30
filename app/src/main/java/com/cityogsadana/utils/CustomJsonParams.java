package com.cityogsadana.utils;

import android.util.Log;


import org.json.JSONException;
import org.json.JSONObject;


public class CustomJsonParams {

    JSONObject params;

    public JSONObject getLogIn(String email, String password) {
        params = new JSONObject();
        try {
//            params.put("device_type", "android");
            params.put("email", email);
            params.put("password", password);

            Log.d("params...", params.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return params;
    }

    public JSONObject getChangePass(String userId, String password, String newPass) {
        params = new JSONObject();
        try {
            params.put("UserId", userId);
            params.put("oldPassword", password);
            params.put("newPassword", newPass);

            Log.d("params...", params.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return params;
    }


    public JSONObject getForgotPass(String email) {
        params = new JSONObject();
        try {
            params.put("email", email);

            Log.d("params...", params.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return params;
    }

    public JSONObject getSignupParams(String fullName, String email, String mobile, String address, String password, String country, String gender) {
        params = new JSONObject();
        try {


            params.put("name", fullName);
            params.put("email", email);
            params.put("phone", mobile);
            params.put("address", address);
            params.put("password", password);
            if (gender.equalsIgnoreCase("Male")) {
                params.put("gender", "M");
            } else if (gender.equalsIgnoreCase("Female")) {
                params.put("gender", "F");
            } else {
                params.put("gender", "O");
            }

            params.put("country", country);
            params.put("deviceType", email);

            Log.d("params...", params.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return params;
    }

    public JSONObject getSelfTestParams(String user_id, int totalTrue) {
        params = new JSONObject();
        try {
            params.put("userId", user_id);
            params.put("numberOfTrue", totalTrue);

            Log.d("params...", params.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return params;
    }

    public JSONObject getUserData(String user_id) {

        params = new JSONObject();
        try {
            params.put("userId", user_id);

            Log.d("params...", params.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return params;
    }

    public JSONObject getProfileUpdate(String userId,String name, String mobile, String address, String profileImg, String gender) {
        params = new JSONObject();
        try {
            params.put("userId", userId);
            params.put("name", name);
            params.put("phone", mobile);
            params.put("address", address);
            if (gender.equalsIgnoreCase("Male")) {
                params.put("gender", "M");
            } else if (gender.equalsIgnoreCase("Female")) {
                params.put("gender", "F");
            } else {
                params.put("gender", "O");
            }

            params.put("profilePic", profileImg);
            Log.d("params...", params.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return params;
    }
    public JSONObject getVerifyEmail(String userId,String email) {
        params = new JSONObject();
        try {
            params.put("userId", userId);
            params.put("email", email);

            Log.d("params...", params.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return params;
    }

    public JSONObject startTest(String userId,String date,String levelNumber) {
        params = new JSONObject();
        try {
            params.put("userId",userId );
            params.put("date", date);
            params.put("levelNumber",levelNumber);
            Log.d("params...", params.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return params;

    }

    public JSONObject submitTest(String userId, String currentDateStr, String userLevel,int trues, int ques) {
        params = new JSONObject();
        try {
            params.put("userId",userId );
            params.put("date", currentDateStr);
            params.put("levelNumber",userLevel);
            params.put("isResult","YES");
            params.put("numberOfTrue",trues);
            params.put("totalNumberOfQuestions",ques);
            Log.d("params...", params.toString());

        } catch (JSONException e) {
            e.printStackTrace();

        }
        return params;
    }

    public JSONObject getLevelResultParams(String user_id, String levelNumber) {
        params = new JSONObject();
        try {
            params.put("userId",user_id );
            params.put("levelNumber", levelNumber);
            Log.d("params...", params.toString());

        } catch (JSONException e) {
            e.printStackTrace();

        }
        return params;
    }
}
