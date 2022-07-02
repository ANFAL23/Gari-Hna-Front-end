package com.example.authentification_cloud;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ParkingService {
   String  sharedPrefFile = "sharedpreference";
    @GET("parkings")
    Call<List<ParkingResponse>> getParkings();


    @GET("parkings/id")
    Call<ParkingResponse> getParking();
    @POST("parkings/search")
    Call<List<ParkingResponse>> recherche(@Body RechercheRequest rechercheRequest);


}
