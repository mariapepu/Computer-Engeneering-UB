/* Perez Pulles Maria - 23845458R */

#include <stdlib.h>
#include <stdio.h>
#include <math.h>
#include "prac1funs.h"

int main(void){
    int n, i, j;
    double **L, **T, *x, *b, *y, total = 0;
    printf("Escribe las dimesiones de la matriz") , (n,n) = \n");
    scanf("%d", &n);
    L = (double **) malloc( n*sizeof(double *) );
    T = (double **) malloc( n*sizeof(double *) );
    if ( L == NULL || T == NULL){
        printf("No hay suficiente memoria");
        return 1;
    }
    for (i = 0; i < n; i++){
        L[i] = (double *) malloc( n*sizeof(double) );
        T[i] = (double *) malloc( n*sizeof(double) );
        if ( L[i] == NULL || T[i] == NULL){
            printf("No hi ha prou memoria");
            return 2;
        }
    }
    x = (double *) malloc( n*sizeof(double) );
    b = (double *) malloc( n*sizeof(double) );
    y = (double *) malloc( n*sizeof(double) );
    if ( x == NULL || b == NULL || y == NULL){
        printf("No hay suficiente memoria");
        return 3;
    }
    printf("Dad el (%d x %d) elemento de la matriz L \n", n, n);
    for (i = 0; i < n; i++){
        for (j = 0; j < n; j++){
            scanf("%le", &L[i][j]);
        }
        if (L[i][i] != 1){
            printf("No tiene diagonal de 1's");
            return 4;
        }
    }
    
    printf("Doneu els %d elementos del vector b \n", n);
    for (i = 0; i < n; i++)
        scanf("%le", &b[i]);

    resTinf(n, L, x, b);
    
    printf("\n Vector solución x = \n");
    for (i=0;i<n;i++)
        printf( "%16.7e ", x[i]);


 /* Valor residual */ 
  
    printf("\n Valor residual ||Lx - b||^2 = \n");
    prodMatVec (n,n,L,x,y);
    for (i=0; i <n; i++){
        y[i] = y[i] - b[i];
        y[i] = pow(y[i],2);
        total += y[i];
    }
    
    total = sqrt(total);
    printf( "%16.7e ", total);
    
    
/* Transponemos la matriz L */

    for (i=0; i<n; i++){
        for (j=0;j<n;j++){
            T[j][i] = L[i][j];
        }
    }
    
    printf("\n Matriz L transpuesta = \n");
    for (i = 0; i < n; i++){
        for (j = 0; j < n; j++){
            printf(" %16.7e ", T[i][j]);
        }
        printf("\n");
    }
    
    resTsup(n,T,x,b);
    
    printf("\n Vector solución x de la Transpuesta = \n");
    for (i=0;i<n;i++){
        printf( "%16.7e ", x[i]);
    }
    printf("\n");

    for (i = 0; i < n; i++)
        free (L[i]);

    free(L);
    
    for (i=0;i<n;i++)
        free (T[i]);
    
    free(T);
    free(x);
    free(b);
    free(y);
    return 0;
}
