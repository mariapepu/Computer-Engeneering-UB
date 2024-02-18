-- INVERSOR  -----------------------------------------
ENTITY inv IS 
	PORT(a:IN BIT; z:OUT BIT);
END inv;


ARCHITECTURE logica OF inv IS
BEGIN
	z<= NOT a;
END logica;


ARCHITECTURE logica_retard OF inv IS
BEGIN
	z<= NOT a AFTER 5 ns;
END logica_retard;


-- AND2 -----------------------------------------
ENTITY and2 IS
	PORT(a,b:IN BIT; z: OUT BIT);
END and2;


ARCHITECTURE logica OF and2 IS
BEGIN
	z<= a AND b;
END logica;

ARCHITECTURE logica_retard OF and2 IS
BEGIN
	z<= a AND b  AFTER 5 ns;
END logica_retard;

--- dec1a2IFTHEN---

ARCHITECTURE ifthen OF dec1a2 IS
BEGIN
 PROCESS (d, e)
 BEGIN	
	IF d = '0' AND e = '0' THEN z0 <= '0'; z1 <= '0';
      	ELSIF d = '0' AND e = '1' THEN z0 <= '0'; z1 <= '0';
       	ELSIF d = '1' AND e = '0' THEN z0 <= '0'; z1 <='1';
	ELSIF d = '1' AND e = '1' THEN z0 <= '1'; z1 <='0';
	END IF;
 END PROCESS;
END ifthen;

------------------------ Decodificador de 2 a 4 bits -------------------------------

ENTITY dec2a4 IS
PORT(control,dada0,dada1:IN BIT; sortida0, sortida1, sortida2, sortida3: OUT BIT); 
END dec2a4;

---IFTHEN---

ARCHITECTURE ifthen OF dec2a4 IS
BEGIN
 PROCESS (control, dada0, dada1)
        BEGIN
		IF control ='0' THEN sortida0 <= '0';sortida1 <= '0';sortida2 <= '0';sortida3 <= '0';
		ELSIF control='1' AND dada0='0' AND dada1='0' THEN sortida0<='0';sortida1<='0';sortida2<='0';sortida3<='1';
		ELSIF control='1' AND dada0='0' AND dada1='1' THEN sortida0<='0';sortida1<='0';sortida2<='1';sortida3<='0';
		ELSIF control='1' AND dada0='1' AND dada1='0' THEN sortida0<='0';sortida1<='1';sortida2<='0';sortida3<='0';
		ELSIF control='1' AND dada0='1' AND dada1='1' THEN sortida0<= '1';sortida1<='0';sortida2<='0';sortida3<='0';
		END IF;
 END PROCESS;
END ifthen;

---ESTRUCTURAL---

ARCHITECTURE estructural OF dec2a4 IS
	
COMPONENT portaand2 IS
PORT(a,b: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT inversor IS
PORT(a: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT dec_1a2 IS
PORT(d,e: IN BIT; z1, z0: OUT BIT);
END COMPONENT;

SIGNAL ent0, ent1, ent2, sort0, sort1, sort2, sort3, sortinv : BIT;

FOR DUT1: inversor USE ENTITY WORK.inv(logica_retard);
FOR DUT2: portaand2 USE ENTITY WORK.and2(logica_retard);
FOR DUT3: portaand2 USE ENTITY WORK.and2(logica_retard);
FOR DUT4: dec_1a2 USE ENTITY WORK.dec1a2(ifthen);
FOR DUT5: dec_1a2 USE ENTITY WORK.dec1a2(ifthen);


BEGIN
ent0<=control;
ent1<=dada0;
ent2<=dada1;

DUT1: inversor PORT MAP (dada1, sortinv);
DUT2: portaand2 PORT MAP (sortinv, control, sort1);
DUT3: portaand2 PORT MAP (dada1, control, sort2);
DUT4: dec_1a2 PORT MAP (dada0, sort1, sortida0, sortida1);
DUT5: dec_1a2 PORT MAP (sort2, dada0, sortida2, sortida3);

END estructural;

---Banc de Proves---

ENTITY p3_bancdeproves IS
END p3_bancdeproves;

ARCHITECTURE testeig OF p3_bancdeproves IS

COMPONENT dec_2a4 IS
PORT (control,dada0,dada1: IN BIT; sortida0,sortida1,sortida2,sortida3: OUT BIT);
END COMPONENT;

SIGNAL control,dada0,dada1,sortida0,sortida1,sortida2,sortida3: BIT;
FOR DUT1: dec_2a4 USE ENTITY WORK.dec2a4(estructural);

BEGIN
DUT1: dec_2a4 PORT MAP(control, dada0, dada1, sortida0, sortida1, sortida2, sortida3);

PROCESS (control, dada0, dada1)
BEGIN
control <= NOT control AFTER 200 ns;
dada0 <= NOT dada0 AFTER 100 ns;
dada1 <= NOT dada1 AFTER 50 ns;
END PROCESS;

END testeig;
 

