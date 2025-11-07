package com.example.mov_tp03.Modelo;

import java.io.Serializable;

public class Contrato implements Serializable {

    private int idContrato;
    private int idInquilino;
    private Inquilino inquilino;
    private int idInmueble;
    private Inmueble inmueble;
    private String fechaInicio;
    private String fechaFinalizacion;
    private double montoAlquiler;
    private boolean estado;

    public Contrato() {
    }

    public Contrato(int idContrato, int idInquilino, Inquilino inquilino, int idInmueble, Inmueble inmueble, String fechaInicio, String fechaFinalizacion, double montoAlquiler, boolean estado) {
        this.idContrato = idContrato;
        this.idInquilino = idInquilino;
        this.inquilino = inquilino;
        this.idInmueble = idInmueble;
        this.inmueble = inmueble;
        this.fechaInicio = fechaInicio;
        this.fechaFinalizacion = fechaFinalizacion;
        this.montoAlquiler = montoAlquiler;
        this.estado = estado;
    }

    public int getIdContrato() {
        return idContrato;
    }

    public void setIdContrato(int idContrato) {
        this.idContrato = idContrato;
    }

    public int getIdInquilino() {
        return idInquilino;
    }

    public void setIdInquilino(int idInquilino) {
        this.idInquilino = idInquilino;
    }

    public Inquilino getInquilino() {
        return inquilino;
    }

    public void setInquilino(Inquilino inquilino) {
        this.inquilino = inquilino;
    }

    public int getIdInmueble() {
        return idInmueble;
    }

    public void setIdInmueble(int idInmueble) {
        this.idInmueble = idInmueble;
    }

    public Inmueble getInmueble() {
        return inmueble;
    }

    public void setInmueble(Inmueble inmueble) {
        this.inmueble = inmueble;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public void setFechaFinalizacion(String fechaFinalizacion) {
        this.fechaFinalizacion = fechaFinalizacion;
    }

    public double getMontoAlquiler() {
        return montoAlquiler;
    }

    public void setMontoAlquiler(double montoAlquiler) {
        this.montoAlquiler = montoAlquiler;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }



}

