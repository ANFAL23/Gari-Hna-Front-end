package com.example.authentification_cloud


import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView

class ReservationAdapter(private val reservationList: List<Reservation>) : RecyclerView.Adapter<ReservationAdapter.ReservationViewHolder>() {

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ReservationAdapter.ReservationViewHolder {

        var itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.rows_reservations, parent, false)
        return ReservationViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ReservationAdapter.ReservationViewHolder, position: Int) {

        val currentItem = reservationList[position]
        //holder.titleImage.setImageResource(currentItem.titleImage)
        holder.numReservation.text = currentItem.numReservation.toString()
       // holder.date.text = currentItem.date
       // holder.beginHour.text = currentItem.beginHour
       // holder.endHour.text = currentItem.endHour
       // holder.id_park.text =
         //   "park " + currentItem.id_park.toString() + " place " + currentItem.numPlace.toString()

        holder.itemView.setOnClickListener {

            //Toast.makeText(MainActivityView," holder:"+ position, Toast.LENGTH_LONG).show()
            reservationPosition = position
            FragmentListReservation().onClickListener(position)

        }

    }

    override fun getItemCount(): Int {

        return reservationList.size
    }

    class ReservationViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        //val titleImage : ShapeableImageView = itemView.findViewById(R.id.titleImage)
        val numReservation: TextView = itemView.findViewById(R.id.numReservation)
       // val date: TextView = itemView.findViewById(R.id.date)
       // val beginHour: TextView = itemView.findViewById(R.id.beginHour)
       // val endHour: TextView = itemView.findViewById(R.id.endHour)
        //val id_park: TextView = itemView.findViewById(R.id.id_park)

    }

}