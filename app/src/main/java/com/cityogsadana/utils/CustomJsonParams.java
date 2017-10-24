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

    public JSONObject getChangePass(String userId, String password ,String newPass) {
        params = new JSONObject();
        try {
            params.put("UserId", userId);
            params.put("password", password);
            params.put("new_password", newPass);

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
}
