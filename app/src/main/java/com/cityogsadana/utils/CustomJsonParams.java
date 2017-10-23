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


}
