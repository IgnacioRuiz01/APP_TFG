package com.vedruna.tfg.ui.dashboard;

import android.content.Context;
import android.util.Log;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import com.vedruna.tfg.DTO.ClassAdDTO;
import com.vedruna.tfg.Interfaces.ClassAdInterface;
import com.vedruna.tfg.Network.ApiClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DashboardViewModel extends ViewModel {

    private final MutableLiveData<List<ClassAdDTO>> classAds;
    private ClassAdInterface classAdInterface;
    private Context context;

    public DashboardViewModel() {
        classAds = new MutableLiveData<>();
    }

    public LiveData<List<ClassAdDTO>> getClassAds() {
        return classAds;
    }

    public void searchClassAdsByTitle(String title) {
        if (context == null) {
            Log.e("DashboardViewModel", "Context is null. Cannot proceed with API call.");
            return;
        }

        classAdInterface = ApiClient.getClient(context).create(ClassAdInterface.class);
        Call<List<ClassAdDTO>> call = classAdInterface.searchClassAdsByTitle(title);

        call.enqueue(new Callback<List<ClassAdDTO>>() {
            @Override
            public void onResponse(Call<List<ClassAdDTO>> call, Response<List<ClassAdDTO>> response) {
                if (response.isSuccessful()) {
                    List<ClassAdDTO> classResponse = response.body();
                    if (classResponse != null && !classResponse.isEmpty()) {
                        classAds.postValue(classResponse);
                        Log.d("DashboardViewModel", "Clases encontradas por título: " + classResponse.size());
                    } else {
                        Log.e("Response error", "No se encontraron clases con el título: " + title);
                    }
                } else {
                    Log.e("Response error", "Error al realizar la solicitud: " + response.message());
                }
            }

            @Override
            public void onFailure(Call<List<ClassAdDTO>> call, Throwable t) {
                Log.e("Throw err", "Error al realizar la llamada: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    // Add setter method for context
    public void setContext(Context context) {
        this.context = context;
    }
}
