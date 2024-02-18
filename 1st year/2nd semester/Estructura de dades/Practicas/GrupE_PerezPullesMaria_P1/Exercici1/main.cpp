/* 
 * File:   main.cpp
 * Author: mperezpulles
 *
 * Created on 28 de febrero de 2021, 19:57
 */

#include <cstdlib>
#include <iostream>
#include <string>

using namespace std;


void Benvinguda(string nom){
    cout << "Benvingut/da a l'assignatura d'estructura de dades " << nom << "." << endl;
}


int main(int argc, char** argv) {
    int opc; string nom;
    string arr_options[] = {"Sortir", "Benvinguda"};
    int opNum = 0;
    
    cout << "Hola, com et dius? ";
    cin >> nom;
    
    do{
         cout<<"\nHola, "<< nom << ", que vols fer?" <<endl;        
        
        for (string opcio: arr_options) {
            cout << opNum+1 << ". " << opcio << endl;
            opNum++;
        }
       opNum = 0;     
        cin>>opc;
        switch(opc){ 
            
        case 1: break;
            
        case 2: 
        Benvinguda(nom);
        break;
            
        default: cout << "Opció no vàlida." << endl;
        break;
        }
        
    }while(opc!=1);
    
    cout << "Fins després, " << nom << "!" << endl;
    return 0;
}