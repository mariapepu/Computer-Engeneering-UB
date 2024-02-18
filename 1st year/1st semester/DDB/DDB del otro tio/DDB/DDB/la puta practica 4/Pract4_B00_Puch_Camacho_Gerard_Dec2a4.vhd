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


------------------------ Decodificador de 2 a 4 bits -------------------------------

ENTITY dec2a4 IS
PORT(c:IN BIT; d: IN BIT_VECTOR(1 DOWNTO 0); sortida: OUT BIT_VECTOR (3 DOWNTO 0)); 
END dec2a4;

---IFTHEN---

ARCHITECTURE ifthen OF dec2a4 IS
BEGIN
 PROCESS (c, d)
        BEGIN
		IF c ='0' THEN sortida <= "0000";
		ELSE
            	IF d(0) = '0' AND d(1) = '0' THEN sortida <= "0001";
              	ELSIF d(0) = '0' AND d(1) = '1' THEN sortida <= "0010";
              	ELSIF d(0) = '1' AND d(1) = '0' THEN sortida <= "0100";
		ELSIF d(0) = '1' AND d(1) = '1' THEN sortida <= "1000";
		END IF;
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
PORT(d,e: IN BIT; z: OUT BIT_VECTOR(1 DOWNTO 0));
END COMPONENT;

SIGNAL control, sort1, sort2, sortinv: BIT;
SIGNAL dada, z1, z2: BIT_VECTOR(1 DOWNTO 0);

FOR DUT1: inversor USE ENTITY WORK.inv(logica_retard);
FOR DUT2: portaand2 USE ENTITY WORK.and2(logica_retard);
FOR DUT3: portaand2 USE ENTITY WORK.and2(logica_retard);
FOR DUT4: dec_1a2 USE ENTITY WORK.dec1a2(ifthen);
FOR DUT5: dec_1a2 USE ENTITY WORK.dec1a2(ifthen);


BEGIN
dada(0) <= d(0);
dada(1) <= d(1);
control <= c;


DUT1: inversor PORT MAP (dada(1), sortinv);
DUT2: portaand2 PORT MAP (sortinv, control, sort1);
DUT3: portaand2 PORT MAP (dada(1), control, sort2);
DUT4: dec_1a2 PORT MAP (dada(0), sort1, z1);
DUT5: dec_1a2 PORT MAP (dada(0), sort2, z2);

sortida(0) <= z1(0);
sortida(1) <= z1(1);
sortida(2) <= z2(0);
sortida(3) <= z2(1);

END estructural;

---Banc de Proves---

ENTITY b_proves IS
END b_proves;

ARCHITECTURE test OF b_proves IS

COMPONENT dec_2a4 IS
PORT(c:IN BIT; d: IN BIT_VECTOR(1 DOWNTO 0); sortida: OUT BIT_VECTOR (3 DOWNTO 0)); 
END COMPONENT;

SIGNAL control: BIT;
SIGNAL z: BIT_VECTOR (3 DOWNTO 0);
SIGNAL dada: BIT_VECTOR (1 DOWNTO 0);

FOR DUT1: dec_2a4 USE ENTITY WORK.dec2a4(estructural);

BEGIN
DUT1: dec_2a4 PORT MAP (control, dada, z);

PROCESS (control, dada)
 BEGIN
	control <= NOT control AFTER 200 ns;
	dada(1) <= NOT dada(1) AFTER 100 ns;
	dada(0) <= NOT dada(0) AFTER 50 ns;
 END PROCESS;

END test;