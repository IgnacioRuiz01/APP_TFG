package com.vedruna.tfg.ui.perfil;

import android.content.Context;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vedruna.tfg.DTO.UserDTO;
import com.vedruna.tfg.Interfaces.UserInterface;
import com.vedruna.tfg.Network.RetrofitClient;
import com.vedruna.tfg.Utils.TokenManager;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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

    public void loadUserProfile(Context context) {
        tokenManager = TokenManager.getInstance(context.getApplicationContext());
        userInterface = RetrofitClient.getUserInterface(context);
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
                        user.postValue(null);
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
                    user.postValue(null);
                }
            });
        } else {
            Log.e(TAG, "Token is null");
            user.postValue(null);
        }
    }
}
