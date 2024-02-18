package edu.ub.prog2.FernandezSergiPerezMaria.vista;

import edu.ub.prog2.utils.Menu;
import java.util.Scanner;
import edu.ub.prog2.FernandezSergiPerezMaria.controlador.Controlador;
import edu.ub.prog2.utils.AplicacioException;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;


/**
 *
 * @author sergi
 */
public class AplicacioUB2 {

    private static enum OpcionsMenuPrincipal {
        MENU_PRINCIPAL_OPCIO1, MENU_PRINCIPAL_OPCIO2, MENU_PRINCIPAL_OPCIO3,
        MENU_PRINCIPAL_OPCIO4, MENU_PRINCIPAL_OPCIO5, MENU_PRINCIPAL_SORTIR
    };

    static private enum OpcionsMenuBiblioteca {
        MENU_BIBLIOTECA_OPCIO1, MENU_BIBLIOTECA_OPCIO2,
        MENU_BIBLIOTECA_OPCIO3, MENU_BIBLIOTECA_SORTIR
    };

    static private enum OpcionsFitxer {
        MENU_FITXER_OPCIO1, MENU_FITXER_OPCIO2, MENU_FITXER_SORTIR
    };

    static private enum OpcionsAlbum {
        MENU_ALBUM_OPCIO1, MENU_ALBUM_OPCIO2, MENU_ALBUM_OPCIO3,
        MENU_ALBUM_OPCIO4, MENU_ALBUM_SORTIR
    };

    static private enum OpcionsGestAlbum {
        MENU_GESTALBUM_OPCIO1, MENU_GESTALBUM_OPCIO2, MENU_GESTALBUM_OPCIO3,
        MENU_GESTALBUM_SORTIR
    };

    static private enum OpcionsControlRep {
        MENU_CONTREP_OPCIO1, MENU_CONTREP_OPCIO2, MENU_CONTREP_OPCIO3,
        MENU_CONTREP_OPCIO4, MENU_CONTREP_OPCIO5, MENU_CONTREP_OPCIO6, MENU_CONTREP_SORTIR
    };

    static private enum OpcionsReproduccio {
        MENU_REPRO_OPCIO1, MENU_REPRO_OPCIO2, MENU_REPRO_OPCIO3,
        MENU_REPRO_OPCIO4, MENU_REPRO_SORTIR
    }

    // Declarem descripcions personalitzades per a les opcions del menÃƒÂº principal
    private String[] descMenuPrincipal = {"Gestio Biblioteca",
        "Gestio Albums",
        "Control Reproducció/Visió",
        "Guardar Dades",
        "Recuperar dades",
        "Sortir"};
    static private String[] descMenuBiblioteca = {"Afegir fitxer multimedia",
        "Mostrar Biblioteca",
        "Eliminar fitxer multimedia",
        "Menu Anterior"};
    static private String[] descMenuFitxer = {"Afegir video",
        "Afegir audio",
        "Menu Anterior"};
    static private String[] descMenuAlbum = {"Afegir Album",
        "Mostrar Albums",
        "Eliminar Album",
        "Gestionar Album",
        "Menu Anterior"};
    static private String[] descMenuGestAlbum = {"Afegir fitxer multimedia",
        "Mostrar Album",
        "Eliminar fitxer multimedia",
        "Menu Anterior"};
    static private String[] descMenuControlRep = {"Reproduir un fitxer reproduible",
        "Reproduir tota la biblioteca",
        "Reproduir un album",
        "Activar/desactivar reproducció continua",
        "Activar/desactivar reproducció aleatoria",
        "Gestio reproducció en curs",
        "Menu Anterior"};
    static private String[] descMenuReproduccio = {"Re-emprèn",
        "Pausa",
        "Atura",
        "Salta",
        "Sortir"};
    Controlador controlador;

    /**
     *
     */
    public AplicacioUB2() {
        controlador = new Controlador();
    }

