package com.example.uartmonitor

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import java.util.Timer
import kotlin.concurrent.schedule

class StartScreen : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        activity?.findViewById<BottomNavigationView>(R.id.topNavigation)?.isVisible = false
        val view = inflater.inflate(R.layout.fragment_start_screen, container, false)

        Timer().schedule(2000){
            val fragment = MainMenu()
            val fragmentTransaction : FragmentTransaction? = activity?.supportFragmentManager?.beginTransaction()
            fragmentTransaction?.replace(R.id.fragmentContainerView, fragment)
            fragmentTransaction?.commit()
        }
        return view
    }

}