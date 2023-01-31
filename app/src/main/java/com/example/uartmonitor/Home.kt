package com.example.uartmonitor

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.RequiresApi
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.jjoe64.graphview.GraphView
import com.jjoe64.graphview.series.DataPoint
import com.jjoe64.graphview.series.LineGraphSeries
import com.smarteist.autoimageslider.SliderView
import androidx.appcompat.app.AppCompatActivity

class Home : Fragment() {

    lateinit var lineGraphView: GraphView
    lateinit var imageUrl: ArrayList<String>


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
        /*lineGraphView = view.findViewById(R.id.idTVHead)

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
        */
        imageUrl = ArrayList()

        // on below line we are adding data to our image url array list.
        imageUrl =
            (imageUrl + "https://practice.geeksforgeeks.org/_next/image?url=https%3A%2F%2Fmedia.geeksforgeeks.org%2Fimg-practice%2Fbanner%2Fdsa-self-paced-thumbnail.png&w=1920&q=75") as ArrayList<String>
        imageUrl =
            (imageUrl + "https://practice.geeksforgeeks.org/_next/image?url=https%3A%2F%2Fmedia.geeksforgeeks.org%2Fimg-practice%2Fbanner%2Fdata-science-live-thumbnail.png&w=1920&q=75") as ArrayList<String>
        imageUrl =
            (imageUrl + "https://practice.geeksforgeeks.org/_next/image?url=https%3A%2F%2Fmedia.geeksforgeeks.org%2Fimg-practice%2Fbanner%2Ffull-stack-node-thumbnail.png&w=1920&q=75") as ArrayList<String>




        navigation?.setOnItemSelectedListener{
            when(it.itemId){
                R.id.airMonitor -> switchFragment(AirMonitor())
                R.id.soilMonitor -> switchFragment(SoilMonitor())
                R.id.terminal -> switchFragment(Terminal())
            }
            true
        }

        val imageSlider = view.findViewById<SliderView>(R.id.imageSlider)
        val imageList: ArrayList<String> = ArrayList()
        imageList.add("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg")
        imageList.add("https://images.ctfassets.net/hrltx12pl8hq/4plHDVeTkWuFMihxQnzBSb/aea2f06d675c3d710d095306e377382f/shutterstock_554314555_copy.jpg")
        imageList.add("https://media.istockphoto.com/photos/child-hands-formig-heart-shape-picture-id951945718?k=6&m=951945718&s=612x612&w=0&h=ih-N7RytxrTfhDyvyTQCA5q5xKoJToKSYgdsJ_mHrv0=")
        setImageInSlider(imageList, imageSlider)




        return view
    }

    private fun setImageInSlider(images: ArrayList<String>, imageSlider: SliderView) {
        val adapter = MySliderImageAdapter()
        adapter.renewItems(images)
        imageSlider.setSliderAdapter(adapter)
        imageSlider.isAutoCycle = true
        imageSlider.startAutoCycle()
    }
    private fun switchFragment(fragment: Fragment) {
        val fragmentTransaction: FragmentTransaction? =
            activity?.supportFragmentManager?.beginTransaction()
        fragmentTransaction?.replace(R.id.fragmentContainerView, fragment)
        fragmentTransaction?.commit()

    }
}