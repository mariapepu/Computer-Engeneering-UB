package buscamines;

/*
Joc del Buscamines:

Heu d'implementar el joc del buscamines on l'usuari entrarÃ  un nombre enter per terminal
i aleshores el programa crearÃ  un tauler nxn de tipus char amb n bombes colÂ·locades aleatÃ²riament.

L'usuari ha de, com a mÃ­nim, poder efectuar mitjanÃ§ant un menu, les segÃ¼ents operacions durant una partida:
1. Fer una aposta d'on es troba una bomba. Per fer-ho, es demanarÃ  per pantalla dos nombres enters, fila i columna.
2. Descobrir el contingut d'una celÂ·la on, 
    Si seleccionem una celÂ·la amb una bomba, perderem la partida i finalitzarem el programa.
    Si no contÃ© cap bomba, el programa calcularÃ  quantes bombes hi ha en les 8 celÂ·les immediatament contigÃ¼es (les 4 a en vertical i horitzontal, i les 4 en diagonal)
3. Imprimir per pantalla l'estat actual del joc.
    S'imprimirÃ  un '?' si no sabem que hi ha en una celÂ·la.
    S'imprimirÃ  una '*' si hem seleccionat aquella celÂ·la com a celÂ·la amb bomba.
    S'imprimirÃ  un int de 0 a 8 si hem descobert aquella celÂ·la (i no contÃ© cap bomba) indicant el nombre de bombes que tÃ© al seu costat.
4. Resoldre la partida. AixÃ² Ã©s, comprovar si hem colÂ·locat les '*' sobre les celÂ·les amb bomba i no ens hem deixat cap.
    Si s'ha realitzat tot correctament apareixerÃ  un missatge indicant que hem guanyat la partida.
    Si ens hem deixat alguna bomba sense marcar, apareixerÃ  un missatge indicant que hem perdut la partida, i ens mostrarÃ  on estaven les bombes.
    En ambdÃ³s casos, un cop realitzada aquesta opciÃ³, finalitzarem el programa correctament.
5. Sortir de la partida sense resoldre-la.
 */
