package com.cityogsadana.handler;

import android.content.Context;
import android.util.Log;

import com.android.volley.DefaultRetryPolicy;
import com.android.volley.NetworkResponse;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyLog;
import com.android.volley.error.VolleyError;
import com.android.volley.request.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.cityogsadana.application.AppController;
import com.cityogsadana.interfaces.DataHandlerCallback;
import com.cityogsadana.utils.Config;


import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;


public class ApiHandler {

    private HashMap<String, Object> map = new HashMap<>();
    private DataHandlerCallback mDataHandler;


    public ApiHandler(DataHandlerCallback mDataHandler) {
        this.mDataHandler = mDataHandler;
    }


    public void apiResponse(final Context context, String url, JSONObject params) {


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Config.URL + url, params,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(final JSONObject jsonObject) {
                        Log.d("response", jsonObject.toString());

                        try {
                            final String status = jsonObject.getString(Config.STATUS);
                            if (status.equalsIgnoreCase(Config.SUCCESS)) {

                                map.put(Config.POST_JSON_RESPONSE, jsonObject);
                                mDataHandler.onSuccess(map);

                            } else if (status.equalsIgnoreCase(Config.ERROR)) {

                                map.put(Config.ERROR, jsonObject.getString("msg"));
                                mDataHandler.onFailure(map);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("error", "Error: " + error.toString());
                map.put(Config.VOLLEY_ERROR, error);
                mDataHandler.onFailure(map);

            }
        });

        // Adding request to request queue
        jsonObjReq.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(jsonObjReq);
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(200 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    public void apiReadNoti(final Context context, String url, JSONObject params) {


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Config.URL + url, params,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(final JSONObject jsonObject) {
                        Log.d("response", jsonObject.toString());

                        try {
                            final String status = jsonObject.getString(Config.STATUS);
                            if (status.equalsIgnoreCase(Config.SUCCESS)) {

                                map.put(Config.POST_JSON_RESPONSE, jsonObject);
                            //    mDataHandler.onSuccess(map);

                            } else if (status.equalsIgnoreCase(Config.ERROR)) {

                                map.put(Config.ERROR, jsonObject.getString("msg"));
                             //   mDataHandler.onFailure(map);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("error", "Error: " + error.toString());
             //   map.put(Config.VOLLEY_ERROR, error);
                mDataHandler.onFailure(map);

            }
        });

        // Adding request to request queue
        jsonObjReq.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(jsonObjReq);
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(200 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    public void apiResponseCheck(final Context context, String url, JSONObject params) {


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Config.URL + url, params,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(final JSONObject jsonObject) {
                        Log.d("response", jsonObject.toString());

                        try {
                            final String status = jsonObject.getString(Config.STATUS);
                            if (status.equalsIgnoreCase(Config.SUCCESS)) {

                                map.put(Config.CHECK_RESPONSE, jsonObject);
                                mDataHandler.onSuccess(map);

                            } else if (status.equalsIgnoreCase(Config.ERROR)) {

                                map.put(Config.ERROR, jsonObject.getString("msg"));
                                mDataHandler.onFailure(map);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("error", "Error: " + error.toString());
                map.put(Config.VOLLEY_ERROR, error);
                mDataHandler.onFailure(map);

            }
        });

        // Adding request to request queue
        jsonObjReq.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(jsonObjReq);
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(200 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }


    public void apiVerifyEmail(final Context context, String url, JSONObject params) {


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Config.URL + url, params,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(final JSONObject jsonObject) {
                        Log.d("response", jsonObject.toString());

                        try {
                            final String status = jsonObject.getString(Config.STATUS);
                            if (status.equalsIgnoreCase(Config.SUCCESS)) {

                                map.put(Config.VERIFY_EMAIL_JSON_RESPONSE, jsonObject);
                                mDataHandler.onSuccess(map);

                            } else if (status.equalsIgnoreCase(Config.ERROR)) {

                                map.put(Config.ERROR, jsonObject.getString("msg"));
                                mDataHandler.onFailure(map);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("error", "Error: " + error.toString());
                map.put(Config.VOLLEY_ERROR, error);
                mDataHandler.onFailure(map);

            }
        });

        // Adding request to request queue
        jsonObjReq.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(jsonObjReq);
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(200 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    public void apiGetResponse(final Context context, String url) {


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(final JSONObject jsonObject) {
                        Log.d("response", jsonObject.toString());

                        try {
                            map.put(Config.GET_RESPONSE, jsonObject);
                            mDataHandler.onSuccess(map);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("error", "Error: " + error.toString());

                map.put(Config.VOLLEY_ERROR, error);
                mDataHandler.onFailure(map);
            }
        });

        // Adding request to request queue

        jsonObjReq.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(jsonObjReq);
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(200 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }



//    public void getMultiPartFileData(final Context context, final String filename, final byte[] byteArray, final String mimeType) {
//        VolleyMultipartRequest multipartRequest = new VolleyMultipartRequest(Request.Method.POST, Config.CLOUD_API, new Response.Listener<NetworkResponse>() {
//            @Override
//            public void onResponse(NetworkResponse response) {
//                String resultResponse = new String(response.data);
//                try {
//                    JSONObject result = new JSONObject(resultResponse);
//                    Log.d("upload response", result.toString());
//                    map.put(Config.MULTIPART_FILE_RESPONSE, result);
//                    mDataHandler.onSuccess(map);
//
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                VolleyLog.d("error", "Error: " + error.toString());
//                map.put(Config.VOLLEY_ERROR, error);
//                mDataHandler.onFailure(map);
//                error.printStackTrace();
//            }
//        }) {
//            @Override
//            protected Map<String, String> getParams() {
//                return null;
//            }
//
//
//            @Override
//            protected Map<String, DataPart> getByteData() {
//                Map<String, DataPart> params = new HashMap<>();
//                // file name could found file base or direct access from real path
//                // for now just get bitmap data from ImageView
//                params.put("imageForCloudinary", new DataPart(filename, byteArray, mimeType));
//                Log.d("params...",params.toString());
//
//                return params;
//            }
//        };
//
//        VolleySingleton.getInstance(context).addToRequestQueue(multipartRequest);
//    }

    public void apiPaymentResponse(final Context context, String url, JSONObject params) {


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Config.URL + url, params,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(final JSONObject jsonObject) {
                        Log.d("response", jsonObject.toString());

                        try {
                            final String status = jsonObject.getString(Config.STATUS);
                            if (status.equalsIgnoreCase(Config.SUCCESS)) {

                                map.put(Config.PAYMENT_RESPONSE, jsonObject);
                                mDataHandler.onSuccess(map);

                            } else if (status.equalsIgnoreCase(Config.ERROR)) {

                                map.put(Config.ERROR, jsonObject.getString("msg"));
                                mDataHandler.onFailure(map);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("error", "Error: " + error.toString());
                map.put(Config.VOLLEY_ERROR, error);
                mDataHandler.onFailure(map);

            }
        });

        // Adding request to request queue
        jsonObjReq.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(jsonObjReq);
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(200 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }

    public void apiUserResponse(final Context context, String url, JSONObject params) {


        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.POST,
                Config.URL + url, params,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(final JSONObject jsonObject) {
                        Log.d("response", jsonObject.toString());

                        try {
                            final String status = jsonObject.getString(Config.STATUS);
                            if (status.equalsIgnoreCase(Config.SUCCESS)) {

                                map.put(Config.USER_RESPONSE, jsonObject);
                                mDataHandler.onSuccess(map);

                            } else if (status.equalsIgnoreCase(Config.ERROR)) {

                                map.put(Config.ERROR, jsonObject.getString("msg"));
                                mDataHandler.onFailure(map);

                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }

                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("error", "Error: " + error.toString());
                map.put(Config.VOLLEY_ERROR, error);
                mDataHandler.onFailure(map);

            }
        });

        // Adding request to request queue
        jsonObjReq.setShouldCache(false);
        AppController.getInstance().addToRequestQueue(jsonObjReq);
        jsonObjReq.setRetryPolicy(new DefaultRetryPolicy(200 * 1000, 0,
                DefaultRetryPolicy.DEFAULT_BACKOFF_MULT));
    }


}
