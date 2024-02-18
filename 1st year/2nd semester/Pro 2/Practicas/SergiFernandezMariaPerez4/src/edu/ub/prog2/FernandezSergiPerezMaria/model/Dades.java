/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.FernandezSergiPerezMaria.model;

import edu.ub.prog2.FernandezSergiPerezMaria.controlador.Reproductor;
import edu.ub.prog2.utils.AplicacioException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author sergi
 */
public class Dades implements Serializable{
    BibliotecaFitxersMultimedia fitxers; 
    ArrayList<AlbumFitxersMultimedia> albums;
    boolean reproduccioAleatoria;
    boolean reproduccioCiclica; 
    

    /**
     *
     */
    public Dades(){
        fitxers = new BibliotecaFitxersMultimedia(); 
        albums = new ArrayList<>(); 
        reproduccioAleatoria=false;
        reproduccioCiclica=false; 
       
        
        
        
    }

    /**
     *
     * @param path
     * @param nomvideo
     * @param codec
     * @param durada
     * @param alcada
     * @param amplada
     * @param fps
     * @param r
     * @throws AplicacioException
     */
    public void afegirVideo(String path, String nomvideo, String codec, float durada, int alcada, int amplada, float fps,Reproductor r) throws AplicacioException{
        Video video = new Video(path,nomvideo,codec,durada,alcada,amplada,fps,r);
        if(video.exists()){
            try{
                fitxers.addFitxer(video); 
            }
            catch(AplicacioException e){
                throw new AplicacioException("Aquest fitxer ja existeix");
            }
        }
        else{
            throw new AplicacioException("Aquest fitxer no existeix al disc");
        }
        
    }

    /**
     *
     * @param path
     * @param camiImatge
     * @param nomAudio
     * @param codec
     * @param durada
     * @param kbps
     * @throws AplicacioException
     */
    public void afegirAudio(String path, String camiImatge, String nomAudio, String codec, float durada, int kbps, Reproductor r) throws AplicacioException{ 
        
        Audio audio= new Audio(path,camiImatge,nomAudio,codec,durada,kbps,r);
        if(audio.exists()){
            try{
                fitxers.addFitxer(audio);
                }
            catch(AplicacioException e){
                throw new AplicacioException("Aquest fitxer ja existeix");
            }
        }
        else{
            throw new AplicacioException("Aquest fitxer no existeix al disc");
        }
        

    }

    /**
     *
     * @return Retorna una llista dels toString dels arxius d ela bibilioteca
     */
    public List<String> mostrarBibiloteca(){
        List <String> llista = new ArrayList<>();  
        for(Iterator<File> i= fitxers.getArray().iterator();i.hasNext();){
            File f= i.next();
            llista.add(f.getName());
        }
        return llista;
    }

    /**
     *
     * @param id
     * @throws AplicacioException
     */
    public void esborrarFitxer(int id) throws AplicacioException{
        if (id>fitxers.numFiles){
            throw new AplicacioException("Aquest index esta fora de rang");
        } 
        else{
            File f= fitxers.getCarpetaElement(id);
            for(Iterator<AlbumFitxersMultimedia> x= albums.iterator();x.hasNext();){
                AlbumFitxersMultimedia a= x.next();
                a.removeFitxer(f);
                    }
            fitxers.removeFitxer(f);
        }
    }

    /**
     *
     * @param string
     * @throws AplicacioException
     * @throws FileNotFoundException
     * @throws IOException
     */
    public void guardarDadesDisc(String str) throws AplicacioException{
        File fitxer = new File(str);
        try (FileOutputStream fout=new FileOutputStream(fitxer); 
            ObjectOutputStream obfout= new ObjectOutputStream(fout)){
            obfout.writeObject(this);
            obfout.flush();
        }
        
        catch(IOException ex){
                throw new AplicacioException ("IOException");
            }
        
        
    }

    /**
     *
     * @param fitxer
     * @param string
     * @return 
     * @throws AplicacioException
     
     */
    static public Dades carregarDadesDisc(String str) throws AplicacioException{
        File fitxer = new File(str);
        Dades dada; 
        if (fitxer.exists()){
            try (FileInputStream fin= new FileInputStream(fitxer);
                ObjectInputStream obfin= new ObjectInputStream(fin)){
                dada=(Dades)obfin.readObject();
                obfin.close();
                fin.close();
                return dada; 
            }
            catch(IOException e){
                throw new AplicacioException("IOExcept");
                
            }
            catch(ClassNotFoundException ex){
                throw new AplicacioException("ClassExcept");
            }
        }
        else{
            throw new AplicacioException("Error de fitxer");
        }
    }
   
