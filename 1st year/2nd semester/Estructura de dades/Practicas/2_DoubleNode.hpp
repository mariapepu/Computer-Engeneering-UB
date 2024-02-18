/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   DoubleNode.hpp
 * Author: Maria
 *
 * Created on 28 / de març / 2021, 18:32
 */

#ifndef DOUBLENODE_HPP
#define DOUBLENODE_HPP

template <class T>
class DoubleNode {
public:
    DoubleNode();
    DoubleNode(T element);
    DoubleNode(const DoubleNode& orig);
    virtual ~DoubleNode();

    T& getElement();
    DoubleNode<T>* getNext();
    void setNext(DoubleNode<T>* next);
    DoubleNode<T>* getPrevious();
    void setPrevious(DoubleNode<T>* prev);

private:
    T _element;
    DoubleNode<T>* next;
    DoubleNode<T>* previous;

};

/**
 * Constructor por defecto
 */
template <class T>
DoubleNode<T>::DoubleNode() {
    this->next = nullptr;
    this->previous = nullptr;
}

/**
 * Constructor con parámetro.
 * @param 
 */
template <class T>
DoubleNode<T>::DoubleNode(T element) {
    this->next = nullptr;
    this->previous = nullptr;
    this->_element = element;
}

/**
 * Constructor copia
 * @param q
 * @return 
 */
template <class T>
DoubleNode<T>::DoubleNode(const DoubleNode& orig) {
    std::cout << "Constructor copia -> DoubleNode(" << orig.element << ")\n";
    this->_element = orig._element;
    this->next = nullptr;
    this->previous = nullptr;
}

/**
 * Método destructor.
 */
template <class T>
DoubleNode<T>::~DoubleNode() {
    std::cout << "Destructor -> DoubleNode(" << this->element << ")\n";
    this->next = nullptr;
    this->previous = nullptr;
}

/**
 * Método getNext.
 * @return 
 */
template <class T>
DoubleNode<T>* DoubleNode<T>::getNext() {
    return this->next;
}

/**
 * Método setNext, utilizando la dirección del siguiente DoubleNode.
 * @param next
 * @return 
 */
template <class T>
void DoubleNode<T>::setNext(DoubleNode<T>* next) {
    this->next = next;
}

/**
 * Método getPrevious, devuelve el nodo anterior.
 * @return 
 */
template <class T>
DoubleNode<T>* DoubleNode<T>::getPrevious() {
    return this->previous;
}

/**
 * Método setPrevious, utilizando la dirección del nodo anterior.
 * @param previous
 * @return 
 */
template <class T>
void DoubleNode<T>::setPrevious(DoubleNode<T> *previous) {
    this->previous = previous;
}

/**
 * Método getElement, te devuelve el _element almacenado en el nodo.
 */
template <class T>
T& DoubleNode<T>::getElement() {
    return this->_element;
}

#endif /* DOUBLENODE_HPP */

