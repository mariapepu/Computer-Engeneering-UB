/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package examenparcial2;

/**
 *
 * @author Sergio
 */
public class CompteBancari {

    private String nom;
    private String DNI;
    private int numCompte;
    private float saldo;

    public CompteBancari(String n, String DNI, int num, float s) {
        nom = n;
        this.DNI = DNI;
        numCompte = num;
        saldo = s;
    }

    public String getNom() {
        return nom;
    }

    public String getDNI() {
        return DNI;
    }

    public int getNumCompte() {
        return numCompte;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float s) {
        saldo = s;
    }

    public void retirarDiners(float quantitat) {
        saldo = saldo - quantitat;
    }

    public void ingressarDiners(float quantitat) {
        saldo = saldo + quantitat;
    }

    public void transferencia(float quantitat, CompteBancari desti) throws ExamenParcialExcepcio {
        //to be completed
        if (quantitat > this.saldo) {
            throw new ExamenParcialExcepcio("Error");
        }
        else{
            this.retirarDiners(quantitat);
            desti.ingressarDiners(quantitat);
            
        }
    }

    public String toString() {
        String aux = "";
        aux += "Nom: "
                + this.nom
                + " DNI: "
                + this.DNI
                + " Saldo: "
                + this.saldo
                + " Num: " + this.numCompte;
        return aux;
    }
}
