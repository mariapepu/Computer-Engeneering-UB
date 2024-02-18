
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   Position.h
 * Author: Maria
 *
 * Created on 24 / d’abril / 2020, 17:27
 */

#ifndef POSITION_HPP
#define	POSITION_HPP
#include <cstdlib>
#include <vector>
#include <string>
#include<iostream>

template <class K, class V>
class Position {
    public:
        Position();
        Position(const K& key);
        Position(const Position& orig);
        virtual ~Position();
        
        //Consultores
        Position<K, V>* getRight() const;
        Position<K, V>* getLeft() const;
        Position<K, V>* getParent() const;
        bool hasRight() const;
        bool hasLeft() const;
        bool isRoot() const;
        bool isLeaf() const;
        const K& getKey () const;
        
        //Modificadores
        void setKey(const K& key);
        void setRight(Position<K, V>* newRight);
        void setLeft(Position<K, V>* newLeft);
        void setParent(Position<K, V>* newParent);
        
    private:
        Position<K, V>* parent;
        Position<K, V>* left;
        Position<K, V>* right;
        vector<V> values;
        K key;
        
};
//Constructores
template<class K, class V>
Position<K, V>::Position(){
    this->parent = nullptr;
    this->left = nullptr;
    this->right = nullptr;
}

template<class K, class V>
Position<K, V>::Position(const K& key){
    this->key = key;
    this->parent = nullptr;
    this->left = nullptr;
    this->right = nullptr;
}

template<class K, class V>
Position<K, V>::Position(const Position& orig){
    this->key = orig.key;
    this->left = nullptr;
    this->right = nullptr;
    this->parent = nullptr;
}

template<class K, class V>
Position<K, V>::~Position(){
    this->right = nullptr;
    this->left = nullptr;
    this->parent = nullptr;
    std::cout << "Hulk ha aplastado el nodo " << key << std::endl;

}

//Consultores

template<class K, class V>
Position<K, V>* Position<K, V>::getRight() const{
    return this->right;
}

template<class K, class V>
Position<K, V>* Position<K, V>::getLeft() const{
    return this->left;
}

template<class K, class V>
Position<K, V>* Position<K, V>::getParent() const{
    return this->parent;
}

template<class K, class V>
bool Position<K, V>::hasRight() const{
    return (right != nullptr);
}

template<class K, class V>
bool Position<K, V>::hasLeft() const{
    return(left != nullptr);
}

template<class K, class V>
bool Position<K, V>::isRoot() const{
    return (parent != nullptr);
}

template<class K, class V>
bool Position<K, V>::isLeaf() const{
    return (left == right && left == nullptr);
}

template<class K, class V>
const K& Position<K, V>::getKey () const{
    return this->key;
}

//Modificadores
template <class K, class V>
void Position<K, V>::setKey(const K& key){
    this->key = key;
}

template <class K, class V>
void Position<K, V>::setRight(Position<K, V>* newRight){
    this->right = newRight;
}

template <class K, class V>
void Position<K, V>::setLeft(Position<K, V>* newLeft){
    this->left = newLeft;
}
template <class K, class V>
void Position<K, V>::setParent(Position<K, V>* newParent){
    this->parent = newParent;
}


#endif	/* NODETREE_HPP */

