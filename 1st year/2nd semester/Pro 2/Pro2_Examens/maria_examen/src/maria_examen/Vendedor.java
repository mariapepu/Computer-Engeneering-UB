/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package maria_examen;

/**
 *
 * @author Rocho
 */
public class Vendedor extends User implements Total {

    Product l;

    public Vendedor() {
        super();
        this.l = new Product();
    }

    @Override
    public float getTotal(){
        return (float) ((super.p.precio*super.p.cantidad*0.5)+(this.l.precio*this.l.cantidad*0.2));
    }
}
