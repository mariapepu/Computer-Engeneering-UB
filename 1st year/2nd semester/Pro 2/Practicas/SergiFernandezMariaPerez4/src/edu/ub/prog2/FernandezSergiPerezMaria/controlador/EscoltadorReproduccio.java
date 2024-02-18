/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.FernandezSergiPerezMaria.controlador;

import edu.ub.prog2.FernandezSergiPerezMaria.model.CarpetaFitxers;
import edu.ub.prog2.FernandezSergiPerezMaria.model.FitxerReproduible;
import edu.ub.prog2.utils.AplicacioException;

import edu.ub.prog2.utils.EscoltadorReproduccioBasic;
import java.io.File;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author sergi
 */
public class EscoltadorReproduccio extends EscoltadorReproduccioBasic {
    private CarpetaFitxers llistaReproduint;
    private boolean[] llistaCtrl;
    private boolean reproduccioCiclica, reproduccioAleatoria;
    int posicio; 
     
    
    public EscoltadorReproduccio() {

    }

    @Override
    protected void onEndFile() {
        next();
    }

    @Override
    protected void next(){
        if (hasNext()){
            //Si la reproduccio NO Aleatoria+ Si Ciclica suma 1 a posicio fins trobar el next.
            if(!reproduccioAleatoria&&reproduccioCiclica){
                boolean trobat = false; 
                do{
                    posicio= (posicio+1)%llistaCtrl.length;
                    if (llistaCtrl[posicio]==false){
                        try{
                            reproduccio(llistaReproduint.getAt(posicio));
                            llistaCtrl[posicio]=true; 
                            trobat= true; 
                        }
                        catch(AplicacioException e){
                            llistaCtrl[posicio]=true; 
                            next(); 
                        }
                    } 
                }while (trobat==false);
            }
            //Si la reproduccio es aleatoria repeteix el Random fins trobat el next. 
            else if(reproduccioAleatoria){
                Random r = new Random(); 
                boolean trobat= false; 
                do{
                    posicio=r.nextInt(llistaCtrl.length); 
                    if(llistaCtrl[posicio]==false){
                        try{
                            reproduccio(llistaReproduint.getAt(posicio));
                            llistaCtrl[posicio]=true; 
                            trobat= true; 
                        }
                        catch(AplicacioException e){
                            llistaCtrl[posicio]=true; 
                            next(); 
                        }
                    }
                }while(trobat==false);
                    
            }
            //Si la llista no es aleatoria i no ciclica suma 1 a a posicio fins trobar 
            //el next(en cas de que tingui sino arribará al final i s'aturará
            else{
                boolean trobat= false; 
                do{
                    posicio=(posicio+1)%llistaCtrl.length;
                    if(llistaCtrl[posicio]==false){
                        try{
                            reproduccio(llistaReproduint.getAt(posicio));
                            llistaCtrl[posicio]=true; 
                            trobat= true; 
                        }
                        catch(AplicacioException e){
                            llistaCtrl[posicio]=true; 
                            next(); 
                        }
                    }
                }while(trobat==false&&posicio==llistaCtrl.length-1); 
            }
        }
        else{
            //Si no te next pero es ciclica torna els arxiu a false.
            if(reproduccioCiclica&&reproduccioAleatoria){
                Random r= new Random();
                for(int i =0;i<llistaCtrl.length;i++){
                    llistaCtrl[i]=false; 
                }
               
                posicio=r.nextInt(llistaCtrl.length);
                try{
                    llistaCtrl[posicio]=true; 
                    reproduccio(llistaReproduint.getAt(posicio));
                }
                catch(AplicacioException e){
                    llistaCtrl[posicio]=true; 
                    next(); 
                }
            }
            //Si no es aleatori al fer-ho començara per l'arxiu de la posicio 0. 
            else if(reproduccioCiclica&&!reproduccioAleatoria){
                for (int i =0;i<llistaCtrl.length;i++){
                    llistaCtrl[i]=false; 
                }
                posicio= 0;
                try{
                    reproduccio(llistaReproduint.getAt(posicio));
                }
                catch(AplicacioException e){
                    llistaCtrl[posicio]=true; 
                    next(); 
                }
            }
            //Si no es compleix cap cas aleshores has arribat al fi de la carpeta.
            else{
                System.out.println("Fi de la carpeta");
            }
            
        }
    }
    
    

    @Override
    protected boolean hasNext() {
        boolean lliure = false;
        //Cas Especific on ==Ultim Fitxer sense reproduccioAleat/Ciclica. 
        if (!reproduccioAleatoria&&!reproduccioCiclica&&posicio==llistaCtrl.length-1){
                return false;
            }
        //Si hi ha un arxiu a false pot haber un next
        for (int i = 0; i < llistaCtrl.length; i++) {
            if (llistaCtrl[i] == false) {
                lliure = true;
            }
        }
        return lliure; 
    }
    public void reproduccio(File f) throws AplicacioException{
        FitxerReproduible a = (FitxerReproduible)f; 
        a.reproduir();
       
    }
    public void iniciarReproduccio(CarpetaFitxers llistaReproduint, boolean reproduccioCiclica,boolean reproduccioAleatoria){
        this.llistaReproduint=llistaReproduint; 
        this.reproduccioAleatoria= reproduccioAleatoria; 
        this.reproduccioCiclica= reproduccioCiclica; 
        posicio=-1; 
        llistaCtrl = new boolean[llistaReproduint.getSize()];
        for (int i =0; i<llistaCtrl.length;i++){
            llistaCtrl[i]=false; 
            
        }
        next(); 
    }
    public void setReproduccioAleatoria(){
        if (reproduccioAleatoria==true){
            reproduccioAleatoria=false;
        }
        else{
            reproduccioAleatoria=true; 
        }
    }
    public void setReproduccioCiclica(){
        if (reproduccioCiclica==true){
            reproduccioCiclica=false;
        }
        else{
            reproduccioCiclica=true; 
        }
    }

    public void setReproduccioCiclica(boolean rep){
       reproduccioCiclica=rep;  
    }
    public void setReproduccioAleatoria(boolean rep){
       reproduccioAleatoria=rep;  
    }
    public boolean getReproduccioAleatoria(){
        return reproduccioAleatoria; 
    }
    public boolean getReproduccioCiclica(){
        return reproduccioCiclica; 
    }
}
    