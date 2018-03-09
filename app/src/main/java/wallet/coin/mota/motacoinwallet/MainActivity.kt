package wallet.coin.mota.motacoinwallet

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity

import kotlinx.android.synthetic.main.activity_main.*
import android.support.v4.view.ViewPager
import wallet.coin.mota.motacoinwallet.adapters.MainViewsAdapter


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewPager.adapter = MainViewsAdapter(supportFragmentManager)
        viewPager.currentItem = 1
    }

}
