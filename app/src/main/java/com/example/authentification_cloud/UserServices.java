package com.example.authentification_cloud;

import com.example.authentification_cloud.UserRequest;
import com.example.authentification_cloud.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public class UserServices {
    @POST("users")
    Call<UserResponse> saveUsers(@Body UserRequest userRequest) {
        return null;
    }
}
