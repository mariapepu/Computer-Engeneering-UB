/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.FernandezSergiPerezMaria.model;

import edu.ub.prog2.FernandezSergiPerezMaria.controlador.Reproductor;
import edu.ub.prog2.utils.AplicacioException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sergi
 */
public class Video extends FitxerReproduible {
    //atributos

    int alcada, amplada;
    float fps;
    //constructor

    /**
     *
     * @param cami
     * @param nom
     * @param codec
     * @param durada
     * @param alcada
     * @param amplada
     * @param fps
     * @param r
     */
    public Video(String cami, String nom, String codec, float durada, int alcada, int amplada, float fps, Reproductor r) {
        super(cami, nom, codec, durada, r);
        this.alcada = alcada;
        this.amplada = amplada;
        this.fps = fps;
        System.out.println("Video");
    }
    //metodos

    /**
     *
     * @throws edu.ub.prog2.utils.AplicacioException
     */
    @Override
    public void reproduir() throws AplicacioException {
        try{
        r.reprodueix(this);
               }
        catch(AplicacioException e){
            throw e; 
        }
    }
}
