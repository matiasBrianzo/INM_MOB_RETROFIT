package com.example.mov_tp03.ui.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.Toast;

import com.example.mov_tp03.MainActivity;
import com.example.mov_tp03.request.ApiClient;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivityViewModel extends AndroidViewModel {

    private final Context context;

    public LoginActivityViewModel(@NonNull Application application) {
         super(application);
        context = application.getApplicationContext();
    }


    public void confirmaLogin(String pUsuario, String pClave) {

        if (pUsuario.isEmpty() || pClave.isEmpty()) {
            Toast.makeText(context, "Campo vacío", Toast.LENGTH_SHORT).show();
            return;
        }
        pUsuario="luisprofessor@gmail.com";
        pClave="DEEKQW";
        ApiClient.EndPointInmobiliaria api = ApiClient.getEndPointInmobiliaria();
        Call<String> call = api.loginForm(pUsuario,pClave);

        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(@NonNull Call<String> call, @NonNull Response<String> response) {
                Log.d("response", response.body() + response.toString());
                if (response.isSuccessful()) {
                    if (response.body() != null) {

                        String token = response.body();
                        Log.d("TOKEN", "Bearer " +token); // Mostrar el token en el log

                       //Guardo Token
                        ApiClient.setToken(context,token);

                        Intent intent = new Intent(context, MainActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);
                    }
                } else {
                    if (response.code() == 404) {
                        Toast.makeText(context, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(context, "Error en la respuesta: " + response.code(), Toast.LENGTH_SHORT).show();
                    }
                }
            }

            @Override
            public void onFailure(@NonNull Call<String> call, @NonNull Throwable t) {
                Log.d("failure", t.getMessage() + t.toString());
                Toast.makeText(context, "Error al llamar al servicio de login", Toast.LENGTH_SHORT).show();
            }
        });
    }
}