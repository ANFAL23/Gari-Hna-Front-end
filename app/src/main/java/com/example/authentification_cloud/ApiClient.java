package com.example.authentification_cloud;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiClient {


    private static Retrofit getRetrofit(){

        HttpLoggingInterceptor httpLogginInterceptor = new HttpLoggingInterceptor();
        httpLogginInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);

        OkHttpClient okHttpClient= new OkHttpClient.Builder().addInterceptor(httpLogginInterceptor).build();

        Retrofit retrofit= new Retrofit.Builder()
                .baseUrl("https://bd38-129-45-38-239.eu.ngrok.io")
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build() ;
        return retrofit;

    }

    public static UserService getUserService(){
        UserService userService =getRetrofit().create(UserService.class);
        return userService;
    }
    public static ParkingService getParkingService(){
        ParkingService parkingService =getRetrofit().create(ParkingService.class);
        return parkingService ;
    }

    public static ReservationService getReservationService(){
        ReservationService reservationService =getRetrofit().create(ReservationService.class);
        return reservationService ;
    }

}
