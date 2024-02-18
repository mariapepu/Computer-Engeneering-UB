/* 
 * File:   main.cpp
 * Author: Maria
 *
 * Created on 24 / d’abril / 2020, 17:00
 */

#include <cstdlib>
#include<iostream>
#include <vector>
#include "BSTree.hpp"

using namespace std;
void caso1(){
    BSTree<int, int> *bst = new BSTree<int, int>();
    int testArray [] = {2, 0, 8, 45, 76, 5, 3, 40 };
    
    cout << "\nInsertando...\n";
    for (int n : testArray){
        bst->insert(n);
    }
    cout << "\nElementos insertados\n";
    
    bst->showPreorder();
    bst->showInorder();
    bst->showPostorder();
    bst->showLeafNodes();
    //bst->~BSTree();
    delete bst;
}

int main(int argc, char** argv) {
    cout << "Ejercicio 1 P3 | BSTree | Alberto Barragán";
    caso1();
    return 0;
}

