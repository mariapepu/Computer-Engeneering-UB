package edu.ub.prog2.FernandezSergiPerezMaria.model; 

import java.io.File;
import java.util.ArrayList;
import edu.ub.prog2.utils.AplicacioException;
import java.io.Serializable;
import edu.ub.prog2.utils.InFileFolder;

/**
 *
 * @author sergi
 */
public class CarpetaFitxers implements inFileFolder, Serializable {
    final static int MAX_SIZE= 100; 

    /**
     *
     */
    protected int numFiles; //numero ficheros

    /**
     *
     */
    protected ArrayList<File> carpeta;
    
    /**
     *
     */
    public CarpetaFitxers(){
       //contstructor per defecte
        numFiles=0; 
        carpeta= new ArrayList<>();
    }
    
    /**
     *
     * @return Tamany carpeta
     */
    public int getSize(){
    //Retornar el nombre dâ€™elements que hi ha a la carpeta.
        return numFiles;
    }
    
    /**
     *
     * @param fitxer
     * @throws AplicacioException
     */
    public void addFitxer(File fitxer) throws AplicacioException{
    //Afegir un nou fitxer a la carpeta. La carpeta permet fitxers duplicats.
        if (isFull()==true){
            throw new AplicacioException("Está ple");
        }
     
        else{
            carpeta.add(numFiles, fitxer);
            numFiles++;
        }
        
    }
    
    /**
     *
     * @param fitxer
     */
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
        
    /**
     *
     * @param position
     * @return Archiu a la posicio position
     */
    public File getAt(int position){
    //Retornar el fitxer a la posiciÃ³ indicada de la carpeta.
        return carpeta.get(position);
    }
    
    /**
     *
     */
    public void clear(){
    //Eliminar tots els elements de la carpeta.
        for (int i=0; i<numFiles;i++){
            carpeta.get(i).delete();
        }
        numFiles=0;
    }
    
    /**
     *
     * @return True si está plena False si no ho está
     */
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

    /**
     *
     * @param index
     * @return Element de la carpeta al index
     */
    public File getCarpetaElement(int index){
      File archiu= carpeta.get(index); 
      return archiu; 
    }

    /**
     *
     * @return Retorna la carpeta
     */
    public ArrayList<File> getArray(){
        return carpeta; 
    }

    /**
     *
     * @return Variable numFiles
     */
    public int getNumFiles(){
        return numFiles; 
    }
    
}