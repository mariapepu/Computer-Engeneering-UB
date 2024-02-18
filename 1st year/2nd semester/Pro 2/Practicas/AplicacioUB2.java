package edu.ub.prog2.FernandezSergiPerezMaria.vista;

import edu.ub.prog2.utils.Menu;
import java.util.Scanner;
import edu.ub.prog2.FernandezSergiPerezMaria.model.CarpetaFitxers;
import edu.ub.prog2.FernandezSergiPerezMaria.model.FitxerMultimedia;
import java.io.File;

/**
 *
 * @author sergi
 */
public class AplicacioUB2 {
    private static enum OpcionsMenuPrincipal {MENU_PRINCIPAL_OPCIO1,MENU_PRINCIPAL_OPCIO2,MENU_PRINCIPAL_OPCIO3,MENU_PRINCIPAL_SUBMENU1,MENU_PRINCIPAL_OPCIO4,MENU_PRINCIPAL_SORTIR};
    static private enum OpcionsSubmenu1 {MENU_S1_OPCIO1,MENU_S1_OPCIO2,MENU_S1_OPCIO3,MENU_S1_SORTIR};
    static private enum OpcionsSubmenu2 {MENU_S2_OPCIO1,MENU_S2_OPCIO2,MENU_S2_SORTIR};
    static private enum OpcionsSubmenu3 {MENU_S3_OPCIO1,MENU_S3_OPCIO2,MENU_S3_OPCIO3,MENU_S3_OPCIO4,MENU_S3_SORTIR};
    static private enum OpcionsSubmenu4 {MENU_S4_OPCIO1,MENU_S4_OPCIO2,MENU_S4_OPCIO3,MENU_S4_SORTIR};
    static private enum OpcionsSubmenu5 {MENU_S5_OPCIO1,MENU_S5_OPCIO2,MENU_S5_OPCIO3,MENU_S5_OPCIO4,MENU_S5_OPCIO5,MENU_S5_OPCIO6,MENU_S5_SORTIR};    
    static private enum OpcionsSubmenu6 {MENU_S6_OPCIO1,MENU_S6_OPCIO2,MENU_S6_OPCIO3,MENU_S6_OPCIO4,MENU_S6_SORTIR};
    // Declarem descripcions personalitzades per a les opcions del menÃƒÂº principal
    private String[] descMenuPrincipal={"Gestio Biblioteca",
                                               "Gestió àlbums",
                                               "Control reproducció/visió",
                                               "Guardar Dades",
                                               "Recuperar dades",
                                               "Sortir"};
    
    static private String[] descMenu1={"Afegir fitxer multimedia",
                                                "Mostrar Biblioteca",
                                                "Eliminar fitxer multimedia",
                                                "Menu Anterior"};
    
    static private String[] descMenu2={"Afegir video",
                                                "Afegir audio",
                                                "Menu Anterior"};
   
    static private String[] descMenu3={"Afegir àlbum",                                              
                                                "Mostrar àlbum",
                                                "Eliminar àlbum",
                                                "Gestionar àlbum",
                                                "Menu Anterior"};
    
    static private String[] descMenu4={"Afegir fitxer multimèdia",                                              
                                               "Mostrar àlbum",
                                               "Eliminar fitxer multimèdia",
                                               "Menu Anterior"};
   
    static private String[] descMenu5={"Reproduir un fitxer reproduible",                                              
                                                "Reproduir tota la biblioteca",
                                                "Reproduir un àlbum",
                                                "Reproducció continua ON/OFF",
                                                "Reproducció aleatòria ON/OFF",
                                                "Gestió de reproducció en curs",
                                                "Menu Anterior"};
    
    static private String[] descMenu6={"Re-emprèn",                                              
                                            "Pausa",
                                            "Atura",
                                            "Salta",
                                            "Sortir"};
   
