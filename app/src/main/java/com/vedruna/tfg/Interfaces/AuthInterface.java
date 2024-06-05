package com.vedruna.tfg.Interfaces;

import com.vedruna.tfg.Auth.AuthResponse;
import com.vedruna.tfg.Auth.LoginRequest;
import com.vedruna.tfg.Auth.RegisterRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface AuthInterface {
    @POST("auth/login")
    Call<AuthResponse> login(@Body LoginRequest loginRequest);

    @POST("auth/register")
    Call<AuthResponse> register(@Body RegisterRequest registerRequest);
}
