package com.example.authentification_cloud

//import com.bumptech.glide.Glide
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import coil.api.load
import com.google.android.material.imageview.ShapeableImageView
import okhttp3.HttpUrl
import okhttp3.HttpUrl.Companion.toHttpUrl
import java.nio.file.Files.size

// var clickeditem : ClickedItem
class ParkAdapter(private var parkList: List<ParkingResponse>) :
      RecyclerView.Adapter<ParkAdapter.ParkViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ParkAdapter.ParkViewHolder {

        var itemView = LayoutInflater.from(parent.context).inflate(R.layout.rows_parkings,
            parent, false)
        return ParkViewHolder(itemView)
    }
    public fun setData(parkingResponseList: List<ParkingResponse?>) {
        this.parkList = parkingResponseList as List<ParkingResponse>;

        notifyDataSetChanged()
    }



    @RequiresApi(Build.VERSION_CODES.O)
    override fun onBindViewHolder(holder: ParkAdapter.ParkViewHolder, position: Int) {
        //var parkingResponse :ParkList.get
        val currentItem = parkList[position]
        val id_park = parkList[position].id_park
        holder.titlePark.text = currentItem.nom

        holder.commun.text = currentItem.commun
        holder.etat.text=currentItem.etat
        holder.distance.text= currentItem.distance.toString()
        holder.duree.text= currentItem.duree.toString()
        holder.Taux.text=currentItem.taux
        holder.titleImage.load(currentItem.photo.toHttpUrl()) {size (150,150)}





        holder.itemView.setOnClickListener {

               FragmentListPark().onClickListener(position)

        }

    }

    override fun getItemCount(): Int {

        return  parkList.size
    }

    class ParkViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){

        var titleImage : ImageView = itemView.findViewById(R.id.titleImage)
        val titlePark : TextView = itemView.findViewById(R.id.titlePark)
        val commun : TextView = itemView.findViewById(R.id.commun)
        val etat: TextView = itemView.findViewById(R.id.etat)
        val distance: TextView = itemView.findViewById(R.id.distance)
        val duree: TextView = itemView.findViewById(R.id.duree)
        val Taux: TextView = itemView.findViewById(R.id.tauxOccupation)

       //


    }

}