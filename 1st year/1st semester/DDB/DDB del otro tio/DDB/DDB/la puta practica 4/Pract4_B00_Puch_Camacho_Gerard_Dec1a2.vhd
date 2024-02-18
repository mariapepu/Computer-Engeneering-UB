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


------------------------ Decodificador de 1 a 2 bits -------------------------------

ENTITY dec1a2 IS
PORT(d,e:IN BIT; z: OUT BIT_VECTOR (1 DOWNTO 0)); 
END dec1a2;
                          
---IFTHEN---

ARCHITECTURE ifthen OF dec1a2 IS
BEGIN
 PROCESS (d, e)
 BEGIN	
	IF d = '0' AND e = '0' THEN Z <= "00";
      	ELSIF d = '0' AND e = '1' THEN Z <= "00";
       	ELSIF d = '1' AND e = '0' THEN Z <= "01";
	ELSIF d = '1' AND e = '1' THEN Z <= "10";
	END IF;
 END PROCESS;
END ifthen;

---ESTRUCTURAL---

ARCHITECTURE estructural OF dec1a2 IS
	
COMPONENT portaand2 IS
PORT(a,b: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT inversor IS
PORT(a: IN BIT; z: OUT BIT);
END COMPONENT;

SIGNAL entr1, entr2, sortinv: BIT;

FOR DUT1: inversor USE ENTITY WORK.inv(logica_retard);
FOR DUT2: portaand2 USE ENTITY WORK.and2(logica_retard);
FOR DUT3: portaand2 USE ENTITY WORK.and2(logica_retard);

BEGIN
entr1 <= d;
entr2 <= e;

DUT1: inversor PORT MAP (entr1, sortinv);
DUT2: portaand2 PORT MAP (sortinv, entr1, z(0));
DUT3: portaand2 PORT MAP (entr1, entr2, z(1));

END estructural;
