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
public class User {
    String nombre;
    int id;
    Product p;

    public User() {
        this.nombre=" ";
        this.id=1;
        this.p= new Product();
        
    }
    
    public Product getProduct(){
        return this.p;
    }
    
    public void setProduct(Product p){
        this.p=p;
    }
           
}
