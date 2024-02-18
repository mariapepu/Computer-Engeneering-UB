package com.example.coinnote.model;

public class Gasto {
    private String concepto;
    private String coste;
    private Imagen imagen;
    private String fecha;

    public Gasto(){}
    public Gasto(String concepto, String coste, String fecha) {
        this.concepto = concepto;
        this.coste = coste;
        this.imagen = null;
        this.fecha = fecha;
    }

    public String getConcepto() {
        return concepto;
    }

    public void setConcepto(String concepto) {
        this.concepto = concepto;
    }

    public String getCoste() {
        return coste;
    }

    public void setCoste(String coste) {
        this.coste = coste;
    }

    public Imagen getImagen() {
        return imagen;
    }

    public void setImagen(Imagen imagen) {
        this.imagen = imagen;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
