package wallet.coin.mota.motacoinwallet;

/**
 * Created by mota on 7/3/2018.
 */


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.util.Log;

import wallet.coin.mota.motacoinwallet.managers.UserManager;


/**
 * Created by Slaush on 15/05/2017.
 */

public class MyApplication extends android.app.Application {
    private static MyApplication instance;

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
    }

    public static MyApplication getInstance() {
        return instance;
    }

    public void logout(Context context) {
        UserManager.INSTANCE.logout();
        goToLoginActivity(context);
    }


    public static void goToLoginActivity(Context context) {
        goToActivity(context, LoginActivity.class);

    }

    public static void goToMainActivity(Context context) {
        goToActivity(context, MainActivity.class);
    }

    public static void goToActivity(Context context, Class clazz) {
        Intent i = new Intent(context, clazz);
        context.startActivity(i);
        if (context instanceof Activity)
            ((Activity) context).finish();
    }
}
