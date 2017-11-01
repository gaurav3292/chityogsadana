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
        try {
            params.put("email", email);

            Log.d("params...", params.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return params;
    }

    public JSONObject getSignupParams(String fullName, String email, String mobile, String address, String password, String country, String gender) {
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
        try {
            params.put("userId", user_id);
            params.put("numberOfTrue", totalTrue);

            Log.d("params...", params.toString());

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return params;
    }
}
