package com.example.authentification_cloud

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.content.pm.ApplicationInfo
import android.content.pm.PackageManager
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.RecyclerView
import com.google.android.gms.common.api.Status
import com.google.android.libraries.places.api.Places
import com.google.android.libraries.places.api.model.Place
import com.google.android.libraries.places.widget.AutocompleteSupportFragment
import com.google.android.libraries.places.widget.listener.PlaceSelectionListener

private lateinit var newRecyclerView2: RecyclerView
lateinit var thisView2 : View
lateinit var parkListd2: List<ParkingResponse>
var parkPosition2 : Int = 0
var latitude: String = "36.705223"
var longitude : String  = "3.173975"
var distance_max= 0.0
var prix_max= 0.0


lateinit var RechercheActivityView : Activity
class RechercheActivity : AppCompatActivity() {
    @SuppressLint("WrongViewCast")
    override fun onCreate(savedInstanceState: Bundle?)
    {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.recherche_parkings)
        RechercheActivityView = this

        // Fetching API_KEY which we wrapped
       val ai: ApplicationInfo = applicationContext.packageManager
        .getApplicationInfo(applicationContext.packageName, PackageManager.GET_META_DATA)
        val value = ai.metaData["com.google.android.geo.API_KE"]
        val apiKey = "your Api key"//value.toString()

        val rechercher = findViewById<Button>(R.id.rechercher)

        rechercher.setOnClickListener{


            val disttt =findViewById<EditText>(R.id.editTextNumber2) as TextView
            val prixx =findViewById<EditText>(R.id.editTextNumber) as TextView
            var distance_text =  disttt.text
            distance_max= distance_text.toString().toDouble()
            Toast.makeText(thisView.context, "$distance_max", Toast.LENGTH_SHORT).show()
           prix_max=prixx.text.toString().toDouble()
            if (recherche==1) {
         val i = Intent(RechercheActivityView, MainActivity::class.java)
         startActivity(i)
        }
            else{
           Toast.makeText(thisView.context, "il faut entre l'adresse", Toast.LENGTH_SHORT).show()

       }

        }


            Places.initialize(applicationContext, apiKey)
        Log.e("la valeur recupe from shared","eeeeeeee")
        // }

        // Initialize Autocomplete Fragments
        // from the main activity layout file
        val autocompleteSupportFragment1 = supportFragmentManager.findFragmentById(R.id.autocomplete_fragment1) as AutocompleteSupportFragment?
        Log.e("la valeur recupe from shared","11111111111")
        // Information that we wish to fetch after typing
        // the location and clicking on one of the options
        autocompleteSupportFragment1!!.setPlaceFields(
            listOf(

                Place.Field.NAME,
                Place.Field.ADDRESS,
                Place.Field.PHONE_NUMBER,
                Place.Field.LAT_LNG,
                Place.Field.OPENING_HOURS,
                Place.Field.RATING,
                Place.Field.USER_RATINGS_TOTAL

            )

        )

        // Display the fetched information after clicking on one of the options
        autocompleteSupportFragment1.setOnPlaceSelectedListener(object : PlaceSelectionListener {
            override fun onPlaceSelected(place: Place) {


                 recherche = 1
                latitude=place.latLng.latitude.toString()
                longitude=place.latLng.longitude.toString()
                val latlng = place.latLng


            }

            override fun onError(status: Status) {
                Toast.makeText(applicationContext,"Some error occurred", Toast.LENGTH_SHORT).show()
            }
        })

    }
}