    public void afegirAlbum(String string) throws AplicacioException {
        if (existeixAlbum(string)){
            throw new AplicacioException("Ja existeix aquest album");
        }
        else{
            AlbumFitxersMultimedia album= new AlbumFitxersMultimedia(string);
            albums.add(album);
        }
    }

    
    public List<String> mostrarLlistatAlbums() {
        List<String> noms;
        noms = new ArrayList<>(); 
        for(Iterator<AlbumFitxersMultimedia> i= albums.iterator();i.hasNext();){
            AlbumFitxersMultimedia f= i.next();
            noms.add(f.getTitol());
            }
        return noms; 
    }

    
    public void esborrarAlbum(String string) throws AplicacioException {
        int cont=0; 
        if (existeixAlbum(string)){
            for(Iterator<AlbumFitxersMultimedia> i= albums.iterator();i.hasNext();){
                AlbumFitxersMultimedia f= i.next();
                if (f.getTitol().equals(string)){
                    albums.remove(cont);
                    break;
                }
                cont+=1; 
            }
        }
        else{
            throw new AplicacioException("Aquest album no existeix");
        }
    }

   public boolean existeixAlbum(String string) {
        for(Iterator<AlbumFitxersMultimedia> i= albums.iterator();i.hasNext();){
            AlbumFitxersMultimedia f= i.next();
            if(f.getTitol().equals(string)){
                return true; 
            }
        }
        return false; 
    }

 
    public void afegirFitxer(String string, int i) throws AplicacioException {
            if (existeixAlbum(string)){
            for(Iterator<AlbumFitxersMultimedia> x= albums.iterator();x.hasNext();){
                AlbumFitxersMultimedia f= x.next();
                if (f.getTitol().equals(string)){
                    f.addFitxer(fitxers.getAt(i));
                }
            }
        }
            else{
            throw new AplicacioException("Aquest album no existeix");
        }
    }

   
    public List<String> mostrarAlbum(String string) throws AplicacioException {
        List<String> mostr = new ArrayList<>(); 
        if (existeixAlbum(string)){
            for(Iterator<AlbumFitxersMultimedia> x= albums.iterator();x.hasNext();){
                AlbumFitxersMultimedia al= x.next();
                if (al.getTitol().equals(string)){
                    for(Iterator<File> i= al.getArray().iterator();i.hasNext();){
                        File f= i.next(); 
                        mostr.add(f.getName());
                    }
                }
            }
            return mostr; 
        }
        else{
            throw new AplicacioException("Aquest album no existeix");
            }
            
    }
    public void esborrarFitxer(String string, int i) throws AplicacioException {
        if (existeixAlbum(string)){
            for(Iterator<AlbumFitxersMultimedia> x= albums.iterator();x.hasNext();){
                AlbumFitxersMultimedia f= x.next();
                    if (f.getTitol().equals(string)){
                        f.removeFitxer(f.getArray().get(i));
                    }
            }
        }
            else{
            throw new AplicacioException("Aquest album no existeix");
        }
    }
    public BibliotecaFitxersMultimedia getBiblioteca(){
        return fitxers; 
    }
    public AlbumFitxersMultimedia getAlbum(String str) throws AplicacioException{
        boolean trobat = false;
        for(Iterator<AlbumFitxersMultimedia> x = albums.iterator();x.hasNext();){
            AlbumFitxersMultimedia f= x.next(); 
            if (f.getTitol().equals(str)){
                return f; 
            }
        }
        throw new AplicacioException("No s'ha trobat l'album");
    }
    public void setReproductors(Reproductor reproductor){
        //Reproductor de Biblioteca
        for(Iterator<File> i= fitxers.getArray().iterator();i.hasNext();){
            File f= i.next();
            if (f instanceof FitxerReproduible){
                ((FitxerReproduible) f).setReproductor(reproductor);
            }
        }
        //Reproductor de Albums
        for(Iterator<AlbumFitxersMultimedia> x = albums.iterator();x.hasNext();){
            AlbumFitxersMultimedia al= x.next(); 
            for(Iterator<File> j= al.getArray().iterator();j.hasNext();){
                File fi= j.next();
                if (fi instanceof FitxerReproduible){
                    ((FitxerReproduible) fi).setReproductor(reproductor);
                }
            }
        }
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
    public boolean getReproduccioAleatoria(){
        return reproduccioAleatoria; 
    }
    public boolean getReproduccioCiclica(){
        return reproduccioCiclica; 
    }
}
    
