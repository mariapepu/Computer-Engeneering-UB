
# Problemes de Complexitat

## Algorísmica I

### Universitat de Barcelona - Grau d'enginyeria informàtica

Les solucions d'aquest notebook poden no ser òptimes, s'han triat només per criteris pedagògics i de demostració del càlcul de la complexitat. Les solucions no són doncs les dels algorismes, que es donen des de l'inici sinó la solució al càlcul de complexitat.
S'anima a intentar fer el càlcul, i només després mirar les solucions.

**Ordre invers** Tenim una llista ordenada (no necessàriament amb un ordre natural) de mida $n$. El que volem ara és donar-li la volta, és a dir, tenir l'ordre invers. 


```python
def reverse_order(arr):
    size = len(arr)
    if(size==1):
        return arr
    elif(size==2):
        listAux = [arr[1], arr[0]]
        return listAux
    else:
        leftArr = arr[:size//2]
        rightArr = arr[size//2:]
        revLeftArr = reverse_order(leftArr)
        revRightArr = reverse_order(rightArr)
        return revRightArr + revLeftArr
```


```python
reverse_order([1,2,3,4,5,6,7,8])
```




    [8, 7, 6, 5, 4, 3, 2, 1]




```python
reverse_order([1,3,4,6,7,12,15,45,65])
```




    [65, 45, 15, 12, 7, 6, 4, 3, 1]




```python
reverse_order(["cat", "dog", "fish"])
```




    ['fish', 'dog', 'cat']



Sol·lució (dóna dos clics aquí per veure-la)

<!--
Per a cada recursió agafem dues llistes de n/2 elements. => a = 2; b = 2
En sumar la llista a l'últim return gastem O(len(invRightArr+invLeftArr))=O(n) en crear la nova llista. => d = 1
Aplicant el teorema master que log_b(a)=log_2(2)=1 i d=1, per tant estem en el cas 2
Complexitat = =(n^1*logn)=O(nlogn) 
-->

Suposem algorismes la complexitat dels quals podem escriure com:

1. $T(n)=3T(n/3)+\sqrt{n}$
2. $T(n)=3T(n/2)+n^2$
3. $T(n)=4T(n/2)+n^2$

Calcula la seva complexitat mitjançant el teorema Master

Sol·lució (dóna dos clics aquí per veure-la)

<!--
 1. log_3(3)=1 > d=1/2  => Cas 1 => O(n^1) = O(n)
 2. log_2(3) < d=2      => Cas 3 => O(n^2)
 3. log_2(4) = 2 == d=2 => Cas 2 = O(n^2log(n))
 -->

**Norma en el infinit** Definim la norma a l'infinit d'una matriu $A$ quadrada $nxn$ com: 

$$\Vert{A}\Vert_{\infty}=max_{1\leq{j}\leq{n}}{\sum_{k=1}^n}{|A_{j,k}|}$$


```python
import math
def infinity_norm(A):
    inf_norm=0
    for j in range(len(A)):
        row_sum = 0
        for k in range(len(A[j])):
            row_sum += abs(A[j][k])
        if(inf_norm < row_sum):
            inf_norm = row_sum
    return inf_norm
```


```python
infinity_norm([[3,2,1],
               [1,4,-4],
               [-2,-4,-1]])
```




    9




```python
infinity_norm([[0,0,0],
                [0,0,0],
                [0,0,0]])
```




    0



Sol·lució (dóna dos clics aquí per veure-la)

<!--
import math
def infinity_norm(A):
    inf_norm=0
    for j in range(len(A)):-------------------------------------------- el de dintre del bucle x n   n x (n+3) O(n^2)
        row_sum = 0 --------------------------------------------------- O(1)
        for k in range(len(A[j])):------------------------------------- el de dintre del bucle x n   n
            row_sum += abs(A[j][k])------------------------------------ O(1)
        if(inf_norm < row_sum):---------------------------------------- O(1)
            inf_norm = row_sum----------------------------------------- O(1)
    return inf_norm---------------------------------------------------- O(1)
 La complexitat de l'algorisme és O(n^2). Si suposem una matrix nxm llavors la complexitat seria O(nxm)
 -->

**La jugada de Bernoulli** En aquest problema tirarem una moneda $n$ cops. Suposarem que una moneda només pot treure cara (C) o creu (X). Volem saber totes les situacions possibles quan tirem la moneda els $n$ cops. Per exemple, per a $n=2$ les possibilitats són $(C,C), (C,X), (X,C), (X,X)$


```python
def bernoulli_possibilities(n):
    import itertools
    a=lst = list(set(itertools.product(("X","C"),repeat=n)))
    return(a)

```


