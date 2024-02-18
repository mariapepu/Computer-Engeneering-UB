/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   main.cpp
 * Author: user
 *
 * Created on 19 de abril de 2020, 16:29
 */

#include <cstdlib>
#include <iostream>

#include "BSTree.h"
#include "Position.h"

using namespace std;

/*
 * 
 */
int main(int argc, char** argv) {
    /* 1. Crear dos arbres (anomenats primer i segon) que emmagatzemin la clau
     * entera i valors enters. */
    BSTree<int, int>* primer = new BSTree<int, int>(); 
    cout << endl;
    /* 2. Inserir a l'arbre (primer) les claus d'un array anomenat testArray que
     * contindrà: int testArray [] = {2, 0, 8, 45, 76, 5, 3, 40 };
     * Com a valor inserteu el mateix que la clau. */
    int testArray [] = {2, 0, 8, 45, 76, 5, 3, 40 };
    for (int i = 0; i < (sizeof(testArray) / sizeof(int)); i++) {
        primer->insert(testArray[i], testArray[i]);
    }
    cout << endl;
    /* 3. Mostrar en preordre l’arbre (primer) per pantalla
     * Preordre = {2, 0, 8, 5, 3, 45, 40, 76} */
    primer->printPreOrder();
    cout << endl;
    /* 4. Mostrar en postordre l’arbre (primer) per pantalla
     * Postordre = { 0, 3, 5, 40, 76, 45, 8, 2} */
    primer->printPostOrder();
    cout << endl;
    /* 5. Fer una còpia de l’arbre primer sobre el segon arbre binari copiat */
    BSTree<int, int> segon(*primer);
    cout << endl;
    /* 6. Cridar a la funció primer.identicalTree(segon) Cert */
    // NO DONA EL RESULTAT ESPERAT :(
    if (primer->identicalTree(segon)) cout << "Cert!!" << endl;
    else cout << "Fals!!" << endl;
    cout << endl;
    /* 7. Afegir a l’arbre primer l’element amb clau 1
     * Inserta a l’arbre l’element 1 */
    primer->insert(1, 1);
    cout << endl;
    /* 8. Cridar a la funció segon.identicalTree(primer) Fals */
    if (segon.identicalTree(*primer)) cout << "Cert!!" << endl;
    else cout << "Fals!!" << endl;
    cout << endl;
    /* 9 Eliminar l'arbre */
    primer->~BSTree();
    return 0;
    
    /*
     * COMPLEXITATS:
     * Classe position:
     * Position(const K key) -------------------------------> O(1)
     * Position(const Position<K, V>& orig) ----------------> O(1)
     * virtual ~Position() ---------------------------------> O(1)
     * int depth() const -----------------------------------> O(n)
     * int height() const ----------------------------------> O(n)
     * const K& getKey() const -----------------------------> O(1)
     * const vector<V>& getValues() const ------------------> O(1)
     * Position<K, V>* parent() const ----------------------> O(1)
     * Position<K, V>* left() const ------------------------> O(1)
     * Position<K, V>* right() const -----------------------> O(1)
     * void setParent(Position<K, V>* parent) --------------> O(1)
     * void setLeft(Position<K, V>* left) ------------------> O(1)
     * void setRight(Position<K, V>* right) ----------------> O(1)
     * bool isRoot() const ---------------------------------> O(1)
     * bool isLeaf() const ---------------------------------> O(1)
     * void addValue(const V& value) -----------------------> O(1)
     * bool operator==(const Position<K, V>& other) const --> O(n)
     * 
     * Classe BSTree:
     * BSTree() --------------------------------------------> O(1)
     * BSTree(const BSTree<K, V>& orig); -------------------> O(n)
     * ~BSTree() -------------------------------------------> O(n)
     * isEmpty() const -------------------------------------> O(1)
     * getRoot() const -------------------------------------> O(1)
     * size() const ----------------------------------------> O(n)
     * height() const --------------------------------------> O(n)
     * insert(&key, &value) --------------------------------> O(n)
     * contains(const K& key) const ------------------------> O(n)
     * vector<V>& getValues(const K& key) const ------------> O(n)
     * printPreOrder(const Position<K, V> *node) const -----> O(n)
     * printPostOrder(const Position<K, V> *node) const ----> O(n)
     * identicalTree(const BSTree<K, V>& other) const ------> O(n)
     * _search(const K& key) const -------------------------> O(n)
     * _search_recursiu(&key, *pos) const ------------------> O(n)
     * size_recursiu(Position<K, V>* pos) const ------------> O(n)
     * insert_recursiu(&key, &value, *pos, &size) const ----> O(n)
     * identicalTree_rec(&other, *other_pos, *pos) const ---> O(n)
     * void printPreOrder_recursiu(*node) const ------------> O(n)
     * void printPostOrder_recursiu(*node) const------------> O(n)
     * void copia_recursiu(*orig, *pos) --------------------> O(n)
     * void destructor_recursiu(Position<K, V>* pos) -------> O(n)
     */

}

