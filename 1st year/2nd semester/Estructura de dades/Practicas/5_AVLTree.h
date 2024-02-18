/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   AVLTree.h
 * Author: user
 *
 * Created on 9 de mayo de 2020, 10:29
 */

#ifndef AVLTREE_H
#define AVLTREE_H

#include "BinaryTreeNode.hpp"
#include "BinarySearchTree.hpp"
#include <string>
#include <iostream>
#include <cstdlib>
#include <vector>


using namespace std;

template <class K, class V>
class AVLTree : public BinarySearchTree<K, V>{
public:
    AVLTree();
    AVLTree(const AVLTree<K, V>& orig);
    virtual ~AVLTree();
    
    BinaryTreeNode<K, V>* add(const K& key, const V& value);
    
private:
    BinaryTreeNode<K, V>* add_aux(const K& key, const V& value, BinaryTreeNode<K,V>* node, int size) const;
    BinaryTreeNode<K,V>* getAlfa(BinaryTreeNode<K,V>* node);
    int factorBalance(BinaryTreeNode<K, V>* node);
    void rotacio_simple_esquerra(BinaryTreeNode<K, V>* node);
    void rotacio_simple_dreta(BinaryTreeNode<K, V>* node);
    void rotacio_dreta_esquerra(BinaryTreeNode<K, V>* node);
    void rotacio_esquerra_dreta(BinaryTreeNode<K, V>* node);
};

template <class K, class V>
AVLTree<K, V>::AVLTree() {
    
}

template <class K, class V>
AVLTree<K, V>::AVLTree(const AVLTree<K, V>& orig) {
    
}

template <class K, class V>
AVLTree<K, V>::~AVLTree() {
    
}

template <class K, class V>
BinaryTreeNode<K, V>* AVLTree<K, V>::add(const K& key, const V& value) {
    // Cas primer element
    if (this->isEmpty()) {
        this->p_root = new BinaryTreeNode<K, V>(key);
        this->p_root->addValue(value);
        cout << "S'ha insertat " << key << " com a root!" << endl;
        return this->p_root;
    } else {
        // Insertem element
        BinaryTreeNode<K, V>* node = add_aux(key, value, this->p_root, this->size());
        BinaryTreeNode<K, V>* alfa = getAlfa(node);
        // Si hi ha alfa, està desbalancejat i s'ha de balancejar
        if (alfa) {
            cout << "ALFA: " << alfa->getKey() << endl;
            // SIMPLE ESQUERRA
            // serà la de quan s'inserta el 75
            if (alfa->getKey() < node->getKey() and alfa->getRight()->getKey() < node->getKey()) {
                cout << alfa->getKey() << " " << node->getKey() << " " << alfa->getRight()->getKey() << " " << node->getKey() << endl;
                cout << "ROTACIO ESQUERRA" << endl;
                rotacio_simple_esquerra(alfa);
            }
            // SIMPLE DRETA
            else if (alfa->getKey() > node->getKey() and alfa->getLeft()->getKey() > node->getKey()) {
                cout << alfa->getKey() << " " << node->getKey() << " " << alfa->getLeft()->getKey() << " " << node->getKey() << endl;
                cout << "ROTACIO DRETA" << endl;
                rotacio_simple_dreta(alfa);
            }
            // DOBLE DRETA-ESQUERRA
            else if (alfa->getKey() < node->getKey() and alfa->getRight()->getKey() > node->getKey()) {
                cout << "ROTACIO DRETA-ESQUERRA" << endl;
                rotacio_dreta_esquerra(alfa);
            }
            // DOBLE ESQUERRA-DRETA
            else if (alfa->getKey() > node->getKey() and alfa->getLeft()->getKey() < node->getKey()) {
                cout << "ROTACIO ESQUERRA-DRETA" << endl;
                rotacio_esquerra_dreta(alfa);
            }
        }
        return node;
    }
}

