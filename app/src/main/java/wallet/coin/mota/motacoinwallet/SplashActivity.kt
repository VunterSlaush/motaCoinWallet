package wallet.coin.mota.motacoinwallet

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import wallet.coin.mota.motacoinwallet.managers.UserManager

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        // TODO poner algun tiempo de espera?
        if (UserManager.getPassword(this) != null &&
                UserManager.getWallet(this) != null && UserManager.getAddress(this) != null)
            MyApplication.goToMainActivity(this)
        else
            MyApplication.goToLoginActivity(this)
    }

}
