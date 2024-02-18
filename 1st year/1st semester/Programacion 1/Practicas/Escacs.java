/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package escacs;
import static java.lang.Character.toChars;
import java.util.*;

/**
 *
 * @author Maria
 */
public class Escacs {
    
public static void main(String[] args) {
    System.out.println("De quant vols el taulell?");
    Scanner scan = new Scanner(System.in);
    int n = scan.nextInt();
    int arr[][] = new int [n][n];
        for (int i=0; i< n ; i++){            
            for (int j=0; j < n ; j++){
                if (i==1 || i==n-2) {
                    arr[i][j]=80;
                } 
                else if ((i%2==0&&j%2==0)||(i%2!=0&&j%2!=0)){
                    arr[i][j]=35;
                }
                else {
                    arr[i][j]=32;
                }
                System.out.print(toChars(arr[i][j]));
            }
        System.out.println(); 
        }
        //definim posicio inicial de la torre
        arr[0][0]=84;
        //pedir fila
        int fila;
        System.out.println("A quina fila vols moure la torre");
        Scanner entrada = new Scanner(System.in);
        fila = entrada.nextInt();
        //fila entre 1 i n
        while (fila<1||fila>n){
            System.out.println("La fila ha d'estar entre 1 i 12");
            Scanner entrada2 = new Scanner(System.in);
            fila=entrada2.nextInt()-1;
        }
        //pedir columna
        int columna;
        System.out.println("I a quina columna?");
        Scanner entrada3 = new Scanner(System.in);
        columna=entrada3.nextInt()-1;
        
        //mostrar torre + sus movimientos
        System.out.println("Moviments de la torre:"); //posicion a la que hemos movido la torre
        //imprimir tablero
        for (int i=0; i< n ; i++){            
            for (int j=0; j < n ; j++){
                if (i==fila &&  j==columna){
                    arr[i][j]=84;
                }
                else if (i==fila||j==columna){
                    arr[i][j]=43;
                }
                else if (i==1 || i==n-2) {
                    arr[i][j]=80;
                } 
                else if ((i%2==0&&j%2==0)||(i%2!=0&&j%2!=0)){
                    arr[i][j]=35;
                }
                else {
                    arr[i][j]=32;
                }
                System.out.print(toChars(arr[i][j]));
            }
        System.out.println(); 
        }

        
}
}

