/* 
 * File:   main.cpp
 * Author: mperezpulles
 *
 * Created on 3 de marzo de 2021, 21:07
 */

#include <cstdlib>
#include <iostream>
#include <vector>



using namespace std;

int opc = 0; string nom; 


int menu(){
    vector<string> arr_options = {"Sortir", "Benvinguda", "Redefinir nom"};
    int opNum = 0;
    cout<<"\nHola, "<< nom << ", que vols fer?" <<endl;   
        
        for (string opcio: arr_options) {
            cout << opNum+1 << ". " << opcio << endl;
            opNum++;
        }
       opNum = 0;
    cin >> opc;
    return opc;
}

void benvinguda(string nom){
    cout << "Benvingut/da a l'assignatura d'estructura de dades " << nom << "." << endl;
}

void setNom(){
    cout << "Com et dius? ";
    cin >> nom;
}


int main(int argc, char** argv) {
    cout << "Hola, com et dius? ";
    cin >> nom;
    
    do{
        menu();
        switch(opc){
        
        case 1: 
        break;
            
        case 2:
        benvinguda(nom);
        break;
            
        case 3: 
        setNom(); 
        break;
            
        default: cout << "Opció no vàlida." << endl;
        break;
        
        }
        
    }while(opc!=1);
    
    cout << "Fins després, " << nom << "!" << endl;
    return 0;
}