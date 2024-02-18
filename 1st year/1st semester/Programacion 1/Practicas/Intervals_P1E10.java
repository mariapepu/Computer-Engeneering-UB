/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package intervals_p1e10;

import java.util.Scanner;

/**
 *
 * @author Maria
 *  
 * TABLA DE TESTS
 * 
 *  ENTRADA   | SORTIDA
 * -----------|---------
 *  2,4 i 2,3 | intersecan
 *  2,4 i 1,3 | intersecan
 *  2,4 i 1,5 | intersecan
 *  2,4 i 3,6 | intersecan
 *  2,4 i 0,1 | no intersecan
 * 
 */
public class Intervals_P1E10 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int x1, x2, y1, y2;

        Scanner entrada = new Scanner(System.in);
        System.out.println("Introdueix un interval");
        x1 = entrada.nextInt();
        x2 = entrada.nextInt();

        System.out.println("Introdueix un altre interval");
        y1 = entrada.nextInt();
        y2 = entrada.nextInt();
        
//veiem si intersecan
        if ((x1 == y1 || x2 == y2 || x1 == y2 || x2 == y1)||
           ((x1 <= y1 && x2 >= y2) || (y1 >= x1 && y1 <= x2) || (y2 >= x1 && y2 <= x2))||
           ((x1 >= y1 && x2 <= y2)|| (x1 >= y1 && x1 <= y2) || (x2 >= y1 && x2 <= y2))) {
            
            System.out.println("intersecan");
        } 
            else{
            System.out.println("no intercan");
        }
    }

}
