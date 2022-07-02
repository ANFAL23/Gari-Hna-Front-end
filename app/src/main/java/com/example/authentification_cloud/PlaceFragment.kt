package com.example.authentification_cloud

import android.R
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AutoCompleteTextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.example.authentification_cloud.databinding.FragmentPlaceBinding
import com.google.android.gms.maps.SupportMapFragment
import java.util.*


class PlaceFragment :Fragment(){//, AdapterView.OnItemClickListener {


    private lateinit var binding: FragmentPlaceBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentPlaceBinding.inflate(layoutInflater)



    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentPlaceBinding.inflate(layoutInflater)
        val view = binding.root

        return view

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val places = binding.autoCompleteEditText as AutoCompleteTextView
        places.setAdapter(PlacesAutoCompleteAdapter(context, R.layout.simple_dropdown_item_1line))
       //  places.setOnItemClickListener(places)


    }
    /*override fun onItemClick(adapterView: AdapterView<*>, view: View?, position: Int, id: Long) {
        val str = adapterView.getItemAtPosition(position) as String

        Toast.makeText(requireActivity(),str, Toast.LENGTH_LONG).show()

    }*/


    /*fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu)
        return true
    }

    fun onItemClick(adapterView: AdapterView<*>, view: View?, position: Int, id: Long) {
        // TODO Auto-generated method stub
        val str = adapterView.getItemAtPosition(position) as String
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show()
    }
*/

}




