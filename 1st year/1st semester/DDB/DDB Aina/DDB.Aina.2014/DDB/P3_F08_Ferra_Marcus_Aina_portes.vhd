-------------------OR PORTS-------------------

--ENTITY OR2:
ENTITY or2 is
  PORT (a,b: IN BIT; z: OUT BIT);
END or2;

--ARCHITECTURE OF OR2:
--Without delay.
ARCHITECTURE logica OF or2 IS
BEGIN
	z <= a OR b;
END logica;

--With 3 ns of delay.
ARCHITECTURE logicaretard OF or2 IS
BEGIN
  z <= a OR b AFTER 3 ns; 
END logicaretard;

--ENTITY OR3:
ENTITY or3 IS
	PORT( a, b, c: IN BIT; z: OUT BIT);
END or3;

--ARCHITECTURES OF OR3:
--Without delay.
ARCHITECTURE logica OF or3 IS
BEGIN
 	z <= a OR b OR c;
END logica;
--With 3 ns of delay.
ARCHITECTURE logicaretard OF or3 IS
BEGIN
	z <= a OR b OR c after 2 ns;
END logicaretard;

--ENTITY OR4:
ENTITY or4 is
	PORT (a, b, c, d: IN BIT; z: OUT BIT);
END or4;

--ARCHITECTURES OF OR4:
--Without delay.
ARCHITECTURE logica of or4 IS
BEGIN
	z <= a OR b OR c OR d;
END logica;

--With 3 ns of delay.
ARCHITECTURE logicaretard OF or4 IS
BEGIN
	z <= a OR b OR c OR d AFTER 3 ns;
END logicaretard;



-------------------AND PORTS-------------------


--ENTITY AND2:
ENTITY and2 IS
  PORT (a,b: IN BIT; z: OUT BIT);
END and2;

--ARCHITECTURES OF AND2:
--Without delay.
ARCHITECTURE logica OF and2 IS
BEGIN
	z <= a AND b;
END logica;

--With 3 ns of delay.
ARCHITECTURE logicaretard OF and2 IS
BEGIN
  z <= a AND b AFTER 3 ns;
END logicaretard;

--ENTITY AND3:
ENTITY and3 IS
	PORT( a, b, c: IN BIT; z: OUT BIT);
END and3;

--ARCHITECTURES OF AND3:
--Without delay.
ARCHITECTURE logica of and3 IS
BEGIN
 	z <= a AND b AND c;
END logica;

--With 3 ns of delay.
ARCHITECTURE logicaretard of and3 IS
BEGIN
 	z <= a AND b AND c after 3 ns;
END logicaretard;

--ENTITY AND4:
ENTITY and4 IS
	PORT (a, b, c, d: IN BIT; z: OUT BIT);
END and4;

--ARCHITECTURE OF AND4:
--Without delay.
ARCHITECTURE logica OF and4 IS
BEGIN
	z <= a AND b AND c AND d;
END logica;

--With 3 ns of delay.
ARCHITECTURE logicaretard OF and4 IS
BEGIN
	z <= a AND b AND c AND d;
END logicaretard;


-------------------XOR PORTS-------------------

--ENTITY XOR2:
ENTITY xor2 IS
	PORT (a, b: IN BIT; z: OUT BIT);
END xor2;

--ARCHITECTURE OF XOR2:
ARCHITECTURE logicaretard of xor2 IS
BEGIN
	z <= a XOR b after 2 ns;
END logicaretard;

-------------------INVERSOR PORTS-------------------


--ENTITY INV:
ENTITY inv IS
  PORT (a: IN BIT; z: OUT BIT);
END inv;

--ARCHITECTURES OF INV:
--Without delay.
ARCHITECTURE logica OF inv IS
BEGIN
	z <= NOT a;
END logica;

--With 3 ns of delay.
ARCHITECTURE logicaretard OF inv IS
BEGIN
  z <= NOT a AFTER 3 ns;
END logicaretard;
