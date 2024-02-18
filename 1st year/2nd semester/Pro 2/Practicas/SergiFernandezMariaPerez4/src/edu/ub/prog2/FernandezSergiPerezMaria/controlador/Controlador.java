/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.ub.prog2.FernandezSergiPerezMaria.controlador;
import edu.ub.prog2.FernandezSergiPerezMaria.model.CarpetaFitxers;
import edu.ub.prog2.utils.AplicacioException;
import edu.ub.prog2.FernandezSergiPerezMaria.model.Dades;
import java.io.IOException;
import java.util.List;
import edu.ub.prog2.utils.InControlador;
import java.io.File;
        
/**
 *
 * @author sergi
 */
public class Controlador implements InControlador {
    Dades dades; 
    protected transient  Reproductor r;
    EscoltadorReproduccio e;
    
    /**
     *
     */
    public Controlador(){
        dades= new Dades(); 
        e= new EscoltadorReproduccio(); 
        r= new Reproductor(e); 
        
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
     * @throws AplicacioException
     */
    public void afegirVideo(String path, String nomvideo, String codec, float durada, int alcada, int amplada, float fps) throws AplicacioException{
        try{
            dades.afegirVideo(path, nomvideo, codec, durada,alcada, amplada,fps,r);
        }
        catch(AplicacioException e){
            throw e; 
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
    public void afegirAudio(String path, String camiImatge, String nomAudio, String codec, float durada, int kbps) throws AplicacioException{
        try{
            dades.afegirAudio(path, camiImatge, nomAudio, codec, durada, kbps,r);
        }
        catch(AplicacioException e){
            throw e; 
        }
    }

    /**
     *
     * @return Llista de toString de la biblioteca
     */
    @Override
    public List<String> mostrarBiblioteca(){
       
        return dades.mostrarBibiloteca();
        
    }

    /**
     *
     * @param id
     * @throws AplicacioException
     */
    @Override
    public void esborrarFitxer(int id) throws AplicacioException{
        try{
            dades.esborrarFitxer(id);
        }
        catch (AplicacioException e){
            throw e;
        }
       
    }

    /**
     *
     * @param string
     * @throws AplicacioException
     * @throws IOException
     */
    @Override
    public void guardarDadesDisc(String string) throws AplicacioException{
        File file = new File(string);
        try{
            dades.guardarDadesDisc(string);
        }
        catch(AplicacioException ex){
            throw new AplicacioException("Adios"); 
        }
      
    }

    /**
     *
     * @param string
     * @throws AplicacioException
     */
    @Override
    public void carregarDadesDisc(String string) throws AplicacioException{
        File file = new File(string); 
        dades= Dades.carregarDadesDisc(string);
        dades.setReproductors(r);
        
    }

    @Override
    public void afegirAlbum(String string) throws AplicacioException {
        try{
            dades.afegirAlbum(string);
        }
        catch(AplicacioException e){
            throw e;
        }
        
    }

    @Override
    public List<String> mostrarLlistatAlbums() {
        return dades.mostrarLlistatAlbums();
    }

    @Override
    public void esborrarAlbum(String string) throws AplicacioException {
       try{
            dades.esborrarAlbum(string);
        }
        catch(AplicacioException e){
            throw e; 
        }
    }

    @Override
    public boolean existeixAlbum(String string) {
       return dades.existeixAlbum(string);
    }

    @Override
    public void afegirFitxer(String string, int i) throws AplicacioException {
        try{
            dades.afegirFitxer(string,i);
        }
        catch(AplicacioException e){
            throw e; 
        }
    }

    @Override
    public List<String> mostrarAlbum(String string) throws AplicacioException {
        try{
            return dades.mostrarAlbum(string);
        }
        catch(AplicacioException e){
            throw e; 
        }
    }

    @Override
    public void esborrarFitxer(String string, int i) throws AplicacioException {
       try{
            dades.esborrarFitxer(string,i);
        }
        catch(AplicacioException e){
            throw e; 
        }
    }
   
    
     @Override
    public void reproduirFitxer(int i) throws AplicacioException {
        CarpetaFitxers x= new CarpetaFitxers();
        x.addFitxer(dades.getBiblioteca().getAt(i));
        e.iniciarReproduccio(x, dades.getReproduccioCiclica(),dades.getReproduccioAleatoria());
    }
    @Override
    public void reproduirCarpeta() throws AplicacioException {
        e.iniciarReproduccio(dades.getBiblioteca(),dades.getReproduccioCiclica(),dades.getReproduccioAleatoria());
    }

    @Override
    public void reproduirCarpeta(String string) throws AplicacioException {
        e.iniciarReproduccio(dades.getAlbum(string),dades.getReproduccioCiclica(),dades.getReproduccioAleatoria());
    }

    @Override
    public void obrirFinestraReproductor() {
        this.r.open();
    }

    @Override
    public void tancarFinestraReproductor() throws AplicacioException {
        this.r.close();
    }
    @Override
    public void reemprenReproduccio() throws AplicacioException {
        this.r.resume();
    }

    @Override
    public void pausaReproduccio() throws AplicacioException {
        this.r.pause();
    }

    @Override
    public void aturaReproduccio() throws AplicacioException {
        this.r.stop();
    }

    @Override
    public void saltaReproduccio() throws AplicacioException {
        e.next();
    }
    public void setReproduccioAleatoria(){
        e.setReproduccioAleatoria();
        dades.setReproduccioAleatoria();
    }
    public void setReproduccioCiclica(){
        e.setReproduccioCiclica(); 
        dades.setReproduccioCiclica();
    }
    public Dades getDades(){
        return dades; 
    }
}


  