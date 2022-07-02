package com.example.authentification_cloud

import android.content.Context
import android.content.SharedPreferences
import android.location.LocationManager
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.constraintlayout.motion.widget.Debug.getLocation
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.authentification_cloud.databinding.FragmentListParkBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


private lateinit var newRecyclerView: RecyclerView
lateinit var thisView :View
lateinit var parkListd: List<ParkingResponse>
var parkPosition : Int = 0
var recherche  : Int = 0
class FragmentListPark : Fragment() {


    private val sharedPrefFile = "sharedpreferencepark"
    private lateinit var binding: FragmentListParkBinding

    val sharedPreferences: SharedPreferences? =
        context?.getSharedPreferences(sharedPrefFile,Context.MODE_PRIVATE)


    override fun onCreate(savedInstanceState: Bundle?) {
        Log.e("la valeur recupe from shared","on create ")
        super.onCreate(savedInstanceState)
        binding = FragmentListParkBinding.inflate(layoutInflater)
        binding.ListParking.layoutManager
        binding.ListParking.apply {
            layoutManager = LinearLayoutManager(activity)

        }



        if(recherche==1)
        {
            Log.e("la valeur recupe from shared","mode recherche")
        }
    }


    override fun onCreateView(

        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        return inflater.inflate(R.layout.fragment__list_park, container, false)

        binding = FragmentListParkBinding.inflate(layoutInflater)
        val view = binding.root
        return view

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        getLocation();

        super.onViewCreated(view, savedInstanceState)
        binding.ListParking
        newRecyclerView = view.findViewById(R.id.ListParking)
        newRecyclerView.layoutManager = LinearLayoutManager(view.context)
        newRecyclerView.setHasFixedSize(true)
        thisView = view
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object:
            OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack(R.id.fragment_ListPark , true)
            }


        })
        Search(CreateRequest())

    }
    private fun GetParkings()
    {
         val parkingResponseCall = ApiClient.getParkingService().getParkings()

         parkingResponseCall.enqueue(object : Callback<List<ParkingResponse>> {
             override fun onResponse(
                 call: Call<List<ParkingResponse>>,
                 response: Response<List<ParkingResponse>>
             ) {


                 if (response?.body() != null)

                     parkListd = response?.body()!!
                 newRecyclerView.adapter = ParkAdapter(response.body()!!)


                 Log.e("seccuess", response.body().toString())

                 if (response != null) {
                     Log.e("seccuess", response.body().toString())
                 };

             }

             override fun onFailure(call: Call<List<ParkingResponse>>, t: Throwable) {
                 Log.e("erreur", t.localizedMessage);
             }


         })

    }
    private fun CreateRequest () : RechercheRequest {


        var rechercheRequest =  RechercheRequest();

          rechercheRequest.latitude=latitude
          rechercheRequest.longitude= longitude
          rechercheRequest.distance_max= distance_max
          rechercheRequest.prix_max= prix_max

        return rechercheRequest


    }
    private fun Search(rechercheRequest: RechercheRequest)
    {
        val parkingResponseCall = ApiClient.getParkingService().recherche(rechercheRequest)

        parkingResponseCall.enqueue(object : Callback<List<ParkingResponse>> {
            override fun onResponse(
                call: Call<List<ParkingResponse>>,
                response: Response<List<ParkingResponse>>
            ) {


                if (response?.body() != null)

                    parkListd = response?.body()!!
                newRecyclerView.adapter = ParkAdapter(response.body()!!)


                Log.e("seccuess", response.body().toString())

                if (response != null) {
                    Log.e("seccuess", response.body().toString())
                };

            }

            override fun onFailure(call: Call<List<ParkingResponse>>, t: Throwable) {
                Log.e("erreur", t.localizedMessage);
            }


        })

    }

    fun onClickListener( position: Int ){

        parkPosition=position
        if (thisView != null) {
            thisView.findNavController().navigate(R.id.action_fragment_listpark_to_fragment_details)
        }

    }



}




