/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.FernandezSergiPerezMaria.model;

import java.io.File;
import edu.ub.prog2.utils.AplicacioException;


/**
 *
 * @author sergi
 */
public class BibliotecaFitxersMultimedia extends CarpetaFitxers {
    
    /**
     *
     */
    public BibliotecaFitxersMultimedia(){
      
    }
    
    /**
     *
     * @param fitxer
     * @throws AplicacioException
     */
    public void addFitxer(File fitxer) throws AplicacioException{
    //Afegir un nou fitxer a la carpeta. La carpeta permet fitxers duplicats.
    boolean hatrobat=false; 
    int c=0; 
        while(c<numFiles&&hatrobat==false){
           if (carpeta.get(c).equals(fitxer)){
               hatrobat=true; 
               }
           c+=1; 
        }
        if (hatrobat==false){
            carpeta.add(numFiles, fitxer);
            numFiles++;
        }
        else{
            throw new AplicacioException("Ja existeix aquest fitxer");
            
        }
    }
}
