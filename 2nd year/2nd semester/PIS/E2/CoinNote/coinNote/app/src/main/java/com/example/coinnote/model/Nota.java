package com.example.coinnote.model;


import java.util.List;


public class Nota {
    private String categoria;
    private List<Gasto> gastoList;
    private List<Usuario> usuarioList;
    private String userId;
    private Fecha fecha;
    private int gastoTotal;
    private String colorNota;

    public Nota(String tipoDeCategoria, int totalGasto, String color,String userId){
        this.categoria=tipoDeCategoria;
        this.gastoTotal=totalGasto;
        this.colorNota=color;
        this.userId=userId;

    }
    public  Nota(){}

    public String getColorNota() {
        return colorNota;
    }

    public void setColorNota(String colorNota) {
        this.colorNota = colorNota;
    }

    public int getGastoTotal() {
        return gastoTotal;
    }

    public void setGastoTotal(int gastoTotal) {
        this.gastoTotal = gastoTotal;
    }

    public Nota(String categoria, List<Gasto> gastoList, List<Usuario> usuarioList, Fecha fecha) {
        this.categoria = categoria;
        this.gastoList = gastoList;
        this.usuarioList = usuarioList;
        this.fecha = fecha;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<Gasto> getGastoList() {
        return gastoList;
    }

    public void setGastoList(List<Gasto> gastoList) {
        this.gastoList = gastoList;
    }

    public List<Usuario> getUsuarioList() {
        return usuarioList;
    }

    public void setUsuarioList(List<Usuario> usuarioList) {
        this.usuarioList = usuarioList;
    }

    public Fecha Fecha() {
        return fecha;
    }

    public void setFecha(Fecha fecha) {
        this.fecha = fecha;
    }
}

