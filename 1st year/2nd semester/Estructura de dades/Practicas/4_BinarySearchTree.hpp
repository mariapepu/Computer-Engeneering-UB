/*
 * To change this license header, choose License Headers in Project Properties->
 * To change this template file, choose Tools | Templates
 * and open the template in the editor->
 */

/* 
 * File:   BinarySearchTree->h
 * Author: Maria
 *
 * Created on 29 / d’abril / 2021, 18:01
 */

#ifndef BINARYSEARCHTREE_HPP
#define BINARYSEARCHTREE_HPP
#include "BinaryTreeNode.hpp"
#include <string>
#include <iostream>
#include <cstdlib>
#include <vector>

using namespace std;

template <class K, class V>
class BinarySearchTree {
public:
    BinarySearchTree();
    BinarySearchTree(const BinarySearchTree<K, V>& orig);
    virtual ~BinarySearchTree();
    //Constructors y destructors aux
    void copiador(BinaryTreeNode<K, V> *p, const BinaryTreeNode<K, V> *orig); //Soport
    void destructor(BinaryTreeNode<K, V> *p); //Soport

    bool isEmpty() const;
    int size() const;
    int size_rec(BinaryTreeNode<K, V>* p) const; //Soport
    int height() const;
    int height_rec(BinaryTreeNode <K, V> * node) const; //Soport

    BinaryTreeNode<K, V>* add(const K& k, const V& value);
    void add_aux(BinaryTreeNode<K, V> *newNode, BinaryTreeNode<K, V>* orig, const V& value); //Soport
    bool has(const K& k) const;
    bool rec_has(const K &key, BinaryTreeNode<K, V> *p); //Soport
    const vector<V>& valuesOf(const K& k) const;

    void showKeysPreorder(const BinaryTreeNode<K, V>* n) const;
    void showKeysInorder(const BinaryTreeNode<K, V>* n) const;
    void showKeysPostorder(const BinaryTreeNode<K, V>* n) const;

    void preorder(const BinaryTreeNode<K, V> *p) const; //Soport
    void inorder(const BinaryTreeNode<K, V> *p) const; //Soport
    void postorder(const BinaryTreeNode<K, V> *p) const; //Soport

    bool equals(const BinarySearchTree<K, V>& other) const;
    bool equals_rec(const BinarySearchTree<K, V>& other, BinaryTreeNode<K, V>* other_p, BinaryTreeNode<K, V>* p) const;
    const vector<BinaryTreeNode<K, V>*>& getLeafs() const;
    vector<BinaryTreeNode<K, V>*>& getLeafs_aux(BinaryTreeNode<K, V>* p);
    BinaryTreeNode<K, V>* getRoot() const;

protected:
    BinaryTreeNode<K, V>* p_root;
    BinaryTreeNode<K, V>* find(const K& k) const;

};

//Constructor buit

template<class K, class V>
BinarySearchTree<K, V>::BinarySearchTree() {
    this->p_root = nullptr;
    cout << "\narbre binari creat\n";
}

/*
 * Constructor copia
 */
template<class K, class V>
BinarySearchTree<K, V>::BinarySearchTree(const BinarySearchTree& orig) {
    if (orig.isEmpty()) throw runtime_error("Ups! Està buit :(");
    this->p_root = new BinaryTreeNode<K, V>(*orig.p_root);
    copiador(p_root, orig.p_root);
    cout << "Arbre copiat!" << endl;
}

/*
 * Métode de soport copiar arbre, usem recorregut preorder
 */
template<class K, class V>
void BinarySearchTree<K, V>::copiador(BinaryTreeNode<K, V> *p, const BinaryTreeNode<K, V> *orig) {
    if (orig) {
        if (orig->hasRight()) {
            p->setRight(new BinaryTreeNode<K, V>(*orig->getRight()));
            p->getRight()->setParent(p);
            copiador(p->getRight(), orig->getRight());
        }
        if (orig->hasLeft()) {
            p->setLeft(new BinaryTreeNode<K, V>(*orig->getLeft()));
            p->getLeft()->setParent(p);
            copiador(p->getLeft(), orig->getLeft());
        }
    }
}

/*
 * Métode destructor
 */
template<class K, class V>
BinarySearchTree<K, V>::~BinarySearchTree() {
    cout << "destruïnt arbre binari\n";
    destructor(p_root);
    cout << "Arbre binari destruït\n";
}

/*
 *aux per borrar arbre, usem recorregut postorder
 */
template<class K, class V>
void BinarySearchTree<K, V>::destructor(BinaryTreeNode<K, V> *p) {
    if (p != nullptr) {
        destructor(p->getRight());
        destructor(p->getLeft());
        delete p;
    }
}

template<class K, class V>
bool BinarySearchTree<K, V>::isEmpty() const {
    return (this->p_root == nullptr);
}

//metode size

template<class K, class V>
int BinarySearchTree<K, V>::size() const {
    if (isEmpty()) {
        return 0;
    } else {
        return 1 + size_rec(p_root);
    }
}

