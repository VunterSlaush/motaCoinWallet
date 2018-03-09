package wallet.coin.mota.motacoinwallet.utils

/**
 * Created by mota on 7/3/2018.
 */


import android.content.Context


object PreferencesHelper {
    val SHARED_PREF_NAME = "MOTA_COIN"
    fun deleteKey(context: Context?, key: String) {
        if (context != null)
            getPreferences(context).remove(key)
    }


    fun writeBoolean(context: Context?, key: String, value: Boolean) {
        if (context != null)
            getPreferences(context).putBoolean(key, value)
    }

    fun readBoolean(context: Context?, key: String,
                    defValue: Boolean): Boolean {
        return context != null && getPreferences(context).getBoolean(key, defValue)
    }


    fun writeInteger(context: Context?, key: String, value: Int) {
        if (context != null)
            getPreferences(context).putInt(key, value)

    }

    fun readInteger(context: Context?, key: String, defValue: Int): Int {
        return if (context != null) getPreferences(context).getInt(key, defValue) else -1
    }


    fun writeString(context: Context?, key: String, value: String) {
        if (context != null)
            getPreferences(context).putString(key, value)
    }

    fun readString(context: Context?, key: String, defValue: String): String {
        return if (context != null) getPreferences(context).getString(key, defValue) else ""
    }

    fun writeDouble(context: Context?, key: String, value: Double?) {
        if (context != null)
            getPreferences(context).putDouble(key, value)
    }

    fun readDouble(context: Context?, key: String, defValue: Double?): Double? {
        return (if (context != null) getPreferences(context).getDouble(key, defValue) else null)?.toDouble()
    }

    private fun getPreferences(context: Context): SecurePreferences {
        return SecurePreferences(context, "5c0d3349dc674fe9d0")

    }

    fun putStringArray(context: Context?, key: String, set: MutableSet<String>) {
        context?.getSharedPreferences(SHARED_PREF_NAME, SecurePreferences.MODE)?.edit()?.putStringSet(key, set)?.commit()
    }

    fun getStringArray(context: Context?, key: String): MutableSet<String>? {
        return context?.getSharedPreferences(SHARED_PREF_NAME, SecurePreferences.MODE)?.getStringSet(key, null)
    }


}