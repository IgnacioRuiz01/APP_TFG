package com.vedruna.tfg.ui.notifications;

import android.content.Context;
import android.util.Base64;
import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.vedruna.tfg.DTO.ClassAdDTO;
import com.vedruna.tfg.Interfaces.ClassAdInterface;
import com.vedruna.tfg.Network.ApiClient;
import com.vedruna.tfg.Utils.TokenManager;

import org.json.JSONException;
import org.json.JSONObject;

import java.nio.charset.StandardCharsets;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NotificationsViewModel extends ViewModel {

    private final MutableLiveData<String> mText;
    private final MutableLiveData<Boolean> creationResult;
    private ClassAdInterface classAdInterface;
    private TokenManager tokenManager;

    private static final  String TAG="";

    public NotificationsViewModel() {
        mText = new MutableLiveData<>();
        mText.setValue("Create a new Class Ad");

        creationResult = new MutableLiveData<>();
    }

    public LiveData<String> getText() {
        return mText;
    }

    public LiveData<Boolean> getCreationResult() {
        return creationResult;
    }

    public void createClassAd(ClassAdDTO classAd, Context context) {
        tokenManager = TokenManager.getInstance(context.getApplicationContext());
        Long user_id = Long.valueOf(tokenManager.getUserId());

        if (user_id == null) {
            Log.e("NotificationsViewModel", "User ID not found in token");
            return;
        }

        // Establecer el ID de usuario en el objeto ClassAdDTO
        classAd.setUserId(user_id);

        Retrofit retrofit = ApiClient.getClient(context.getApplicationContext());
        classAdInterface = retrofit.create(ClassAdInterface.class);

        Call<ClassAdDTO> call = classAdInterface.createClassAd(classAd);

        call.enqueue(new Callback<ClassAdDTO>() {
            @Override
            public void onResponse(Call<ClassAdDTO> call, Response<ClassAdDTO> response) {
                if (response.isSuccessful()) {
                    creationResult.postValue(true);
                } else {
                    Log.e("NotificationsViewModel", "Response error: " + response.message());
                    creationResult.postValue(false);
                }
            }

            @Override
            public void onFailure(Call<ClassAdDTO> call, Throwable t) {
                Log.e("NotificationsViewModel", "API call failed", t);
                creationResult.postValue(false);
            }
        });
    }


}
