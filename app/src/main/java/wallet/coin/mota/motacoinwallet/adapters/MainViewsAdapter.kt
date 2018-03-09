package wallet.coin.mota.motacoinwallet.adapters

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import wallet.coin.mota.motacoinwallet.MineFragment
import wallet.coin.mota.motacoinwallet.ResumeFragment
import wallet.coin.mota.motacoinwallet.WalletsFragment

/**
 * Created by mota on 8/3/2018.
 */
class MainViewsAdapter(fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getItem(position: Int): Fragment {
        return when (position) {
            0 -> WalletsFragment()
            1 -> ResumeFragment()
            2 -> MineFragment()
            else -> ResumeFragment()
        }
    }

    override fun getCount(): Int {
        return 3
    }

}
