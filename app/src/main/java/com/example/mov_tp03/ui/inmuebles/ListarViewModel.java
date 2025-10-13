package com.example.mov_tp03.ui.inmuebles;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;
import  static com.example.mov_tp03.MainActivity.*;

import com.example.mov_tp03.Modelo.Producto;

import java.util.ArrayList;
import java.util.Comparator;

public class ListarViewModel extends ViewModel {

    private  MutableLiveData<ArrayList<Producto>> mLista;

    public LiveData<ArrayList<Producto>> getMLista() {
        if(mLista == null)
        {
            mLista= new MutableLiveData<>();
        }
        return mLista;
    }

    public void CargarLista()
    {
        lstProducto.sort(new Comparator<Producto>() {
            @Override
            public int compare(Producto p1, Producto p2) {
                return p1.getDescricion().compareToIgnoreCase(p2.getDescricion());
            }
        });
        mLista.setValue(lstProducto);
    }
}