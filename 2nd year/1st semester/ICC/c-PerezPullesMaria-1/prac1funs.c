/* Perez Pulles Maria - 23845458R */

#include <stdio.h>
#include <stdlib.h>
#include "prac1funs.h"


void resTsup (int n, double **U, double *x, double *b){
    int i,j;
    
    for (i=n-1; i>=0; i--){
        x[i] = b[i];
        for(j=n-1; j>i; j--){
            x[i] -= x[j] * U[i][j];
        }
    }
}
    

void resTinf(int n, double **L, double *x, double *b){
    int i,j;
    
    for (i=0; i<n; i++){
        x[i] = b[i];
        for(j=0; j<i; j++){
            x[i] -= x[j] * L[i][j];
        }
    }
}

void prodMatVec (int m, int n, double **A, double *x, double *y){
    int i,j;
    
    for (i = 0; i < m; i++){
        y[i] = 0;
        for (j = 0; j < n; j++){
            y[i] += x[j] * A[i][j];
        }
    }
}

void prodMatMat (int m, int n, int p, double **A, double **B, double **C){
    int i,j,z;

    for(i = 0; i < m; i++){
        for(z = 0; z < p; z++){
            C[i][z] = 0;
            for(j = 0; j < n; j++){
                C[i][z] += A[i][j] * B[j][z];
            }
        }
    }

}

