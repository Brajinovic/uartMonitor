package com.example.uartmonitor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView

class AirMonitor : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val navigation = activity?.findViewById<BottomNavigationView>(R.id.topNavigation)
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_air_monitor, container, false)

        view.findViewById<Button>(R.id.backSoilButton).setOnClickListener {
            val fragment = MainMenu()
            val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragmentContainerView, fragment)
            fragmentTransaction?.commit()
        }
        navigation?.setOnItemSelectedListener{
            when(it.itemId){
                R.id.mainMenu -> switchFragment(MainMenu())
                R.id.soilMonitor -> switchFragment(SoilMonitor())
                R.id.terminal -> switchFragment(serialMonitor())
            }
            true
        }
        return view
    }
    private fun switchFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction? =
            activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction?.commit()

    }
}