    private CarpetaFitxers carpeta; 
    
public AplicacioUB2(){
     this.carpeta= new CarpetaFitxers();  
    }
    public  void gestioAplicacioUB() {
          // Creem un objecte per llegir des del teclat
        Scanner sc=new Scanner(System.in);
        manager(sc);
        
    } 
        private void manager(Scanner sc) {

        // Creem l'objecte per al menU. Li passem com a primer parÃƒÂ metre el nom del menÃƒÂº
        Menu<OpcionsMenuPrincipal> menu=new Menu<OpcionsMenuPrincipal>("Menu Principal",OpcionsMenuPrincipal.values());

        // Assignem la descripcio de les opcions
        menu.setDescripcions(descMenuPrincipal);

        // Obtenim una opcio des del menu i fem les accions pertinents
        OpcionsMenuPrincipal opcio = null;
        do {
            // Mostrem les opcions del menu
            menu.mostrarMenu();

            // Demanem una opcio
            opcio=menu.getOpcio(sc);
            // Fem les accions necessÃƒÂ ries
            switch(opcio) {
                case MENU_PRINCIPAL_OPCIO1:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ³
                    gestioBiblioteca(sc);
                    break; 
                case MENU_PRINCIPAL_OPCIO2:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ³
                    gestioAlbums(sc);
                    break;
                case MENU_PRINCIPAL_OPCIO3:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ³
                    controlReproduccio(sc);
                    break;
                case MENU_PRINCIPAL_SUBMENU1:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ³
                    break;
                case MENU_PRINCIPAL_OPCIO4:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ³
                    break;                
                case MENU_PRINCIPAL_SORTIR:
                    System.out.println("Fins aviat!");
                    break;
            }

        } while(opcio!=OpcionsMenuPrincipal.MENU_PRINCIPAL_SORTIR);
    }
        private void gestioBiblioteca(Scanner sc) {

        // Creem l'objecte per al menÃƒÂº. Li passem com a primer parÃƒÂ metre el nom del menÃƒÂº
        Menu<OpcionsSubmenu1> menu=new Menu<OpcionsSubmenu1>("Menu Biblioteca",OpcionsSubmenu1.values());

        // Assignem la descripciÃƒÂ³ de les opcions
        menu.setDescripcions(descMenu1);

        // Obtenim una opciÃƒÂ³ des del menÃƒÂº i fem les accions pertinents
        OpcionsSubmenu1 opcio = null;
        do {
            // Mostrem les opcions del menÃƒÂº
            menu.mostrarMenu();

            // Demanem una opcio
            opcio=menu.getOpcio(sc);

            // Fem les accions necessÃƒÂ ries
            switch(opcio) {
                case MENU_S1_OPCIO1:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ³
                    gestioFitxer(sc);
                    break;
                case MENU_S1_OPCIO2:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ³
                    break;
                    case MENU_S1_OPCIO3:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ³
                    break;
                case MENU_S1_SORTIR:
                    manager(sc);
                    break;
            }

        } while(opcio!=OpcionsSubmenu1.MENU_S1_SORTIR);
    }
        private void gestioFitxer(Scanner sc) {

        // Creem l'objecte per al menÃƒÂº. Li passem com a primer parÃƒÂ metre el nom del menÃƒÂº
        Menu<OpcionsSubmenu2> menu=new Menu<OpcionsSubmenu2>("Afegir fitxer",OpcionsSubmenu2.values());

        // Assignem la descripciÃƒÂ³ de les opcions
        menu.setDescripcions(descMenu2);

        // Obtenim una opciÃƒÂ³ des del menÃƒÂº i fem les accions pertinents
        OpcionsSubmenu2 opcio = null;
        do {
            // Mostrem les opcions del menÃƒÂº
            menu.mostrarMenu();

            // Demanem una opcio
            opcio=menu.getOpcio(sc);

            // Fem les accions necessÃƒÂ ries
            switch(opcio) {
                case MENU_S2_OPCIO1:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ³
                   break;
                case MENU_S2_OPCIO2:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ
                    break;
                case MENU_S2_SORTIR:
                    gestioBiblioteca(sc);
                    break;
            }

        } while(opcio!=OpcionsSubmenu2.MENU_S2_SORTIR);
    }

    private void gestioAlbums (Scanner sc) {

        // Creem l'objecte per al menÃƒÂº. Li passem com a primer parÃƒÂ metre el nom del menÃƒÂº
        Menu<AplicacioUB2.OpcionsSubmenu3> menu=new Menu<AplicacioUB2.OpcionsSubmenu3>("Gestió Àlbums",AplicacioUB2.OpcionsSubmenu3.values());

        // Assignem la descripciÃƒÂ³ de les opcions
        menu.setDescripcions(descMenu3);

        // Obtenim una opciÃƒÂ³ des del menÃƒÂº i fem les accions pertinents
        AplicacioUB2.OpcionsSubmenu3 opcio = null;
        do {
            // Mostrem les opcions del menÃƒÂº
            menu.mostrarMenu();

            // Demanem una opcio
            opcio=menu.getOpcio(sc);

            // Fem les accions necessÃƒÂ ries
            switch(opcio) {
                case MENU_S3_OPCIO1:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ³
                   break;
                case MENU_S3_OPCIO2:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ
                    break;
                case MENU_S3_OPCIO3:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ 
                    break;
                case MENU_S3_OPCIO4:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ 
                    gestioAlbum(sc);
                    break;
                case MENU_S3_SORTIR:
                    manager(sc);
                    break;
            }

        } while(opcio!=OpcionsSubmenu3.MENU_S3_SORTIR);
    }
                    
