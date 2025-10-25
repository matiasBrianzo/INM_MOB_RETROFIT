package com.example.mov_tp03.ui.inmuebles;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.mov_tp03.Modelo.Inmueble;
import com.example.mov_tp03.request.ApiClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InmueblesViewModel extends AndroidViewModel {

    private MutableLiveData<List<Inmueble>> listaInmuebles = new MutableLiveData<>();
    private final Context context;
    public InmueblesViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public LiveData<List<Inmueble>> getListaInmuebles(){
        return listaInmuebles;
    }

    public void obtenerListaInmuebles(){
        String token = ApiClient.getToken(context);
        ApiClient.EndPoint api = ApiClient.getSrv();
        Call<List<Inmueble>> call = api.getInmuebles(token);

        call.enqueue(new Callback<List<Inmueble>>() {
            @Override
            public void onResponse(Call<List<Inmueble>> call, Response<List<Inmueble>> response) {
                if (response.isSuccessful()){
                    listaInmuebles.postValue(response.body());
                }else {
                    Toast.makeText(context,"no se obtuvieron Inmuebles",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<List<Inmueble>> call, Throwable throwable) {
                Log.d("errorInmueble",throwable.getMessage());

                Toast.makeText(context,"Error al obtener Inmuebles",Toast.LENGTH_LONG).show();
            }
        });
    }


}