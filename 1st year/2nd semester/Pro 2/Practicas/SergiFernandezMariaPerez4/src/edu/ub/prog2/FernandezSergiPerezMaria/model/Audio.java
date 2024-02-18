/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.FernandezSergiPerezMaria.model;

import edu.ub.prog2.FernandezSergiPerezMaria.controlador.Reproductor;
import edu.ub.prog2.utils.AplicacioException;
import java.io.File;


/**
 *
 * @author sergi
 */
public class Audio extends FitxerReproduible{
	//atributos
	float kbps;	
        String camiImatge;
        

	//constructor

    /**
     *
     * @param cami
     * @param camiImatge
     * @param nom
     * @param codec
     * @param durada
     * @param kbps
     * @param r
     */
	public Audio(String cami, String camiImatge, String nom, String codec, float durada, float kbps, Reproductor r){
		super(cami, nom, codec, durada, r);
		this.camiImatge=camiImatge;
		this.kbps=kbps;
                System.out.println("Audio");
	}
	//metodos

    /**
     *
     * @throws edu.ub.prog2.utils.AplicacioException
     */
    @Override
    public void reproduir() throws AplicacioException{
        File imtg= new File(camiImatge);
       
            r.reprodueix(this, imtg);
         
        
    }
}

