/* 
 * File:   main.cpp
 * Author: mperezpulles
 *
 * Created on 4 de marzo de 2021, 10:56
 */

#include <cstdlib>
#include <iostream>
#include <stdexcept>
#include <string>
#include <vector>

#include "Square.h"

using namespace std;

int opc;
int menu(){
    vector<string> arr_options = {"Sortir", "Introducir Quadrat"};
    int opNum = 0;
    cout<< "Hola, Qué vols fer?" <<endl;
        
        for (string opcio: arr_options) {
            cout << opNum+1 << ". " << opcio << endl;
            opNum++;
        }
       opNum = 0;
    cin >> opc;
    return opc;
}

void introduirSquare(int &cont){
    float costat;
    cont++;
    cout << "Quadrat " << cont << endl;
    cout << "Base? ";
    cin >> costat;
    Square* s = new Square();
    s->setCostat(costat);
    s->print();
    cout << "L'àrea d'aquest quadrat és " << s->getArea() << "u" << endl;
    cout << "El perímetre d'aquest quadrat és " << s->getPerimetre() << "u" << endl;
}


int main(int argc, char** argv) {
    double input;
    int cont = 0;
    
    do{
        menu();
        switch(opc){
        
        case 1: break;
            
        case 2: 
        try {
            introduirSquare(cont);
        } catch (int ex) {
            cout << "S'ha produit una excepció" << endl;
        }
        break;
            
        default: cout << "Opcióno vàlida." << endl;
        break;
        
        }
        
    }while(opc!=1);
    
    cout << "Fins després!" << endl;
    return 0;
}

