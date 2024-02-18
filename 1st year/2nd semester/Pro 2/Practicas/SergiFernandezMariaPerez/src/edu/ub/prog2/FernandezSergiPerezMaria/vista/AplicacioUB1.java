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
public class AplicacioUB1 {
    private static enum OpcionsMenuPrincipal {MENU_PRINCIPAL_OPCIO1,MENU_PRINCIPAL_OPCIO2,MENU_PRINCIPAL_SUBMENU1,MENU_PRINCIPAL_SORTIR};
    // Declarem descripcions personalitzades per a les opcions del menÃƒÂº principal
    private String[] descMenuPrincipal={"Afegir fitxer multimèdia",
                                               "Eliminar fitxer multimèdia",
                                               "Mostrar carpeta",
                                               "Sortir"};
    private CarpetaFitxers carpeta; 
public AplicacioUB1(){
     this.carpeta= new CarpetaFitxers();  
    }
    public  void gestioAplicacioUB() {
          // Creem un objecte per llegir des del teclat
        Scanner sc=new Scanner(System.in);
        manager(sc);
        
    } 
        private void manager(Scanner sc) {

        // Creem l'objecte per al menÃƒÂº. Li passem com a primer parÃƒÂ metre el nom del menÃƒÂº
        Menu<OpcionsMenuPrincipal> menu=new Menu<OpcionsMenuPrincipal>("Menu Principal",OpcionsMenuPrincipal.values());

        // Assignem la descripciÃƒÂ³ de les opcions
        menu.setDescripcions(descMenuPrincipal);

        // Obtenim una opciÃƒÂ³ des del menÃƒÂº i fem les accions pertinents
        OpcionsMenuPrincipal opcio = null;
        do {
            // Mostrem les opcions del menÃƒÂº
            menu.mostrarMenu();

            // Demanem una opcio
            opcio=menu.getOpcio(sc);

            // Fem les accions necessÃƒÂ ries
            switch(opcio) {
                case MENU_PRINCIPAL_OPCIO1:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ³
                    System.out.println("Introdueix la ruta del fitxer: ");
                    String ruta= sc.nextLine(); 
                    System.out.println("Introdueix la descripció del fitxer: ");
                    String descripcio= sc.nextLine(); 
                    FitxerMultimedia fitxer= new FitxerMultimedia(ruta, descripcio);
                    carpeta.addFitxer(fitxer);
                    break;
                case MENU_PRINCIPAL_OPCIO2:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ³
                    int fitxerEsborrar; 
                    System.out.println("Quin fitxer vols esborrar?");
                    System.out.println(carpeta.toString()); 
                    fitxerEsborrar= sc.nextInt()-1;
                    File fitx=carpeta.getCarpetaElement(fitxerEsborrar);
                    carpeta.removeFitxer(fitx);
                    break;
                case MENU_PRINCIPAL_SUBMENU1:
                    // Mostrem un missatge indicant que s'ha triat aquesta opciÃƒÂ³
                    System.out.println(carpeta.toString());
                    break;
                
                case MENU_PRINCIPAL_SORTIR:
                    System.out.println("Fins aviat!");
                    break;
            }

        } while(opcio!=OpcionsMenuPrincipal.MENU_PRINCIPAL_SORTIR);
    }
}

    /**
     * MenÃƒÂº principal del reproductor d'audio
     * @param sc Objecte de tipus Scanner que permet accedir al teclat
     */


    