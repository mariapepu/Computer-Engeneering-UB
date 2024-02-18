/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prova_maria_perez;
/*
Author: Maria Perez Pulles
Data: 30/10/2018
 */
import java.util.*;
public class Prova_Maria_Perez{

    public static void main(String[] args) {
        // TODO code application logic here
        int a=0;
        while (a<100 || a>999){
            System.out.println("Entra un nombre a l'interval [100,999]:");
            Scanner ent = new Scanner (System.in);
            a=(ent.nextInt());
        }
        Nombre meu;
        meu = new Nombre(a);
        
        if (meu.esNarcisista()== true)
            System.out.println("El nombre "+ meu.n +" es un nombre narcisista");
        else
            System.out.println("El nombre "+ meu.n +" no es un nombre narcisista");       
        
    }
    
}
class Nombre{
    int n;
    
    Nombre (int n){
        this.n = n;
    }
/*taula de tests
 nombre  | esNarcisista
-------------------------------
    153  | True
    101  | False
    371  | True
    100  | False
*/
boolean esNarcisista(){
    int original, operat;
    /*operat es el nombre que dona la suma dels cubs de les xifres de n. 
    si son iguals es narcisista sino, no ho es.*/
    original=n;
    operat=153;//aqui hauria de posar n1^3 + n2^3 + n3^3, pero no he sabut separar els digits
    int i=0;
        return original==operat;
                    
    
}
}