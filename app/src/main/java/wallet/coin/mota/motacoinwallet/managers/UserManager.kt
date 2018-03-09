package wallet.coin.mota.motacoinwallet.managers

import android.content.Context
import wallet.coin.mota.motacoinwallet.utils.PreferencesHelper

import android.provider.Telephony.Carriers.PASSWORD
import wallet.coin.mota.motacoinwallet.MyApplication


/**
 * Created by mota on 7/3/2018.
 */
object UserManager {
    fun saveUserCredentials(context: Context, password: String, wallet: String, address: String) {
        PreferencesHelper.writeString(context, "password", password)
        PreferencesHelper.writeString(context, "wallet", wallet)
        PreferencesHelper.writeString(context, "address", address)
    }

    fun getWallet(context: Context): String? {
        val wallet = PreferencesHelper.readString(context, "wallet", "")
        return if (!wallet.isEmpty()) wallet else null
    }

    fun getPassword(context: Context): String? {
        val password = PreferencesHelper.readString(context, "password", "")
        return if (!password.isEmpty()) password else null
    }


    fun logout() {
        PreferencesHelper.deleteKey(MyApplication.getInstance(), "password")
        PreferencesHelper.deleteKey(MyApplication.getInstance(), "wallet")
    }

    fun getAddress(context: Context): String? {
        val address = PreferencesHelper.readString(context, "address", "")
        return if (!address.isEmpty()) address else null
    }

}

