//Queue using Arrays

#include <cstdlib>
#include <iostream>
#include <vector>
#include "arrayQueue.h"
#include <string>
using namespace std;

//constructor de la queue:

arrayQueue::arrayQueue(const int max_size){
    this->_max_size = max_size;
    std::vector<int> arr(max_size);
    this->_data = arr;
    this->_size = -1;
    cout<< "S'ha creat la cua"<<endl;
}

//metode per afegir elements a la cua

void arrayQueue::enqueue(const int key) {
    if (!this->isFull()) {
        this->_size++;
        this->_data[this->_size] = key;
        cout << "Element " << key << " agregat" << endl;
    } else {
        throw invalid_argument("la cua està plena"); //llancem excepcio
    }
}

//metode per borrar elements de la cua

void arrayQueue::dequeue() {
    if (!this->isEmpty()) {
        cout << "Element " << this->_data[0] << " Eliminat" << endl;
        for (int i = 0; i < this->_size; i++) {
            this->_data[i] = this->_data[i + 1];
        }
        this->_data[this->_size] = NULL; 
        this->_size--;
    } else {
        throw invalid_argument("la cua està buida"); //llancem excepcio
    }

}

//metode per obtenir el primer element

const int arrayQueue::getFront() {
    if (!this->isEmpty()) {
        return this->_data[0];
    }
    throw invalid_argument("la cua està buida"); //llancem excepcio
    return -1;
}

//saber si esta buida

bool arrayQueue::isEmpty() {
    if (this->_size == -1)
        return true;
    return false;
}

//saber si esta plena

bool arrayQueue::isFull() {
    if (this->_size == this->_max_size - 1)
        return true;
    return false;
}

// metode per imprimir els elemnts per pantalla

void arrayQueue::print() {
    cout << "[";
    string elements = ","; //no se pq no va el string, he provat varies coses pero no ho pilla.
    for (int i = 0; i <= this->_size; i++) {
        if (i == this->_size) elements = "";
        cout << this->_data[i] << elements;
    }
    cout << "]" << endl;
}

arrayQueue::~arrayQueue() {
    this->_data.clear(); //el .clear tampoc el pilla be.
}
