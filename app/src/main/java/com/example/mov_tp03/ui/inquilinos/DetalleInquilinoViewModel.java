package com.example.mov_tp03.ui.inquilinos;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mov_tp03.Modelo.Contrato;
import com.example.mov_tp03.Modelo.Inmueble;
import com.example.mov_tp03.Modelo.Inquilino;
import com.example.mov_tp03.Modelo.Propietario;
import com.example.mov_tp03.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleInquilinoViewModel extends AndroidViewModel {

    private MutableLiveData<Contrato> inquilinoMutable;
    private MutableLiveData<Propietario> propietarioGaranteMutable;
    private Context context;
    public DetalleInquilinoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<Contrato> getInquilinoMutable() {
        if(inquilinoMutable == null){
            inquilinoMutable = new MutableLiveData<>();
        }
        return inquilinoMutable;
    }
    public LiveData<Propietario> getPropietarioGaranteMutable() {
        if(propietarioGaranteMutable == null){
            propietarioGaranteMutable = new MutableLiveData<>();
        }
        return propietarioGaranteMutable;
    }

    public void obtenerInquilino(Inmueble inmueble){
        try {

        String token = ApiClient.getToken(context);
        ApiClient.EndPoint end = ApiClient.getSrv();
        String id= String.valueOf(inmueble.getIdInmueble());
        Call<Contrato> call = end.getContratoXInmueble(token,id);

        call.enqueue(new Callback<Contrato>() {
            @Override
            public void onResponse(@NonNull Call<Contrato> call, @NonNull Response<Contrato> response) {
                if (response.isSuccessful()) {
                    if (response.body() != null) {
                        inquilinoMutable.setValue(response.body());
                    }
                }
            }
            @Override
            public void onFailure(@NonNull Call<Contrato> call, @NonNull Throwable t) {
                Toast.makeText(context, "Error al obtener inquilino", Toast.LENGTH_SHORT).show();
            }
        });
        } catch (Exception e) {
                Toast.makeText(getApplication(), "Error,", Toast.LENGTH_SHORT).show();
        }

}
    public void obtenerPropietarioGarante() {
        String token = ApiClient.getToken(context);
        ApiClient.EndPoint api = ApiClient.getSrv();
        Call<Propietario> call=api.getPropietario(token);

        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()){
                    propietarioGaranteMutable.postValue(response.body());
                }else{
                    Toast.makeText(context, "Error en la respuesta: " + response.toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<Propietario> call, Throwable throwable) {
                Toast.makeText(context,throwable.getMessage(),Toast.LENGTH_LONG).show();
            }
        });
    }


}