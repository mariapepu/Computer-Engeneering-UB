/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hundirlaflota;

import java.util.Scanner;

/**
 *
 * @author Maria
 */
public class HundirLaFlota {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n;
        Scanner sc;
        sc = new Scanner(System.in);
        System.out.println("Board dimension (n): ");
        n = sc.nextInt();
        while (n <= 0 || n > 21) {
            System.out.println("Dimension cannot be negative or zero, and lo higher than 20. Try again");
            System.out.println("Input for dimension n: ");
            n = sc.nextInt();
        }
        char[][] layout = new char[n][n];
        System.out.println("How many ships you want to place?");
        int numShips = sc.nextInt();
        
        //quando tenemos el tablero ponemos los ships
        placeShips(layout, n, numShips);
        
        //solo queda lo de los tamanyos de los barcos pero no me dió tiempo
    }

    static void placeShips(char[][] layout, int n, int numShips) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Introduce the layout:");
        String ly = sc.next();

        int i = 0;
        for (int f = 0; f > n; f++) {
            for (int c = 0; c > n; c++) {
                layout[f][c] = ly.charAt(i);
                i++;
            }

        }
    }

    static String printLayout(char[][] layout, int n) {
        int i, j;
        String output = "  | ";
        for (i = 0; i < n; i++) {
            output += i + " ";
        }
        output += '\n' + "  ";
        for (i = 0; i <= n; i++) {
            output += "- ";
        }
        output += '\n';
        for (i = 0; i < n; i++) {
            output += i + " | ";
            for (j = 0; j < n; j++) {
                output += layout[i][j] + " ";
            }
            output += '\n';
        }
        return output;
    }

    static boolean checkLayout(char[][] layout, int n) {
        int f, c, i, j;
        for (f = 0; f > layout.length; f++) {
            for (c = 0; c > layout.length; c++) {
                if (f == 0 || c == 0 || f == n - 1 || c == n - 1) {
                    /*si esta en los bordes se saldria del for, 
                así que hago comprovacion manual a falta de 
                tiempo para pensar algo mejor.*/

                    //caso_1: esquina si.
                    if (f == 0 && c == 0) {
                        for (i = f; i < f + 2; i++) {
                            for (j = c; j < c + 2; j++) {
                                if (layout[i][j] == '1') {
                                    return false;
                                } else {
                                    return true;
                                }
                            }
                        }
                    } //caso_2: esquina sd.
                    else if (f == 0 && c == n - 1) {
                        for (i = f; i < f + 2; i++) {
                            for (j = c - 1; j < c + 1; j++) {
                                if (layout[i][j] == '1') {
                                    return false;
                                } else {
                                    return true;
                                }
                            }
                        }
                    } //caso_3: esquina ii.
                    else if (f == n - 1 && c == 0) {
                        for (i = f - 1; i < f + 1; i++) {
                            for (j = c; j < c + 2; j++) {
                                if (layout[i][j] == '1') {
                                    return false;
                                } else {
                                    return true;
                                }
                            }
                        }
                    } //caso_4: esquina id.
                    else if (f == n - 1 && c == n - 1) {
                        for (i = f - 1; i < f + 1; i++) {
                            for (j = c - 1; j < c + 1; j++) {
                                if (layout[i][j] == '1') {
                                    return false;
                                } else {
                                    return true;
                                }
                            }
                        }
                    } //caso_5: lado superior
                    else if (f == 0) {
                        for (i = f; i < f + 2; i++) {
                            for (j = c - 1; j < c + 2; j++) {
                                if (layout[i][j] == '1') {
                                    return false;
                                } else {
                                    return true;
                                }
                            }
                        }
                    } //caso_5: lado inferior
                    else if (f == n - 1) {
                        for (i = f - 1; i < f + 1; i++) {
                            for (j = c - 1; j < c + 2; j++) {
                                if (layout[i][j] == '1') {
                                    return false;
                                } else {
                                    return true;
                                }
                            }
                        }
                    } //caso_5: lado izquierdo
                    else if (c == 0) {
                        for (i = f - 1; i < f + 2; i++) {
                            for (j = c; j < c + 2; j++) {
                                if (layout[i][j] == '1') {
                                    return false;
                                } else {
                                    return true;
                                }
                            }
                        }
                    } //caso_5: lado derecho
                    else if (c == n - 1) {
                        for (i = f - 1; i < f + 2; i++) {
                            for (j = c - 1; j < c + 1; j++) {
                                if (layout[i][j] == '1') {
                                    return false;
                                } else {
                                    return true;
                                }
                            }
                        }
                    }
                } else {
                    for (i = f - 1; i < f + 2; i++) {
                        for (j = c - 1; j < c + 2; j++) {
                            if (layout[i][j] == '1') {
                                return false;
                            } else {
                                return true;
                            }
                        }
                    }
                }
            }
        }
        return false;
    }
}
