package com.example.authentification_cloud

import android.os.Bundle
import android.util.Log
import com.example.authentification_cloud.databinding.*
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

private lateinit var parkList: List<ParkingResponse>
class MapsFragment2 : SupportMapFragment(), OnMapReadyCallback {
    private var googleMap: GoogleMap? = null
    private lateinit var binding: FragmentMaps2Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentMaps2Binding.inflate(layoutInflater)



    }

    override fun onMapReady(gmap: GoogleMap) {
        googleMap = gmap
        var i = 0;

        GetParkings()
    }


    init {
        getMapAsync(this)
    }


    private fun GetParkings() {
        val parkingResponseCall = ApiClient.getParkingService().getParkings()
        parkingResponseCall.enqueue(object : Callback<List<ParkingResponse>> {
            override fun onResponse(
                call: Call<List<ParkingResponse>>,
                response: Response<List<ParkingResponse>>
            ) {


                if (response?.body() != null) {
                    parkList = response.body()!!
                    initMarker(parkList)


                        Log.e("secced", parkList[1].commun)


                } else (Log.e("erreur", response.message().toString()))
            }

            override fun onFailure(call: Call<List<ParkingResponse>>, t: Throwable) {
                Log.e("erreur", t.localizedMessage);
            }


        })
    }

    private fun initMarker(parkList: List<ParkingResponse>) {
        var location =   LatLng(36.736220116669614, 3.070647167338848)
        for (i in 1..parkList.size-1) {


             location =
                LatLng(parkList[i].latitude, parkList[i].longitude)
            googleMap!!.addMarker(
                MarkerOptions().position(location)
                    .title(parkList[i].commun + "longitude" + parkList[i].longitude + "latitude" + parkList[i].latitude)
            )
        }
          googleMap!!.moveCamera(CameraUpdateFactory.newLatLng(location))
          googleMap!!.setOnMapClickListener { latLng ->
            val markerOptions = MarkerOptions()
            markerOptions.position(latLng)
            markerOptions.title(latLng.latitude.toString() + " : " + latLng.longitude)
            // Clear previously click position.
            googleMap!!.clear()

            // Zoom the Marker
           googleMap!!.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 10F))
            googleMap!!.animateCamera( CameraUpdateFactory.zoomTo( 30900f ) );
            // Add Marker on Map
            googleMap!!.addMarker(markerOptions)
            googleMap!!.setMinZoomPreference(10f)


    }}
}
