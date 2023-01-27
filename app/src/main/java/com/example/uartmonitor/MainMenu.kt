package com.example.uartmonitor

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries

class MainMenu : Fragment() {

    lateinit var lineGraphView: GraphView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //show navigation
        val navigation = activity?.findViewById<BottomNavigationView>(R.id.topNavigation)
        navigation?.isVisible = true

        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_main_menu, container, false)
        lineGraphView = view.findViewById(R.id.idTVHead)

        val series: LineGraphSeries<DataPoint> = LineGraphSeries(
            arrayOf(
        // on below line we are adding
        // each point on our x and y axis.
        DataPoint(0.0, 1.0),
        DataPoint(1.0, 3.0),
        DataPoint(2.0, 4.0),
        DataPoint(3.0, 9.0),
        DataPoint(4.0, 6.0),
        DataPoint(5.0, 3.0),
        DataPoint(6.0, 6.0),
        DataPoint(7.0, 1.0),
        DataPoint(8.0, 2.0)
    )
        )

        lineGraphView.animate()
        lineGraphView.viewport.isScrollable = true
        //lineGraphView.viewport.isScalable = true
        //lineGraphView.viewport.setScalableY(true)
        lineGraphView.viewport.setScrollableY(true)
        lineGraphView.viewport.borderColor = R.color.white
        lineGraphView.viewport.setDrawBorder(true)
        series.color = R.color.white
        lineGraphView.addSeries(series)


        navigation?.setOnItemSelectedListener{
            when(it.itemId){
                R.id.airMonitor -> switchFragment(AirMonitor())
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