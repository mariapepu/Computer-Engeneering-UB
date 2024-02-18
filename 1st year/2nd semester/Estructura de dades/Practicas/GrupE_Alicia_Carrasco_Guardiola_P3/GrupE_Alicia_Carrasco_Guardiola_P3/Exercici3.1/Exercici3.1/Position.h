/* 
 * File:   Position.h
 * Author: user
 *
 * Created on 19 de abril de 2020, 16:29
 */

#ifndef POSITION_H
#define POSITION_H

#include "BSTree.h"
#include <iostream>
#include <vector>

using namespace std;

template <class K, class V>
class Position {
public:
    Position(const K key);
    Position(const Position<K, V>& orig);
    virtual ~Position();
    int depth() const;
    int height() const;
    const K& getKey() const;
    const vector<V>& getValues() const;
    Position<K, V>* parent() const;
    Position<K, V>* left() const;
    Position<K, V>* right() const;
    void setParent(Position<K, V>* parent);
    void setLeft(Position<K, V>* left);
    void setRight(Position<K, V>* right);
    bool isRoot() const;
    bool isLeaf() const;
    void addValue(const V& value);
    bool operator==(const Position<K, V>& other) const;

private:
    K key;
    vector<V> values; // Per aquesta pràctiva només
    Position<K, V>* _parent;
    Position<K, V>* _left;
    Position<K, V>* _right;
    // Aqui definiu els atributs que us falten a Position
};

template <class K, class V>
Position<K, V>::Position(const K key) {
    this->key = key;
    // Inicialitzem el pare i els dos fills a null
    this->_parent = nullptr;
    this->_left = nullptr;
    this->_right = nullptr;
}

template <class K, class V>
Position<K, V>::Position(const Position<K,V>& orig) {
    this->key = orig.key;
    this->_parent = nullptr;
    this->_left = nullptr;
    this->_right = nullptr;
}

template <class K, class V>
Position<K, V>::~Position() {
    K *k = new K(key);
    delete(k);
    vector<V> *v = new vector<V>(values);
    delete(v);
    this->_parent = nullptr;
    this->_left = nullptr;
    this->_right = nullptr;
}

// Desde abajo
template <class K, class V>
int Position<K, V>::depth() const {
    if (isRoot()) return 0;
    return 1 + parent()->depth();
}

// Desde arriba
template <class K, class V>
int Position<K, V>::height() const {
    int esq = 0, dreta = 0;
    if (left()) esq = left()->height();
    if (right()) dreta = dreta = right()->height(); 
    if(esq > dreta) return esq + 1;
    else return dreta + 1;
}

template <class K, class V>
const K& Position<K, V>::getKey() const {
    return this->key;
}

template <class K, class V>
const vector<V>& Position<K, V>::getValues() const {
    return this->values;
}

template <class K, class V>
Position<K, V>* Position<K, V>::parent() const {
    return this->_parent;
}

template <class K, class V>
Position<K, V>* Position<K, V>::left() const {
    return this->_left;
}

template <class K, class V>
Position<K, V>* Position<K, V>::right() const {
    return this->_right;
}

template <class K, class V>
void Position<K, V>::setParent(Position<K, V>* parent) {
    this->_parent = parent;
}

template <class K, class V>
void Position<K, V>::setLeft(Position<K, V>* left) {
    this->_left = left;
}

template <class K, class V>
void Position<K, V>::setRight(Position<K, V>* right) {
    this->_right = right;
}

template <class K, class V>
bool Position<K, V>::isRoot() const {
    // És root si no té pare
    if (this->_parent == nullptr) return true;
    return false;
}

template <class K, class V>
bool Position<K, V>::isLeaf() const {
    // És fulla si no té fills nullptr
    if (this->_left && this->_right){
        return false;
    }
    return true;
}

template <class K, class V>
void Position<K, V>::addValue(const V& value) {
    // Afegim el valor al final
    values.push_back(value);
}

template <class K, class V>
bool Position<K, V>::operator==(const Position<K, V>& other) const{
    // En el nostre cas no pot haver una paraula dos cops, o un enter dos cops,
    // aleshores només ens cal comparar si la clau és la mateixa.
    if (key != other.key) return false;
    for (int i = 0; i < values.size(); i++) {
        if (values[i] != other.values[i]) return false;
    }
    return true;
}

#endif /* BSTREE_H */