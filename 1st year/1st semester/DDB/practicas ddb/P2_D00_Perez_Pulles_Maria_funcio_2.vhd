-----------FUNCIO 2---------------

ENTITY funcio_2 IS
PORT(a, b, c, d: IN BIT; f: OUT BIT);
END funcio_2;

--LA FUNCIO
ARCHITECTURE logica of funcio_2 IS
BEGIN
f <= (a AND c AND (a XOR d))OR((NOT b) AND c);
END logica;

------ESTRUCTURAL----------

ARCHITECTURE estructural of funcio_2 IS

COMPONENT portainv IS
	PORT (a: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portaand2 IS
	PORT (a, b: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portaand3 IS
	PORT (a, b, c: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portaor2 IS
	PORT (a, b: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portaxor2 IS
	PORT (a, b: IN BIT; z: OUT BIT);
END COMPONENT;

SIGNAL invb, andbc, andace, xorad, oracebc : BIT;
FOR DUT1: portainv USE ENTITY WORK.inv(logica);
FOR DUT2: portaand2 USE ENTITY WORK.and2(logica);
FOR DUT3: portaxor2 USE ENTITY WORK.or2(logica);
FOR DUT4: portaand3 USE ENTITY WORK.and3(logica);
FOR DUT5: portaor2 USE ENTITY WORK.xor2(logica);

BEGIN
DUT1: portainv PORT MAP (b, invb);
DUT2: portaand2 PORT MAP (invb, c, andbc);
DUT3: portaxor2 PORT MAP (a, d, xorad);
DUT4: portaand3 PORT MAP (a, c, xorad, andace);
DUT5: portaor2 PORT MAP (andace, andbc, f);

END estructural;

-------banc de proves--------

ENTITY banc_de_proves IS
END banc_de_proves;

ARCHITECTURE test OF banc_de_proves IS
COMPONENT bloc_simulat IS
PORT (a, b, c, d: IN BIT; f: OUT BIT);
END COMPONENT;

SIGNAL ent3, ent2, ent1, ent0, sort_logica, sort_estructural: BIT;
FOR DUT1: bloc_simulat USE ENTITY WORK.funcio_2(logica);
FOR DUT2: bloc_simulat USE ENTITY WORK.funcio_2(estructural);

BEGIN
DUT1: bloc_simulat PORT MAP(ent3, ent2, ent1, ent0,  sort_logica);
DUT2: bloc_simulat PORT MAP(ent3, ent2, ent1, ent0,  sort_estructural);

PROCESS (ent3, ent2, ent1, ent0)
BEGIN
ent3 <= NOT ent3 AFTER 400 ns;
ent2 <= NOT ent2 AFTER 200 ns;
ent1 <= NOT ent1 AFTER 100 ns;
ent0 <= NOT ent0 AFTER 50 ns;
END PROCESS;

END test;

---
