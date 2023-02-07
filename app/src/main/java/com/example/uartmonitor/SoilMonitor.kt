package com.example.uartmonitor

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.LegendRenderer
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.util.*
import kotlin.collections.ArrayList


class SoilMonitor : Fragment() {
    lateinit var lineGraphView: GraphView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val navigation = activity?.findViewById<BottomNavigationView>(R.id.topNavigation)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_soil_monitor, container, false)
        lineGraphView = view.findViewById(R.id.soilGraph)

        navigation?.setOnItemSelectedListener{
            when(it.itemId){
                R.id.mainMenu -> switchFragment(Home())
                R.id.airMonitor -> switchFragment(AirMonitor())
                R.id.terminal -> switchFragment(Terminal())
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

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProviders.of(requireActivity())[DataStorage::class.java]

        val dates: ArrayList<Date> = model.GetDates()
        val moistures: DoubleArray = model.GetMoistures()
        val series: LineGraphSeries<DataPoint> = LineGraphSeries(arrayOf(DataPoint(dates[0], moistures[0])))
        for(date in dates){
            series.appendData(DataPoint(date, moistures[dates.indexOf(date)]), false, 200, true)
        }
        lineGraphView = view.findViewById(R.id.soilGraph)

        lineGraphView.animate()
        lineGraphView.viewport.borderColor = R.color.white
        lineGraphView.viewport.setDrawBorder(true)
        series.color = (0xFF0000cc).toInt()
        series.title = "Moisture"
        lineGraphView.addSeries(series)
        lineGraphView.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(activity);
        lineGraphView.gridLabelRenderer.setHorizontalLabelsAngle(110)
        lineGraphView.gridLabelRenderer.numHorizontalLabels = 10;

        lineGraphView.gridLabelRenderer.setHumanRounding(false);
        lineGraphView.legendRenderer.backgroundColor = (0xAA555555).toInt()
        lineGraphView.legendRenderer.isVisible = true
        lineGraphView.legendRenderer.align = LegendRenderer.LegendAlign.TOP

    }
}