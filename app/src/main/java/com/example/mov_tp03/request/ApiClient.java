package com.example.mov_tp03.request;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.mov_tp03.Modelo.Inmueble;
import com.example.mov_tp03.Modelo.Propietario;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.ArrayList;
import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;

public class ApiClient {
    private static final String PATH = "https://inmobiliariaulp-amb5hwfqaraweyga.canadacentral-01.azurewebsites.net/";
    private static ApiClient api = null;
    public static EndPoint getSrv() {
        Gson gson = new GsonBuilder().setLenient().create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(PATH)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        return retrofit.create(EndPoint.class);
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

    public interface EndPoint {

        //login propietario
        @FormUrlEncoded
        @POST("api/Propietarios/login")
        Call<String> loginForm(@Field("Usuario") String Usuario,@Field("Clave") String Clave);

        @GET("api/Propietarios")
        Call<Propietario> getPropietario(@Header("Authorization") String token);

        @PUT("api/Propietarios/actualizar")
        Call<Propietario> actualizarProp(@Header("Authorization") String token, @Body Propietario p);

        @GET("api/Inmuebles")
        Call<List<Inmueble>> getInmuebles(@Header("Authorization") String token);


        @PUT("api/Inmuebles/actualizar")
        Call<Inmueble> actualizarInmueble(@Header("Authorization") String token, @Body Inmueble inmueble);

        @Multipart
        @PUT("api/Inmuebles/cargar")
        Call<Inmueble> cargarInmueble(@Header("Authorization") String token, @Part MultipartBody.Part imagen, @Part("inmueble")RequestBody inmuebleBody);
    }
}
