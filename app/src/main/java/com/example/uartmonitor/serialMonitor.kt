package com.example.uartmonitor


import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView

class serialMonitor : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        val navigation = activity?.findViewById<BottomNavigationView>(R.id.topNavigation)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_serial_monitor, container, false)
        val text = arguments?.getString("buttonPressed").toString()

        val recycleView = view.findViewById<RecyclerView>(R.id.recycler_item_layout)

        recycleView.layoutManager = LinearLayoutManager(context)

        val data = ArrayList<textLine>()


        for(i in 1..20){
            data.add(textLine("Hello, world $i"))
        }
        data.add(textLine(text))

        val adapter = lineAdapter(data)
        navigation?.setOnItemSelectedListener{
            when(it.itemId){
                R.id.mainMenu -> switchFragment(MainMenu())
                R.id.airMonitor -> switchFragment(AirMonitor())
                R.id.soilMonitor -> switchFragment(SoilMonitor())
            }
            true
        }
        recycleView.adapter = adapter
        return view
    }
    private fun switchFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction? =
            activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction?.commit()

    }
}