import java.util.Arrays;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class BuscaMines {

    enum MenuOption {
        BET, UNBET, DISCOVER, PRINT, RESOLVE, EXIT;
    }

    public static void main(String[] args) {
        Scanner sc;
        int n, opt, numBets = 0;
        char[][] game;
        char[][] bombs;
        MenuOption option;

        sc = new Scanner(System.in);
        System.out.println("Board dimension (n): ");
        n = sc.nextInt();
        while (n <= 0) {
            System.out.println("Dimension cannot be negative or zero. Try again");
            System.out.println("Input for dimension n: ");
            n = sc.nextInt();
        }

        game = createLayout(n, '?');
        bombs = createLayout(n, ' ');
        placeBombs(bombs, n, n);
        System.out.println("The board: \n" + printLayout(game, n));
        System.out.println("There are " + n + " bombs, FIND THEM!!!");
        System.out.println(printLayout(bombs,n));
        printMenu();
        opt = sc.nextInt();
        option = MenuOption.values()[opt - 1];
        while (option != MenuOption.EXIT) {
            switch (option) {
                case BET:
                    /* YOUR CODE GOES HERE */
                    betBomb(game, n, sc);
                    break;
                
                case UNBET:
                    unbetBomb(game,n,sc);
                    break;

                case DISCOVER:
                    /* YOUR CODE GOES HERE */
                    discoverCellAndCheckNeighborhood(game, bombs, n, sc);
                    break;

                case PRINT:
                    System.out.println(printLayout(game, n));
                    break;

                case RESOLVE:
                    /* YOUR CODE GOES HERE */
                    resolve(game, bombs, n);
                    break;

                case EXIT:
                    System.out.println("Bye! :)");
                    break;
                default:
                    System.out.println("Not a valid option.");
            }
            printMenu();
            opt = sc.nextInt();
            option = MenuOption.values()[opt - 1];

        }
    }

    static void printMenu() {
        System.out.println();
        System.out.println("Welcome to Minesweeper!");
        System.out.println("1. Bet a bomb (B).");
        System.out.println("2. Unbet a bomb (B).");
        System.out.println("3. Discover a cell.");
        System.out.println("4. Print board.");
        System.out.println("5. Resolve Game.");
        System.out.println("6. Exit.");
        System.out.println("Choose an option:");
    }

    static int discoverCellAndCheckNeighborhood(char[][] layout, char[][] bombs, int n, Scanner sc) {
        /* YOUR CODE GOES HERE */
        int i, j, nearBombs = 48; //48 en ascii es el 1
        System.out.println("What cell do you want to discover?");
        System.out.println("Insert row");
        Scanner fila = new Scanner(System.in);
        int f = fila.nextInt();
        System.out.println("Insert column");
        Scanner columna = new Scanner(System.in);
        int c = columna.nextInt();
        //comprvar si esta dentro del tablero.
        while ((c > n || c < 0) || (f > n || f < 0)) {
            Scanner fila2 = new Scanner(System.in);
            f = fila2.nextInt();
            Scanner columna2 = new Scanner(System.in);
            c = columna2.nextInt();
        }
        /*comprovar si es una bomba. tendre q hacer el checkindexes
        if tiene bomba, fin de la partida*/
        if (bombs[f][c] == 'B') {
            layout[f][c] = 'B';
            System.out.println("BOOM!! Has perdut, has explotat una bomba.");
            System.out.println(printLayout(layout, n));
            System.out.println(printLayout(bombs, n)); // ho faig perque es vegi on eren, com en el joc d tota la vida
            System.exit(0); //para q acabe el juego
        } //else, descubrimos la celda
        else {
            /*comprovamos quantas bombas hay alrededor.
        recorremos las celdas de alrededor
        i sumamos las q encontremos a un contador.*/
            if (f == 0 || c == 0 || f == n - 1 || c == n - 1) {
                /*si esta en los bordes se saldria del for, 
                así que hago comprovacion manual a falta de 
                tiempo para pensar algo mejor.*/
                //caso_1: esquina si.
                if (f == 0 && c == 0) {
                    for (i = f; i < f + 2; i++) {
                        for (j = c; j < c + 2; j++) {
                            if (bombs[i][j] == 'B') {
                                nearBombs += 1;
                            } else {
                                nearBombs += 0;
                            }
                        }
                    }
                } //caso_2: esquina sd.
                else if (f == 0 && c == n - 1) {
                    for (i = f; i < f + 2; i++) {
                        for (j = c - 1; j < c + 1; j++) {
                            if (bombs[i][j] == 'B') {
                                nearBombs += 1;
                            } else {
                                nearBombs += 0;
                            }
                        }
                    }
                } //caso_3: esquina ii.
                else if (f == n - 1 && c == 0) {
                    for (i = f - 1; i < f + 1; i++) {
                        for (j = c; j < c + 2; j++) {
                            if (bombs[i][j] == 'B') {
                                nearBombs += 1;
                            } else {
                                nearBombs += 0;
                            }
                        }
                    }
                } //caso_4: esquina id.
                else if (f == n - 1 && c == n - 1) {
                    for (i = f - 1; i < f + 1; i++) {
                        for (j = c - 1; j < c + 1; j++) {
                            if (bombs[i][j] == 'B') {
                                nearBombs += 1;
                            } else {
                                nearBombs += 0;
                            }
                        }
                    }
                } //caso_5: lado superior
                else if (f == 0) {
                    for (i = f; i < f + 2; i++) {
                        for (j = c - 1; j < c + 2; j++) {
                            if (bombs[i][j] == 'B') {
                                nearBombs += 1;
                            } else {
                                nearBombs += 0;
                            }
                        }
                    }
                } //caso_5: lado inferior
                else if (f == n - 1) {
                    for (i = f - 1; i < f + 1; i++) {
                        for (j = c - 1; j < c + 2; j++) {
                            if (bombs[i][j] == 'B') {
                                nearBombs += 1;
                            } else {
                                nearBombs += 0;
                            }
                        }
                    }
                } //caso_5: lado izquierdo
                else if (c == 0) {
                    for (i = f - 1; i < f + 2; i++) {
                        for (j = c; j < c + 2; j++) {
                            if (bombs[i][j] == 'B') {
                                nearBombs += 1;
                            } else {
                                nearBombs += 0;
                            }
                        }
                    }
                } //caso_5: lado derecho
                else if (c == n - 1) {
                    for (i = f - 1; i < f + 2; i++) {
                        for (j = c - 1; j < c + 1; j++) {
                            if (bombs[i][j] == 'B') {
                                nearBombs += 1;
                            } else {
                                nearBombs += 0;
                            }
                        }
                    }
                }
            } else {
                for (i = f - 1; i < f + 2; i++) {
                    for (j = c - 1; j < c + 2; j++) {
                        if (bombs[i][j] == 'B') {
                            nearBombs += 1;
                        } else {
                            nearBombs += 0;
                        }
                    }
                }
            }
        }
        //cambiamos ? por lo q tengamos en el contador.
        layout[f][c] = (char) nearBombs;
        // fare q imprimeixi el tablero, mes que res q sera mes visual.
        System.out.println(printLayout(layout, n));
        return 0;
    }

    static void resolve(char[][] layout, char[][] bombs, int n) {
        /* YOUR CODE GOES HERE */
 /*comprovem si tots els * son sobre una bomba
        per fer-ho haurem de recorrer el taulell amb dos fors
        fer el check idexes cada cop que trobem un *
        if tots els * son bombes ha guanyat
        else, ha perdut*/
        //FUNCIONA!!
        int i, j, bombCounter = 0;
        for (i = 0; i < n; i++) {
            for (j = 0; j < n; j++) {
                if ((layout[i][j] == ('*')) && (bombs[i][j] == ('B'))) {
                    bombCounter++;
                }
                if ((layout[i][j] == ('*')) && (bombs[i][j] == ('?'))) {
                    System.out.println(printLayout(bombs, n));
                    System.out.println("Has perdut, no has trobat totes les bombes.");
                } else if ((layout[i][j] == ('?')) && (bombs[i][j] == ('B'))) {
                    System.out.println("Has perdut, no has trobat totes les bombes.");
                    System.out.println(printLayout(bombs, n));
                }
            }
        }
        if (bombCounter == n) {
            System.out.println("                0000000000000000\n" +
"             000                  0000\n" +
"          000                          00\n" +
"        00                               00\n" +
"     00       000000         000000       00\n" +
"    00     0000000000000   000000000000     00\n" +
"   0     0000                        0000     0\n" +
"  0                           000000           0\n" +
" 0                          00      00          0\n" +
" 0           000000        00  0000  00         0\n" +
" 0        000000000000     00 000000 00         0\n" +
" 0                         00 000000 00         0\n" +
" 0                         00  0000  00         0\n" +
" 0                          00      00          0\n" +
" 0                            000000            0\n" +
"  0                                 00     00  0\n" +
"   0           000                 0  0   0  0\n" +
"    0            0000              0  0  0  0\n" +
"      0            0000000         0  0 0  0\n" +
"        00                         0   0   00000\n" +
"          000                     0000000   0  0\n" +
"              000                0       0  0  0\n" +
"                 000000000000000 00  0000 00 00");
            
            System.out.println("HAS GUANYAT!! Has trobat totes les bombes.");
        }
        System.exit(0); //para q acabe el juego
    }

    static void betBomb(char[][] layout, int n, Scanner sc) {
        /* YOUR CODE GOES HERE */
        //FUNCIONA!
        int f, c;
        System.out.println("Where do you think the bomb is?");
        System.out.println("Insert row");
        Scanner fila = new Scanner(System.in);
        f = fila.nextInt();
        System.out.println("Insert column");
        Scanner columna = new Scanner(System.in);
        c = columna.nextInt();
        //comprovem si esta dins el tauler
        while ((c > n || c < 0) || (f > n || f < 0)) {
            Scanner fila2 = new Scanner(System.in);
            f = fila2.nextInt();
            Scanner columna2 = new Scanner(System.in);
            c = columna2.nextInt();
        }
        //cambiar ? per *
        layout[f][c] = '*';
        System.out.println(printLayout(layout, n));
    }
    
    static void unbetBomb(char[][] layout, int n, Scanner sc) {
        /* YOUR CODE GOES HERE */
        //FUNCIONA!
        int f, c;
        System.out.println("What bet do you want to undo?");
        System.out.println("Insert row");
        Scanner fila = new Scanner(System.in);
        f = fila.nextInt();
        System.out.println("Insert column");
        Scanner columna = new Scanner(System.in);
        c = columna.nextInt();
        //comprovem si esta dins el tauler
        while ((c > n || c < 0) || (f > n || f < 0)) {
            Scanner fila2 = new Scanner(System.in);
            f = fila2.nextInt();
            Scanner columna2 = new Scanner(System.in);
            c = columna2.nextInt();
        }
        //cambiar * per ?
        layout[f][c] = '?';
        System.out.println(printLayout(layout, n));
    }

    static char[][] createLayout(int n, char c) {
        char[][] layout = new char[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                layout[i][j] = c;
            }
        }
        return layout;

    }

    static void placeBombs(char[][] layout, int n, int numBombs) {
        /* YOUR CODE GOES HERE */
        //repartir les bombs en plan random
        //FUNCIONA!!
        int f, c, i;

        for (i = 0; i < numBombs; i++) {
            f = (int) (Math.random() * (n - 1 + 1) + 0);
            c = (int) (Math.random() * (n - 1 + 1) + 0);
            //fem aixo pq no coincideixi una B sobre una B
            if (layout[f][c] != 'B') {
                layout[f][c] = 'B';
            } else {
                i--;
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

    static void checkIndexes(int i, int j, int n, Scanner sc) {
        /* YOUR CODE GOES HERE */

    }

}
