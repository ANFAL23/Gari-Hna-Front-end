package com.example.authentification_cloud

import androidx.room.*

@Dao
interface ReservationDao {
    @Query("select * from Reservation ")
    fun getReservation ():List<Reservation>
    @Query("select * from Reservation where date=:date ")
    fun getReservationByDate (date:String):List<Reservation>
    @Insert
    fun addReservations (reservation:Reservation)
    @Update
    fun updateReservation (reservation:Reservation)
    @Delete
    fun deleteReservation (reservation:Reservation)
}
