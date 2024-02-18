/* 
 * File:   Rectangle.h
 * Author: mperezpulles
 *
 * Created on 5 de marzo de 2021, 11:14
 */

#ifndef RECTANGLE_H
#define	RECTANGLE_H

class Rectangle {
public:
    Rectangle();
    Rectangle(const Rectangle& orig);
    virtual ~Rectangle();
    
    float getAreaR();
    float getPerimetreR();
    float getBase() const;
    float getAltura() const;
    void setBase(float base);
    void setAltura(float altura);
    void printR();
    
private:
    float base;
    float altura;
};

#endif	/* RECTANGLE_H */

