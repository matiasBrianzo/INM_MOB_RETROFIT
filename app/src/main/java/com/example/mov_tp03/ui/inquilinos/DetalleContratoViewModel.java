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
import com.example.mov_tp03.Modelo.Pago;
import com.example.mov_tp03.request.ApiClient;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class DetalleContratoViewModel extends AndroidViewModel {

    private Context context;
    private MutableLiveData<Contrato> contratoMutable;
    private MutableLiveData<ArrayList<Pago>> pagosMutable;
    public DetalleContratoViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }

    public LiveData<ArrayList<Pago>> getPagosMutable() {
        if(pagosMutable == null){
            pagosMutable = new MutableLiveData<>();
        }
        return pagosMutable;
    }
    public LiveData<Contrato> getContratoMutable() {
        if(contratoMutable == null){
            contratoMutable = new MutableLiveData<>();
        }
        return contratoMutable;
    }

    public void obtenerContrato(Inmueble inmueble) {

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
                            contratoMutable.setValue(response.body());
                        }
                    }
                }
                @Override
                public void onFailure(@NonNull Call<Contrato> call, @NonNull Throwable t) {
                    Toast.makeText(context, "Error al obtener contrato", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplication(), "Error,", Toast.LENGTH_SHORT).show();
        }
    }
    public void obtenerPagosPorContrato(Contrato contrato) {

        try {

            String token = ApiClient.getToken(context);
            ApiClient.EndPoint end = ApiClient.getSrv();
            String id= String.valueOf(contrato.getIdContrato());
            Call<ArrayList<Pago>> call = end.getPagoXContrato(token,id);

            call.enqueue(new Callback<ArrayList<Pago>>() {
                @Override
                public void onResponse(@NonNull Call<ArrayList<Pago>> call, @NonNull Response<ArrayList<Pago>> response) {
                    if (response.isSuccessful()) {
                        if (response.body() != null) {
                            pagosMutable.setValue(response.body());
                        }
                    }
                }
                @Override
                public void onFailure(@NonNull Call<ArrayList<Pago>> call, @NonNull Throwable t) {
                    Toast.makeText(context, "Error al obtener contrato", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (Exception e) {
            Toast.makeText(getApplication(), "Error,", Toast.LENGTH_SHORT).show();
        }
    }


    public String customFecha(String pfecha) {
        try {
            SimpleDateFormat parser = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            return formatter.format(parser.parse(pfecha));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

  
}