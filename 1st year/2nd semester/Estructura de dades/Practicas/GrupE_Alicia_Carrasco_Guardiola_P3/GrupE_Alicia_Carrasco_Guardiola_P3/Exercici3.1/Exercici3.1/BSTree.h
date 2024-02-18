/* 
 * File:   BSTree.h
 * Author: user
 *
 * Created on 19 de abril de 2020, 16:30
 */

#ifndef BSTREE_H
#define BSTREE_H

#include "Position.h"
#include <iostream>

using namespace std;

template <class K, class V>
class BSTree {
public:
    BSTree();
    BSTree(const BSTree<K, V>& orig);
    virtual ~BSTree();

    bool isEmpty() const;
    Position<K, V>* getRoot() const;
    int size() const;
    int height() const;

    virtual Position<K, V>* insert(const K& key, const V& value);
    bool contains(const K& key) const;
    const vector<V>& getValues(const K& key) const;

    void printPreOrder(const Position<K, V> *node = nullptr) const;
    void printPostOrder(const Position<K, V> *node = nullptr) const;

    bool identicalTree(const BSTree<K, V>& other) const;

protected:
    Position<K, V>* root;

private:
    int _size;
    Position<K, V>* _search(const K& key) const;
    Position<K, V>* _search_recursiu(const K& key, Position<K, V>* pos) const;
    int size_recursiu(Position<K, V>* pos) const;
    Position<K, V>* insert_recursiu(const K& key, const V& value, Position<K, V>* pos, int& size) const;
    bool identicalTree_recursiu(const BSTree<K, V>& other, Position<K, V>* other_pos, Position<K, V>* pos) const;
    void printPreOrder_recursiu(const Position<K, V> *node) const;
    void printPostOrder_recursiu(const Position<K, V> *node) const;
    void copia_recursiu(const Position<K, V>* orig, Position<K, V>* pos);
    void destructor_recursiu(Position<K, V>* pos);
};

template <class K, class V>
BSTree<K, V>::BSTree() {
    this->_size = 0;
    cout << "Arbre binari creat!" << endl;
}

template <class K, class V>
BSTree<K, V>::BSTree(const BSTree<K, V>& orig) {
    if(orig.isEmpty()) throw runtime_error("Ups! Està buit :(");
    this->_size = orig._size;
    this->root = new Position<K, V>(*orig.root);
    copia_recursiu(orig.root, root);
    cout << "Arbre copiat!" << endl;
}

template <class K, class V>
void BSTree<K, V>::copia_recursiu(const Position<K, V>* orig, Position<K,V>* pos) {
   if (orig){
        if(orig->right()){
            pos->setRight(new Position<K, V>(*orig->right()));
            pos->right()->setParent(pos);
            copia_recursiu(orig->right(), pos->right());
        }
        if(orig->left()){
            pos->setLeft(new Position<K, V>(*orig->left()));
            pos->left()->setParent(pos);
            copia_recursiu(orig->left(), pos->left());    
        }
    }
}
 
template <class K, class V>
BSTree<K, V>::~BSTree() {
    destructor_recursiu(root);
    cout << "Arbre eliminat!" << endl;
}

template <class K, class V>
void BSTree<K, V>::destructor_recursiu(Position<K, V>* pos) {
    if(pos){
        if (pos->left()) destructor_recursiu(pos->left());
        if (pos->right()) destructor_recursiu(pos->right());
        pos->~Position();
    }
}

// Retorna cert si l’arbre binari està buit, fals en cas contrari
template <class K, class V>
bool BSTree<K, V>::isEmpty() const {
    if (_size == 0) return true;
    return false;
}

// Retorna l’adreça del root
template <class K, class V>
Position<K, V>* BSTree<K, V>::getRoot() const {
    return this->root;
}

template <class K, class V>
int BSTree<K, V>::size() const {
    if (isEmpty()) return 0;
    return 1 + size_recursiu(root);
}

template <class K, class V>
int BSTree<K, V>::size_recursiu(Position<K, V>* pos) const {
    int count = 0;
    if (pos) {
        if (pos->right()) count += 1 + size_recursiu(pos->right());
        if (pos->left()) count += 1 + size_recursiu(pos->left());
    }
    return count;
}

template <class K, class V>
int BSTree<K, V>::height() const {
    return this->root->height(); // El height de position ja és recirsiu
}

template <class K, class V>
Position<K, V>* BSTree<K, V>::insert(const K& key, const V& value) {
    if (isEmpty()) {
        this->root = new Position<K, V>(key);
        root->addValue(value);
        _size++;
        cout << "S'ha insertat " << key << " com a root!" << endl;
        return root;
    } else {
        return insert_recursiu(key, value, root, _size);
    }
}

