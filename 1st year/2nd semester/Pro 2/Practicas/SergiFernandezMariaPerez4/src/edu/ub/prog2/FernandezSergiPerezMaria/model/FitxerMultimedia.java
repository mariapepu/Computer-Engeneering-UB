package edu.ub.prog2.FernandezSergiPerezMaria.model;

import java.io.File;
import java.util.Date;

/**
 *
 * @author sergi
 */
public class FitxerMultimedia extends File{
    
    private String extensio; 
    private String descripcio; 
    
    /**
     *
     * @param cami
     */
    public FitxerMultimedia(String cami) {
        super(cami);
        descripcio= "Descripcio"; 
    }

    /**
     *
     * @param cami
     * @param descripcio
     */
    public FitxerMultimedia(String cami, String descripcio) {
        super(cami);
        this.descripcio= descripcio; 
    }
    
    //amb els seguent metodes s'obte el nom, modificacio, cami, etc. (getters)

    /**
     *
     * @return Retorna el nom de l'arxiu
     */
    public String getNom(){
         String x= this.getName(); 
         int lastIndexOf= x.lastIndexOf(".");
         if (lastIndexOf==-1){
             return x.substring(0);
         }
         String nom= x.substring(0, lastIndexOf);
         return nom;
    }
    
    /**
     *
     * @return Retorna l'extensió del arxiu
     */
    public String getExtensio(){
        int lastIndexOf = this.getName().lastIndexOf(".");
    if (lastIndexOf == -1) {
        return ""; // empty extension
    }
    
    return this.getName().substring(lastIndexOf+1);
    }
    
    /**
     *
     * @return Retorna la ultima modificacio del arxiu
     */
    public Date getUltimaModificacio(){
        long lastModified= this.lastModified(); 
        Date date = new Date(lastModified);
        
        return date;  
    }
    
    /**
     *
     * @return Retorna el cami del archiu
     */
    public String getCamiAbsolut(){
        return this.getAbsolutePath(); 
    }
    
    /**
     *
     * @return Retorna la descripció del archiu
     */
    public String getDescripcio(){
        return descripcio; 
        }
    
    @Override
    //amb el seguent metode comparem els archius per veure si son el mateix.
    public boolean equals(Object obj){
        boolean aux= false; 
        if(obj==null){
            aux= false;
        }
        if(obj instanceof FitxerMultimedia){
            FitxerMultimedia fmult= (FitxerMultimedia)obj;
            aux = fmult.getCamiAbsolut().equals(this.getCamiAbsolut()); 
        }
        return aux; 
    }
    @Override
    //ens mostra la informacio del arxiu com a un string.
    public String toString(){
        String x= "Nom: "+getNom()+"\nExtensió: "+ getExtensio()+ "\nRuta: "+getCamiAbsolut()+
                "\nUltima modificació: "+ getUltimaModificacio()+"\nDescripció: "+getDescripcio()+"\n"; 
        return x; 
    }
}