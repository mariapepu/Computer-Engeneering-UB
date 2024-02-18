/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * File:   ArrayDeque.cpp
 * Author: Maria
 * 
 * Created on 28 / de març / 2021, 01:46
 */

#include <vector>
#include "ArrayDeque.h"
#include <iostream>
using namespace std;

ArrayDeque::ArrayDeque(const int size) {
    this->_max_size = size;
    cout << "Array de tamany " << size << " creat" << endl;
}

ArrayDeque::ArrayDeque(const ArrayDeque& orig) {
}

ArrayDeque::~ArrayDeque() {
}

void ArrayDeque::enqueueBack(int key) {
    if (!isFull()) {
        this->_data.push_back(key);
        std::cout << "Element " << key << " agregat.\n";
    } else {
        throw std::runtime_error("Atenció, capacitat del contenedor plena.");
    }
}

void ArrayDeque::enqueueFront(int key) {
    if (!isFull()) {
        //        for (int i = _size; i > -1; i--) {
        //            if (i == 0) {
        //                _data[0] = key;
        //            } else {
        //                _data[i + 1] = _data[i];
        //            }
        //        }
        _data.emplace(_data.begin(), key);
        _size++;

        std::cout << "Element " << key << " agregat.\n";
    } else {
        throw std::runtime_error("Atenció, capacitat del contenedor plena..");
    }
}

void ArrayDeque::dequeueBack() {
//    std::cout << "ja som dins el dequeue back \n";
    if (!isEmpty()) {
        std::cout << "Element " << _data.at(_size-1) << " eliminat. \n";
//        std::cout << "ja som dins el if \n";
        this->_data.erase(_data.end()-1);
//        std::cout << "element borrat \n";
    } else {
        throw std::runtime_error("Atenció, el contenedor esta buit.");
    }
}

void ArrayDeque::dequeueFront() {
    if (!isEmpty()) {
        std::cout << "Element " << _data.at(0) << " eliminat. \n";
        this->_data.erase(_data.begin());
    } else {
        throw std::runtime_error("Atenció, el contenedor està buit.");
    }
}

bool ArrayDeque::isEmpty() {
    return this->_data.size() == 0;
}

bool ArrayDeque::isFull() {
    return this->_data.size() >= this->_max_size;
}

const int ArrayDeque::getFront() {
    return this->_data.at(0);
}

const int ArrayDeque::getBack() {
    return this->_data.at(_size-1);
}

void ArrayDeque::print() {
    std::cout << "Els valors són: ";
    int p = this->_data.size() - 1;
    for (int i = 0; i <= p; i++) {
        if (i == p) {
            std::cout << this->_data.at(i) << ".\n";
        } else {
            std::cout << this->_data.at(i) << ", ";
        }
    }
}