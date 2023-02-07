package com.example.uartmonitor

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.LegendRenderer
import com.jjoe64.graphview.helper.DateAsXAxisLabelFormatter
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import java.util.*

class AirMonitor : Fragment() {
    lateinit var lineGraphView: GraphView
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view =  inflater.inflate(R.layout.fragment_air_monitor, container, false)

        return view
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val model = ViewModelProviders.of(requireActivity())[DataStorage::class.java]

        val dates: ArrayList<Date> = model.GetDates()
        val temperatures: DoubleArray = model.GetTemperatures()
        val humidities: DoubleArray = model.GetHumidities()

        val series1: LineGraphSeries<DataPoint> = LineGraphSeries(arrayOf(DataPoint(dates[0], temperatures[0])))
        val series2: LineGraphSeries<DataPoint> = LineGraphSeries(arrayOf(DataPoint(dates[0], humidities[0])))

        for(date in dates){
            Log.d("data", date.toString())
            series1.appendData(DataPoint(date, temperatures[dates.indexOf(date)]), false, 101, true)
            series2.appendData(DataPoint(date, humidities[dates.indexOf(date)]), false, 101, true)
        }
        lineGraphView = view.findViewById(R.id.airGraph)

        lineGraphView.animate()
        lineGraphView.viewport.borderColor = R.color.white
        lineGraphView.viewport.setDrawBorder(true)
        series1.color = (0xFFFF0000).toInt()
        series1.title = "Temperature [°C]"
        series2.color = (0xFF0000FF).toInt()
        series2.title = "Humidity [%]"
        lineGraphView.addSeries(series1)
        lineGraphView.addSeries(series2)
        lineGraphView.gridLabelRenderer.labelFormatter = DateAsXAxisLabelFormatter(activity);
        lineGraphView.gridLabelRenderer.setHorizontalLabelsAngle(110)
        lineGraphView.gridLabelRenderer.numHorizontalLabels = 12;
        lineGraphView.gridLabelRenderer.setHumanRounding(false);
        lineGraphView.legendRenderer.backgroundColor = (0xAA555555).toInt()
        lineGraphView.legendRenderer.isVisible = true
        lineGraphView.legendRenderer.align = LegendRenderer.LegendAlign.TOP



        val navigation = activity?.findViewById<BottomNavigationView>(R.id.topNavigation)
        navigation?.setOnItemSelectedListener{
            when(it.itemId){
                R.id.mainMenu -> switchFragment(Home())
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