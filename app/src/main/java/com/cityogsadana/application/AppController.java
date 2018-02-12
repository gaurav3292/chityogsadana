package com.cityogsadana.application;

import android.app.Application;
import android.text.TextUtils;
import android.util.DisplayMetrics;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.cache.DiskLruBasedCache;
import com.android.volley.cache.SimpleImageLoader;
import com.android.volley.toolbox.Volley;
import com.cityogsadana.utils.Config;
import com.cityogsadana.utils.ConnectivityReceiver;
import com.cityogsadana.utils.Fonts;
import com.cityogsadana.utils.Global;
import com.google.firebase.FirebaseApp;


public class AppController extends Application {

    private static AppController mInstance;
    private RequestQueue mRequestQueue;
    private static SimpleImageLoader mImageLoader;

    public static final String TAG = AppController.class
            .getSimpleName();

    @Override
    public void onCreate() {
        super.onCreate();
        mInstance = this;

        Global.initializeFont(this);
        Global.overrideFont(getApplicationContext(), "MONOSPACE", Fonts.REGULAR);

            FirebaseApp.initializeApp(this);




        //Calculate height
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        Config.DEVICE_WIDTH = displayMetrics.widthPixels;
        Config.DEVICE_HEIGHT = displayMetrics.heightPixels;
        Log.d(AppController.TAG, "Device width" + Config.DEVICE_WIDTH);


    }

    public static synchronized AppController getInstance() {
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }

        return mRequestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }

    public void setConnectivityListener(ConnectivityReceiver.ConnectivityReceiverListener listener) {
        ConnectivityReceiver.connectivityReceiverListener = listener;
    }



    public SimpleImageLoader getImageLoader() {
        DiskLruBasedCache.ImageCacheParams cacheParams = new DiskLruBasedCache.ImageCacheParams(this, "images");
        cacheParams.setMemCacheSizePercent(0.25f); // Set memory cache to 25% of app memory
        if (mImageLoader != null) {
            return mImageLoader;
        } else {
            mImageLoader = new SimpleImageLoader(this, cacheParams);
//            throw new IllegalStateException("ImageLoader not initialized");
            return mImageLoader;
        }
    }
}
