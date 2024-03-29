package wallet.coin.mota.motacoinwallet.utils;

/**
 * Created by mota on 7/3/2018.
 */


import android.content.Context;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.Volley;

import java.net.CookieHandler;
import java.net.CookieManager;


public class SingletonRequester {
    private static SingletonRequester mInstance;
    private RequestQueue mRequestQueue;
    ;
    private static Context mCtx;

    private SingletonRequester(Context context) {
        mCtx = context;
        mRequestQueue = getRequestQueue();
    }

    public static synchronized SingletonRequester getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SingletonRequester(context);
        }
        return mInstance;
    }

    public RequestQueue getRequestQueue() {
        if (mRequestQueue == null) {
            // getApplicationContext() is key, it keeps you from leaking the
            // Activity or BroadcastReceiver if someone passes one in.
            CookieManager cookieManager = new CookieManager();
            CookieHandler.setDefault(cookieManager);
            mRequestQueue = Volley.newRequestQueue(mCtx.getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        getRequestQueue().add(req);
    }

}
