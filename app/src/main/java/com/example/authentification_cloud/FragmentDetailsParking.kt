package com.example.authentification_cloud

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import coil.api.load
import com.example.authentification_cloud.databinding.FragmentDetailsParkingBinding
import com.google.android.gms.maps.model.LatLng
import okhttp3.HttpUrl.Companion.toHttpUrl

lateinit var titleImage: ImageView
lateinit var titlePark1 : TextView
lateinit var titlePark : TextView
lateinit var commun : TextView
lateinit var etat: TextView
lateinit var tauxOccupation: TextView
lateinit var distance: TextView
lateinit var tempsEstime: TextView
lateinit var jour: TextView
lateinit var heure: TextView
lateinit var temps: TextView
lateinit var prix: TextView

lateinit var buttonReser : Button
lateinit var button : ImageButton

class FragmentDetailsParking : Fragment() {

    private val sharedPrefFile = "sharedpreference"


    private lateinit var binding: FragmentDetailsParkingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentDetailsParkingBinding.inflate(layoutInflater)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDetailsParkingBinding.inflate(layoutInflater)
        val view = binding.root
        return view

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner,object:
            OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                findNavController().popBackStack(R.id.fragment_ListPark , false)
            }

        })
        titleImage = binding.titleImage as ImageView
        titlePark = binding.titlePark as TextView
        commun = binding.commun as TextView
        etat = binding.etat as TextView
        tauxOccupation = binding.tauxOccupation as TextView
        distance = binding.distance as TextView
        tempsEstime = binding.tempsEstime as TextView
        jour = binding.jour as TextView
        heure = binding.horaire as TextView
        temps = binding.temps as TextView
        prix = binding.prix as TextView
        buttonReser =binding.button

        button = binding.imageButton as ImageButton


        val park = parkListd [parkPosition]


        binding.name.text = park.nom
        //titleImage.setImageResource(park.photo)
        titleImage.load(park.photo.toHttpUrl()) {size (200,300)}
        titlePark.text = park.nom
        commun.text = park.commun

        etat.text =  park.etat
        tauxOccupation.text = park.taux.toString()+"%"
        distance.text = park.distance.toString()+" km"
        tempsEstime.text = park.duree.toString()+" min"
        /* val day = android.text.format.DateFormat.format("EEEE", Date()).toString() // (SimpleDateFormat("EEEE").format( Date()) )
         val dayInd = Days.valueOf(day).ordinal
         jour.text = ( park.days[ dayInd ].day.jour )         //park.days[0].name
         heure.text = park.days[ dayInd ].beginHour + " à " + park.days[ dayInd ].endHour*/

        temps.text = "1 heure (s) "
        prix.text = park.tarif.toString()+" DZD"

        button.setOnClickListener {
            val latitude = park.latitude
            val longtitude = park.longitude

            val intent = Uri.parse("geo:$latitude,$longtitude").let{
                Intent(Intent.ACTION_VIEW,it)
            }
            startActivity(intent)
        }

        binding.button.setOnClickListener {
           if(login==0){
               Toast.makeText(requireActivity(), "vous devez s'itentifier d'abord !", Toast.LENGTH_SHORT ).show()
               view.findNavController().navigate(R.id.action_to_fragment_login)
            }
            else {
               view.findNavController().navigate(R.id.action_fragment_details_to_fragment_reserver)
           }
        }

        /*binding.imageButton.setOnClickListener{
            Toast.makeText(requireActivity(),"Opening google maps", Toast.LENGTH_LONG).show()
            val gmmIntentUri = //Uri.parse("google.streetview:cbll=46.414382,10.013988")
            Uri.parse("geo:36.736220116669614,3.07064716733884?z=14")
           // var location =   LatLng(36.736220116669614, 3.070647167338848)

            val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)

            mapIntent.setPackage("com.google.android.apps.maps")

// Attempt to start an activity that can handle the Intent

// Attempt to start an activity that can handle the Intent
            startActivity(mapIntent)

        }*/

        //binding.name.setText("anfel")
        /* val parkResponseCall = ApiClient.getParkingService().LoginUser(loginRequest)
         userResponseCall.enqueue(object : Callback<UserResponse?> {
             override fun onResponse(
                 call: retrofit2.Call<UserResponse?>,
                 response: Response<UserResponse?>
             ) {
                 if (response.isSuccessful) {
                     user  = response.body()!!
                     var id = user.id_user
                     var name = user.nom
                     val sharedPreferences: SharedPreferences? =
                         context?.getSharedPreferences(sharedPrefFile, Context.MODE_PRIVATE)
                     val editor: SharedPreferences.Editor? = sharedPreferences?.edit()
                     if (editor != null) {
                         editor.putInt("id_key",id )
                         editor.putString("name_key",name)
                         editor.apply()
                         editor.commit()
                     }
                     val recup = sharedPreferences?.getInt("id_key",1)
                     Log.e("la valeur recupe from shared", recup.toString())


                     Toast.makeText(requireActivity(),response.message().toString() , Toast.LENGTH_LONG).show()

                     view?.findNavController()?.navigate(R.id.action_fragment_login_to_fragment_listpark)
                 } else {
                     Toast.makeText(requireActivity(), "erreur in saved ", Toast.LENGTH_LONG)
                 }
             }

             override fun onFailure(call: retrofit2.Call<UserResponse?>, t: Throwable) {
                 Toast.makeText(requireActivity(), "erreur in saved ", Toast.LENGTH_LONG)
             }
         })*/



    }
}