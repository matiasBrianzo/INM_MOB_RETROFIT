package com.example.mov_tp03.Modelo;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class Producto  {
    private String codigo;
    private String descricion;
    private double precio;

    public Producto(String codigo,String descricion,double precio ) {
        this.precio = precio;
        this.descricion = descricion;
        this.codigo = codigo;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescricion() {
        return descricion;
    }

    public void setDescricion(String descricion) {
        this.descricion = descricion;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public boolean equals(@Nullable Object obj) {
       if(!(obj instanceof Producto))
       { return false;}

       Producto p = (Producto)obj;
       if (p.codigo.equals(this.codigo))
       {
           return true;
       }
        return false;
    }

    @Override
    public int hashCode() {
        return codigo.hashCode();
    }
}
