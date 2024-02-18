/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tresenratlla;

import java.util.Random;
import java.util.Scanner;

/**
 *
 * @author Maria
 */
public class TresEnRatlla {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        char p1 = 'X';
        char p2 = 'O';
        char empty = ' ';
        boolean torn = true;
        char layout[][] = new char[3][3];
        int fila, columna;
        Scanner s = new Scanner(System.in);
        
        initLayout(layout, empty);
        showLayout(layout);
        if(booleaAleatori()){
            System.out.println("Comença l'ordinador.");
            fila = posOrdinador();
            columna = posOrdinador();
            esPotAfegir(layout, fila, columna, p1);
            showLayout(layout);
                
        }else{
            System.out.println("Comences!!");
            showLayout(layout);
        }
        
        while(!jocAcabat(layout, empty)){
            System.out.println("Introdueix fila i columna.");
            fila = s.nextInt();
            columna = s.nextInt();
            esPotAfegir(layout, fila, columna, p2);
            showLayout(layout);
            //ara li toca a l'ordinador
            System.out.println("Torn de l'ordinador:");
            fila = posOrdinador();
            columna = posOrdinador();
            esPotAfegir(layout, fila, columna, p1);
            showLayout(layout);
       
            
            
        }
        
    }
    
    static void initLayout(char[][] layout, char c){
        for(int i = 0; i < layout.length; i++){
            for(int j = 0; j < layout.length; j++){
                layout[i][j] = c; 
            }
        }
        
    }
    static void showLayout(char[][] layout){
        for(int i = 0; i < layout.length; i++){
            for(int j = 0; j < layout[0].length; j++){
                System.out.print(layout[i][j] + " ");
            }
            System.out.println("");
        }
        
    }
    static boolean booleaAleatori(){
        int low = 1;
        int high = 3;
        Random r= new Random();
        int resultat= r.nextInt(high - low) + low;
        
        if (resultat == 1){
            return true;
        }else{
            return false;
        }
    }
    
    static boolean esPotAfegir(char[][] layout, int fila, int columna, char f){
        if((fila >= 0 && fila < layout.length) && (columna >= 0 && columna < layout.length)){
            if(layout[fila][columna] == "-".charAt(0)){
                layout[fila][columna] = f;
                
            } else {
                System.out.println("La casella ja està ocupada.");
                return false;
            }
            return true;  
            
        }
        System.out.println("Posició no vàlida.");
        return false;
        
        
    }
    
    static int posOrdinador(){
        int pos = (int) (Math.random() * (3 - 1 + 1) + 1);
        return pos;   
    }
    //veiem si el joc s'ha acabat
    static boolean jocAcabat(char[][] layout, char f){
        if(totPle(layout, f) || tresEnFila(layout, f) || tresEnColumna(layout, f ) || tresEnDiagonal(layout, f)){
             System.out.println("Joc acabat!");
            return true;
        }
       return false;
    }
    
    //check if 3 in a row
    static boolean tresEnFila(char[][] layout, char f) {
        char fitxa;
        boolean enFila;
        for (int i = 0; i < layout.length; i++) {
            enFila = true;
            fitxa = layout[i][0];
            if (fitxa != f) {
                for (int j = 1; j < layout[0].length; j++) {
                    if(fitxa != layout[i][j]){
                        enFila = false;
                    } 
                }
                if(enFila){
                    return true;
                }
            }

        }
        return false;
    }
    
    //check 3 in a column
    static boolean tresEnColumna(char[][] layout, char f) {
        char fitxa;
        boolean enColumna;
        for (int j = 0; j < layout.length; j++) {
            enColumna = true;
            fitxa = layout[0][j];
            if (fitxa != f) {
                for (int i = 1; i < layout[0].length; i++) {
                    if (fitxa != layout[i][j]) {
                        enColumna = false;
                    }
                }
                if (enColumna) {
                    return true;
                }
            }

        }
        return false;

    }
    
    //chec 3 in diagonal
    static boolean tresEnDiagonal(char[][] layout, char f){
        char fitxa = layout[0][0];
        boolean esDiagonal = false;
        
        if (fitxa != f) {
            for (int i = 1; i < layout.length; i++) {
                if(layout[i][i] != fitxa){
                    esDiagonal = false;
                }else{
                    esDiagonal = true;
                }

            }
        }
        fitxa = layout[0][2];
        if (fitxa != f) {
            for (int i = 1, j = 1; i < layout.length; i++, j--) {
                if (layout[i][j] != fitxa) {
                    esDiagonal = false;
                }else{
                    esDiagonal = true;
                }

            }

        }
        return esDiagonal ;  
        
    }
    static boolean totPle(char[][] layout, char f){
        int c = 0;
        for(int i = 0; i < layout.length; i++){
            for(int j = 0; j < layout[0].length; j++){
                if(layout[i][j] == f){
                    return false;
                }
            }
            
        }
        System.out.println("Tauler ple!");
        return true;
    
        
    }
    
    
}
