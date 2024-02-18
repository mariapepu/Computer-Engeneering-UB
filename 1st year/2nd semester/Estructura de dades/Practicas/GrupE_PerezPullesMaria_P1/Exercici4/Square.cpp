/* 
 * File:   Cercle.cpp
 * Author: mperezpulles
 * 
 * Created on 4 de marzo de 2021, 11:16
 */

#include <iostream>
#include "Square.h"

Square::Square() {
}

Square::Square(const Square& orig) {
}

Square::~Square() {
}

float Square::getArea() {
    return (costat * costat);
}

float Square::getPerimetre()  {
    return (costat * 4);
}

void Square::setCostat(float costat) {
    if (costat < 0) {
        std::cout << ", c < 0" << std::endl;
        throw -1;
    } else {
        this->costat=costat;
    }
}

void Square::print() {
    std::cout << "Square(" << costat << ")" << std::endl;
}