package edu.ub.prog2.FernandezSergiPerezMaria.model; 

import java.io.File;
import java.util.ArrayList;
import edu.ub.prog2.utils.AplicacioException;


public class CarpetaFitxers {
    final static int MAX_SIZE= 100; 
    protected int numFiles; //numero ficheros
    protected ArrayList<File> carpeta;
    
    public CarpetaFitxers(){
       //contstructor per defecte
        numFiles=0; 
        carpeta= new ArrayList<>();
    }
    
    public int getSize(){
    //Retornar el nombre dâ€™elements que hi ha a la carpeta.
        return numFiles;
    }
    
    public void addFitxer(File fitxer) throws AplicacioException{
    //Afegir un nou fitxer a la carpeta. La carpeta permet fitxers duplicats.
        if (isFull()==true){
            System.out.println("Carpeta plena");
        }
     
        else{
            carpeta.add(numFiles, fitxer);
            numFiles++;
        }
        
    }
    
    public void removeFitxer(File fitxer){
    //Eliminar un fitxer de la carpeta si coincideix amb el fitxer passat.
        for (int i=0; i<numFiles; i++){
            File x= carpeta.get(i);
            if (x.equals(fitxer)){
                carpeta.remove(fitxer); 
                numFiles--; 
            }
        }
    }
        
    
    
    public File getAt(int position){
    //Retornar el fitxer a la posiciÃ³ indicada de la carpeta.
        return carpeta.get(position);
    }
    
    public void clear(){
    //Eliminar tots els elements de la carpeta.
        for (int i=0; i<numFiles;i++){
            carpeta.get(i).delete();
        }
        numFiles=0;
    }
    
    public boolean isFull(){
    //Indicar si la carpeta estÃ  plena o per el contrari podem afegir mÃ©s elements.
        if (numFiles>=MAX_SIZE){
            return true;
        }
        
        else{
            return false;
        }
    }
    @Override
    public String toString(){
       //ens mostra informacio del arxiu
        String x=""; 
        for (int i =0; i<numFiles;i++){  
            x+=i+1+". "+ carpeta.get(i).toString(); 
            }
        return x; 
   
    }
    public File getCarpetaElement(int index){
      File archiu= carpeta.get(index); 
      return archiu; 
    }
    
}