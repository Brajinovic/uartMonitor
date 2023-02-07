package com.example.uartmonitor

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*
import kotlin.concurrent.schedule

class StartScreen : Fragment() {
    lateinit var data: DataPacket//maybe error
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment

        activity?.findViewById<BottomNavigationView>(R.id.topNavigation)?.isVisible = false

        val view = inflater.inflate(R.layout.fragment_start_screen, container, false)

        return view
    }
    private var model: DataStorage?=null
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        model = activity?.let { ViewModelProviders.of(it) }?.get(DataStorage::class.java)

        val destinationService  = ServiceBuilder.buildService(TemperatureService::class.java)
        val requestCall =destinationService.getTemperatureList()

        //make network call asynchronously
        requestCall.enqueue(object : Callback<DataPacket> {
            override fun onResponse(call: Call<DataPacket>, response: Response<DataPacket>) {
                if (response.isSuccessful){
                    data = response.body()!!
                    Log.d("fail", "data filled")
                    for(i in data.feeds){
                        model!!.AddDate(Date(
                            i.created_at.substring(0, 4).toInt() - 1900,
                            i.created_at.substring(5, 7).toInt(),
                            i.created_at.substring(8, 10).toInt(),
                            i.created_at.substring(11, 13).toInt(),
                            i.created_at.substring(14, 16).toInt(),
                            i.created_at.substring(17, 19).toInt()
                        ))
                        model!!.AddTemperature(i.field1.toDouble(), data.feeds.indexOf(i))
                        model!!.AddHumidity(i.field2.toDouble(), data.feeds.indexOf(i))
                        model!!.AddMoisture("%.1f".format(i.field4.toDouble()).toDouble(), data.feeds.indexOf(i))
                        model!!.SetHeater(25)
                        model!!.SetFan(25)
                        model!!.SetPump(25)
                    }
                }
            }
            override fun onFailure(call: Call<DataPacket>, t: Throwable) {
                Log.d("FAIL", t.message.toString())
            }
        })
        Timer().schedule(2000){


        val fragment = Home()
        val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction?.addToBackStack(null)
        fragmentTransaction?.commit()
        }
    }
}