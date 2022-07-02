package com.example.authentification_cloud;

import com.example.authentification_cloud.UserRequest;
import com.example.authentification_cloud.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserService {
    @POST("users")
    Call<UserResponse> saveUser(@Body UserRequest userRequest);


    @POST("users/loggin")
    Call<UserResponse> LoginUser(@Body LoginRequest loginRequest);

    @POST("users/GoogleLoggin")
    Call<UserResponse> GoogleLoginUser(@Body LoginRequest loginRequest);
}