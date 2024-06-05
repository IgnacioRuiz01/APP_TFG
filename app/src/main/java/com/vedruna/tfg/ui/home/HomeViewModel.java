package com.vedruna.tfg.ui.home;

import android.app.Application;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.vedruna.tfg.DTO.ClassAdDTO;
import com.vedruna.tfg.Interfaces.ClassAdInterface;
import com.vedruna.tfg.Network.ApiClient;
import com.vedruna.tfg.Utils.TokenManager;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class HomeViewModel extends AndroidViewModel {

    private final MutableLiveData<List<ClassAdDTO>> classAds;
    private ClassAdInterface classAdInterface;
    private TokenManager tokenManager;

    public HomeViewModel(@NonNull Application application) {
        super(application);
        classAds = new MutableLiveData<>();
        tokenManager = TokenManager.getInstance(application.getApplicationContext());
        getAllClassAds();
    }

    public LiveData<List<ClassAdDTO>> getClassAds() {
        return classAds;
    }

    private void getAllClassAds() {
        classAdInterface = ApiClient.getClient(getApplication().getApplicationContext()).create(ClassAdInterface.class);
        Call<List<ClassAdDTO>> call = classAdInterface.findAllClassAds();

        call.enqueue(new Callback<List<ClassAdDTO>>() {
            @Override
            public void onResponse(Call<List<ClassAdDTO>> call, Response<List<ClassAdDTO>> response) {
                if (!response.isSuccessful()) {
                    Log.e("Response error ", response.message());
                    // Handle error here
                    return;
                }

                List<ClassAdDTO> classResponse = response.body();
                if (classResponse == null || classResponse.isEmpty()) {
                    Log.e("Response error ", "La lista de clases está vacía");
                    // Handle empty response here
                    return;
                }

                classAds.postValue(classResponse);
            }

            @Override
            public void onFailure(Call<List<ClassAdDTO>> call, Throwable t) {
                Log.e("Throw err:", t.getMessage());
                // Handle failure here
            }
        });
    }

    public void updateClassAds(List<ClassAdDTO> classAds) {
        this.classAds.setValue(classAds);
    }
}
