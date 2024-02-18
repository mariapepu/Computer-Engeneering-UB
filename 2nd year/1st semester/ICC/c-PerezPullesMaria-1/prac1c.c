/* Perez Pulles Maria - 23845458R */

#include<stdlib.h>
#include<stdio.h>
#include "prac1funs.h"
#include <math.h>

int ldlt(int, double **, double);

int main(void){
    int n,m, i, j, a;
    double **A, **T,**C, *x, *z, *b, *y, *v, total = 0;
    printf("Escribe las dimesiones de la matriz, (m * n) = \n");
    scanf("%d %d", &m,&n);
    A = (double **) malloc( m*sizeof(double *) );
    T = (double **) malloc( n*sizeof(double *) );
    C = (double **) malloc( n*sizeof(double *) );
    if (A == NULL || T == NULL || C == NULL){
        printf("No hay suficiente memoria");
        return 1;
    }
    for (i=0; i<m; i++){
        A[i] = (double *) malloc( n*sizeof(double) );
    }

    for (i = 0; i < n; i++){
        T[i] = (double *) malloc( m*sizeof(double) );
        C[i] = (double *) malloc( n*sizeof(double) );
        if ( A[i] == NULL || T[i] == NULL || C[i] == NULL){
            printf("No hay suficiente memoria");
            return 2;
        }
    }

    x = (double *) malloc( n*sizeof(double) );
    b = (double *) malloc( m*sizeof(double) );
    y = (double *) malloc( n*sizeof(double) );
    z = (double *) malloc( n*sizeof(double) );
    v = (double *) malloc( n*sizeof(double) );
    if ( x == NULL || b == NULL || y == NULL || z == NULL || v == NULL){
        printf("No hay suficiente memoria");
        return 3;
    }
    printf("Dad el (%d x %d) elemento de la matriz L \n", m, n);
    for (i = 0; i < m; i++){
        for (j = 0; j < n; j++){
            scanf("%le", &A[i][j]);
        }
    }
    
    printf("Dad los %d elementos del vector b \n", m);
    for (i = 0; i < m; i++)
        scanf("%le", &b[i]);
    
    /* Transponemos la matriz A */
	
    for (i=0; i<m; i++){
        for (j=0;j<n;j++){
            T[j][i] = A[i][j];
        }
    }
    
    prodMatMat(n,m,n,T,A,C);
    prodMatVec(n,m,T,b,v);

    a = ldlt(n,C,0.1);
    if (a==1){
        printf("No es diagonalizable");
        for (i = 0; i < n; i++){
            free (A[i]);
            free (T[i]);
            free (C[i]);
        }
        
        free(A);
        free(T);
        free(C);
        
        free(z);
        free(x);
        free(b);
        free(y);
        free(v);

        return 6;
        
    } else{
        
        
        for (i = 0; i < n; i++){
            for (j = 0; j < n; j++){
                printf ("%16.7e ", C[i][j]);
            }
            printf("\n");
        }

        resTinf(n, C, z, v);
    
        for (i=0; i<n; i++) {
            y[i] = z[i]/C[i][i];
        }
    
        resTsup(n, C, x, y);
    
        /* Valor residual */
		
        printf("\n Valor residual ||Ax - b||^2 = \n");
        prodMatVec (n,n,C,x,y);
        for (i=0; i <n; i++){
            y[i] = y[i] - v[i];
            y[i] = pow(y[i],2);
            total += y[i];
        }
    
        total = sqrt(total);
        printf( "%16.7e ", total);
    
    
        printf("\n Vector solución x = \n");
        for (i=0;i<n;i++){
            printf( "%16.7e ", x[i]);
        }
    
    
        printf("\n");
        
        for (i = 0; i < n; i++){
            free (A[i]);
            free (T[i]);
            free (C[i]);
        }

        free(A);
        free(T);
        free(C);
        
        free(z);
        free(x);
        free(b);
        free(y);
        free(v);

        return 0;
    }
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
