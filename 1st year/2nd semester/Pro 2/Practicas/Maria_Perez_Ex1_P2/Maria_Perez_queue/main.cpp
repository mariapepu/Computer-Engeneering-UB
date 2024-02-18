/* 
 * File:   main.cpp
 * Author: maria
 *
 * Created on 14 / de març / 2019, 20:15
 */

#include <cstdlib>
#include <iostream>
#include <vector>
#include "arrayQueue.h"
using namespace std;

//a ver aviso: cada cop que escric "what" netbeans ho marca, pero funciona be, asi que ni cas.

//CASOS DE PROVA DE CLASSE:

//CAS 1:

void casProva1() {
    arrayQueue cua(3);
    try {
        cua.enqueue(10);
    } catch (invalid_argument &e) {
        cout << "EXCEPTION:" << e.what() << endl;
    }
    try {
        cua.enqueue(20);
    } catch (invalid_argument &e) {
        cout << "EXCEPTION:" << e.what() << endl;
    }
    try {
        cua.enqueue(30);
    } catch (invalid_argument &e) {
        cout << "EXCEPTION:" << e.what() << endl;
    }
    try {
        cua.enqueue(40); //Trhow exception
    } catch (invalid_argument &e) {
        cout << "EXCEPTION:" << e.what() << endl;
    }
    try {
        cua.print();
    } catch (invalid_argument &e) {
        cout << "EXCEPTION:" << e.what() << endl;
    }
    try {
        cua.dequeue();
    } catch (invalid_argument &e) {
        cout << "EXCEPTION:" << e.what() << endl;
    }
    try {
        cua.enqueue(40);
    } catch (invalid_argument &e) {
        cout << "EXCEPTION:" << e.what() << endl;
    }
    try {
        cua.print();
    } catch (invalid_argument &e) {
        cout << "EXCEPTION:" << e.what() << endl;
    }

    delete &cua;
}

//CAS 2:

void casProva2() {
    arrayQueue cua(3);
    try {
        cua.enqueue(10);
    } catch (invalid_argument &e) {
        cout << "EXCEPTION:" << e.what() << endl;
    }
    try {
        cout << cua.getFront() << endl;
    } catch (invalid_argument &e) {
        cout << "EXCEPTION:" << e.what() << endl;
    }
    try {
        cua.enqueue(20);
    } catch (invalid_argument &e) {
        cout << "EXCEPTION:" << e.what() << endl;
    }
    try {
        cua.enqueue(30);
    } catch (invalid_argument &e) {
        cout << "EXCEPTION:" << e.what() << endl;
    }
    try {
        cua.print();
    } catch (invalid_argument &e) {
        cout << "EXCEPTION:" << e.what() << endl;
    }
    try {
        cua.dequeue();
    } catch (invalid_argument &e) {
        cout << "EXCEPTION:" << e.what() << endl;
    }
    try {
        cout << cua.getFront() << endl;
    } catch (invalid_argument &e) {
        cout << "EXCEPTION:" << e.what() << endl;
    }
    try {
        cua.dequeue();
    } catch (invalid_argument &e) {
        cout << "EXCEPTION:" << e.what() << endl;
    }
    try {
        cua.dequeue();
    } catch (invalid_argument &e) {
        cout << "EXCEPTION:" << e.what() << endl;
    }
    try {
        cua.dequeue(); //trhow exception
    } catch (invalid_argument &e) {
        cout << "EXCEPTION:" << e.what() << endl;
    }
    try {
        cua.print();
    } catch (invalid_argument &e) {
        cout << "EXCEPTION:" << e.what() << endl;
    }
    delete &cua;
}
int main() {

    int opcio, key;
    //creem la queue de 100 elements
    arrayQueue cua(100);
    //executem els casos de prova de clase
    casProva1(); //cas 1
    casProva2(); //cas 2
    do {
    cout<<"1) Afegir element a la cua"<<endl;
    cout<<"2) Treure element de la cua"<<endl;
    cout<<"3) Consultar el primer element"<<endl;
    cout<<"4) Mostrar tots els elements de la cua"<<endl;
    cout<<"5) Sortir"<<endl;
    cout<<"Què vols fer?: "<<endl;
    cin>>opcio;

    switch (opcio) {
        case 1:
            cout << "Quin element vols afegir?" << endl;
            cin >> key;
            try {
                cua.enqueue(key);
            } catch (invalid_argument &e) {
                cout << "EXCEPTION:" << e.what() << endl; //mu senyala netbeans pero funciona.
            }
            break;
        
        case 2:
            try {
                cua.dequeue();
            } catch (invalid_argument &e) {                
                cout << "EXCEPTION:" << e.what() << endl; //mu senyala netbeans pero funciona.
            }
            break;
        
        case 3:
            try {
                cout << "El primer numero es " << cua.getFront() << endl;
            } catch (invalid_argument &e) {
                cout << "EXCEPTION:" << e.what() << endl; //mu senyala netbeans pero funciona.
            }
       
        case 4: 
            cua.print();
            break;

        case 5: cout<<"Adéu"<<endl;
            break;

        default: cout<<"Opció no vàlida"<<endl;
            break;

        }

} while(opcio!=5);

return 0;

}
