package com.example.mov_tp03.request;

import com.example.mov_tp03.Modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public class ApiClient {
    private static final String PATH = "https://inmobiliariaulp-amb5hwfqaraweyga.canadacentral-01.azurewebsites.net/";
    private static ApiClient api = null;
    public static EndPointInmobiliaria getEndPointInmobiliaria() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndPointInmobiliaria.class);
    }

    public static ApiClient getApi() {
        if (api == null) {
            api = new ApiClient();
        }
        return api;

    }

    public interface EndPointInmobiliaria {

        //login propietario
        @FormUrlEncoded
        @POST("api/Propietarios/login")
        Call<String> loginForm(@Field("Usuario") String Usuario,@Field("Clave") String Clave);


    }
}
