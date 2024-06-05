package com.vedruna.tfg.Interfaces;

import com.vedruna.tfg.DTO.ClassAdDTO;

import retrofit2.Call;
import retrofit2.http.*;

import java.util.List;

public interface ClassAdInterface {

    @POST("api/v1/classAds/create")
    Call<ClassAdDTO> createClassAd(@Body ClassAdDTO classAdDto);

    @PUT("api/v1/classAds/update/{id}")
    Call<ClassAdDTO> updateClassAd(@Path("id") Long id, @Body ClassAdDTO classAdDto);

    @DELETE("api/v1/classAds/delete/{id}")
    Call<String> deleteClassAd(@Path("id") Long id);

    @GET("api/v1/classAds/search/{title}")
    Call<List<ClassAdDTO>> searchClassAdsByTitle(@Path("title") String title);

    @GET("api/v1/classAds/findAll")
    Call<List<ClassAdDTO>> findAllClassAds();

    @GET("api/v1/classAds/findById/{id}")
    Call<ClassAdDTO> findClassAdById(@Path("id") Long id);

    @POST("api/v1/classAds/like/{classAdId}")
    Call<Void> likeClassAd(@Path("classAdId") Long classAdId);

    @DELETE("api/v1/classAds/unlike/{classAdId}")
    Call<Void> unlikeClassAd(@Path("classAdId") Long classAdId);

    @GET("api/v1/classAds/countLikes/{classAdId}")
    Call<Integer> countLikes(@Path("classAdId") Long classAdId);

    @GET("api/v1/classAds/favorites")
    Call<List<ClassAdDTO>> getFavoriteClassAds();
}

