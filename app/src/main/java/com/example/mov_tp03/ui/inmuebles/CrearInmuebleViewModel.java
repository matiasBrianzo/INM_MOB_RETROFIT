package com.example.mov_tp03.ui.inmuebles;

import static android.app.Activity.RESULT_OK;


import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.util.Log;
import android.widget.Toast;

import androidx.activity.result.ActivityResult;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mov_tp03.MainActivity;
import com.example.mov_tp03.Modelo.Inmueble;
import com.example.mov_tp03.request.ApiClient;
import com.google.gson.Gson;


import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class CrearInmuebleViewModel extends AndroidViewModel {
    private MutableLiveData<Uri> uriMutableLiveData;
    private MutableLiveData<Inmueble> mInmueble;
    private static Inmueble inmueble;
    private final Context context;

    public CrearInmuebleViewModel(@NonNull Application application) {
        super(application);
        inmueble = new Inmueble();
        context = application.getApplicationContext();
    }

    // TODO: Implement the ViewModel
    public LiveData<Uri> getUriMutable() {
        if (uriMutableLiveData == null) {
            uriMutableLiveData = new MutableLiveData<>();
        }
        return uriMutableLiveData;

    }

    public LiveData<Inmueble> getmInmueble() {
        if (mInmueble == null) {
            mInmueble = new MutableLiveData<>();
        }
        return mInmueble;
    }

    public void recibirFoto(ActivityResult result) {
        if (result.getResultCode() == RESULT_OK) {
            Intent data = result.getData();
            Uri uri = data.getData();
            Log.d("salada", uri.toString());
            uriMutableLiveData.setValue(uri);
        }
    }

    private byte[] transformarImagen() {
        try {
            Uri uri = uriMutableLiveData.getValue();  //lo puedo usar porque estoy en viewmodel
            InputStream inputStream = getApplication().getContentResolver().openInputStream(uri);
            Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (
                FileNotFoundException er) {
            Toast.makeText(getApplication(), "No ha seleccinado una foto", Toast.LENGTH_SHORT).show();
            return new byte[]{};
        }

    }

    public void RunSave(String pDireccion, String pUso, String pTipo, String pLatitud, String pLongitud, String pPrecio, String pAmbientes, boolean pisponible, String pSuperficie) {

        boolean valido=validoCampos( pDireccion,  pUso,  pTipo,  pLatitud,  pLongitud,  pPrecio,  pAmbientes,  pisponible,  pSuperficie);
        if (!valido) {
            Toast.makeText(getApplication(), "Complete todos los campos correctamente", Toast.LENGTH_SHORT).show();
        }
        else {
            guardarInmueble( pDireccion,  pUso,  pTipo,  pLatitud,  pLongitud,  pPrecio,  pAmbientes,  pisponible,  pSuperficie);
        }
    }

    private void guardarInmueble(String pDireccion, String pUso, String pTipo, String pLatitud, String pLongitud, String pPrecio, String pAmbientes, boolean pDisponible, String pSuperficie) {

        try {
            int amb = Integer.parseInt(pAmbientes);
            int superf = Integer.parseInt(pSuperficie);
            double prec = Double.parseDouble(pPrecio);
            inmueble = new Inmueble();
            inmueble.setDireccion(pDireccion);
            inmueble.setUso(pUso);
            inmueble.setTipo(pTipo);
            inmueble.setValor(prec);
            inmueble.setSuperficie(superf);
            inmueble.setAmbientes(amb);
            inmueble.setDisponible(pDisponible);
            // convertir la imagen en bits
            byte[] imagen=transformarImagen();
            if (imagen.length == 0){
                Toast.makeText(getApplication(), "Ustes debe ingresar una imagen", Toast.LENGTH_SHORT).show();
                return;
            }
            String inmuebleJson = new Gson().toJson(inmueble);
            RequestBody inmuebleBody = RequestBody.create(MediaType.parse("application/json; charset=utf-8"), inmuebleJson);
            RequestBody requestFile = RequestBody.create(MediaType.parse("image/jpeg"), imagen);
            MultipartBody.Part imagenPart = MultipartBody.Part.createFormData("imagen", "imagen.jpg", requestFile);

            ApiClient.EndPoint api = ApiClient.getSrv();
            String token=ApiClient.getToken(context);
            Call<Inmueble> llamada=api.CargarInmueble(token, imagenPart, inmuebleBody);
            llamada.enqueue(new Callback<Inmueble>() {
                @Override
                public void onResponse(Call<Inmueble> call, Response<Inmueble> response) {
                    if (response.isSuccessful()){
                        Toast.makeText(context, "Inmueble guardado correctamente", Toast.LENGTH_SHORT).show();

                        Intent intent = new Intent(context, InmueblesFragment.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        context.startActivity(intent);

                    }
                    else {
                        if (response.code() == 404) {
                            Toast.makeText(context, "Datos incorrectos", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(context, "Error en la respuesta: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }
                }
                @Override
                public void onFailure(Call<Inmueble> call, Throwable throwable) {
                    Toast.makeText(getApplication(), "Error al guardar inmueble", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (NumberFormatException e) {
            Toast.makeText(getApplication(), "Error,", Toast.LENGTH_SHORT).show();
        }
    }

    private boolean validoCampos(String pDireccion, String pUso, String pTipo, String pLatitud, String pLongitud, String pPrecio, String pAmbientes, boolean pisponible, String pSuperficie) {

        try{
            int amb = Integer.parseInt(pAmbientes);
            int superf = Integer.parseInt(pSuperficie);
            double prec = Double.parseDouble(pPrecio);
            double Latitud = Double.parseDouble(pLatitud);
            double Longitud = Double.parseDouble(pLongitud);
            boolean valido = true;

            if (pDireccion.isEmpty()) valido = false;
            if (pUso.isEmpty()) valido = false;
            if (pTipo.isEmpty()) valido = false;
            if (amb <= 0) valido = false;
            if (superf <= 0) valido = false;
            if (prec <= 0) valido = false;
            if (Latitud <= 0) valido = false;
            if (Longitud <= 0) valido = false;
            if (!valido) {
                Toast.makeText(getApplication(), "Complete todos los campos correctamente", Toast.LENGTH_SHORT).show();
                return false;
            }
        } catch (Exception e) {
            Toast.makeText(getApplication(), "Complete todos los campos correctamente", Toast.LENGTH_SHORT).show();
            return false;
        }
      return true;
    }
}