template <class K, class V>
BinaryTreeNode<K, V>* AVLTree<K, V>::add_aux(const K& key, const V& value, BinaryTreeNode<K,V>* node, int size) const {
    // Si existeix, se li afegeix el nou valor
    if (key == node->getKey()) {
        node->addValue(value);
        cout << "La clau ja existia. S'ha afegit el valor " << value << " a " <<key << "! :)" << endl;
        return node;
    }
    // Si no existeix i hauria de situar-se cap a l'esquerra
    else if (key < node->getKey()) {
        if (node->hasLeft()){
            return add_aux(key, value, node->getLeft(), size);
        }
        else {
            BinaryTreeNode<K, V>* node = new BinaryTreeNode<K, V>(key);
            node->addValue(value);
            node->setParent(node);
            node->getParent()->setLeft(node);
            size++;
            cout << "S'ha afegit " << key << "!" << endl;
            return node;
        }
        return node;
    } 
    // Si no existeix i hauria de situar-se cap a la dreta
    else if (key > node->getKey()) {
        if (node->hasRight()){
            return add_aux(key, value, node->getRight(), size);
        }
        else {
            BinaryTreeNode<K, V>* node = new BinaryTreeNode<K, V>(key);
            node->addValue(value);
            node->setParent(node);
            node->getParent()->setRight(node);
            size++;
            cout << "S'ha insertat " << key << "!" << endl;
            return node;
        }
    }
    return node;
}

template <class K, class V>
BinaryTreeNode<K,V>* AVLTree<K, V>::getAlfa(BinaryTreeNode<K,V>* node) {
    while (node) {
        int fb = factorBalance(node);
        if (fb > 1 or fb < -1){
            return node;
        }
        else if (node->hasParent()){
            node = node->getParent();
        }
        else{
            return nullptr;
        }
    }
    return node;
}

template <class K, class V>
int AVLTree<K, V>::factorBalance(BinaryTreeNode<K, V>* node) {
    int esquerra, dreta;
    if (node->hasLeft()) esquerra = node->getLeft()->height();
    else esquerra = 0;
    if (node->hasRight()) dreta = node->getRight()->height();
    else dreta = 0;
    cout << esquerra << " - " << dreta <<" = "<< endl;
    return dreta-esquerra;
}

template <class K, class V>
void AVLTree<K, V>::rotacio_simple_esquerra(BinaryTreeNode<K,V>* node) {
    // Si té fills
    bool fills = false;
    BinaryTreeNode<K, V>* aux;
    if(node->getRight()->hasLeft()) {
        aux = node->getRight()->getLeft();
        fills = true;
    }
    if (node->isRoot()) {
        node->getRight()->setParent(nullptr);
        this->p_root = node->getRight();
    }
    else {
        node->getRight()->setParent(node->getParent());
        if (node->getRight()->getKey() < node->getParent()->getKey()) node->getParent()->setLeft(node->getRight());
        else if (node->getRight()->getKey() > node->getParent()->getKey()) node->getParent()->setRight(node->getRight());
    }
    node->setParent(node->getRight());
    node->getRight()->setLeft(node);
    if (fills) node->setRight(aux);
    else node->setRight(nullptr);
}

template <class K, class V>
void AVLTree<K, V>::rotacio_simple_dreta(BinaryTreeNode<K, V>* node) {
    // Si té fills
    bool fills = false;
    BinaryTreeNode<K, V>* aux;
    if (node->getLeft()->hasRight()) {
        aux = node->getLeft()->getRight();
        fills = true;
    }
    if (node->isRoot()) {
        node->getLeft()->setParent(nullptr);
        this->p_root = node->getLeft();
    }
    else {
        node->getLeft()->setParent(node->getParent());
        if (node->getLeft()->getKey() < node->getParent()->getKey()) node->getParent()->setLeft(node->getLeft());
        else node->getParent()->setRight(node->getLeft());
    }
    node->setParent(node->getLeft());
    node->getLeft()->setRight(node);
    if (fills) node->setLeft(aux);
    else node->setLeft(nullptr);
}

template <class K, class V>
void AVLTree<K, V>::rotacio_esquerra_dreta(BinaryTreeNode<K, V>* node) {
    node->setLeft(node->getLeft()->getRight());
    node->getLeft()->setLeft(node->getLeft()->getParent());
    node->getLeft()->getParent()->setParent(node->getLeft());
    node->getLeft()->setParent(node);
    node->getLeft()->getLeft()->setRight(nullptr);
    rotacio_simple_dreta(node);
}

template <class K, class V>
void AVLTree<K, V>::rotacio_dreta_esquerra(BinaryTreeNode<K, V>* node) {
    node->setRight(node->getLeft()->getLeft());
    node->getRight()->setRight(node->getRight()->getParent());
    node->getRight()->getParent()->setParent(node->getRight());
    node->getRight()->setParent(node);
    node->getRight()->getRight()->setLeft(nullptr);
    rotacio_simple_esquerra(node);
}

#endif /* AVLTREE_H */

