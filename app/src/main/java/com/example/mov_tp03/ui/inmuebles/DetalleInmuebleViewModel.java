package com.example.mov_tp03.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mov_tp03.Modelo.Inmueble;
import com.example.mov_tp03.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInmuebleViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Inmueble> dataInmuebleMutable;
    public DetalleInmuebleViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Inmueble> getDataInmuebleMutable() {
        if(dataInmuebleMutable == null){
            dataInmuebleMutable = new MutableLiveData<>();
        }
        return dataInmuebleMutable;
    }


    public void actualizarInmueble(Inmueble inmueble) {


        String token = ApiClient.getToken(context);
        ApiClient.EndPoint api = ApiClient.getSrv();
        Call<Inmueble> call = api.actualizarInmueble(token, inmueble);

        call.enqueue(new Callback<Inmueble>() {
            @Override
            public void onResponse(@NonNull Call<Inmueble> call, @NonNull Response<Inmueble> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        dataInmuebleMutable = (dataInmuebleMutable != null) ? dataInmuebleMutable : new MutableLiveData<>();
                        dataInmuebleMutable.setValue(response.body());

                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<Inmueble> call, @NonNull Throwable t) {
                Toast.makeText(context, "Error al actualizar inmueble", Toast.LENGTH_SHORT).show();
            }
        });
    }
}