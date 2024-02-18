/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   BSTree.h
 * Author: Maria
 *
 * Created on 24 / d’abril / 2020, 17:28
 */


#ifndef BSTREE_HPP
#define	BSTREE_HPP
#include "Position.hpp"
#include <string>
#include<iostream>
#include <cstdlib>
#include <vector>

using namespace std;
template <class K, class V>
class BSTree {
    public:
        //Constructores y destructores
        BSTree();
        BSTree(const BSTree &orig);
        virtual ~BSTree();
        //Consultores
        int size() const;
        int height();
        bool isEmpty();
        Position<K, V>* root();
        void showPreorder() const;
        void showPostorder() const;
        void showInorder() const;
        void showLeafNodes();
        bool booleanSearch(const K& element);
        int min();
        //Modificadores
        void insert(const K &element);
    private:
        //Constructores y destructores
        void copiador(Position<K, V> *p, const Position<K, V> *orig); //Soporte
        void destructor(Position<K, V> *p); //Soporte
        
        //Consultores
        int size (Position<K, V>* p) const; //Soporte
        int height(Position<K, V> *p); //Soporte
        void showLeafNodes(Position<K, V> *p); //Soporte
        void preorder(Position<K, V> *p) const; //Soporte
        void postorder(Position<K, V> *p) const; //Soporte
        void inorder(Position<K, V> *p) const; //Soporte
        int min(Position<K, V> *min); //Soporte
        bool booleanSearch(const K &key, Position<K, V> *p); //Soporte
        
        //Modificadores
        void insert(Position<K, V> *p, Position<K, V>* element); //Soporte
        
        //Atributos
        Position<K, V>* pRoot;
};
//Constructores

//Constructor vacío
template<class K, class V>
BSTree<K, V>::BSTree(){
    this->pRoot = nullptr;
}
//Copia
/**
 * Constructor copia.
 * @param orig
 */
template<class K, class V>
BSTree<K, V>::BSTree(const BSTree& orig){
    this->pRoot = new Position<K, V>(*orig.pRoot);
    copy(this->pRoot, orig.pRoot);
    std::cout << "Árbol copiado\n";
}
/**
 * Método de soporte para copiar el árbol, usamos recorrido preorden.
 * @param p
 * @param orig
 */
template<class K, class V>
void BSTree<K, V>::copiador(Position<K, V> *p, const Position<K, V> *orig){
    if (orig != nullptr){
        if(orig->hasRight()){
            p->setRight(new Position<K, V>(*orig->getRight()));
            p->getRight()->setParent(p);
            copy(p.getRight(), orig->getRight());
        }
        if(orig->hasLeft()){
            p->setLeft(new Position<K, V>(*orig->getLeft()));
            p->getLeft()->setParent(p);
            copy(p.getLeft(), orig->getLeft());    
        }
    }
}

//Destructor
/**
 * Método destructor.
 */
template<class K, class V>
BSTree<K, V>::~BSTree(){
    destructor(pRoot);
    cout << "Árbol eliminado\n";
}

/**
 * Método de soporte para destruir el árbol, usamos recorrido postorden.
 * @param p
 */
template<class K, class V>
void BSTree<K, V>::destructor(Position<K, V> *p){
    if(p != nullptr) {
        destructor(p->getRight());
        destructor(p->getLeft());
        //Vas visitando los nodos antes de borrarlos, por eso es postorden, bobo, que eres bobo Alberto.
        delete p;
    }
}
//Consultores

//Size
/**
 * Devuelve el tamaño del árbol.
 * @return 
 */
template<class K, class V>
int BSTree<K, V>::size() const{
    if (isEmpty()){
        return 0;
    } else {
        return 1 + size(this->pRoot);
    }
}

/**
 * Recorre el árbol desde el nodo dado para calcular su tamaño.
 * Se usa como soporte para calcular el tamaño de un árbol.
 * @param p
 * @return 
 */
template <class K, class V>
int BSTree<K, V>::size(Position<K, V>* p) const{
    int contador = 0;
    if (p != nullptr){
        if (p->hasRight()){
            contador += 1 + size(p->getRight());
        }
        if (p->hasLeft()){
            contador += 1 + size(p->getLeft());
        }
    }
    return contador;
}
/**
 * Devuelve la altura del árbol binario.
 * @return 
 */
template<class K, class V>
int BSTree<K, V>::height(){
    if (!isEmpty()){
        return 0;
    } else {
      height(this->pRoot);  
    }
}
/**
 * Devuelve la altura de un nodo concreto.
 * Se usa como soporte para devolver la altura del árbol.
 * @param p
 * @return 
 */
template<class K, class V>
int BSTree<K, V>::height(Position<K, V> *p){
    if (p == nullptr){
        return -1;
    }
    int getLeft = height(p.getLeft());
    int getRight = height(p.getRight());
    
    if(getLeft > getRight){
        return getLeft + 1;
    } else {
        return getRight + 1;
    }
}
/**
 * Devuvelve true si el árbol está vacío.
 * @return 
 */
template<class K, class V>
bool BSTree<K, V>::isEmpty(){
    return (this->pRoot == nullptr); 
}
/**
 * Devuelve el nodo Raíz.
 * @return 
 */
template<class K, class V>
Position<K, V>* BSTree<K, V>::root(){
    return this->pRoot;
}
/**
 * Imprime el árbol con Preorder.
 */
template<class K, class V>
void BSTree<K, V>::showPreorder() const{
    cout << "Preorden: {";
    preorder(this->pRoot);
    cout << "}" << endl;
}
/**
 * Método recursivo de soporte para preorder.
 * @param p
 */
