package com.example.authentification_cloud

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.authentification_cloud.databinding.FragmentListReservationBinding
import retrofit2.Call
import java.text.SimpleDateFormat
import java.util.*
import retrofit2.Callback
import retrofit2.Response

//lateinit var thisView : View
private lateinit var newRecyclerView: RecyclerView
var reservationList: List<Reservation>? = null //= ArrayList<Reservation>() //= FragmentListReservation().getReservation()
var reservationPosition : Int = 0

class FragmentListReservation : Fragment() {

    private lateinit var binding : FragmentListReservationBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        //return inflater.inflate(R.layout.fragment__list_reservation, container, false)
        binding = FragmentListReservationBinding.inflate(layoutInflater)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if(isLogin(requireActivity())) {
            //binding.TextView2.visibility = View.VISIBLE
        }
        else {
            findNavController().navigate(R.id.action_fragment_ListReservation_to_fragment_login)
        }

        reservationList = db?.getReservation()
        thisView = view
        newRecyclerView = view.findViewById(R.id.ListReservation)
        newRecyclerView.layoutManager = LinearLayoutManager(view.context)
        newRecyclerView.setHasFixedSize(true)
        newRecyclerView.adapter = ReservationAdapter(reservationList!!)


        var TodayReservation = ""
        for ( res in reservationList!!)
        {
            if ( res.date == SimpleDateFormat ("dd/MM/yyyy").format(Date()))
            {
                TodayReservation += res.toString()
            }
        }

    }

    fun onClickListener( position: Int ){
        Toast.makeText(MainActivityView," OnClickListener:"+ position,Toast.LENGTH_LONG).show()

        if (thisView != null) {
            thisView.findNavController().navigate(R.id.action_fragment_ListReservation_to_fragment_details_reservation)
        }

    }

    public fun getReservation(): List<Reservation>? {


        db = AppDatabase.buildDatabase(requireActivity())?.getReservationDao()


        reservationList = db?.getReservation() as ArrayList<Reservation>

        if ( (reservationList as ArrayList<Reservation>).size <= 0)
        {
            var reservations: List <ReservationRequest>? = GetApiReservations()
            if ( reservations != null )
            {
                for ( res in reservations )
                {
                    addReservation(Reservation((reservationList as ArrayList<Reservation>).size, res.date, res.entree, res.sortie, res.id_user, res.id_park, res.valider, 1))
                }
            }

        }

        return reservationList

    }

    public fun addReservation (reservation: Reservation)  {

        db?.addReservations(reservation)
        //Toast.makeText(thisContext,"après ajout: $nbreReservation".toString(),Toast.LENGTH_LONG).show()
    }

    fun GetApiReservations() : List<ReservationRequest>?
    {
        var reservations  : List<ReservationRequest>? = null
        val reservationResponseCall = ApiClient.getReservationService().reservations
        reservationResponseCall.enqueue(object : Callback<List<ReservationRequest>> {
            override fun onResponse(
                call: Call<List<ReservationRequest>>,
                response: Response<List<ReservationRequest>>
            ) {
                if (response?.body() != null)
                    reservations = response?.body()!!
                Log.e("seccuess", response.body().toString())
            }

            override fun onFailure(call: Call<List<ReservationRequest>>, t: Throwable) {
                Log.e("erreur", t.localizedMessage);
            }
        })
        return reservations
    }

}

/*
class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val pref = getSharedPreferences("Auth", Context.MODE_PRIVATE)
        button8.setOnClickListener {
            pref.edit().putBoolean("connected", false).apply()
            val intent = Intent(this, MainActivity3::class.java)
            startActivity(intent)
            finish()
        }
    }
}
 */