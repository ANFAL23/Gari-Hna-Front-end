package com.example.authentification_cloud;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ReservationService {
    @POST("reservations")
    Call<ReservationRequest> saveReservation(@Body ReservationRequest reservationRequest);
    @GET("reservations")
    Call<List<ReservationRequest>> getReservations();

}
