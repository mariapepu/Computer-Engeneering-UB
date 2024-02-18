/* 
 * File:   Square.h
 * Author: mperezpulles
 *
 * Created on 4 de marzo de 2021, 11:03
 */

#ifndef SQUARE_H
#define SQUARE_H

class Square {
public:
    Square();
    Square(const Square& orig);
    virtual ~Square();

    float getArea();
    void setCostat(float costat);
    float getPerimetre() ;
    void print();
private:
    float costat;
};

#endif /* SQUARE_H */


