package wallet.coin.mota.motacoinwallet

import android.content.Context
import android.content.Context.SENSOR_SERVICE
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.support.v4.app.Fragment
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import wallet.coin.mota.motacoinwallet.managers.UserManager
import wallet.coin.mota.motacoinwallet.utils.RequestManager


/**
 * A simple [Fragment] subclass.
 * Activities that contain this fragment must implement the
 * [MineFragment.OnFragmentInteractionListener] interface
 * to handle interaction events.
 * Use the [MineFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class MineFragment : Fragment(), SensorEventListener {

    var sensorManager: SensorManager? = null
    lateinit var shakeSensor: Sensor
    lateinit var text: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(inflater: LayoutInflater?, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        val view: View = inflater!!.inflate(R.layout.fragment_mine, container, false)
        text = view.findViewById<TextView>(R.id.text) as TextView
        sensorManager = this.activity.getSystemService(SENSOR_SERVICE) as SensorManager
        shakeSensor = sensorManager!!.getDefaultSensor(Sensor.TYPE_ACCELEROMETER)

        return view
    }

    override fun setMenuVisibility(visible: Boolean) {
        super.setMenuVisibility(visible)
        Log.i("MINE", "VISIBILITY " + visible)
        if (sensorManager != null) {
            if (visible)
                sensorManager!!.registerListener(this, shakeSensor, SensorManager.SENSOR_DELAY_UI)
            else
                sensorManager!!.unregisterListener(this, shakeSensor)
        }


    }

    override fun onAccuracyChanged(p0: Sensor?, p1: Int) {

    }

    override fun onSensorChanged(event: SensorEvent?) {
        val values = event?.values
        // Movement
        val x = values!![0]
        val y = values[1]
        val z = values[2]

        val force = (x * x + y * y + z * z) / (SensorManager.GRAVITY_EARTH * SensorManager.GRAVITY_EARTH)
        if (force > 11 && force < 11.5) mine()
    }

    fun mine() {
        sensorManager!!.unregisterListener(this, shakeSensor)
        text.text = "Procesando, Espere ...."
        RequestManager.mine(UserManager.getAddress(this.context)!!).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    onMineSuccess()
                }, {
                    onMineNotSuccess()
                    Log.i("MINE", "ERROR" + it.message + it.localizedMessage)
                })
    }

    fun onMineSuccess() {
        text.text = "Minado Satisfactorio, Actualiza tu Balance"
        Handler().postDelayed(
                {
                    text.text = "Agita para Minar"
                    sensorManager!!.registerListener(this, shakeSensor, SensorManager.SENSOR_DELAY_UI)
                },
                10000)
    }

    fun onMineNotSuccess() {
        text.text = "Intenta de nuevo"
        Handler().postDelayed(
                {
                    text.text = "Agita para Minar"
                    sensorManager!!.registerListener(this, shakeSensor, SensorManager.SENSOR_DELAY_UI)
                },
                10000)
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
         * @return A new instance of fragment MineFragment.
         */
        // TODO: Rename and change types and number of parameters
        fun newInstance(param1: String, param2: String): MineFragment {
            val fragment = MineFragment()
            val args = Bundle()
            args.putString(ARG_PARAM1, param1)
            args.putString(ARG_PARAM2, param2)
            fragment.arguments = args
            return fragment
        }
    }
}// Required empty public constructor
