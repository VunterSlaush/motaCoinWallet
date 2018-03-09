package wallet.coin.mota.motacoinwallet.utils

/**
 * Created by mota on 7/3/2018.
 */


import android.util.Log;

import com.android.volley.Request;
import com.android.volley.VolleyError
import com.android.volley.toolbox.JsonObjectRequest;
import io.reactivex.Observable
import io.reactivex.Observer

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;
import wallet.coin.mota.motacoinwallet.Urls
import wallet.coin.mota.motacoinwallet.MyApplication
import wallet.coin.mota.motacoinwallet.managers.UserManager


/**
 * Created by Slaush on 22/05/2017.
 */

object RequestManager {
    val urlBase: String = "http://motacoin.herokuapp.com"

    private fun request(method: Int, url: String, obj: JSONObject?): Observable<JSONObject> {
        try {
            val adapter = RxRequestAdapter<JSONObject>()
            val request = CustomJSONRequest(method, url, obj, adapter, adapter)
            SingletonRequester.getInstance(MyApplication.getInstance()).addToRequestQueue(request)
            return adapter.getObservable()
        } catch (e: Exception) {
            e.printStackTrace()
            Log.i("LOGIN", "E" + e.message)
            return object : Observable<JSONObject>() {
                protected override fun subscribeActual(observer: Observer<in JSONObject>) {

                    observer.onError(Throwable("Undefined Error!"))
                }
            }
        }

    }

    fun login(password: String): Observable<JSONObject> {
        val body = JSONObject()
        body.put("password", password)
        return request(Request.Method.POST, urlBase + Urls.URL_LOGIN, body)
    }

    fun mine(address: String): Observable<JSONObject> {
        val body = JSONObject()
        body.put("rewardAddress", address)
        return request(Request.Method.POST, urlBase + Urls.URL_MINE, body)
    }

    fun createAddress(wallet: String): Observable<JSONObject> {
        return request(Request.Method.POST, urlBase + Urls.URL_CREATE_ADDRESS.replace("{wallet}", wallet), null)
    }

    fun balance(address: String): Observable<JSONObject> {
        return request(Request.Method.GET, urlBase + Urls.URL_BALANCE.replace("{address}", address), null)
    }

    fun transfer(wallet: String, from: String, to: String, amount: Double): Observable<JSONObject> {
        return request(Request.Method.POST, urlBase + Urls.URL_TRANSFER.replace("{wallet}", wallet), JSONObject("{\n" +
                "  \"fromAddress\": \"$from\",\n" +
                "  \"toAddress\": \"$to\",\n" +
                "  \"amount\": $amount,\n" +
                "}"))
    }


}
