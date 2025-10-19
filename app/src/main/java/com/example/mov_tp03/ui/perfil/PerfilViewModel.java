package com.example.mov_tp03.ui.perfil;

import android.app.Application;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mov_tp03.Modelo.Propietario;
import com.example.mov_tp03.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PerfilViewModel extends AndroidViewModel {  
    private MutableLiveData<Boolean> mEstado=new MutableLiveData<>();
    private MutableLiveData<Propietario> propietario=new MutableLiveData<>();
    private MutableLiveData<String> mNombre=new MutableLiveData<>();
    private final Context context;

    public PerfilViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
    }
    public LiveData<Boolean> getEstado(){
        return mEstado;
    }
    public LiveData<String> getNombre(){
        return mNombre;
    }
    public LiveData<Propietario> getPropietario(){
        return propietario;
    }
    public void cambioBoton(String nombreboton, String nombre, String apellido, String dni, String telefono, String email){
        if (nombreboton.equalsIgnoreCase("EDITAR")){
            mEstado.setValue(true);
            mNombre.setValue("GUARDAR");
        }else {
            mEstado.setValue(false);
            mNombre.setValue("EDITAR");
            Propietario actualizado = new Propietario();
            actualizado.setIdPropietario(propietario.getValue().getIdPropietario());
            actualizado.setNombre(nombre);
            actualizado.setApellido(apellido);
            actualizado.setDni(dni);
            actualizado.setTelefono(telefono);
            actualizado.setEmail(email);

            String token = ApiClient.getToken(context);
            ApiClient.EndPointInmobiliaria api = ApiClient.getEndPointInmobiliaria();
            Call<Propietario> call = api.actualizarProp(token, actualizado);

            call.enqueue(new Callback<Propietario>() {
                @Override
                public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(context, "ACTUALIZADO CON EXITO", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(context, "ERROR AL ACTUALIZAR"+response.toString(), Toast.LENGTH_SHORT).show();
                        Log.d("respuesta", response.toString());
                        Log.d("respuesta", token);
                    }
                }

                @Override
                public void onFailure(Call<Propietario> call, Throwable throwable) {
                    Toast.makeText(context, "ERROR EN LA API", Toast.LENGTH_SHORT).show();

                }
            });
        }
    }
    public void obtenerPerfil(){

        String token = ApiClient.getToken(context);
        ApiClient.EndPointInmobiliaria api = ApiClient.getEndPointInmobiliaria();
        Call<Propietario> call=api.getPropietario(token);

        call.enqueue(new Callback<Propietario>() {
            @Override
            public void onResponse(Call<Propietario> call, Response<Propietario> response) {
                if (response.isSuccessful()){
                    propietario.postValue(response.body());
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