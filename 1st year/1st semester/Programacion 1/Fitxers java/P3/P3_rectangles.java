/*
2 * Author : Maria Perez
3 * Data : 29/10/18
4 */
/* Tests metode getArea ()
7 * Entrada | Sortida Esperada
8 * -----------------------------------
9 * Amplada : 4.0 | 80.0
10 * Alcada : 20.0 |
11 * -----------------------------------
12 * Amplada : 3.5 | 14.70
13 * Alcada : 4.2 |
14 * -----------------------------------
15 /* Fer el mateix per els tests del metode getPerimetre ()
17 /* Tests metode getPerimetre ()
18 * Entrada | Sortida Esperada
19 * -----------------------------------
20 * Amplada : 3.5 | 15.40
13 * Alcada : 4.2 | 
22 * -----------------------------------
23 * Amplada : 4.0| 48.00
24 * Alçada: 20.0|
25 * -----------------------------------
26 */
public class P3_rectangles{
    public static void main ( String [] args ) {
        Rectangle meu = new Rectangle (4.0 , 20.0) ;
        System.out.println ("L’area  d’un  rectangle  d’amplada  " + meu.amplada + " i  alcada  " + meu.alcada + ": " + meu.getArea ());
        System.out.println ("El  perimetre : " + meu.getPerimetre ());
        
Rectangle teu = new Rectangle (3.5 , 4.2) ;
        System.out.println ("\nL ’area  d’un  rectangle  d’amplada  " + teu.amplada + " i  alcada  " + teu.alcada + ": " + teu.getArea ());
        System.out.println ("El  perimetre : " + teu.getPerimetre ());
    }
}
class Rectangle {
// Atributs
    double amplada , alcada ;
    // Constructor per defecte
    Rectangle () {
        amplada = 1.0;
        alcada = 1.0;
    }
    // Constructor amb parametres
    Rectangle ( double novaAmplada , double novaAlcada ) {
        amplada = novaAmplada ;
        alcada = novaAlcada ;
    }
    // Metode per obtenir l’area del rectangle
    double getArea () {
        // Introduiu solucio aqui
        ab=novaAmplada;
        h=novaAlcada;
        getArea=ab*h;
    }
    // Metode per obtenir el perimetre del rectangle
    double getPerimetre () {
        // Introduiu solucio aqui
        ab=novaAmplada
        h=novaAlcada
        getPerimetre=(2*ab)+(2*h)  
    }
}