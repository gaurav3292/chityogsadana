package com.cityogsadana.utils;


import com.android.volley.NetworkResponse;
import com.android.volley.error.AuthFailureError;
import com.android.volley.error.NetworkError;
import com.android.volley.error.NoConnectionError;
import com.android.volley.error.ParseError;
import com.android.volley.error.ServerError;
import com.android.volley.error.TimeoutError;
import com.android.volley.error.VolleyError;

import org.json.JSONObject;

import java.net.SocketTimeoutException;
import java.net.UnknownHostException;

public class ErrorHelper {

    public static String getErrorResponse(VolleyError error)
    {
        String json = null;

        NetworkResponse response = error.networkResponse;
        if(response != null && response.data != null){
            switch(response.statusCode){
                case 500:
                    json = new String(response.data);
                    try{
                        JSONObject obj = new JSONObject(json);
                        json = obj.getString("message");
                    }
                    catch (Exception e) {
                        json = "Unknown Error";
                    }

                    if(json != null)
                    {
                        return json;
                        //TODO
                    }
                    break;
            }
            //Additional cases
        }

        if (error instanceof TimeoutError){
            return "The connection has timed out. Please try again.";
        }
        else if( error instanceof NoConnectionError) {
            return "Please check your connection settings.";
        } else if (error instanceof AuthFailureError) {
            return "Authorisation Failure.";
        } else if (error instanceof ServerError) {
            return "Server Failure.";
        } else if (error instanceof NetworkError) {
            return "Network Failure.";
        } else if (error instanceof ParseError) {
            return "Parsing Failure.";
        }
        return "Please check your connection setting.";
    }

    public static String getErrorResponse(Exception exception)
    {
        if (exception instanceof SocketTimeoutException){
            return "The connection has timed out. Please try again.";
        }
         else if (exception instanceof UnknownHostException) {
            return "Please check your connection settings.";
        } else
            return "Please check your connection settings.";
    }
}
