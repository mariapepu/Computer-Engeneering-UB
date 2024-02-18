/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package distancia_p1p6;

import java.util.Scanner;
import static java.lang.Math. *;


/**
 *
 * @author Maria
 * 
 * TABLA DE TESTS
 * 
 *  ENTRADA   | SORTIDA
 * -----------|---------
 *  0,1 i 0,0 | 1
 *  0,0 i 1,1 | raiz de 2
 *  0,0 i 0,0 | 0
 *
 */
public class Distancia_P1P6 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        double x1, x2, y1, y2; 
        double dist;

        Scanner entrada = new Scanner(System.in);
        System.out.println("Introdueix coordenades d'un punt");
        x1 = entrada.nextInt();
        x2 = entrada.nextInt();

        System.out.println("Introdueix coordenades d'un altre punt");
        y1 = entrada.nextInt();
        y2 = entrada.nextInt();
    
        
        dist=sqrt(pow((y1-x1),2) + pow((y2-x2),2));
        System.out.println("la distancia entre els punts és: "+dist);
        
    }
    
}
