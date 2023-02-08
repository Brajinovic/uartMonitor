package com.example.uartmonitor

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.w3c.dom.Text

class Terminal : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val navigation = activity?.findViewById<BottomNavigationView>(R.id.topNavigation)
        // Inflate the layout for this fragment
        val view = inflater.inflate(R.layout.fragment_terminal, container, false)
        val text = arguments?.getString("buttonPressed").toString()
        view.findViewById<TextView>(R.id.messageTextView).setHint("Command")
        navigation?.setOnItemSelectedListener{
            when(it.itemId){
                R.id.mainMenu -> switchFragment(Home())
                R.id.airMonitor -> switchFragment(AirMonitor())
                R.id.soilMonitor -> switchFragment(SoilMonitor())
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
        val recycleView = view.findViewById<RecyclerView>(R.id.recycler_item_layout)

        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.setHasFixedSize(true)
        val adapter = LineAdapter(model.GetConsoleLog())
        var command: String
        recycleView.adapter = adapter
        activity?.findViewById<ImageButton>(R.id.sendButton)?.setOnClickListener{
            Log.d("data", "fucking works")

            command = activity?.findViewById<TextView>(R.id.messageTextView)?.text.toString()


            if (command.startsWith("set")){
                Log.d("POST", "first if ok")
                    if(command.substring(4, 7) == "fan" || command.substring(4, 8) == "pump" || command.substring(4, 10) == "heater"){
                        Log.d("POST", command)
                        adapter.AddLine(command)
                        recycleView.adapter = adapter
                        activity?.findViewById<TextView>(R.id.messageTextView)?.text = ""
                    }else{
                        Log.d("POST", "invalid format")
                        adapter.AddLine("Invalid command!")
                        recycleView.adapter = adapter
                        activity?.findViewById<TextView>(R.id.messageTextView)?.text = ""
                    }
                }else{
                    Log.d("POST", "invalid format")
                    adapter.AddLine("Invalid command!")
                    recycleView.adapter = adapter
                activity?.findViewById<TextView>(R.id.messageTextView)?.text = ""
            }
            activity?.findViewById<RecyclerView>(R.id.recycler_item_layout)?.smoothScrollToPosition(adapter.getItemCount() - 1);
        }


    }
}