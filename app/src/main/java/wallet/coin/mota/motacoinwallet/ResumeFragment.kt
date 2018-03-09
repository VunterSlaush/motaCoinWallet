package wallet.coin.mota.motacoinwallet


import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.widget.SwipeRefreshLayout
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import wallet.coin.mota.motacoinwallet.managers.UserManager
import wallet.coin.mota.motacoinwallet.utils.RequestManager


class ResumeFragment : Fragment() {

    lateinit var balance: TextView
    lateinit var address: TextView
    lateinit var swipe: SwipeRefreshLayout
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    fun fetchBalance() {
        RequestManager.balance(UserManager.getAddress(this.context)!!).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    swipe.isRefreshing = false
                    balance.text = """${(it.getDouble("balance") / 100000000)} MTC"""
                }, {
                    swipe.isRefreshing = false
                    Log.i("BALANCE", "ERROR" + it.message + it.localizedMessage)
                })
    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val view: View = inflater!!.inflate(R.layout.fragment_resume, container, false)
        balance = view.findViewById<TextView>(R.id.balance) as TextView
        address = view.findViewById<TextView>(R.id.address) as TextView
        address.text = UserManager.getAddress(this.context)
        swipe = view.findViewById<SwipeRefreshLayout>(R.id.swiperefresh) as SwipeRefreshLayout
        swipe.setOnRefreshListener { fetchBalance() }
        fetchBalance()
        return view
    }

    companion object {
        // TODO: Rename parameter arguments, choose names that match
        // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
        private val ARG_PARAM1 = "param1"
        private val ARG_PARAM2 = "param2"

        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @param param1 Parameter 1.
         * @param param2 Parameter 2.
         * @return A new instance of fragment ResumeFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): ResumeFragment {
            val fragment = ResumeFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }

}// Required empty public constructor
