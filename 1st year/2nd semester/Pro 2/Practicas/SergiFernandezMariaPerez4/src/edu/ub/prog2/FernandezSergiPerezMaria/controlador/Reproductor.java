/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.FernandezSergiPerezMaria.controlador;

import edu.ub.prog2.FernandezSergiPerezMaria.model.Audio;
import edu.ub.prog2.utils.ReproductorBasic;
import java.io.Serializable;
import edu.ub.prog2.FernandezSergiPerezMaria.model.FitxerReproduible;
import edu.ub.prog2.utils.AplicacioException;
import java.io.File;

/**
 *
 * @author sergi
 */
public class Reproductor extends ReproductorBasic implements Serializable{

    /**
     *
     * @param controlador
     * 
     */
    public Reproductor(EscoltadorReproduccio controlador){
        super("C:\\Program Files\\VideoLAN\\VLC",controlador);
    }
    public void reprodueix(FitxerReproduible fr) throws AplicacioException{
        play(fr);
    }
    public void reprodueix(Audio audio, File fitxerImatge) throws AplicacioException{
        play(audio, fitxerImatge);
    }
}
