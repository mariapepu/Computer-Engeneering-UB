------------------------------------- LACH D -------------------------------------------
ENTITY lach_d_PreClr IS
PORT(D,Clk,Pre,Clr: IN BIT; Q,NO_Q: OUT BIT);
END lach_d_PreClr;

ARCHITECTURE ifthen OF lach_d_PreClr IS
SIGNAL qint: BIT;

BEGIN

PROCESS (Clk,Pre,Clr)
BEGIN
IF Clr='0' THEN qint<='0' AFTER 3 ns;
      ELSIF Pre='0' THEN qint<='1' AFTER 3 ns;
      ELSIF Clk'EVENT AND Clk='0' THEN qint <= D AFTER 6 ns;

END IF;
END PROCESS;
Q<=qint; 
NO_Q<=NOT qint;
END ifthen;

------------------------------------- FLIP FLOP JK -----------------------------------
ENTITY ff_jk_PreClr IS
PORT(J,K,Clk,Pre,Clr: IN BIT; Q,NO_Q: OUT BIT);
END ff_jk_PreClr;

ARCHITECTURE ifthen OF ff_jk_PreClr IS
SIGNAL qint: BIT;
BEGIN
PROCESS (J,K,Clk,Pre,Clr)
BEGIN
IF Clr='0' THEN qint<='0' AFTER 3 ns;
ELSE
IF Pre='0' THEN qint<='1' AFTER 3 ns;
ELSE
		IF Clk='1' THEN
			IF J='0' AND K='0' THEN qint<=qint AFTER 6 ns;
			ELSIF J='0' AND K='1' THEN qint<='0' AFTER 6 ns;
			ELSIF J='1' AND K='0' THEN qint<='1' AFTER 6 ns;
			ELSIF J='1' AND K='1' THEN qint<= NOT qint AFTER 6 ns;
			END IF;

		END IF;
	END IF;
END IF;

END PROCESS;
Q<=qint; 
NO_Q<=NOT qint;
END ifthen;

------------------------------- BNAC DE PROVES DELS DOS -----------------------------------
ENTITY banco_pruebas IS
END banco_pruebas;

ARCHITECTURE test OF banco_pruebas IS

COMPONENT mi_lach_d_PreClr IS
PORT(D,Clk,Pre,Clr: IN BIT; Q,NO_Q: OUT BIT);
END COMPONENT;

COMPONENT mi_ff_jk_PreClr IS
PORT(J,K,Clk,Pre,Clr: IN BIT; Q,NO_Q: OUT BIT);
END COMPONENT;

SIGNAL ent1,ent2,clock,preset,clear,Dsort_Q,Dsort_noQ,JKsort_Q,JKsort_noQ: BIT;
FOR DUT1: mi_lach_d_PreClr USE ENTITY WORK.lach_d_PreClr(ifthen);
FOR DUT2: mi_ff_jk_PreClr USE ENTITY WORK.ff_jk_PreClr(ifthen);

BEGIN
DUT1: mi_lach_d_PreClr PORT MAP (ent1,clock,preset,clear,Dsort_Q,Dsort_noQ);
DUT2: mi_ff_jk_PreClr PORT MAP (ent1,ent2,clock,preset,clear,JKsort_Q,JKsort_noQ);
ent1 <= NOT ent1 AFTER 800 ns;
ent2 <= NOT ent2 AFTER 400 ns;
clock <= NOT clock AFTER 500 ns;
preset <= '0', '1' AFTER 600 ns;
clear <= '1','0' AFTER 200 ns, '1' AFTER 400 ns;

END test;
------------------------------ PORTES PER IMPLEMENTAR EL CIRCUIT -----------------------------
-------- AND2 ---------
ENTITY and2 IS
	PORT(a,b:IN BIT; z: OUT BIT);
END and2;


ARCHITECTURE logica OF and2 IS
BEGIN
	z<= a AND b;
END logica;
-------- INVERSOR --------
ENTITY inv IS 
	PORT(a:IN BIT; z:OUT BIT);
END inv;


ARCHITECTURE logica OF inv IS
BEGIN
	z<= NOT a;
END logica;
---------- NOR -----------
ENTITY nor2 IS
	PORT(a,b:IN BIT; z: OUT BIT);
END nor2;


ARCHITECTURE logica OF nor2 IS
BEGIN
	z<= NOT(a OR b);
END logica;
------------------------------------- CIRCUIT --------------------------------------

ENTITY circuit IS
	PORT(x, ck:IN BIT; z:OUT BIT);
END circuit;

ARCHITECTURE estructural OF circuit IS

COMPONENT inversor IS 
PORT(a:IN BIT; z:OUT BIT);
END COMPONENT;

COMPONENT portaand2 IS
PORT(a,b:IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portanor2 IS
PORT(a,b:IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT Lach_D IS
PORT(D,Clk,Pre,Clr: IN BIT; Q,NO_Q: OUT BIT);
END COMPONENT;

COMPONENT FF_JK IS
PORT(J,K,Clk,Pre,Clr: IN BIT; Q,NO_Q: OUT BIT);
END COMPONENT;

SIGNAL invX, a, q, no_q, j, k: BIT;

FOR DUT1: inversor USE ENTITY WORK.inv(logica);
FOR DUT2: portaand2 USE ENTITY WORK.and2(logica);
FOR DUT3: portanor2 USE ENTITY WORK.nor2(logica);
FOR DUT4: Lach_D USE ENTITY WORK.lach_d_PreClr(ifthen);
FOR DUT5: FF_JK USE ENTITY WORK.ff_jk_PreClr(ifthen);

BEGIN
---al compilar em diu que no pot usar la sortida z com a entrada, li e asignat "a" pero  no funciona. no se com solucionar-ho
	z<=a	
	DUT1: inversor PORT MAP(x, invX);
	DUT2: portaand2 PORT MAP(x, no_q, j);
	DUT3: portanor2 PORT MAP(invX, q, k);
	DUT4: Lach_D PORT MAP(x, ck, q, no_q);
	DUT5: FF_JK PORT MAP(j, k, ck, a); --aqui enlloc de a he posat z al principi, com e descrit a dalt, no funciona)
END estructural;


------------- BANC PROVES CIRCUIT ----------------

ENTITY banc__prova_circuit IS
END banc__prova_circuit;

ARCHITECTURE test OF banc__prova_circuit IS

COMPONENT com IS
PORT(x, ck:IN BIT; z:OUT BIT);
END COMPONENT;

SIGNAL x, ck, z: BIT;

FOR DUT: com USE ENTITY WORK.circuit(estructural);

BEGIN
DUT: com PORT MAP(x, ck, z);

ck <= NOT ck AFTER 100 ns;
------- x = el que es veu a la imatge del doc de la practica que aproximadament ve a ser el que veiem a sota ---------------
x <= '0', '1' AFTER 130 ns, '0' AFTER 350 ns, '1' AFTER 510 ns, '0' AFTER 530 ns, '1' AFTER 570 ns, '0' AFTER 590 ns, '1' AFTER 670 ns, '0' AFTER 750 ns;

END test;