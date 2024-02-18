------------------------------------ INVERSOR ----------------------------------------
ENTITY inv IS 
	PORT(a: IN BIT; z: OUT BIT);
END inv;


ARCHITECTURE logica_retard OF inv IS
BEGIN
	z <= NOT a AFTER 4 ns;
END logica_retard;


------------------------------------- AND2 -------------------------------------------
ENTITY and2 IS
	PORT(a, b: IN BIT; z: OUT BIT);
END and2;

ARCHITECTURE logica_retard OF and2 IS
BEGIN
	z <= a AND b  AFTER 4 ns;
END logica_retard;

-------------------------------------- OR3 -------------------------------------------
ENTITY or3 IS
	PORT(a, b, c:IN BIT; z: OUT BIT);
END or3;

ARCHITECTURE logica_retard OF or3 IS
BEGIN
	z<= a OR b OR c  AFTER 5 ns;
END logica_retard;

------------------------------------- XOR2 -------------------------------------------
ENTITY xor2 IS
	PORT(a, b:IN BIT; z: OUT BIT);
END xor2;

ARCHITECTURE logica_retard OF xor2 IS
BEGIN
	z<= (NOT a AND b) OR (a AND NOT b) AFTER 5 ns;
END logica_retard;

--------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------

--- 1) FF D actius per flanc de baixada ---

ENTITY FF_D IS
PORT (D,Clk,Pre: IN BIT; Q: OUT BIT);
END FF_D;

ARCHITECTURE ifthen OF FF_D IS

SIGNAL qint: BIT;
  
BEGIN

PROCESS (Clk,Pre,D)
BEGIN
    IF Clk'EVENT AND Clk='0' THEN
      IF D='0' THEN Q <= '0' AFTER 6 ns;
			ELSIF D='1' THEN Q <= '1' AFTER 6 ns;
      END IF;
    END IF;
END PROCESS;

END ifthen;

--- 2) Funcio ---

ENTITY funcio IS
PORT (estat: IN BIT; A: IN BIT_VECTOR (1 DOWNTO 0); Z: OUT BIT_VECTOR (1 DOWNTO 0));
END funcio;

ARCHITECTURE estructural OF funcio IS

COMPONENT portaand2 IS
PORT(a,b: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portaor3 IS
PORT(a,b,c: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portaxor2 IS
PORT(a,b: IN BIT; z: OUT BIT);
END COMPONENT;

SIGNAL and1, and2, and3, orZ, xor1, xorZ: BIT;

FOR DUT1: portaand2 USE ENTITY WORK.and2(logica_retard);
FOR DUT2: portaand2 USE ENTITY WORK.and2(logica_retard);
FOR DUT3: portaand2 USE ENTITY WORK.and2(logica_retard);
FOR DUT4: portaor3 USE ENTITY WORK.or3(logica_retard);

FOR DUT5: portaxor2 USE ENTITY WORK.xor2(logica_retard);
FOR DUT6: portaxor2 USE ENTITY WORK.xor2(logica_retard);

BEGIN

DUT1: portaand2 PORT MAP (A(1), A(0), and1);
DUT2: portaand2 PORT MAP (A(0), estat, and2);
DUT3: portaand2 PORT MAP (A(1), estat, and3);
DUT4: portaor3 PORT MAP (and1, and2, and3, orZ);

DUT5: portaxor2 PORT MAP (A(0), A(1), xor1);
DUT6: portaxor2 PORT MAP (xor1, estat, xorZ);

Z(1) <= orZ;
Z(0) <= xorZ;

END estructural;


--- 2) Màquina ---

ENTITY MAQUINA IS
PORT(clock: IN BIT; X: IN BIT_VECTOR (1 DOWNTO 2); Z, E: OUT BIT_VECTOR (1 DOWNTO 0));
END MAQUINA;

ARCHITECTURE estructural OF MAQUINA IS

COMPONENT FFD IS
PORT(D,Clk,Pre: IN BIT; Q: OUT BIT);
END COMPONENT;

COMPONENT fun IS
PORT(estat: IN BIT; A: IN BIT_VECTOR (1 DOWNTO 0); Z: OUT BIT_VECTOR (1 DOWNTO 0));
END COMPONENT;

SIGNAL Q1, Q0: BIT;
SIGNAL funcio: BIT_VECTOR (1 DOWNTO 0);

FOR DUT1: fun USE ENTITY WORK.funcio(estructural);
FOR DUT2: FFD USE ENTITY WORK.FF_D(ifthen);
FOR DUT3: FFD USE ENTITY WORK.FF_D(ifthen);

BEGIN

DUT1: fun PORT MAP (Q1, X, funcio);
DUT2: fun PORT MAP (funcio(1), clock, '1', Q1);
DUT2: fun PORT MAP (funcio(0), clock, '1', Q0);

Z(0) <= Q0;
Z(1) <= Q1;

PROCESS (E)
  BEGIN
    IF Z(0) = '0' AND Z(1) = '0' THEN E <= '00' AFTER 6 ns;
    ELSIF Z(0) = '0' AND Z(1) = '1' THEN E <= '01' AFTER 6 ns;
    ELSIF Z(0) = '1' AND Z(1) = '0' THEN E <= '10' AFTER 6 ns;
    ELSIF Z(0) = '1' AND Z(1) = '1' THEN E <= '11' AFTER 6 ns;
    END IF;
END PROCESS;

END estructural;

--- 3) Banc de Proves ---

ENTITY banc_de_proves IS
END banc_de_proves;


ARCHITECTURE test OF banc_de_proves IS

COMPONENT MACHINE IS
PORT(clock: IN BIT; X: IN BIT_VECTOR (1 DOWNTO 2); Z, E: OUT BIT_VECTOR (1 DOWNTO 0));
END COMPONENT;

SIGNAL rellotge: BIT;
SIGNAL ics, f, state: BIT_VECTOR (1 DOWNTO 0);

FOR DUT1: MACHINE USE ENTITY WORK.MAQUINA(estructural);

BEGIN

DUT1: MACHINE PORT MAP (rellotge, ics, f, state);

PROCESS
  BEGIN
    rellotge <= NOT rellotge AFTER 50 ns;
END PROCESS;

END test;

--- 4) Comentari ---

--- L'entitat MAQUINA és una màquina d'estats finits, esta constituida per dos 
--- flip-flops D (actius per flanc de baixada) i una funció.
--- El rellotge canvia cada 50 ns periodicament.
--- A més de la sortida del circuit, hi ha una altre sortida que ens diu en 
--- quin dels estats del diagrama d'estats es troba (en aquest cas és equivalent 
--- a la sortida).