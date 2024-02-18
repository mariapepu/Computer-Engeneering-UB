/* Perez Pulles Maria - 23845458R */

#include<stdlib.h>
#include<stdio.h>
#include "prac1funs.h"
#include <math.h>

int ldlt(int, double **, double);

int main(void){
    int n, i, j, a;
    double **A, *x, *z, *b, *y, total = 0;
    printf("Escribe las dimesiones de la matriz , (n,n) = \n");
    scanf("%d", &n);
    A = (double **) malloc( n*sizeof(double *) );
    if (A == NULL){
        printf("No hay suficiente memoria");
        return 1;
    }
    for (i = 0; i < n; i++){
        A[i] = (double *) malloc( n*sizeof(double) );
        if ( A[i] == NULL){
            printf("No hay suficiente memoria");
            return 2;
        }
    }
    x = (double *) malloc( n*sizeof(double) );
    b = (double *) malloc( n*sizeof(double) );
    y = (double *) malloc( n*sizeof(double) );
    z = (double *) malloc( n*sizeof(double) );
    if ( x == NULL || b == NULL || y == NULL || z == NULL){
        printf("No hay suficiente memoria");
        return 3;
    }
    printf("Dad el (%d x %d) elemento de la matriz L \n", n, n);
    for (i = 0; i < n; i++){
        for (j = 0; j < n; j++){
            scanf("%le", &A[i][j]);
        }
    }
    /* Miramos si es simetrica */
	
    for (i = 0; i < n; i++){
        for (j = 0; j < n; j++){
            if (A[i][j] != A[j][i]){
                printf("No es simetrica");
                return 4;
            }
        }
    }
    
    
    printf("Dad los %d element0s del vector b \n", n);
    for (i = 0; i < n; i++)
        scanf("%le", &b[i]);
    
    a = ldlt(n,A,0.1);
    if (a==1){
        printf("No es diagonalizable");
        for (i = 0; i < n; i++)
            free (A[i]);
        
        free(A);
        
        free(z);
        free(x);
        free(b);
        free(y);
        return 6;
        
    }else{
        for (i = 0; i < n; i++){
            for (j = 0; j < n; j++){
                printf ("%16.7e ", A[i][j]);
            }
            printf("\n");
        }
        
        resTinf(n, A, z, b);
        
        for (i=0; i<n; i++) {
            y[i] = z[i]/A[i][i];
        }
        
        resTsup(n, A, x, y);
        
        /* Valor residual */
		
        printf("\n Valor residual ||Ax - b||^2 = \n");
        prodMatVec (n,n,A,x,y);
        for (i=0; i <n; i++){
            y[i] = y[i] - b[i];
            y[i] = pow(y[i],2);
            total += y[i];
        }
        
        total = sqrt(total);
        printf( "%16.7e ", total);
        
        
        printf("\n Vector solución x = \n");
        for (i=0;i<n;i++){
            printf( "%16.7e ", x[i]);
        }
    }
    
    printf("\n");
    
    for (i = 0; i < n; i++)
        free (A[i]);
    
    free(A);
    
    free(z);
    free(x);
    free(b);
    free(y);
    return 0;
}

int ldlt(int n, double **A, double tol){
    int i, j, k;
    
    for (k=0; k <= n-1; k++){
        
        for (j=0; j<= k-1; j++){
            A[k][k] -= pow(A[k][j],2) * A[j][j];
        }
        for (i=k+1; i<= n-1; i++){
            for (j=0; j<= k-1; j++){
                A[i][k] -= (A[i][j] * A[k][j] * A[j][j]);
            }
            A[i][k] = A[i][k] * (1/A[k][k]);
            A[k][i] = A[i][k];
        }
    }
    
    for (k = 0; k<n; k++){
        if (fabs(A[k][k]) < tol )
            return 1;
    }
    
    return 0;
}
