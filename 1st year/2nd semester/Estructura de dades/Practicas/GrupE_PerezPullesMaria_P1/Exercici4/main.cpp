/* 
 * File:   main.cpp
 * Author: mperezpulles
 *
 * Created on 5 de marzo de 2021, 19:44
 */

#include <cstdlib>
#include <iostream>
#include <stdexcept>
#include <string>
#include <vector>

#include "Square.h"
#include "Rectangle.h"

using namespace std;

int opc; //numSquaress = 0;

int menu() {
    vector<string> arr_options = {"Sortir", "Introduir figura", "Glosari de figures"};
    int opNum = 0;
    cout << "Hola, que vols fer?" << endl;

    for (string opcio : arr_options) {
        cout << opNum + 1 << ". " << opcio << endl;
        opNum++;
    }
    opNum = 0;
    cin >> opc;
    return opc;
}

void introduirSquare(int &cont, float costat) {
    Square* s = new Square();
    s->setCostat(costat);
    s->print();
    cout << "L'àrea d'aquest quadrat és " << s->getArea() << "u" << endl;
    cout << "El perímetre d'aquest quadrat és " << s->getPerimetre() << "u" << endl;
    cont++;
}

void introduirRectangle(int &cont, float base, float altura) {
    Rectangle* r = new Rectangle();
    r->setBase(base);
    r->setAltura(altura);
    r->printR();
    cout << "L'àrea d'aquest rectangle és de " << r->getAreaR() << "u" << endl;
    cout << "El perímetre d'aquest rectangle és " << r->getPerimetreR() << "u" << endl;
    cont++;
}

void addFigura(int &contS, int &contR) {
    float base, altura;
    char selector;
    cout << "(S)quare / (R)ectangle? ";

    cin >> selector;
    //Mirem quin tipus es
    if (selector == 'S' || selector == 's') {
        cout << "Introdueix la base: ";
        cin >> base;
        //Comprovem
        if (cin.fail()) {
            cin.clear();
            cout << "Square incorrecte" << endl;
            throw -1;
        }
        introduirSquare(contS, base);

        //Repetim el proces selector del square pero amb el rectangle
    } else if (selector == 'R' || selector == 'r') {
        cout << "Introdueix la base i la altura: ";
        cin >> base >> altura;
        if (cin.fail()) {
            cin.clear();
            cout << "Rectangle incorrecte" << endl;
            throw -1;
        }
        introduirRectangle(contR, base, altura);
    } else {
        cout << "Figura incorrecta." << endl;
        throw -1;
    }
}

void glosariFigures(int contS, int contR) {
    cout << "Tens " << contS << " quadrats i " << contR << " rectangles." << endl;
}

int main(int argc, char** argv) {
    int contS = 0, contR = 0;

    do {
        menu();
        switch (opc) {

            case 1:
                break;

            case 2:
                try {
                    addFigura(contS, contR);
                } catch (int ex) {
                    cout << "S'ha produit una excepció" << endl;
                }
                break;
            case 3:
                glosariFigures(contS, contR);
                break;

            default:
                cout << "Opció no vàlida." << endl;
                break;
        }
    } while (opc != 1);

    cout << "Fins després!" << endl;
    return 0;
}

