/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.FernandezSergiPerezMaria.model;

import java.io.Serializable;

/**
 *
 * @author sergi
 */
public class AlbumFitxersMultimedia extends CarpetaFitxers{
    private String titol;
    int N; 
    public AlbumFitxersMultimedia(String tit){
        titol= tit; 
        N = 10; 
    }
    public AlbumFitxersMultimedia(String tit, int N){
        this.N= N;
        titol = tit; 
    }
    @Override
    public boolean isFull(){
        if (numFiles>=N){
            return true; 
        }
        else{
            return false; 
        }
    }
    public String getTitol(){
        return titol;
    }
    
    
}
