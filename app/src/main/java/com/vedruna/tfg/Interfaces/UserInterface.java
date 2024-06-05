package com.vedruna.tfg.Interfaces;

import com.vedruna.tfg.DTO.ChangePasswordDTO;
import com.vedruna.tfg.DTO.UserDTO;

import java.util.Map;

import retrofit2.Call;
import retrofit2.http.*;

public interface UserInterface {
    @GET("api/v1/users/findById/{id}")
    Call<UserDTO> findUserById(@Path("id") Long id);

    @GET("api/v1/users/getUsernameById/{id}")
    Call<String> getUsernameById(@Path("id") Long id);

    @GET("api/v1/users/getUserIdByUsername/{username}")
    Call<Long> getUserIdByUsername(@Path("username") String username);

    @GET("api/v1/users/findByUsername/{username}")
    Call<UserDTO> findByUsername(@Path("username") String username);

    @PATCH("api/v1/users/update")
    Call<UserDTO> updateUser(@Body Map<String, Object> updates);

    @PUT("api/v1/users/changePassword")
    Call<String> changePassword(@Body ChangePasswordDTO changePasswordDto);

    @POST("api/v1/users/addFavorite/{classAdId}")
    Call<String> addFavorite(@Path("classAdId") Long classAdId);

    @DELETE("api/v1/users/removeFavorite/{classAdId}")
    Call<String> removeFavorite(@Path("classAdId") Long classAdId);
}
