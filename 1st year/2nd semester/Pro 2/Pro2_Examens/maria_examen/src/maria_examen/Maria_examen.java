/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maria_examen;

import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Rocho
 */
public class Maria_examen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        ArrayList<Product> arrayList = new ArrayList<>();
        //Product[] array = new Product[3];
        for (int i = 0; i < 3; i++) {
            Product p = new Product();
            p.nombre = Integer.toString(i);
            p.cantidad = (i + 1) * 100;
            p.precio = (i + 1) * 200;
            arrayList.add(p);
        }

        Vendedor b = new Vendedor();
        Iterator a = arrayList.iterator();

        User yo = new User();

        yo.nombre = "Maria";
        yo.id = 123;

        while (a.hasNext()) {
            Product p = (Product) a.next();
            yo.setProduct(p);

            System.out.println("nombre: " + yo.nombre);
            System.out.println("id: " + yo.id);
            System.out.println("producto: " + yo.getProduct());

        }

        b.setProduct(arrayList.get(0));
        b.l = arrayList.get(1);

        System.out.println("Total: " + b.getTotal());
    }

}