template <class K, class V>
Position<K, V>* BSTree<K, V>::insert_recursiu(const K& key, const V& value, Position<K,V>* pos, int &size) const {
    // Si existeix, se li afegeix el nou valor
    if (key == pos->getKey()) {
        pos->addValue(value);
        cout << "La clau ja existia. S'ha afegit el valor " << value << " a " <<
                key << "! :)" << endl;
        return pos;
    }
    // Si no existeix i hauria de situar-se cap a l'esquerra
    else if (key < pos->getKey()) {
        if (pos->left()) insert_recursiu(key, value, pos->left(), size);
        else {
            Position<K, V>* position = new Position<K, V>(key);
            position->addValue(value);
            position->setParent(pos);
            position->parent()->setLeft(position);
            size++;
            cout << "S'ha insertat " << key << "!" << endl;
            return position;
        }
    } 
    // Si no existeix i hauria de situar-se cap a la dreta
    else if (key > pos->getKey()) {
        if (pos->right()) insert_recursiu(key, value, pos->right(), size);
        else {
            Position<K, V>* position = new Position<K, V>(key);
            position->addValue(value);
            position->setParent(pos);
            position->parent()->setRight(position);
            size++;
            cout << "S'ha insertat " << key << "!" << endl;
            return position;
        }
    }
}

template <class K, class V>
bool BSTree<K, V>::contains(const K& key) const {
    Position<K, V>* pos = root;
    while(pos) {
        if (key == pos->getKey()) return true;
        else if (key < pos->getKey()) pos = pos->left();
        else if (key > pos->getKey()) pos = pos->right();
    }
    return false;
}

template <class K, class V>
const vector<V>& BSTree<K, V>::getValues(const K& key) const {
    Position<K, V>* pos = (_search(key));
    return pos->getValues();
}

template <class K, class V>
void BSTree<K, V>::printPreOrder(const Position<K, V> *node) const {
    if (isEmpty()) throw runtime_error("L'arbre està buit :(");
    // Si no hem sol·licitat el node d'inici, li assignem el root
    if (node == nullptr) node = root;
    cout << "Preordre = { ";
    printPreOrder_recursiu(node);
    cout << "}" << endl;
}

template <class K, class V>
void BSTree<K, V>::printPreOrder_recursiu(const Position<K, V> *node) const {
    cout << node->getKey() << " ";
    if (node->left() != nullptr) {
        printPreOrder_recursiu(node->left());
    }
    if (node->right() != nullptr) {
        printPreOrder_recursiu(node->right());
    }
    if (node->isLeaf()) return;
}


template <class K, class V>
void BSTree<K, V>::printPostOrder(const Position<K, V> *node) const {
    if (isEmpty()) throw runtime_error("L'arbre està buit :(");
    // Si no hem sol·licitat el node d'inici, li assignem el root
    if (node == nullptr) node = root;
    cout << "Postordre = { ";
    printPostOrder_recursiu(node);
    cout << "}" << endl;
}

template <class K, class V>
void BSTree<K, V>::printPostOrder_recursiu(const Position<K, V> *node) const {
    if (node->left() != nullptr) printPostOrder_recursiu(node->left());
    if (node->right() != nullptr) printPostOrder_recursiu(node->right());
    cout << node->getKey() << " ";
}

template <class K, class V>
bool BSTree<K, V>::identicalTree(const BSTree<K, V>& other) const {
    if (size() != other.size())return false;
    return identicalTree_recursiu(other, other.root, root);
}

template <class K, class V>
bool BSTree<K, V>::identicalTree_recursiu(const BSTree<K, V>& other, Position<K, V>* other_pos, Position<K, V>* pos) const {
    // Les claus no coincideixen
    if (pos->getKey() != other_pos->getKey()) return false;
    // Una es fulla i l'altre no
    if ((pos->isLeaf() and !other_pos->isLeaf()) or (!pos->isLeaf() and other_pos->isLeaf())) return false;
    // Cap de les dues és fulla
    if (!pos->isLeaf() and !other_pos->isLeaf()) {
        if (pos->left() && other_pos->left()) {
            return identicalTree_recursiu(other, other_pos->left(), pos->left());
        }
        else if ((!pos->left() && other_pos->left()) || (pos->left() && !other_pos->left())) return false;
        if (pos->right() && other_pos->right()) {
            return identicalTree_recursiu(other, other_pos->right(), pos->right());
        }
        else if ((!pos->right() && other_pos->right()) || (pos->right() && !other_pos->right())) return false;
    }
    return true;   
}

template <class K, class V>
Position<K, V>* BSTree<K, V>::_search(const K& key) const {
    if (isEmpty()) throw runtime_error("L'arbre està buit :(");
    return _search_recursiu(key, root);
}

template <class K, class V>
Position<K, V>* BSTree<K, V>::_search_recursiu(const K& key, Position<K,V>* pos) const {
    if (key == pos->getKey()) return pos;
    // Si està a l'esquerra
    else if (key < pos->getKey()) return _search_recursiu(key, pos->left());
    // Si està a la dreta
    else if (key > pos->getKey()) return _search_recursiu(key, pos->right());
    // Si no hi és
    return nullptr;
}

#endif /* POSITION_H */