    private void gestioAlbum (Scanner sc) {

        // Creem l'objecte per al menÃƒÂº. Li passem com a primer parÃƒÂ metre el nom del menÃƒÂº
        Menu<AplicacioUB2.OpcionsSubmenu4> menu=new Menu<AplicacioUB2.OpcionsSubmenu4>("Menú Àlbum",AplicacioUB2.OpcionsSubmenu4.values());

        // Assignem la descripciÃƒÂ³ de les opcions
        menu.setDescripcions(descMenu4);

        // Obtenim una opciÃƒÂ³ des del menÃƒÂº i fem les accions pertinents
        AplicacioUB2.OpcionsSubmenu4 opcio = null;
        do {
            // Mostrem les opcions del menÃƒÂº
            menu.mostrarMenu();

            // Demanem una opcio
            opcio=menu.getOpcio(sc);

            // Fem les accions necessÃƒÂ ries
            switch(opcio) {
                case MENU_S4_OPCIO1:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ³
                   break;
                case MENU_S4_OPCIO2:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ
                    break;
                case MENU_S4_OPCIO3:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ 
                    break;
                case MENU_S4_SORTIR:
                    gestioAlbums(sc);
                    break;
            }

        } while(opcio!=AplicacioUB2.OpcionsSubmenu4.MENU_S4_SORTIR);
    }
    
    private void controlReproduccio (Scanner sc) {

        // Creem l'objecte per al menÃƒÂº. Li passem com a primer parÃƒÂ metre el nom del menÃƒÂº
        Menu<AplicacioUB2.OpcionsSubmenu5> menu=new Menu<AplicacioUB2.OpcionsSubmenu5>("Reproducció/Visió",AplicacioUB2.OpcionsSubmenu5.values());

        // Assignem la descripciÃƒÂ³ de les opcions
        menu.setDescripcions(descMenu5);

        // Obtenim una opciÃƒÂ³ des del menÃƒÂº i fem les accions pertinents
        AplicacioUB2.OpcionsSubmenu5 opcio = null;
        do {
            // Mostrem les opcions del menÃƒÂº
            menu.mostrarMenu();

            // Demanem una opcio
            opcio=menu.getOpcio(sc);

            // Fem les accions necessÃƒÂ ries
            switch(opcio) {
                case MENU_S5_OPCIO1:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ³
                   break;
                case MENU_S5_OPCIO2:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ
                    break;
                case MENU_S5_OPCIO3:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ
                    break;
                case MENU_S5_OPCIO4:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ
                    break;
                case MENU_S5_OPCIO5:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ
                    break;
                case MENU_S5_OPCIO6:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ
                    enCurs(sc);
                    break;
                case MENU_S5_SORTIR:
                    manager(sc);
                    break;
            }

        } while(opcio!=AplicacioUB2.OpcionsSubmenu5.MENU_S5_SORTIR);
    }
    private void enCurs (Scanner sc) {

        // Creem l'objecte per al menÃƒÂº. Li passem com a primer parÃƒÂ metre el nom del menÃƒÂº
        Menu<AplicacioUB2.OpcionsSubmenu6> menu=new Menu<AplicacioUB2.OpcionsSubmenu6>("Reproducció en curs",AplicacioUB2.OpcionsSubmenu6.values());

        // Assignem la descripciÃƒÂ³ de les opcions
        menu.setDescripcions(descMenu6);

        // Obtenim una opciÃƒÂ³ des del menÃƒÂº i fem les accions pertinents
        AplicacioUB2.OpcionsSubmenu6 opcio = null;
        do {
            // Mostrem les opcions del menÃƒÂº
            menu.mostrarMenu();

            // Demanem una opcio
            opcio=menu.getOpcio(sc);

            // Fem les accions necessÃƒÂ ries
            switch(opcio) {
                case MENU_S6_OPCIO1:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ³
                   break;
                case MENU_S6_OPCIO2:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ
                    break;
                case MENU_S6_OPCIO3:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ 
                    break;
                case MENU_S6_OPCIO4:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ 
                    break;                    
                case MENU_S6_SORTIR:
                    controlReproduccio(sc);
                    break;
            }

        } while(opcio!=AplicacioUB2.OpcionsSubmenu6.MENU_S6_SORTIR);
    }
}


/**
     * MenÃƒÂº principal del reproductor d'audio
     * @param sc Objecte de tipus Scanner que permet accedir al teclat
     */


    