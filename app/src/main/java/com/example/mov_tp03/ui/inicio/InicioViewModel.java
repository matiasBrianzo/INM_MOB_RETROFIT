package com.example.mov_tp03.ui.inicio;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.mov_tp03.request.ApiClient;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.util.ArrayList;
import java.util.List;
public class InicioViewModel extends AndroidViewModel {
    private FusedLocationProviderClient fusedLocationClient;
    private MutableLiveData<Location> ubicacionActual = new MutableLiveData<>();
    private MutableLiveData<List<MarkerOptions>> marcadores = new MutableLiveData<>();
    private Context context;
    private ApiClient api;
    private MutableLiveData<GoogleMap> mapaListo = new MutableLiveData<>();

    public InicioViewModel(@NonNull Application application) {
        super(application);
        context = application.getApplicationContext();
        api = ApiClient.getApi();
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(application.getApplicationContext());
    }


    public LiveData<Location> getUbicacionActual() {
        return ubicacionActual;
    }
    public LiveData<List<MarkerOptions>> getMarcadores() {
        return marcadores;
    }

    public LiveData<GoogleMap> getMapaListo() {
        return mapaListo;
    }


    public void actualizarUbicacion() {
        if (ContextCompat.checkSelfPermission(getApplication().getApplicationContext(), Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            fusedLocationClient.getLastLocation().addOnSuccessListener(location -> {
                if (location != null) {
                    ubicacionActual.setValue(location);
                    actualizarMarcadores(location);
                }
            });
        }
    }
    private void actualizarMarcadores(Location location) {
        List<MarkerOptions> listaMarcadores = new ArrayList<>();

        LatLng SANLUIS = new LatLng(-33.2914, -66.32467);
        listaMarcadores.add(new MarkerOptions().position(SANLUIS).title("inmobiliaria"));


        // Agregar más marcadores según la ubicación actual
        if (location != null) {
            LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
            listaMarcadores.add(new MarkerOptions().position(latLng).title("Mi ubicación"));
        }

        marcadores.setValue(listaMarcadores);
    }


    // Método que el Fragment llamará cuando el mapa esté listo
    public void onMapReady(GoogleMap googleMap) {
        mapaListo.setValue(googleMap); // Notificamos a los observadores que el mapa está listo

        // Observar marcadores
        marcadores.observeForever(markerOptionsList -> { // Usamos observeForever para que siempre esté activo
            GoogleMap map = mapaListo.getValue();
            if (map != null) {
                map.clear(); // Limpiamos todos los marcadores anteriores del mapa
                for (MarkerOptions markerOptions : markerOptionsList) {
                    map.addMarker(markerOptions);
                }
            }
        });

        // Observar ubicación actual
        ubicacionActual.observeForever(location -> { // Usamos observeForever para que siempre esté activo
            GoogleMap map = mapaListo.getValue();
            if (map != null && location != null) {
                LatLng latLng = new LatLng(location.getLatitude(), location.getLongitude());
                map.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng, 16));
            }
        });

        // Configurar la capa de ubicación y actualizar ubicación si los permisos están concedidos
        verificarYConfigurarUbicacionEnMapa();
    }

    private void verificarYConfigurarUbicacionEnMapa() {
        GoogleMap map = mapaListo.getValue();
        if (map != null && ContextCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
            try {
                map.setMyLocationEnabled(true);
                actualizarUbicacion(); // Solicitar la ubicación una vez que el mapa esté listo y los permisos concedidos
            } catch (SecurityException e) {
                // Manejar si los permisos son revocados después de la verificación
                e.printStackTrace();
            }
        }
    }
}