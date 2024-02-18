/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   LinkedDeque.hpp
 * Author: Maria
 *
 * Created on 28 / de març / 2021, 19:04
 */

#include <stddef.h>
#include <iostream>
#include "DoubleNode.hpp"
#ifndef LINKEDDEQUE_HPP
#define LINKEDDEQUE_HPP
using namespace std;

template <class T>
class LinkedDeque {
public:

    LinkedDeque();
    LinkedDeque(T max);
    virtual ~LinkedDeque();
    LinkedDeque(const LinkedDeque& q);
    void enqueueFront(const T &f);
    void enqueueBack(const T &f);
    void dequeueFront();
    void dequeueBack();
    bool isEmpty();
    void print();
    T& getFront();
    T& getBack();
    const int& size() const;

private:
    int _size;
    int _max_size;
    DoubleNode<T>* back;
    DoubleNode<T>* front;
};

/**
 * Constructor
 */
template <class T>
LinkedDeque<T>::LinkedDeque() {

}

/**
 * constructor por parametros
 * @param elem
 */
template <class T>
LinkedDeque<T>::LinkedDeque(T max) {
    _max_size = max;
    _size = 0;
    back = nullptr;
    front = nullptr;
}
/**
 * constructor por parametros
 * @param orig
 */
template <class T>
LinkedDeque<T>::LinkedDeque(const LinkedDeque& q) {
}

/**
 * Destructor
 */
template <class T>
LinkedDeque<T>::~LinkedDeque() {
}

/*
 *Imprimr linked deque
 */
template <class T>
void LinkedDeque<T>::print() {
    DoubleNode<T>* ptr;
    ptr = front;

    while (ptr != nullptr) {
        cout << ptr->getElement() << " ";
        ptr = ptr->getNext();
    }

}

/**
 * Insertar Nodo al principio
 * @param elem
 */
template <class T>
void LinkedDeque<T>::enqueueFront(const T &f) {

    DoubleNode<T>* newnode = new DoubleNode<T>(f);

    if (_size == _max_size) {
        cout << "PLE";

    } else {
        if (front == nullptr) {
            front = newnode;
            back = newnode;
        } else {
            newnode->setNext(front);
            (*front).setPrevious(newnode);
            front = newnode;
        }
        _size++;
    }
}

/**
 * Insertar Nodo al final
 * @param elem
 */
template <class T>
void LinkedDeque<T>::enqueueBack(const T &f) {
    DoubleNode<T>* newnode = new DoubleNode<T>(f);

    if (_size == _max_size) {
        cout << "PLE";

    } else {
        if (isEmpty()) {
            back = newnode;
            front = newnode;
        } else {
            newnode->setPrevious(back);
            (*back).setNext(newnode);
            back = newnode;
        }
        _size++;
    }
}

/**
 * Método getFront.
 * @return 
 */
template <class T>
T& LinkedDeque<T>::getFront() {
    return front->getElement();
}

/**
 * Método getBack
 * @return 
 */
template <class T>
T& LinkedDeque<T>::getBack() {
    return this->back->getElement();
}

/**
 * Comprueba si la cola está vacía.
 * @return 
 */
template <class T>
bool LinkedDeque<T>::isEmpty() {
    if (back == nullptr && front == nullptr) {
        return true;
    } else {
        return false;
    }
}


/**
 * Borrar el último nodo de la Queue
 */
template <class T>
void LinkedDeque<T>::dequeueBack() {

    if (isEmpty()) {
        cout << "Està buit";

    } else {
        back = back->getPrevious();
        back->setNext(nullptr);
        _size--;
    }
}

/**
 * Borrar el primer nodo de la Queue
 */
template <class T>
void LinkedDeque<T>::dequeueFront() {
    if (isEmpty()) {
        cout << "Està buit";

    } else {
        front = front->getNext();
        front->setPrevious(nullptr);
        _size--;

    }
}
#endif /* LINKEDDEQUE_HPP */