```python
bernoulli_possibilities(3)
```




    [('C', 'X', 'C'),
     ('C', 'C', 'C'),
     ('X', 'X', 'X'),
     ('C', 'C', 'X'),
     ('X', 'C', 'X'),
     ('X', 'C', 'C'),
     ('C', 'X', 'X'),
     ('X', 'X', 'C')]




```python
bernoulli_possibilities(4)
```




    [('X', 'X', 'X', 'C'),
     ('X', 'C', 'C', 'C'),
     ('C', 'C', 'C', 'C'),
     ('X', 'X', 'X', 'X'),
     ('X', 'C', 'C', 'X'),
     ('C', 'C', 'X', 'X'),
     ('X', 'C', 'X', 'C'),
     ('X', 'X', 'C', 'X'),
     ('X', 'C', 'X', 'X'),
     ('C', 'X', 'C', 'C'),
     ('C', 'C', 'X', 'C'),
     ('C', 'C', 'C', 'X'),
     ('C', 'X', 'C', 'X'),
     ('C', 'X', 'X', 'X'),
     ('X', 'X', 'C', 'C'),
     ('C', 'X', 'X', 'C')]



Sol·lució (dóna dos clics aquí per veure-la)

<!--
Estem bàsicament creant variacions amb repetició de n elements sobre un conjunt de 2 elements('x','c'), per tant, exactament fem  (2^n) llistes. Cada llista té n elements i la creem de zero.
La complexitat és d'ordre n*2^n => O(n 2^n)
 -->

**Residus quadràtics** Volem trobar els elements $r$ tal que $x^2 = r \ (mod \ m)$


```python
def quadratic_residue(m):
    q_residues = set()
    for x in range(m):
        r = (x**2)%m
        if(r!=0):
            q_residues.add(r)
    return q_residues
```


```python
quadratic_residue(15)
```




    {1, 4, 6, 9, 10}



perque 1 mod 15 = 1 i 1 = 1^2; perque 25 mod 15 = 10 i 25 = 5^2; perque 64 mod 15 = 4 i 64 = 8^2 etc  

Considerem que treballem amb nombres petits, i per tant les operacions de comparació i aritmètiques tenen un cost de $\mathcal{O}(1)$

Sol·lució (dóna dos clics aquí per veure-la)

<!--
def quadratic_residue(n):
    q_residues = set() ------------------------------------- O(1)
    for x in range(n): ------------------------------------- el de dintre del bucle x n   n x (3) O(n)
        r = (x**2)%n --------------------------------------- O(1), considerem que els nombres són petits
        if(r!=0):------------------------------------- O(1)
            q_residues.add(r)------------------------------- O(1)
    return q_residues
La complexitat és O(n), essent n el mòdul considerat.
-->
 
 

**Fusió de llistes** Donades dues llistes $A, B$ de mida $n$ i $m$ respectivament llistes volem fusionar-les sense alterar la llista inicial. Una manera de fer-ho és la següent:


```python
def fusio(A, B):
    novaA=A[:]
    for i in range(len(B)):
        novaA.append(B[i])
    return novaA
```


```python
A = [1,3,5,4]
B = [3,2,1,1]
print(fusio(A,B))
print(A)
print(B)
```

    [1, 3, 5, 4, 3, 2, 1, 1]
    [1, 3, 5, 4]
    [3, 2, 1, 1]
    

Sol·lució (dóna dos clics aquí per veure-la)

<!--
def fusio(A, B):
    novaA=A[:]------------------------------------------  O(n) on n és la longitud d'A
    for i in range(len(B)):-----------------------------  el de dintre el bucle x m, on m és la longitud de B   O(m)
        novaA.append(B[i])------------------------------- O(1)
    return novaA
 La complexitat és O(n+m), on n és la longitud d'A i m la longitud de B
 --> 

**Eliminar duplicats** Suposem que tenim una llista amb $n$ elements $[a_1,a_2,...,a_n]$. Volem generar una nova llista amb el mateix ordre però amb només una còpia de cada element: la primera que surt a la llista. Un algorisme per fer açò és:


```python
def delete_duplicates(A):
    newList = []
    for i in range(0,len(A)):
        actual_elem = A[i]       
        if(actual_elem != 'repetit'):
            newList.append(actual_elem)
            for j in range(i, len(A)):
                if(A[j] == actual_elem):
                    A[j] = 'repetit'
    return newList
```


```python
delete_duplicates([1,1,2,3,4,5,6,7,5,2,9])
```




    [1, 2, 3, 4, 5, 6, 7, 9]



