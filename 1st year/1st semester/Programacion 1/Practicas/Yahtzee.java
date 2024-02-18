/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
 /*
Feu un programa per a jugar a una versió simple del Yahtzee. El programa ha de
simular la tirada de cinc daus i imprimeix "Yahtzee" si els cinc daus són iguals; en
cas contrari, s'hauria d'imprimir "Torna a intentar-ho". Teniu en compte que la tirada
de un dau dona un nombre entre 1 i 6. (Yahtzee.java)

Implementa els següents mètodes:
• static void tiraDados(int [] daus)

Aquest mètode omple l’array daus amb els valors dels daus d’una tirada.
• static boolean esYahtzee(int [] daus)

Aquest mètode retorna true si s’ha aconseguit yatzee amb els valors del
paràmetre daus, false en cas contrari.
 */
package yahtzee;

import java.util.Arrays;
import java.util.Scanner;

/*
Maria Pérez
 */
public class Yahtzee {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //primer tirem els daus
        int[] daus = new int[5];
        System.out.println("S'ha creat una nova partida");

        //he decidit fer un menú ya que així es més facil i visual jugar.
        Scanner scan = new Scanner(System.in);
        boolean salir = false;
        int opcio; //Guardarem la opcio del usuari

        while (!salir) {
            System.out.println("Què vols fer?");
            System.out.println("1. Tirar Daus.");
            System.out.println("2. Acabar Partida.");
            opcio = scan.nextInt();

            switch (opcio) {
                case 1:
                    tiraDaus(daus);
                    System.out.println(Arrays.toString(daus));
                    if (esYahtzee(daus)) {
                        System.out.println("Yahtzee!");
                    } else {
                        System.out.println("Torna a intentar-ho.");
                    }
                    break;

                case 2:
                    System.out.println("Adéu!");
                    salir = true;
                    break;

                default:
                    System.out.println("Només número 1 o 2");
            }

        }

    }

    static void tiraDaus(int[] daus) {
        // metode per tirar els daus.
        //farem un while
        int i = 0, m = 1, n = 6;
        while (i < 5) {
            daus[i] = (int) (Math.random() * (n - m + 1) + m);
            i++;
        }
    }

    static boolean esYahtzee(int[] daus) {
        //mirem si tots els daus son iguals, ho farem amb un for
        //per recorrer l'array de daus
        int i, counter = 0;
        for (i = 0; i < daus.length; i++) {
            if (daus[0] == daus[i]) {
                counter++;
            }
        }
        return counter == 5;
    }
}