/*
 * metode recursiu per tornar el tamany
 */
template <class K, class V>
int BinarySearchTree<K, V>::size_rec(BinaryTreeNode<K, V>* p) const {
    int contador = 0;
    if (p != nullptr) {
        if (p->hasRight()) {
            contador += 1 + size_rec(p->getRight());
        }
        if (p->hasLeft()) {
            contador += 1 + size_rec(p->getLeft());
        }
    }
    return contador;
}

/*
 Metode per calcular l'altura
 */
template<class K, class V>
int BinarySearchTree<K, V>::height() const {
    if (isEmpty()) {
        return 0;
    } else {
        //return height_rec(p_root);
        return this->p_root->height();
    }
}

/*
 * metode auxiliar recursiu per a l'altura
 */
template <class K, class V>
int BinarySearchTree<K, V>::height_rec(BinaryTreeNode <K, V> * node) const {
    if (node == nullptr) {
        return -1;
    } else {
        int getLeft = 0, getRight = 0;

        if (node->hasLeft()) {
            int getLeft = height_rec(node->getLeft());
        }
        if (node->hasRight()) {
            int getRight = height_rec(node->getRight());
        }

        if (getLeft > getRight) {
            return getLeft + 1;
        } else {
            return getRight + 1;
        }
    }
}

/*
 * afegir
 */
template <class K, class V>
BinaryTreeNode<K, V> * BinarySearchTree<K, V>::add(const K &key, const V& value) {
    BinaryTreeNode<K, V> *newNode = new BinaryTreeNode<K, V>(key);
    if (isEmpty()) {
        newNode->addValue(value);
        this->p_root = newNode;
        cout << "Inserta a l'arbre element: " << newNode->getKey() << endl;
        return this->p_root;
    } else {
        this->add_aux(newNode, p_root, value);
        return newNode;
    }
}

/*
 * soport per afegir
 */
template<class K, class V>
void BinarySearchTree<K, V>::add_aux(BinaryTreeNode<K, V> *newNode, BinaryTreeNode<K, V>* orig, const V& value) {
    if (orig->getKey() < newNode->getKey()) {
        if (!orig->hasRight()) {
            newNode->setParent(orig);
            orig->setRight(newNode);
            newNode->addValue(value);
            cout << "Inserta a l'arbre element: " << newNode->getKey() << endl;
        } else {
            add_aux(newNode, orig->getRight(), value);
        }
    } else if (orig->getKey() > newNode->getKey()) {
        if (!orig->hasLeft()) {
            newNode->setParent(orig);
            orig->setLeft(newNode);
            newNode->addValue(value);
            cout << "Inserta a l'arbre element: " << newNode->getKey() << endl;
        } else {
            add_aux(newNode, orig->getLeft(), value);
        }
    } else {
        //el key esta repetit, afegim els values al key;
        //d'aquesta manera estic afegin al node original els nous valors
        //pertant, no insereixo un node nou nomes modifico el vector del node q ja existia amb aquella key
        orig->addValue(value);
        cout << "element ja existeix, afegim valor en el element: " << newNode->getKey() << endl;
    }
}

/*
 * Búsqueda de un key por ID (montañas)
 */
template<class K, class V>
bool BinarySearchTree<K, V>::has(const K &k) const {
    if (!isEmpty) {
        return rec_has(k, p_root);
    }
    return false;
}

/*
 * Métode de soport rec_has
 */
template<class K, class V>
bool BinarySearchTree<K, V>::rec_has(const K &key, BinaryTreeNode<K, V> *p) {
    if (p == nullptr) {
        return false;
    }

    if (key < p->getKey()) {
        return rec_has(key, p->getLeft());
    } else if (key > p->getKey()) {
        return rec_has(key, p->getRight());
    }
    return true;
}

/*
 *obtenir valors de un node
 */
template<class K, class V>
const vector<V>& BinarySearchTree<K, V>::valuesOf(const K& k) const {
    if (has(k)) {
        return find(k)->getValues();
    } else {
        throw runtime_error("la clau no existeix");
    }
}

/*
 *imprimir arbre preorder
 */
template<class K, class V>
void BinarySearchTree<K, V>::showKeysPreorder(const BinaryTreeNode<K, V>* n) const {
    if (isEmpty()) {
        throw runtime_error("L'arbre està buit :(");
    } else {
        // Si no hem sol·licitat el node d'inici, li assignem el root
        if (n == nullptr) {
            n = p_root;
        }
        cout << "{";
        preorder(n);
        cout << "}" << endl;
    }
}

/*
 *soport per preorder
 */
template<class K, class V>
void BinarySearchTree<K, V>::preorder(const BinaryTreeNode<K, V> *p) const {
    if (p != nullptr) {
        cout << p->getKey(); //SOLO SIRVE PARA COSAS BÁSICAS
        if (p->hasLeft()) {
            cout << ", ";
            preorder(p->getLeft());
        }
        if (p->hasRight()) {
            cout << ", ";
            preorder(p->getRight());
        }
    }

}

