package com.example.coinnote.model;

public class Calendario {

    private Fecha fechas[];

    public Calendario(){

    }

    public Calendario(Fecha[] fechas) {
        this.fechas = fechas;
    }

    public Fecha[] getFechas() {
        return fechas;
    }

    public void setFechas(Fecha[] fechas) {
        this.fechas = fechas;
    }
}
