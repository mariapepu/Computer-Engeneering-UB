/* Perez Pulles Maria - 23845458R */

#ifndef PRAC1FUNS_H_INCLUDED
#define PRAC1FUNS_H_INCLUDED

void resTinf (int n, double **L, double *x, double *b);
void resTsup (int n, double **U, double *x, double *b);
void prodMatVec (int m, int n, double **A, double *x, double *y);
void prodMatMat (int m, int n, int p, double **A, double **B, double **C);

#endif
