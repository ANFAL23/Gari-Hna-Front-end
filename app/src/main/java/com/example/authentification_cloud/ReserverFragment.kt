package com.example.authentification_cloud

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.app.TimePickerDialog
import android.app.TimePickerDialog.OnTimeSetListener
import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import com.example.authentification_cloud.databinding.FragmentReserverBinding
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*


class ReserverFragment : Fragment() {


    private lateinit var binding: FragmentReserverBinding
    private var datePickerDialog: DatePickerDialog? = null
    private var dateButton: Button? = null
    private var timeButton: Button? = null
    private var timeButton2: Button? = null
    private var hour = 0
    private var minute:Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = FragmentReserverBinding.inflate(layoutInflater)



    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentReserverBinding.inflate(layoutInflater)
        val view = binding.root

        return view

    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        dateButton = binding.datePickerButton;
        timeButton2 = binding.timeButton;
        timeButton= binding.timeButton2;
        initDatePicker();
        var park = binding.park
        park.text= parkListd[parkPosition].nom
        dateButton!!.setText(getTodaysDate())
        dateButton!!.setText(getTodaysDate())
        binding.datePickerButton
            .setOnClickListener {openDatePicker(view) }
        binding.timeButton
            .setOnClickListener {popTimePicker(view, timeButton2!!) }
        binding.timeButton2
            .setOnClickListener {popTimePicker(view, timeButton!!) }
        binding.reserver.setOnClickListener{


           Reserver(CreateRequest ())
        }
    }
    private fun CreateRequest () : ReservationRequest {


        var reservationRequest =  ReservationRequest();

        reservationRequest.date=binding.datePickerButton.text.toString()
        reservationRequest.entree=binding.timeButton.text.toString()
        reservationRequest.sortie=binding.timeButton2.text.toString()
        reservationRequest.valider=false
       //**********************recup de l'id utilisateur *******************************

        reservationRequest.id_park=parkListd[parkPosition].id_park
        //*******************************************************************************
        reservationRequest.id_user=user
        return reservationRequest


    }
    private fun Reserver (reservationRequest : ReservationRequest) {

        val reservationResponseCall =
            ApiClient.getReservationService().saveReservation(reservationRequest)

        reservationResponseCall.enqueue(object : Callback<ReservationRequest?> {
            override fun onResponse(
                call: retrofit2.Call<ReservationRequest?>,
                response: Response<ReservationRequest?>
            ) {

                if (response.isSuccessful) {
                    Toast.makeText(requireActivity(),"savedSuccesfuli", Toast.LENGTH_LONG).show()
                    //view?.findNavController()?.navigate(R.id.action_fragment_login_to_fragment_listpark)

                } else {
                    Toast.makeText(requireActivity(), "erreur in saved ", Toast.LENGTH_LONG)
                }
            }
            override fun onFailure(call: Call<ReservationRequest?>, t: Throwable) {

                        Toast.makeText(requireActivity(), "erreur in saved ", Toast.LENGTH_LONG)
            }
        })


    }
    fun popTimePicker(view: View?,timeButton: Button ) {
        val onTimeSetListener =
            OnTimeSetListener { timePicker, selectedHour, selectedMinute ->
                hour = selectedHour
                minute = selectedMinute
                timeButton!!.text =
                    java.lang.String.format(Locale.getDefault(), "%02d:%02d", hour, minute)
            }

        // int style = AlertDialog.THEME_HOLO_DARK;
        val timePickerDialog =
            TimePickerDialog(context,  /*style,*/onTimeSetListener, hour, minute, true)
        timePickerDialog.setTitle("Select Time")
        timePickerDialog.show()
    }
    private fun getTodaysDate(): String {
        val cal: Calendar = Calendar.getInstance()
        val year: Int = cal.get(Calendar.YEAR)
        var month: Int = cal.get(Calendar.MONTH)
        month = month + 1
        val day: Int = cal.get(Calendar.DAY_OF_MONTH)
        return makeDateString(day, month, year)
    }

    private fun initDatePicker() {
        val dateSetListener =
            OnDateSetListener { datePicker, year, month, day ->
                var month = month
                month = month + 1
                val date = makeDateString(day, month, year)
                dateButton!!.text = date
            }
        val cal = Calendar.getInstance()
        val year = cal[Calendar.YEAR]
        val month = cal[Calendar.MONTH]
        val day = cal[Calendar.DAY_OF_MONTH]
        val style: Int = AlertDialog.THEME_HOLO_LIGHT

        datePickerDialog =
            context?.let { DatePickerDialog(it, style, dateSetListener, year, month, day) }
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    private fun makeDateString(day: Int, month: Int, year: Int): String {
        return getMonthFormat(month) + " " + day + " " + year
    }

    private fun getMonthFormat(month: Int): String {
        if (month == 1) return "JAN"
        if (month == 2) return "FEB"
        if (month == 3) return "MAR"
        if (month == 4) return "APR"
        if (month == 5) return "MAY"
        if (month == 6) return "JUN"
        if (month == 7) return "JUL"
        if (month == 8) return "AUG"
        if (month == 9) return "SEP"
        if (month == 10) return "OCT"
        if (month == 11) return "NOV"
        return if (month == 12) "DEC" else "JAN"

        //default should never happen
    }

    fun openDatePicker(view: View?) {
        datePickerDialog!!.show()
    }





}