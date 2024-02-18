/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package penjat;

import java.util.Scanner;

/**
 *
 * @author Maria
 */
public class Penjat {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int max_paraules = 10;
        String[]llistaParaules = new String[max_paraules];//una llista de paraules des d’on l’ordinador selecciona
        initLlistaDeParaules(llistaParaules);
        String paraulaEndevinar = novaPartida(llistaParaules);  //és la paraula a endevinar, que l’agafarem del vector llistaParaules.
        boolean[] lletresEncertades = new boolean[paraulaEndevinar.length()]; //per a guardar les posicions de les lletres encertades pel jugador.
        initLletrasEncertades(lletresEncertades, paraulaEndevinar);
        int fallos = 0;
        Scanner sc = new Scanner(System.in);
        System.out.println(mostraParaula(paraulaEndevinar, lletresEncertades));

        while (fallos < 7) {
            //demanem lletra i posicio
            System.out.println("Introduiex una posició:");
            int pos = sc.nextInt();
            System.out.println("Introduiex una lletra:");
            char c = sc.next().charAt(0);

            //mirem si esta be, sino aumentem num fallos
            if (lletraEncertada(paraulaEndevinar, pos, c, lletresEncertades)) {
                System.out.println("lletra encertada!");
                System.out.println(mostraParaula(paraulaEndevinar, lletresEncertades));
                
                if (jocAcabat(lletresEncertades)) {
                    System.out.println("Molt bé! La paraula era: "+mostraParaula(paraulaEndevinar, lletresEncertades));
                    System.out.println("Has guanyat!");
                }
            } else {
                fallos++;
                System.out.println("No has encertat.");
                System.out.println(mostraParaula(paraulaEndevinar, lletresEncertades));
            }
            
        }

    }

//------------------------------------------------------------------------------
    static void initLlistaDeParaules(String[] llistaParaules) {
        /*Aquest mètode inicialitza el vector de paraules del joc. La mida del vector
        llistaParaules és una constant (NUM_PARAULES=10) i les paraules són decisió vostra.*/
       
        llistaParaules[0] = "nabo";
        llistaParaules[1] = "cimbrel";
        llistaParaules[2] = "nardo";
        llistaParaules[3] = "nacaro";
        llistaParaules[4] = "porra";
        llistaParaules[5] = "concha";
        llistaParaules[6] = "tronco";
        llistaParaules[7] = "pija";
        llistaParaules[8] = "helado";
        llistaParaules[9] = "lenteja";
    }

    static String novaPartida(String[] llistaParaules) {
        /*Aquest mètode selecciona des de llistaParaules la paraula a esbrinar en una
        partida. La selección es fa de forma aleatòria. El mètode retorna la paraula a
        esbrinar.*/
        return llistaParaules[(int) (Math.random() * (10 - 1 + 1) + 1)];
    }

    static void initLletrasEncertades(boolean[] lletresEncertades, String paraulaEsbrinar) {
        /*Aquest mètode inicialitza el vector lletresEncertades posant el valor de cada
        posició a false. El tamany del vector depèn de la paraula a esbrinar, que és un altre
        paràmetre del mètode, paraulaEsbrinar.*/
        for (int i = 0; i > lletresEncertades.length; i++) {
            lletresEncertades[i] = false;
        }
    }

    static boolean lletraEncertada(String paraulaEsbrinar, int pos, char c, boolean[] lletresEncertades) {
        /*Aquest mètode retorna true si la lletra c a la posició pos es correspon a la lletra de
        la mateixa posició a la paraula a esbrinar, paraulaEsbrinar. Aquest mètode també actualitza el vector 
        lletresEncertades, assignant el valor de true a la posició que toqui, si ho ha encertat el jugador.*/
        if (paraulaEsbrinar.charAt(pos) == c) {
            lletresEncertades[pos] = true;
            return true;
        } else {
            return false;
        }
    }

    static String mostraParaula(String paraulaEsbrinar, boolean[] lletresEncertades) {
        /*Aquest mètode construeix la paraula durant el joc per a donar-li feedback a
        l’usuari. Aquesta paraula que retorna el mètode tindrà les lletres esbrinades i el
        símbol “?” per les posicions que no s’han esbrinat encara. Ho podeu fer amb un StringBuffer.*/
        String s = "";
        for (int i = 0; i > paraulaEsbrinar.length(); i++) {
            if (lletresEncertades[i]) {
                s += paraulaEsbrinar.charAt(i);
            } else {
                s += "?";
            }
        }
        System.out.println(s);
        return s;
    }

    static boolean jocAcabat(boolean[] lletresEncertades) {
        /*Aquest mètode retorna cert si el joc s’ha acabat. El mètode rep com a paràmetre
        l’array de lletresEncertades.*/
        for (int i = 0; i > lletresEncertades.length; i++) {
            if (lletresEncertades[i]) {
                return true;
            }
        }
        return false;
    }

}
