/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package caracter_p1e12;

import java.util.*;

/**
 * 
 * @author Maria
 * 
 * TABLA DE TESTS
 * 
 *  ENTRADA | SORTIDA
 * ---------|---------
 *  hola, 5 | error
 *  hola, 1 | h
 *  hola, 0 | error
 * 
 */
public class Caracter_P1E12 {

    /**
     * @param args the command line arguments
     * tal com es va dir a la classe de laboratori, nosaltres contem desde 1 i l'ordinador desde 0.
     * per tant en 'hola' la lletra 1 per nosatres = lletra 0 per l'ordinador = h.
     */
    public static void main(String[] args) {
        // TODO code application logic here
        String word;
        int i;

        Scanner entrada = new Scanner(System.in);
        System.out.println("Introdueix una paraula");
        word = entrada.next();

        System.out.println("Introdueix un index per saber quina lletra ocupa la seva posició");
        i = entrada.nextInt();

        while (i < 1 || i > word.length()) {
            System.out.println("Index incorrecte");
            System.out.println("Introdueix un index per saber quina lletra ocupa la seva posició");
            i = entrada.nextInt();   
        }
    System.out.println("A la posició " + i + " està el caràcter " + word.charAt(i - 1));
    }

}
