/* 
 * File:   Rectangle.cpp
 * Author: mperezpulles
 * 
 * Created on 5 de marzo de 2021, 11:14
 */

#include <iostream>

#include "Rectangle.h"

Rectangle::Rectangle() {
}

Rectangle::Rectangle(const Rectangle& orig) {
}

Rectangle::~Rectangle() {
}

float Rectangle::getAreaR() {
    return (base * altura);
}

float Rectangle::getPerimetreR() {
    return ((base * 2)+ (altura * 2));
}

float Rectangle::getBase() const {
    return base;
}

float Rectangle::getAltura() const {
    return altura;
}

void Rectangle::setBase(float base) {
    if (base < 0) {
        std::cout << "Aquest valor no és acceptable, b < 0" << std::endl;
        throw -1;
    } else {
        this->base = base;
    }
}

void Rectangle::setAltura(float altura) {
    if (altura < 0) {
        std::cout << "Aquest valor no és acceptable, a < 0" << std::endl;
        throw -1;
    } else {
        this->altura = altura;
    }
}

void Rectangle::printR() {
    std::cout << "Rectangle(" << base << ", " << altura << ")" << std::endl;
}