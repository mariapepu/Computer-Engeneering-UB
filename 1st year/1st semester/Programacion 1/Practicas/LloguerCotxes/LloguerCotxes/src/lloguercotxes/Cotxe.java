package lloguercotxes;


//aquestes son les caracteristiques dels cotxes que tindrem.
public class Cotxe {

    public int iD;
    public String marca;
    public String model;
    public String color;
    public float preuPerDia;
    public boolean llogat;
    public int numDiesLloguer;

    public Cotxe(int iD, String marca, String model, String color, float preuPerDia, boolean llogat, int numDiesLloguer) {
        this.iD = iD;
        this.marca = marca;
        this.model = model;
        this.color = color;
        this.preuPerDia = preuPerDia;
        this.llogat = llogat;
        this.numDiesLloguer = numDiesLloguer;
    }
    
    //el cotxe esta o no llogat
    public boolean estaLlogat() {
        return llogat;
    }

    //llogar un cotxe
    public void llogar(int numDiesLloguer) {
        this.numDiesLloguer = numDiesLloguer;
        this.llogat = true;
    }

    //multipliquem el preu per dia per el numero de dies per saber el preu
    public float getPreuTotalLloguer() {
        return preuPerDia * numDiesLloguer;
    }

}