Sol·lució (dóna dos clics aquí per veure-la)

<!--
def delete_duplicates(A):
    newList = []-------------------------------------------------- O(1)
    for i in range(0,len(A)):------------------------------------- el de dintre el bucle per n, on n és la longitud d'A                                                                                 O(n*n*n/2) 
        actual_elem = A[i] --------------------------------------- O(1)      
        if(actual_elem != 'repetit'):----------------------------- O(1)  en el pitjor cas l'if es compliria n-1 vegades O(n*n/2)
            newList.append(actual_elem)--------------------------- O(1)
            for j in range(i, len(A)):---------------------------- O(n-1, n-2, n-3...) el de dintre * en terme mig O(n/2) O(n/2)
                if(A[j] == actual_elem):-------------------------- O(1)
                    A[j] = 'repetit'------------------------------ O(1)
    return newList ----------------------------------------------- O(1)
-->
O(n3)

**Producte dels $log(n)$ primers nombres** Donat un n enter volem saber quin és el producte dels enters des d'1 fins a l'enter més gran menor que $log(n)$


```python
from math import log
def multiplication(n):
    i=1
    product = 1
    while(i<=log(n)):
        product *= i
        i+=1
    return product
```


```python
multiplication(120)
```




    24



Sol·lució (dóna dos clics aquí per veure-la)

<!--

def multiplication(n):
    i=1 ------------------------------------------------- O(1)
    product = 1 ----------------------------------------- O(1)
    while(i<=log(n)): ----------------------------------- el de dins x log(n), O(logn)
        product *= i ------------------------------------ O(1)  considerem que els valors són molt petits
        i+=1 -------------------------------------------- O(1)
    return producte ------------------------------------- O(1)

Per tant la complexitat és O(logn)
-->
 
 

**Comprovar si un nombre és primer** Sigui $n$ un nombre, volem saber si és primer o no. Per fer-ho només necessitem saber si te algún divisor menor que $\sqrt{n}$. Si no en té no serà primer.


```python
from math import sqrt
def check_prime_number(n):
    prime = True
    sqrtn = int(sqrt(n))
    i=2
    while i<=sqrtn and prime:
        if(n%i==0):
            prime = False
        i+=1
    return prime
```


```python
check_prime_number(9)
```




    False



Sol·lució (dóna dos clics aquí per veure-la)

<!--

def check_prime_number(n):
    prime = True ------------------------------------- O(1)
    sqrtn = int(sqrt(n)) ----------------------------- O(1)
    i=2 ---------------------------------------------- O(1)
    while i<=sqrtn and prime: ------------------------ O(sqrt(n)). En el cas pitjor(primer) sqrt(n)*complexitat del de dins
        if(n%i==0): ---------------------------------- O(1)
            prime = False ---------------------------- O(1)
        i+=1 ----------------------------------------- O(1)
    return prime ------------------------------------- O(1)

Per tant la complexitat és O(sqrt(n))
-->

**Llista de repeticions** Volem saber quants cops surt cada nombre si fem totes les multiplicacions possibles d'1 fins a $n$ tres cops, és a dir, el següent algorisme:


```python
def count_repetitions(n):
    dict_of_reps = {}
    for i in range(1,n+1):
        for j in range(1,n+1):
            for k in range(1,n+1):
                if (i*j*k) not in dict_of_reps:
                    dict_of_reps[(i*j*k)] = 1
                else:
                    dict_of_reps[(i*j*k)] +=1
    return dict_of_reps
```

Retorna només els nombres que surten com a key i el nombre de cops com a value.


```python
count_repetitions(4)
```




    {1: 1,
     2: 3,
     3: 3,
     4: 6,
     6: 6,
     8: 7,
     9: 3,
     12: 9,
     16: 6,
     18: 3,
     24: 6,
     32: 3,
     27: 1,
     36: 3,
     48: 3,
     64: 1}



Sol·lució (dóna dos clics aquí per veure-la)

<!--

def count_repetitions(n):
    dict_of_reps = {} ------------------------------------------ O(1)
    for i in range(1,n+1): ------------------------------------- n per el de dins
        for j in range(1,n+1): --------------------------------- n per el de dins
            for k in range(1,n+1): ----------------------------- n per el de dins
                if (i*j*k) not in dict_of_reps: ---------------- O(1)
                    dict_of_reps[(i*j*k)] = 1 ------------------ O(1)
                else:
                    dict_of_reps[(i*j*k)] +=1 ------------------ O(1)
    return dict_of_reps

Per tant la complexitat és O(n^3)
-->

(c) Ruben Ballester, auxiliar docent curs 2018-2019
