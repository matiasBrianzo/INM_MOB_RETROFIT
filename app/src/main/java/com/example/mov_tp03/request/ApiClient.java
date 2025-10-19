package com.example.mov_tp03.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mov_tp03.Modelo.Propietario;
import com.example.mov_tp03.Modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;

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
    public static void setToken(Context context, String token){
// Guardar el token en SharedPreferences
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("token", "Bearer " + token);
        editor.apply();
    }
    public static String getToken(Context context){
        SharedPreferences sp = context.getSharedPreferences("token.xml", Context.MODE_PRIVATE);
        return sp.getString("token", null);
    }

    public interface EndPointInmobiliaria {

        //login propietario
        @FormUrlEncoded
        @POST("api/Propietarios/login")
        Call<String> loginForm(@Field("Usuario") String Usuario,@Field("Clave") String Clave);

        @GET("api/Propietarios")
        Call<Propietario> getPropietario(@Header("Authorization") String token);

        @PUT("api/Propietarios/actualizar")
        Call<Propietario> actualizarProp(@Header("Authorization") String token, @Body Propietario p);

    }
}
