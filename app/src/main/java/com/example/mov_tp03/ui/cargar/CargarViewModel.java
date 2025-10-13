package com.example.mov_tp03.ui.cargar;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import  static  com.example.mov_tp03.MainActivity.*;

import android.app.Application;

import com.example.mov_tp03.Modelo.Producto;
import com.example.mov_tp03.databinding.FragmentCargarBinding;

public class CargarViewModel extends AndroidViewModel {

    private  MutableLiveData<String> MsjError;
    private  MutableLiveData<String> MsjExito;
    private FragmentCargarBinding binding;
    private  StringBuilder sb;

    public CargarViewModel(@NonNull Application application) {
        super(application);
    }

    public LiveData<String> getMsjError() {
    if(MsjError == null)
    {
        MsjError= new MutableLiveData<String>();
    }
        return MsjError;
    }
    public LiveData<String> getMsjExito() {
        if(MsjExito == null)
        {
            MsjExito= new MutableLiveData<String>();
        }
        return MsjExito;
    }

    public void AddProducto(String pCodigo, String pDesc, String pPrec)
    {
        boolean validar = LogicaValidacion(pCodigo,pDesc,pPrec);

       if(validar){
           double pre= Double.parseDouble(pPrec);
           lstProducto.add(new Producto(pCodigo,pDesc,pre));
           MsjExito.setValue("CARGA EXITOSA!!");
       }

    }
    private boolean LogicaValidacion(String pCodigo, String pDesc, String pPrec)
    {
       boolean validar= true;
       sb= new StringBuilder();

        if(ValidoCampos(pCodigo,pDesc,pPrec)){
            MsjError.setValue(sb.toString());
            return false;
        }
        if (validoDuplicado(pCodigo,pDesc,pPrec))
        {
            MsjError.setValue(sb.toString());
           return false;
        }

        return validar;
    }

    private boolean ValidoCampos(String pCodigo, String pDesc, String pPrec) {
        boolean validar=false;

        if (pCodigo.isBlank())
        {
            sb.append("- CODIGO: Valor erróneo. ");
            validar=true;
        }
        if (pDesc.isBlank())
        {
            sb.append("- DESCRIPCIÓN: Valor erróneo. ");
            validar=true;
        }
        if (pPrec.isBlank())
        {
            sb.append("- PRECIO: Valor erróneo. ");
            validar=true;
        }
        return validar;
    }

    private boolean validoDuplicado(String pCodigo, String pDesc, String pPrec) {
        boolean validar=false;
        try{
            double pre= Double.parseDouble(pPrec);
            validar= lstProducto.contains(new Producto(pCodigo,pDesc,pre));

        } catch (NumberFormatException e) {
            sb.append("Solo se permiten números en este campo");
            return true;
        }
        if(validar){
            sb.append("CÓDIGO duplicado");
            return true;
        }
        return validar;
    }
}