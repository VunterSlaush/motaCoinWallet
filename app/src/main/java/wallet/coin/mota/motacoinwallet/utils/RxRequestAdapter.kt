package wallet.coin.mota.motacoinwallet.utils

/**
 * Created by mota on 7/3/2018.
 */


import android.util.Log;

import com.android.volley.Response;
import com.android.volley.VolleyError;

import java.util.concurrent.CountDownLatch;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import android.icu.lang.UCharacter.GraphemeClusterBreak.T


/**
 * Created by Slaush on 22/05/2017.
 */

class RxRequestAdapter<T>() : Response.Listener<T>, Response.ErrorListener {


    private var mVolleyError: VolleyError? = null
    private var mResponse: T? = null

    private var mLatch: CountDownLatch = CountDownLatch(1)
    private var mObservable: Observable<T>

    fun getObservable(): Observable<T> {
        return mObservable
    }

    override fun onErrorResponse(volleyError: VolleyError) {
        mVolleyError = volleyError
        mLatch.countDown()
    }

    override fun onResponse(t: T) {
        mResponse = t
        mLatch.countDown()
    }

    init {
        mObservable = Observable.create { e ->
            try {
                mLatch.await()
            } catch (ex: InterruptedException) {
                mVolleyError = VolleyError(ex)
            }

            if (mVolleyError != null) {
                e.onError(mVolleyError!!)
            } else {
                e.onNext(mResponse!!)
                e.onComplete()
            }
        }
    }
}