/*
 * Impriemix l'arbre Inorder
 */
template<class K, class V>
void BinarySearchTree<K, V>::showKeysInorder(const BinaryTreeNode<K, V>* n) const {
    if (isEmpty()) {
        throw runtime_error("L'arbre està buit :(");
    } else {
        // Si no hem sol·licitat el node d'inici, li assignem el root
        if (n == nullptr) {
            n = p_root;
        }
        cout << "{";
        inorder(n);
        cout << "}" << endl;
    }
}

/*
 * Métode recursiu per imprimir inorder
 */
template<class K, class V>
void BinarySearchTree<K, V>::inorder(const BinaryTreeNode<K, V> *p) const {
    if (p != nullptr) {
        if (p->hasLeft()) {
            inorder(p->getLeft());
            cout << ", ";
        }
        cout << p->getKey(); //SOLO FUNCIONA PARA COSAS BÁSICAS
        if (p->hasRight()) {
            cout << ", ";
            inorder(p->getRight());
        }
    }
}

/*
 * imprimeix en PostOrder
 */
template<class K, class V>
void BinarySearchTree<K, V>::showKeysPostorder(const BinaryTreeNode<K, V>* n) const {
    if (isEmpty()) {
        throw runtime_error("L'arbre està buit :(");
    } else {
        // Si no hem sol·licitat el node d'inici, li assignem el root
        if (n == nullptr) {
            n = p_root;
        }
        cout << "{";
        postorder(n);
        cout << "}" << endl;
    }
}

/*
 *  postorder suport
 */
template<class K, class V>
void BinarySearchTree<K, V>::postorder(const BinaryTreeNode<K, V>* p) const {
    if (p != nullptr) {
        if (p->hasLeft()) {
            postorder(p->getLeft());
            cout << ", ";
        }
        if (p->hasRight()) {
            postorder(p->getRight());
            cout << ", ";
        }
        cout << p->getKey();
    }
}

/*
 *comparar dos arbres
 */
template <class K, class V>
bool BinarySearchTree<K, V>::equals(const BinarySearchTree<K, V>& other) const {
    if (size() != other.size())return false;
    return equals_rec(other, other.p_root, p_root);
}

/*
 *auxiliar per comparar arbres
 */
template <class K, class V>
bool BinarySearchTree<K, V>::equals_rec(const BinarySearchTree<K, V>& other, BinaryTreeNode<K, V>* other_p, BinaryTreeNode<K, V>* p) const {
    // Les claus no coincideixen
    if (p->getKey() != other_p->getKey()) return false;
    // Una es fulla i l'altre no
    if ((p->isLeaf() and !other_p->isLeaf()) or (!p->isLeaf() and other_p->isLeaf())) return false;
    // Cap de les dues és fulla
    if (!p->isLeaf() and !other_p->isLeaf()) {
        if (p->getLeft() && other_p->getLeft()) {
            return equals_rec(other, other_p->getLeft(), p->getLeft());
        } else if ((!p->getLeft() && other_p->getLeft()) || (p->getLeft() && !other_p->getLeft())) return false;
        if (p->getRight() && other_p->getRight()) {
            return equals_rec(other, other_p->getRight(), p->getRight());
        } else if ((!p->getRight() && other_p->getRight()) || (p->getRight() && !other_p->getRight())) return false;
    }
    return true;
}

/*
 * trobar un key
 */
template <class K, class V>
BinaryTreeNode<K, V>* BinarySearchTree<K, V>::find(const K& key) const {
    BinaryTreeNode<K, V>* p = p_root;
    if (!has(key) || isEmpty()) {
        throw runtime_error("No existeix l'element");
    } else {
        while (p->getKey() != key) {
            if (key < p->getKey()) {
                //visitamos subarbol izq
                p = p->getLeft();
            }
            if (key > p->getKey()) {
                //visitamos subarbol derecho
                p = p->getRight();
            }
        }
        //en ppi ara p->getKey == key es a dir que l'hem trobat
        return p;
    }
}

/*
 * obtenir fulles
 */
template<class K, class V>
const vector<BinaryTreeNode<K, V>*>& BinarySearchTree<K, V>::getLeafs() const {
    return getLeafs_aux(p_root);

}

/*
 * obtenir fulles aux
 */
template<class K, class V>
vector<BinaryTreeNode<K, V>*>& BinarySearchTree<K, V>::getLeafs_aux(BinaryTreeNode<K, V>* p) {
    vector<BinaryTreeNode<K, V>*>& _leafs;
    if (p != nullptr) {
        if (p->hasLeft()) {
            getLeafs_aux(p->getLeft());
        }
        if (p->hasRight()) {
            getLeafs_aux(p->getRight());
        }
        if (p->isLeaf()) {
            _leafs->push_back(p);
        }
    }
    return _leafs;
}

/*
 *  Retorna l’adreça del root
 */
template <class K, class V>
BinaryTreeNode<K, V>* BinarySearchTree<K, V>::getRoot() const {
    return this->p_root;
}

#endif /* BINARYSEARCHTREE_HPP */