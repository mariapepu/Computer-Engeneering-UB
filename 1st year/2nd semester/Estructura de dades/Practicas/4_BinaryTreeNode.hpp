/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   BinaryTreeNode.h
 * Author: Maria
 *
 * Created on 29 / d’abril / 2021, 18:03
 */

#ifndef BINARYTREENODE_HPP
#define BINARYTREENODE_HPP
#include <string>
#include <iostream>
#include <cstdlib>
#include <vector>

using namespace std;

template <class K, class V>
class BinaryTreeNode {
public:
    BinaryTreeNode();
    BinaryTreeNode(const K& key);
    BinaryTreeNode(const BinaryTreeNode<K, V>& orig);
    virtual ~BinaryTreeNode();

    /* Modificadors */
    void setParent(BinaryTreeNode<K, V>* parent); //modifica l’adreça del pare de la position
    void setLeft(BinaryTreeNode<K, V>* left); //modifica l’adreça del fill esquerra de la position
    void setRight(BinaryTreeNode<K, V>* right); //modifica l’adreça del fill dret de la position
    // Declareu-hi aquí els modificadors (setters) dels atributs que manquen

    /* Consultors */
    BinaryTreeNode<K, V>* getParent() const; //retorna l’adreça del pare d’una position
    BinaryTreeNode<K, V>* getLeft() const; //retorna l’adreça del fill esquerra de la position
    BinaryTreeNode<K, V>* getRight() const; //retorna l’adreça del fill dret de la position
    const K& getKey() const;
    const vector<V>& getValues() const;
    // Declareu-hi aquí els consultors (getters) dels atributs que manquen
    /* Operacions */
    bool isRoot() const;
    bool hasLeft() const;
    bool hasRight() const;
    bool hasParent() const;
    bool isLeaf() const;
    
    void addValue(const V& v);
    
    bool operator==(const BinaryTreeNode<K, V>& node) const;
    int height() const;

private:
    BinaryTreeNode<K, V>* parent;
    BinaryTreeNode<K, V>* left;
    BinaryTreeNode<K, V>* right;
    K key;
    vector<V> values;
    // Afegiu-hi aquí els atributs que manquen
};
//Constructors

template<class K, class V>
BinaryTreeNode<K, V>::BinaryTreeNode() {
    this->parent = nullptr;
    this->left = nullptr;
    this->right = nullptr;

}

template<class K, class V>
BinaryTreeNode<K, V>::BinaryTreeNode(const K& key) {
    this->key = key;
    this->parent = nullptr;
    this->left = nullptr;
    this->right = nullptr;
}

template<class K, class V>
BinaryTreeNode<K, V>::BinaryTreeNode(const BinaryTreeNode& orig) {
    this->key = orig.key;
    this->left = nullptr;
    this->right = nullptr;
    this->parent = nullptr;
}

template<class K, class V>
BinaryTreeNode<K, V>::~BinaryTreeNode() {
    this->right = nullptr;
    this->left = nullptr;
    this->parent = nullptr;
    cout << "Eliminant node " << key << endl;

}

//Consultors

template<class K, class V>
BinaryTreeNode<K, V>* BinaryTreeNode<K, V>::getRight() const {
    return this->right;
}

template<class K, class V>
BinaryTreeNode<K, V>* BinaryTreeNode<K, V>::getLeft() const {
    return this->left;
}

template<class K, class V>
BinaryTreeNode<K, V>* BinaryTreeNode<K, V>::getParent() const {
    return this->parent;
}

template<class K, class V>
bool BinaryTreeNode<K, V>::hasRight() const {
    return (right != nullptr);
}

template<class K, class V>
bool BinaryTreeNode<K, V>::hasParent() const {
    return (parent != nullptr);
}

template<class K, class V>
bool BinaryTreeNode<K, V>::hasLeft() const {
    return (left != nullptr);
}

template<class K, class V>
bool BinaryTreeNode<K, V>::isRoot() const {
    return (parent != nullptr);
}

template<class K, class V>
bool BinaryTreeNode<K, V>::isLeaf() const {
    return (left == right && left == nullptr);
}

template<class K, class V>
const K& BinaryTreeNode<K, V>::getKey() const {
    return this->key;
}

template <class K, class V>
const vector<V>& BinaryTreeNode<K, V>::getValues() const {
    return this->values;
}

//Modificadors

template <class K, class V>
void BinaryTreeNode<K, V>::setRight(BinaryTreeNode<K, V>* newRight) {
    this->right = newRight;
}

template <class K, class V>
void BinaryTreeNode<K, V>::setLeft(BinaryTreeNode<K, V>* newLeft) {
    this->left = newLeft;
}

template <class K, class V>
void BinaryTreeNode<K, V>::setParent(BinaryTreeNode<K, V>* newParent) {
    this->parent = newParent;
}

template <class K, class V>
void BinaryTreeNode<K, V>::addValue(const V& value) {
    this->values.push_back(value);
}

// Desde arriba
template <class K, class V>
int BinaryTreeNode<K, V>::height() const {
    int esq = 0, dreta = 0;
    if (hasLeft()) esq = getLeft()->height();
    if (hasRight()) dreta = dreta = getRight()->height(); 
    if(esq > dreta) return esq + 1;
    else return dreta + 1;
}

#endif /* BINARYTREENODE_HPP */

