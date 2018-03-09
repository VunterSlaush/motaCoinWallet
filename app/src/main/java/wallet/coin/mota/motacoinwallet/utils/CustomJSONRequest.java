package wallet.coin.mota.motacoinwallet.utils;

import android.util.Log;

import com.android.volley.AuthFailureError;
import com.android.volley.Response;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import wallet.coin.mota.motacoinwallet.MyApplication;
import wallet.coin.mota.motacoinwallet.managers.UserManager;

/**
 * Created by mota on 8/3/2018.
 */

public class CustomJSONRequest extends JsonObjectRequest {

    public CustomJSONRequest(int method, String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(method, url, jsonRequest, listener, errorListener);
    }

    public CustomJSONRequest(String url, JSONObject jsonRequest, Response.Listener<JSONObject> listener, Response.ErrorListener errorListener) {
        super(url, jsonRequest, listener, errorListener);
    }

    @Override
    public Map<String, String> getHeaders() throws AuthFailureError {
        //Map<String, String> headers = super.getHeaders();
        Map<String, String> headers = new HashMap<>();
        String password = UserManager.INSTANCE.getPassword(MyApplication.getInstance());
        if (password != null) headers.put("password", password);
        Log.i("LOGIN", "getHeaders: " + headers);
        return headers;
    }
}