    /**
     *
     * @throws AplicacioException
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void gestioAplicacioUB() throws AplicacioException, IOException, ClassNotFoundException {
        // Creem un objecte per llegir des del teclat
        Scanner sc = new Scanner(System.in);
        manager(sc);

    }

    private void manager(Scanner sc) throws AplicacioException, IOException, ClassNotFoundException {

        // Creem l'objecte per al menu. Li passem com a primer parametre el nom del menu.
        Menu<OpcionsMenuPrincipal> menu = new Menu<>("Menu Principal", OpcionsMenuPrincipal.values());

        // Assignem la descripció de les opcions
        menu.setDescripcions(descMenuPrincipal);

        // Obtenim una opcio i fem les accions pertinents
        OpcionsMenuPrincipal opcio = null;
        do {
            // Mostrem les opcions del menu
            menu.mostrarMenu();

            // Demanem una opcio
            opcio = menu.getOpcio(sc);
            // Fem les accions necessaries
            switch (opcio) {
                case MENU_PRINCIPAL_OPCIO1:
                    // Porta al menu GestioBiblioteca
                    gestioBiblioteca(sc);
                    break;
                case MENU_PRINCIPAL_OPCIO2:
                    // Porta al menu GestAlbums.
                    gestioAlbum(sc);
                    break;
                case MENU_PRINCIPAL_OPCIO3:
                    //Porta al menu ControlReproducció
                    gestioControlRepro(sc);
                    break;
                case MENU_PRINCIPAL_OPCIO4:
                    //Guarda dades al disc
                    try {
                        controlador.guardarDadesDisc("C:\\Users\\sergi\\Desktop\\holasi.dat");
                    } catch (AplicacioException e) {

                    }
                    break;
                case MENU_PRINCIPAL_OPCIO5:
                    // Carrega les dades
                    try {
                        controlador.carregarDadesDisc("C:\\Users\\sergi\\Desktop\\holasi.dat");
                    } catch (AplicacioException e) {

                    }
                    break;

                case MENU_PRINCIPAL_SORTIR:
                    //Sortida programa
                    System.out.println("Fins aviat!");
                    break;
            }

        } while (opcio != OpcionsMenuPrincipal.MENU_PRINCIPAL_SORTIR);
    }

    private void gestioBiblioteca(Scanner sc) {

        // Creem l'objecte per al menÃƒÂº. Li passem com a primer parÃƒÂ metre el nom del menÃƒÂº
        Menu<OpcionsMenuBiblioteca> menu = new Menu<>("Menu Biblioteca", OpcionsMenuBiblioteca.values());

        // Assignem la descripciÃƒÂ³ de les opcions
        menu.setDescripcions(descMenuBiblioteca);

        // Obtenim una opciÃƒÂ³ des del menÃƒÂº i fem les accions pertinents
        OpcionsMenuBiblioteca opcio = null;
        do {
            // Mostrem les opcions del menÃƒÂº
            menu.mostrarMenu();

            // Demanem una opcio
            opcio = menu.getOpcio(sc);

            // Fem les accions necessÃƒÂ ries
            switch (opcio) {
                case MENU_BIBLIOTECA_OPCIO1:
                    //Porta al menu GestioFitxer
                    gestioFitxer(sc);
                    break;
                case MENU_BIBLIOTECA_OPCIO2:
                    // Mostra la Biblioteca
                    List lista = controlador.mostrarBiblioteca();
                    int c = 1;
                    for (Iterator<String> i = lista.iterator(); i.hasNext();) {
                        System.out.println(c + ".\n" + i.next());
                        c += 1;
                    }
                    break;
                case MENU_BIBLIOTECA_OPCIO3:
                    //Elimina el fitxer de la biblioteca
                    List lista1 = controlador.mostrarBiblioteca();
                    int ca = 1;
                    for (Iterator<String> i = lista1.iterator(); i.hasNext();) {
                        System.out.println(ca + ".\n" + i.next());
                        ca += 1;
                    }
                    System.out.println("Introdueix l'index del arxiu que vols esborrar");
                    int esborrar = sc.nextInt() - 1;
                    try {
                        controlador.esborrarFitxer(esborrar);
                    } catch (AplicacioException e) {

                    }
                    break;
                case MENU_BIBLIOTECA_SORTIR:
                    //No posem res ja que torna al menu anterior
                    break;
            }

        } while (opcio != OpcionsMenuBiblioteca.MENU_BIBLIOTECA_SORTIR);
    }

    private void gestioFitxer(Scanner sc) {

        // Creem l'objecte per al menÃƒÂº. Li passem com a primer parÃƒÂ metre el nom del menÃƒÂº
        Menu<OpcionsFitxer> menu = new Menu<>("Afegir fitxer", OpcionsFitxer.values());

        // Assignem la descripciÃƒÂ³ de les opcions
        menu.setDescripcions(descMenuFitxer);

        // Obtenim una opciÃƒÂ³ des del menÃƒÂº i fem les accions pertinents
        OpcionsFitxer opcio = null;
        do {
            // Mostrem les opcions del menÃƒÂº
            menu.mostrarMenu();

            // Demanem una opcio
            opcio = menu.getOpcio(sc);

            // Fem les accions necessÃƒÂ ries
            switch (opcio) {
                case MENU_FITXER_OPCIO1:
                    // Afegir Video
                    System.out.println("Digues el path del video, la descripcio i el codec.");
                    String path = sc.nextLine();
                    String nomvideo = sc.nextLine();
                    String codec = sc.nextLine();
                    try {
                        controlador.afegirVideo(path, nomvideo, codec, 0, 0, 0, 0);
                    } catch (AplicacioException e) {

                    }
                    break;
                case MENU_FITXER_OPCIO2:
                    // Afegir Audio
                    System.out.println("Digues el path del audio, el camid e la imatge, la descripcio i el codec.");
                    String path1 = sc.nextLine();
                    String camiImatge = sc.nextLine();
                    String nomAudio = sc.nextLine();
                    String codec1 = sc.nextLine();
                    try {
                        controlador.afegirAudio(path1, camiImatge, nomAudio, codec1, 0, 0);
                    } catch (AplicacioException e) {

                    }
                    break;
                case MENU_FITXER_SORTIR:
                    //Menu anterior
                    break;
            }

        } while (opcio != OpcionsFitxer.MENU_FITXER_SORTIR);
    }

    private void gestioGestorAlbum(Scanner sc, String nomMenu) {

        // Creem l'objecte per al menÃƒÂº. Li passem com a primer parÃƒÂ metre el nom del menÃƒÂº
        Menu<OpcionsGestAlbum> menu = new Menu<>("Afegir fitxer", OpcionsGestAlbum.values());

        // Assignem la descripciÃƒÂ³ de les opcions
        menu.setDescripcions(descMenuGestAlbum);

        // Obtenim una opciÃƒÂ³ des del menÃƒÂº i fem les accions pertinents
        OpcionsGestAlbum opcio = null;
        do {
            // Mostrem les opcions del menÃƒÂº
            menu.mostrarMenu();

            // Demanem una opcio
            opcio = menu.getOpcio(sc);

            // Fem les accions necessÃƒÂ ries
            switch (opcio) {
                case MENU_GESTALBUM_OPCIO1:
                    // Afegir fitxer 
                    List lista = controlador.mostrarBiblioteca();
                    int c = 1;
                    for (Iterator<String> i = lista.iterator(); i.hasNext();) {
                        System.out.println(c + ".\n" + i.next());
                        c += 1;
                    }
                    System.out.println("Que fitxer vols afegir al teu album?(ID))");
                    int num = sc.nextInt() - 1;
                    try {
                        controlador.afegirFitxer(nomMenu, num);
                    } catch (AplicacioException e) {
                        System.out.println(e);
                    }
                    break;
                case MENU_GESTALBUM_OPCIO2:
                    // Mostrar album
                    try {
                        List llista = controlador.mostrarAlbum(nomMenu);
                        int a = 1;
                        for (Iterator<String> i = llista.iterator(); i.hasNext();) {
                            System.out.println(a + "\n" + i.next());
                            a += 1;
                        }
                    } catch (AplicacioException e) {
                        System.out.println(e);
                    }

                    break;
                case MENU_GESTALBUM_OPCIO3:
                    //Eliminar fitxer
                    try {
                        List llista2 = controlador.mostrarAlbum(nomMenu);

                        int ca = 1;
                        for (Iterator<String> i = llista2.iterator(); i.hasNext();) {
                            System.out.println(ca + ".\n" + i.next());
                            ca += 1;
                        }
                        System.out.println("Introdueix l'index del arxiu que vols esborrar");
                        int esborrar = sc.nextInt() - 1;

                        controlador.esborrarFitxer(nomMenu, esborrar);
                    } catch (AplicacioException e) {
                        System.out.println(e);
                    }

                    break;
                case MENU_GESTALBUM_SORTIR:
                    //Torna al menu anterior

                    break;
            }

        } while (opcio != OpcionsGestAlbum.MENU_GESTALBUM_SORTIR);
    }

    private void gestioAlbum(Scanner sc) {

        // Creem l'objecte per al menÃƒÂº. Li passem com a primer parÃƒÂ metre el nom del menÃƒÂº
        Menu<OpcionsAlbum> menu = new Menu<>("Afegir fitxer", OpcionsAlbum.values());

        // Assignem la descripciÃƒÂ³ de les opcions
        menu.setDescripcions(descMenuAlbum);

        // Obtenim una opciÃƒÂ³ des del menÃƒÂº i fem les accions pertinents
        OpcionsAlbum opcio = null;
        do {
            // Mostrem les opcions del menÃƒÂº
            menu.mostrarMenu();

            // Demanem una opcio
            opcio = menu.getOpcio(sc);

            // Fem les accions necessÃƒÂ ries
            switch (opcio) {
                case MENU_ALBUM_OPCIO1:
                    // Afegir Album 
                    System.out.println("Introdueix el nom del album");
                    String nom = sc.nextLine();
                    try {
                        controlador.afegirAlbum(nom);
                    } catch (AplicacioException e) {
                        System.out.println(e);
                    }
                    break;
                case MENU_ALBUM_OPCIO2:
                    //Mostrar Albums
                    List llista = controlador.mostrarLlistatAlbums();
                    int a = 1;
                    for (Iterator<String> i = llista.iterator(); i.hasNext();) {
                        System.out.println(a + "\n" + i.next());
                        a += 1;
                    }
                    break;
                case MENU_ALBUM_OPCIO3:
                    //Eliminar Albums
                    List llista1 = controlador.mostrarLlistatAlbums();
                    int e = 1;
                    for (Iterator<String> i = llista1.iterator(); i.hasNext();) {
                        System.out.println(e + "\n" + i.next());
                        e += 1;
                    }
                    System.out.println("Introdueix el nom del album que vols esborrar");
                    String noms = sc.nextLine();
                    try {
                        controlador.esborrarAlbum(noms);
                    } catch (AplicacioException ex) {
                        System.out.println(ex);
                    }
                    break;
                case MENU_ALBUM_OPCIO4:
                    // Porta al menú GestionarAlbums
                    List llistas = controlador.mostrarLlistatAlbums();
                    int s = 1;
                    for (Iterator<String> i = llistas.iterator(); i.hasNext();) {
                        System.out.println(s + "\n" + i.next());
                        s += 1;
                    }
                    System.out.println("Introdueix el nom del album que vols editar");
                    String str = sc.nextLine();
                    gestioGestorAlbum(sc, str);
                    break;
                case MENU_ALBUM_SORTIR:
                    //Torna al menu anterior

                    break;
            }
        } while (opcio != OpcionsAlbum.MENU_ALBUM_SORTIR);
    }

    private void gestioControlRepro(Scanner sc) {

        // Creem l'objecte per al menÃƒÂº. Li passem com a primer parÃƒÂ metre el nom del menÃƒÂº
        Menu<OpcionsControlRep> menu = new Menu<>("Afegir fitxer", OpcionsControlRep.values());

        // Assignem la descripciÃƒÂ³ de les opcions
        menu.setDescripcions(descMenuControlRep);

        // Obtenim una opciÃƒÂ³ des del menÃƒÂº i fem les accions pertinents
        OpcionsControlRep opcio = null;
        do {
            // Mostrem les opcions del menÃƒÂº
            menu.mostrarMenu();

            // Demanem una opcio
            opcio = menu.getOpcio(sc);

            // Fem les accions necessÃƒÂ ries
            switch (opcio) {
                case MENU_CONTREP_OPCIO1:
                    // Reproduir Fitxer
                    List lista1 = controlador.mostrarBiblioteca();
                    int ca = 1;
                    for (Iterator<String> i = lista1.iterator(); i.hasNext();) {
                        System.out.println(ca + ".\n" + i.next());
                        ca += 1;
                    }
                    System.out.println("Introdueix l'index del arxiu que vols reproduir");
                    int reproduir = sc.nextInt() - 1;
                    try {
                        controlador.obrirFinestraReproductor();
                        controlador.reproduirFitxer(reproduir);

                    } catch (AplicacioException e) {
                        System.out.println(e);
                    }
                    break;
                case MENU_CONTREP_OPCIO2:
                    // Reproduir Biblioteca
                    try {
                        controlador.obrirFinestraReproductor();
                        controlador.reproduirCarpeta();

                    } catch (AplicacioException e) {
                        System.out.println(e);
                    }
                    break;
                case MENU_CONTREP_OPCIO3:
                    //Reproduir Album
                    System.out.println("Introdueix el nom del album que vols reproduir");
                    String name = sc.nextLine();
                    try {
                        controlador.obrirFinestraReproductor();
                        controlador.reproduirCarpeta(name);

                    } catch (AplicacioException ex) {
                        System.out.println(ex);
                    }
                    break;
                case MENU_CONTREP_OPCIO4:
                    // Activar rep Ciclica
                    controlador.setReproduccioCiclica();
                    if (controlador.getDades().getReproduccioCiclica()) {
                        System.out.println("Reproduccio Ciclica Activada");
                    } else {
                        System.out.println("Reproducció Ciclica Desactivada");
                    }
                    break;
                case MENU_CONTREP_OPCIO5:
                    // Activar rep Aleatoria
                    controlador.setReproduccioAleatoria();
                    if (controlador.getDades().getReproduccioAleatoria()) {
                        System.out.println("Reproduccio Aleatoria Activada");
                    } else {
                        System.out.println("Reproducció Aleatoria Desactivada");
                    }
                    break;
                case MENU_CONTREP_OPCIO6:
                    // Menu Reproduccio
                    gestioReproduccio(sc);
                    break;
                case MENU_CONTREP_SORTIR:
                    //Torna al menu anterior
                    try {
                        controlador.tancarFinestraReproductor();
                    } catch (AplicacioException e) {
                        System.out.println(e);
                    }
                    break;
            }

        } while (opcio != OpcionsControlRep.MENU_CONTREP_SORTIR);
    }

    private void gestioReproduccio(Scanner sc) {

        // Creem l'objecte per al menÃƒÂº. Li passem com a primer parÃƒÂ metre el nom del menÃƒÂº
        Menu<OpcionsReproduccio> menu = new Menu<>("Afegir fitxer", OpcionsReproduccio.values());

        // Assignem la descripciÃƒÂ³ de les opcions
        menu.setDescripcions(descMenuReproduccio);

        // Obtenim una opciÃƒÂ³ des del menÃƒÂº i fem les accions pertinents
        OpcionsReproduccio opcio = null;
        do {
            // Mostrem les opcions del menÃƒÂº
            menu.mostrarMenu();

            // Demanem una opcio
            opcio = menu.getOpcio(sc);

            // Fem les accions necessÃƒÂ ries
            switch (opcio) {
                case MENU_REPRO_OPCIO1:
                    // Re-empren
                    try {
                        controlador.reemprenReproduccio();
                    } catch (AplicacioException e) {
                        System.out.println(e);
                    }
                    break;
                case MENU_REPRO_OPCIO2:
                    // Pausa
                    try {
                        controlador.pausaReproduccio();
                    } catch (AplicacioException e) {
                        System.out.println(e);
                    }
                    break;
                case MENU_REPRO_OPCIO3:
                    //Atura
                    try {
                        controlador.aturaReproduccio();
                    } catch (AplicacioException e) {
                        System.out.println(e);
                    }
                    break;
                case MENU_REPRO_OPCIO4:
                    // Salta
                    try {
                        controlador.saltaReproduccio();
                    } catch (AplicacioException e) {
                        System.out.println(e);
                    }
                    break;
                case MENU_REPRO_SORTIR:
                    //Torna al menu anterior

                    break;
            }

        } while (opcio != OpcionsReproduccio.MENU_REPRO_SORTIR);
    }
}

/**
 * MenÃƒÂº principal del reproductor d'audio
 *
 * @param sc Objecte de tipus Scanner que permet accedir al teclat
 */
