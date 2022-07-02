package com.example.authentification_cloud

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class Reservation(
    @PrimaryKey var numReservation: Int,
    var date: String?,
    var beginHour: String?,
    var endHour: String?,
    var id_user: Int,
    var id_park: Int,
    var paymentValid: Boolean,
    var numPlace: Int,
    //var codeQr: Int,
)
