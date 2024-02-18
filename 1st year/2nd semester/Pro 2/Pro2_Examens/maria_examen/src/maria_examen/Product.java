package maria_examen;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Rocho
 */
public class Product {

    String nombre;
    int cantidad;
    double precio;

    public Product() {
        this.cantidad = 0;
        this.nombre = " ";
        this.precio = 0.00;
    }

    public String toString() {
        return nombre + " " + cantidad + " " + precio + "€";
    }

}
