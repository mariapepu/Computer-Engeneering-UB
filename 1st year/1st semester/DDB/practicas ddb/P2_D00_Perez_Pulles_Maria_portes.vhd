--Maria Perez Pulles
--21/10/19

---------PUERTA INV-----------
ENTITY inv IS
  PORT (a: IN BIT; z: OUT BIT);
END inv;

ARCHITECTURE logica OF inv IS
BEGIN
z <= NOT a;
END logica;

ARCHITECTURE logicaretard OF inv IS
BEGIN
z <= NOT a AFTER 3 ns;
END logicaretard;

----------PUERTAS OR-----------

ENTITY or2 is
PORT (a,b: IN BIT; z: OUT BIT);
END or2;

ARCHITECTURE logica OF or2 IS
BEGIN
z <= a OR b;
END logica;

ARCHITECTURE logicaretard OF or2 IS
BEGIN
z <= a OR b AFTER 3 ns; 
END logicaretard;

ENTITY or3 IS
PORT( a, b, c: IN BIT; z: OUT BIT);
END or3;

ARCHITECTURE logica OF or3 IS
BEGIN
z <= a OR b OR c;
END logica;

ARCHITECTURE logicaretard OF or3 IS
BEGIN
z <= a OR b OR c after 2 ns;
END logicaretard;

ENTITY or4 is
PORT (a, b, c, d: IN BIT; z: OUT BIT);
END or4;

ARCHITECTURE logica of or4 IS
BEGIN
z <= a OR b OR c OR d;
END logica;

ARCHITECTURE logicaretard OF or4 IS
BEGIN
z <= a OR b OR c OR d AFTER 3 ns;
END logicaretard;

------------PUERTAS AND------------

ENTITY and2 IS
PORT (a,b: IN BIT; z: OUT BIT);
END and2;

ARCHITECTURE logica OF and2 IS
BEGIN
z <= a AND b;
END logica;

ARCHITECTURE logicaretard OF and2 IS
BEGIN
z <= a AND b AFTER 3 ns;
END logicaretard;

ENTITY and3 IS
PORT( a, b, c: IN BIT; z: OUT BIT);
END and3;

ARCHITECTURE logica of and3 IS
BEGIN
z <= a AND b AND c;
END logica;

ARCHITECTURE logicaretard of and3 IS
BEGIN
z <= a AND b AND c after 3 ns;
END logicaretard;

ENTITY and4 IS
PORT (a, b, c, d: IN BIT; z: OUT BIT);
END and4;

ARCHITECTURE logica OF and4 IS
BEGIN
z <= a AND b AND c AND d;
END logica;

ARCHITECTURE logicaretard OF and4 IS
BEGIN
z <= a AND b AND c AND d;
END logicaretard;

------------PUERTA XOR-------------

ENTITY xor2 IS
PORT (a, b: IN BIT; z: OUT BIT);
END xor2;

ARCHITECTURE logica OF xor2 IS
BEGIN
z <= a XOR b;
END logica;

ARCHITECTURE logicaretard of xor2 IS
BEGIN
z <= a XOR b after 3 ns;
END logicaretard;