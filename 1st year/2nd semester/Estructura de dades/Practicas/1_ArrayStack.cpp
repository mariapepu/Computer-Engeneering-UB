/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   ArrayStack.cpp
 * Author: Maria
 * 
 * Created on 21 / de març / 2020, 19:59
 */
#include "ArrayStack.h"

#include <vector>
#include <string>
#include <valarray>
#include <iostream>

ArrayStack::ArrayStack() {
    this->capacity = DEFAULT_MAX_STACK;
    this->t = -1;
}

ArrayStack::ArrayStack(int max) {
    this->capacity = max;
    this->t = -1;
}

ArrayStack::ArrayStack(const ArrayStack& orig) {
}

ArrayStack::~ArrayStack() {
    //destructor
//he echo el destrucor con un while
//mientras t (top) no sea < 0, eliminaremos el elemento
//como en "pop" tenemos puesto que t se decrementa, no hace falta ponerlo aqui
    while (this->t>=0) {
        pop();
    }
}

int ArrayStack::size() const {
    return this->S.size();
}

bool ArrayStack::isEmpty() const {
    return this->S.size() == 0;
}

bool ArrayStack::isFull() const {
    return this->S.size() >= this->capacity;
}

const int& ArrayStack::top() const {
    return this->S.at(t);
}

void ArrayStack::push(const int& key) {
    if (!isFull()) {
        this->S.push_back(key);
        this->t++;
    } else {
        throw std::runtime_error("Atención, capacidad del contenedor llena.");
    }
}

void ArrayStack::pop() {
    if (!isEmpty()) {
        this->S.pop_back();
        this->t--;
    } else {
        throw std::runtime_error("Atención, el contenedor está vacío.");
    }
}

void ArrayStack::print() const {
    int p = this->S.size() - 1;
    for (int i = 0; i <= p; i++) {
        if (i == p) {
            std::cout << this->S.at(p-i) << ".\n";
        } else {
            std::cout << this->S.at(p-i) << ", ";
        }
    }
}