template<class K, class V>
void BSTree<K, V>::preorder(Position<K, V> *p) const{
    if (p != nullptr){
        cout << p->getKey(); //SOLO SIRVE PARA COSAS BÁSICAS
        if (p->hasLeft()){
            cout << ", ";
            preorder(p->getLeft());
        }
        if (p->hasRight()){
            cout << ", ";
            preorder(p->getRight());
        }
    }
    
}
/**
 * Imprime el árbol con Postorder.
 */
template<class K, class V>
void BSTree<K, V>::showPostorder() const{
    cout << "Postorden: {";
    postorder(this->pRoot);
    cout << "}" << endl;
}
/**Método recursivo de soporte para postorder.
 * 
 * @param p
 */
template<class K, class V>
void BSTree<K, V>::postorder(Position<K, V>* p) const{
    if (p != nullptr){
        if (p->hasLeft()){
            postorder(p->getLeft());
            cout << ", ";
        }
        if (p->hasRight()){
            postorder(p->getRight());
            cout << ", ";
        }
        cout << p->getKey();
    }
}
/**
 * Imprime el árbol con Inorder.
 */
template<class K, class V>
void BSTree<K, V>::showInorder() const{
    cout << "Inorden: {";
    inorder(this->pRoot);
    cout << "}" << endl;
}
/**
 * Método recursivo de soporte para inorder.
 * @param p
 */
template<class K, class V>
void BSTree<K, V>::inorder(Position<K, V> *p) const{
    if (p != nullptr){
        if (p->hasLeft()){
            inorder(p->getLeft());
            cout << ", ";
        }
        cout << p->getKey(); //SOLO FUNCIONA PARA COSAS BÁSICAS
        if (p->hasRight()){
            cout << ", ";
            inorder(p->getRight());
        }
    }
}
/**
 * Devuelve todos los nodos externos del árbol.
 */
template<class K, class V>
void BSTree<K, V>::showLeafNodes() {
    cout << "Hojas = {";
    showLeafNodes(this->pRoot);
    cout << "\b\b" << "} " << std::endl;
}
/**
 * Método de soporte para mostrar los nodos externos.
 * @param p
 */
template<class K, class V>
void BSTree<K, V>::showLeafNodes(Position<K, V> *p){
    if (p->isLeaf()){
        cout << p->getKey() << ", "; //SOLO SIRVE PARA COSAS BÁSICAS
    } else {
        if (p->hasLeft()){
            showLeafNodes(p->getLeft());
        }
        if (p->hasRight()){
            showLeafNodes(p->getRight());
        }
    }
}
/**
 * Búsqueda de un key por ID (montañas).
 * @return 
 */
template<class K, class V>
bool BSTree<K, V>::booleanSearch(const K &key){
    if (!isEmpty){
        return booleanSearch(key, pRoot);
    }
    return false;
}
/**
 * Método de soporte para booleanSearch.
 * @param key
 * @param p
 * @return 
 */
template<class K, class V>
bool BSTree<K, V>::booleanSearch(const K &key, Position<K, V> *p){
    if (p == nullptr){
        return false;
    }
    
    if (key < p->getKey()){
       return booleanSearch(key, p->getLeft());
    } else if (key > p->getKey()){
        return booleanSearch(key, p->getRight());
    }
    return true;
}


/**
 * Función mínimo, clase de problemas.
 * @return 
 */
template<class K, class V>
int BSTree<K, V>::min() {
    Position<K, V> *newNodo = new Position<K, V>();
    if(!isEmpty()){
    newNodo = this->pRoot;
    if (newNodo.hasLeft()){
        return min(newNodo);
    } else {
        return newNodo->getKey();
    }
    } else {
        throw ("No hay árbol en el que buscar.");
    }
}
/**
 * Método de apoyo para la función mínimo, clase de problemas.
 * @param min
 * @return 
 */
template<class K, class V>
int BSTree<K, V>::min(Position<K, V> *min){
    if (min->hasLeft()){
       return min(min);
    } else {
        return min->getKey();
    }
}

//Modificadores
/**
 * Método para insertar keys en el árbol binario.
 * @param element
 */
template <class K, class V>
void BSTree<K, V>::insert(const K &element) {
    Position<K, V> *newNode = new Position<K, V>(element);
    if (isEmpty()) {
        this->pRoot = newNode;
        std::cout << "Inserta en el árbol el key: " << newNode->getKey() << std::endl;
    } else {
        insert(newNode, pRoot);
    }
}
/**
 * Método de soporte para insertar keys en el árbol binario.
 * @param newNodo
 * @param orig
 */
template<class K, class V>
void BSTree<K, V>::insert(Position<K, V> *newNodo, Position<K, V>* orig ){
    if (orig->getKey() < newNodo->getKey()){
        if(!orig->hasRight()){
            newNodo->setParent(orig);
            orig->setRight(newNodo);
            std::cout << "Inserta en el árbol el key: " << newNodo->getKey() << std::endl;
        } else{
            insert(newNodo, orig->getRight());
        }
    } else if (orig->getKey() > newNodo->getKey()){
        if (!orig->hasLeft()){
            newNodo->setParent(orig);
            orig->setLeft(newNodo);
            std::cout << "Inserta en el árbol el key: " << newNodo->getKey() << std::endl;
        } else{
            insert(newNodo, orig->getLeft());
        }
    } else {
        throw ("Key repetido, normalmente se podría insertar pero lo excluimos para no generar errores de búsqueda.");
    }
}


#endif	/* BINARYSEARCHTREE_HPP */