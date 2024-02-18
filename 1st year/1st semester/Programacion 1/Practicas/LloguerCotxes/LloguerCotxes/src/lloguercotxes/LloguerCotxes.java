//
// Maria Pérez Pullès
// 18/12/19
//
//
package lloguercotxes;

import java.util.Scanner;

public class LloguerCotxes {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int id, dies;
        String marca, model, color;
        float preu;
        int MAX_COTXES = 100;
        int opcio;
        Scanner sc = new Scanner(System.in);
        TaulaCotxe taula = new TaulaCotxe(MAX_COTXES);
        do {
            menuOpcions();
            System.out.println("Quina opcio vols? la resposta ha de ser un nombre");
            opcio = sc.nextInt();
            switch (opcio) {
                case 1:
                    System.out.println("Introdueix les dades seguents: ");
                    System.out.println("Marca");
                    marca = sc.next();
                    System.out.println("Model");
                    model = sc.next();
                    System.out.println("Color");
                    color = sc.next();
                    System.out.println("Preu");
                    preu = sc.nextFloat();
                    taula.afegir(marca, model, color, preu);
                    break;

                case 2:
                    taula.veureDisponibles();
                    break;
                case 3:
                    taula.veureLlogats();
                    break;
                case 4:
                    taula.veureDisponibles();
                    System.out.println("Introdueix l'id del cotxe que vols eliminar");
                    id = sc.nextInt();
                    taula.esborrar(id);
                    break;
                case 5:
                    taula.veureDisponibles();
                    System.out.println("Introdueix l'ID del cotxe que vols llogar");
                    id = sc.nextInt();
                    System.out.println("Introdueix els dies del cotxe que vols llogar-ho");
                    dies = sc.nextInt();
                    taula.llogar(id, dies);
                    break;
                case 6:
                    taula.veureLlogats();
                    System.out.println("Introdueix l'ID del cotxe que vols lliurar");
                    id = sc.nextInt();
                    taula.lliurar(id);
                    break;
                case 7:
                    taula.veureLlogats();
                    System.out.println("Introdueix l'ID del cotxe que vols veure");
                    id = sc.nextInt();
                    taula.veurePreu(id);
                    break;

            }
        } while (opcio != 8);
        System.out.println("Fins un altre");

    }

    public static void menuOpcions() {
        System.out.println("1. Afegir cotxe.\n"
                + "2. Veure cotxes disponibles.\n"
                + "3. Veure cotxes llogats.\n"
                + "4. Esborrar cotxe.\n"
                + "5. Llogar Cotxe.\n"
                + "6. Lliurar cotxe.\n"
                + "7. Veure preu lloguer.\n"
                + "8. Sortir.");
    }
}
