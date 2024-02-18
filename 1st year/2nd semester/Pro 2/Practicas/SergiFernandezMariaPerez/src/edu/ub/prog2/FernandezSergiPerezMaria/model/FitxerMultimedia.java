package edu.ub.prog2.FernandezSergiPerezMaria.model;

import java.io.File;
import java.util.Date;

public class FitxerMultimedia extends File{
    
    private String extensio; 
    private String descripcio; 
    
    public FitxerMultimedia(String cami) {
        super(cami);
        descripcio= "Descripcio"; 
    }
    public FitxerMultimedia(String cami, String descripcio) {
        super(cami);
        this.descripcio= descripcio; 
    }
    
    //amb els seguent metodes s'obte el nom, modificacio, cami, etc. (getters)
    public String getNom(){
         return this.getName();  
    }
    
    public String getExtensio(){
        int lastIndexOf = this.getNom().lastIndexOf(".");
    if (lastIndexOf == -1) {
        return ""; // empty extension
    }
    
    return this.getNom().substring(lastIndexOf+1);
    }
    
    public Date getUltimaModificacio(){
        long lastModified= this.lastModified(); 
        Date date = new Date(lastModified);
        
        return date;  
    }
    
    public String getCamiAbsolut(){
        return this.getAbsolutePath(); 
    }
    
    public String getDescripcio(){
        return descripcio; 
        }
    
    @Override
    //amb el seguent metode comparem els archius per veure si son el mateix.
    public boolean equals(Object obj){
        boolean aux= true; 
        if(obj==null){
            aux= false;
        }
        if(obj instanceof FitxerMultimedia){
            FitxerMultimedia fmult= (FitxerMultimedia)obj;
            aux= fmult.getDescripcio().equals(this.getDescripcio())&&
                    fmult.getCamiAbsolut().equals(this.getCamiAbsolut()); 
        }
        return aux; 
}
    @Override
    //ens mostra la informacio del arxiu.
    public String toString(){
        String x= "Nom: "+getNom()+"\nExtensió: "+ getExtensio()+ "\nRuta: "+getCamiAbsolut()+
                "\nUltima modificació: "+ getUltimaModificacio()+"\nDescripció: "+getDescripcio()+"\n"; 
        return x; 
    }
}