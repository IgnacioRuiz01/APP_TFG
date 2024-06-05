package com.vedruna.tfg.ui.perfil;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vedruna.tfg.DTO.UserDTO;
import com.vedruna.tfg.Interfaces.UserInterface;
import com.vedruna.tfg.Utils.Constants;
import com.vedruna.tfg.Utils.TokenManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PerfilViewModel extends ViewModel {

    private final MutableLiveData<UserDTO> user;
    private UserInterface userInterface;
    private TokenManager tokenManager;
    private static final String TAG = "PerfilViewModel";

    public PerfilViewModel() {
        user = new MutableLiveData<>();
    }

    public LiveData<UserDTO> getUser() {
        return user;
    }

    private void setupRetrofit() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        userInterface = retrofit.create(UserInterface.class);
    }

    public void loadUserProfile(Context context) {
        tokenManager = TokenManager.getInstance(context.getApplicationContext());
        setupRetrofit();
        String token = tokenManager.getToken();
        if (token != null) {
            Log.d(TAG, "Token retrieved: " + token);
            Long userId = Long.valueOf(tokenManager.getUserId());
            Log.d(TAG, "User ID from token: " + userId);

            Call<UserDTO> call = userInterface.findUserById(userId);
            call.enqueue(new Callback<UserDTO>() {
                @Override
                public void onResponse(Call<UserDTO> call, Response<UserDTO> response) {
                    if (response.isSuccessful()) {
                        Log.d(TAG, "User profile loaded successfully");
                        user.postValue(response.body());
                    } else {
                        Log.e(TAG, "Error loading user profile. Response code: " + response.code());
                        // Añade más detalles del error si están disponibles
                        try {
                            Log.e(TAG, "ErrorBody: " + response.errorBody().string());
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }

                @Override
                public void onFailure(Call<UserDTO> call, Throwable t) {
                    Log.e(TAG, "Error loading user profile", t);
                }
            });
        } else {
            Log.e(TAG, "Token is null");
        }
    }


}
