package lloguercotxes;

public class TaulaCotxe {

    //iniciem el array de coches. maxim 100 coxes. coxes inicials 0
    public Cotxe[] parking;
    final int maxim = 100;
    int numCotxes = 0;

    public TaulaCotxe(int limit) {
        parking = new Cotxe[limit];
    }

    // metode que ens mostra el preu en llogar un cotxe
    public void veurePreu(int id) {
        if (this.parking[id].estaLlogat()) {
            System.out.println(this.parking[id].getPreuTotalLloguer());
        } else {
            System.out.println("No esta llogat.\n");
        }
    }

    //metode per llogar el cotxe
    void llogar(int id, int dies) {
        if (!this.parking[id].estaLlogat()) {
            this.parking[id].llogar(dies);
            System.out.println("Has llogat el cotxe amb el ID " + id);
        } else {
            System.out.println("Aquest cotxe ja esta llogat.\n");
        }
    }

    //metode per "desllogar" un cotxe
    public void lliurar(int id) {
        if (this.parking[id].estaLlogat()) {
            this.parking[id].llogat = false;
        } else {
            System.out.println("No estava llogat.");
        }
    }

    //metode per afegir cotxes
    public void afegir(String marca, String model, String color, float preu) {
        if (parking.length > numCotxes) {
            this.parking[numCotxes] = new Cotxe(numCotxes, marca, model, color, preu, false, 0);
            numCotxes++;
        } else {
            System.out.println("El parking esta ple.\n");
        }
    }

    //metodeper esborrar cotxes
    public void esborrar(int id) {
        if (parking[id] == null) {
            System.out.println("El cotxe no existeix.\n");
        } else {
            parking[id] = null;
            for (int i = id; i < numCotxes - 1; i++) {
                parking[id] = parking[id + 1];
                parking[id].iD = i;
            }
            parking[numCotxes - 1] = null;
            numCotxes--;
        }

    }

    //metode per veure els cotxes disponibles
    public void veureDisponibles() {
        if (numCotxes == 0) {
            System.out.println("No hay cotxes en el parking\n");
        } else {
            System.out.println("Ara es mostraran els IDs dels cotxes disponibles: ");
            for (int i = 0; i < numCotxes; i++) {
                if (!parking[i].estaLlogat()) {
                    System.out.print(parking[i].iD + " ");
                }
            }
            System.out.println("\n");
        }

    }

    //veure els cotxes llogats
    public void veureLlogats() {
        if (numCotxes == 0) {
            System.out.println("No hay cotxes en el parking\n");
        } else {
            System.out.println("Ara es mostraran els IDs dels cotxes llogats: ");
            for (int i = 0; i < numCotxes; i++) {
                if (parking[i].estaLlogat()) {
                    System.out.print(parking[i].iD + " ");
                }
            }
            System.out.println("\n");
        }
    }
}
