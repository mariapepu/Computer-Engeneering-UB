/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.FernandezSergiPerezMaria.model;

import edu.ub.prog2.FernandezSergiPerezMaria.controlador.Reproductor;
import edu.ub.prog2.utils.AplicacioException;


/**
 *
 * @author sergi
 */
    abstract public class FitxerReproduible extends FitxerMultimedia{
	
	//atributs
        protected transient Reproductor r; 
	protected float durada;
	protected String codec;
	//costructor
	protected FitxerReproduible(String cami, String nom, String codec, float durada, Reproductor r){
	super(cami, nom);
	this.codec=codec;                                                                 
	this.durada=durada;
	this.r=r;
	}	
	//mÃ¨todes

    /**
     *
     * @throws AplicacioException
     */
	public abstract void reproduir() throws AplicacioException;
        public void setReproductor(Reproductor reproductor){
            r= reproductor; 
        }
}


