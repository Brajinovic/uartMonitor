package com.example.uartmonitor

import android.media.Image
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jjoe64.graphview.GraphView
import java.util.*

class Home : Fragment() {

    private var model: DataStorage?=null
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //show navigation
        val navigation = activity?.findViewById<BottomNavigationView>(R.id.topNavigation)
        navigation?.isVisible = true

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_home, container, false)
        Log.d("data", "enters")



        return view
    }

    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        model = activity?.let { ViewModelProviders.of(it) }?.get(DataStorage::class.java)
        val upPump = activity?.findViewById<ImageButton>(R.id.pumpUp)
        val downPump = activity?.findViewById<ImageButton>(R.id.pumpDown)
        val upFan = activity?.findViewById<ImageButton>(R.id.fanUp)
        val downFan = activity?.findViewById<ImageButton>(R.id.fanDown)
        val upHeater = activity?.findViewById<ImageButton>(R.id.heaterUp)
        val downHeater = activity?.findViewById<ImageButton>(R.id.heaterDown)

        view.findViewById<TextView>(R.id.heaterValue).text = model!!.GetHeater().toString()
        view.findViewById<TextView>(R.id.fanValue).text = model!!.GetFan().toString()
        view.findViewById<TextView>(R.id.pumpValue).text = model!!.GetPump().toString()

        upPump?.setOnClickListener{
            model!!.SetPump(model!!.GetPump() + 1)
            view.findViewById<TextView>(R.id.pumpValue).text = model!!.GetPump().toString()
        }
        downPump?.setOnClickListener{
            model!!.SetPump(model!!.GetPump() - 1)
            view.findViewById<TextView>(R.id.pumpValue).text = model!!.GetPump().toString()
        }
        upPump?.setOnLongClickListener{
            model!!.SetPump(model!!.GetPump() + 10)
            view.findViewById<TextView>(R.id.pumpValue).text = model!!.GetPump().toString()
            true
        }
        downPump?.setOnLongClickListener{
            model!!.SetPump(model!!.GetPump() - 10)
            view.findViewById<TextView>(R.id.pumpValue).text = model!!.GetPump().toString()
            true
        }

        upFan?.setOnClickListener{
            model!!.SetFan(model!!.GetFan() + 1)
            view.findViewById<TextView>(R.id.fanValue).text = model!!.GetFan().toString()
        }
        downFan?.setOnClickListener{
            model!!.SetFan(model!!.GetFan() - 1)
            view.findViewById<TextView>(R.id.fanValue).text = model!!.GetFan().toString()
        }
        upFan?.setOnLongClickListener{
            model!!.SetFan(model!!.GetFan() + 10)
            view.findViewById<TextView>(R.id.fanValue).text = model!!.GetFan().toString()
            true
        }
        downFan?.setOnLongClickListener{
            model!!.SetFan(model!!.GetFan() - 10)
            view.findViewById<TextView>(R.id.fanValue).text = model!!.GetFan().toString()
            true
        }

        upHeater?.setOnClickListener{
            model!!.SetHeater(model!!.GetHeater() + 1)
            view.findViewById<TextView>(R.id.heaterValue).text = model!!.GetHeater().toString()
        }
        downHeater?.setOnClickListener{
            model!!.SetHeater(model!!.GetHeater() - 1)
            view.findViewById<TextView>(R.id.heaterValue).text = model!!.GetHeater().toString()
        }
        upHeater?.setOnLongClickListener{
            model!!.SetHeater(model!!.GetHeater() + 10)
            view.findViewById<TextView>(R.id.heaterValue).text = model!!.GetHeater().toString()
            true
        }
        downHeater?.setOnLongClickListener{
            model!!.SetHeater(model!!.GetHeater() - 10)
            view.findViewById<TextView>(R.id.heaterValue).text = model!!.GetHeater().toString()
            true
        }


        val navigation = activity?.findViewById<BottomNavigationView>(R.id.topNavigation)
        navigation?.setOnItemSelectedListener{
            when(it.itemId){
                R.id.airMonitor -> switchFragment(AirMonitor())
                R.id.soilMonitor -> switchFragment(SoilMonitor())
                R.id.terminal -> switchFragment(Terminal())
            }
            true
        }
    }

    private fun switchFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction? =
            activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction?.commit()

    }
}