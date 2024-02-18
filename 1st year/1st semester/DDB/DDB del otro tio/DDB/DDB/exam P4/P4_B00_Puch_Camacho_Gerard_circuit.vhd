
--1) NAND2 -----------------------------------------
--- Entitat de la porta nand (2 bits d'entrada) que ens servira com a component per al circuit. ---

ENTITY nand2 IS
	PORT(a,b:IN BIT; z: OUT BIT);
END nand2;

ARCHITECTURE logica OF nand2 IS
BEGIN
	z<= NOT a AND b;
END logica;

ARCHITECTURE logicaretard OF nand2 IS
BEGIN
	z<= NOT a AND b AFTER 5 ns;
END logicaretard;

--2) Decodificador de 2 a 4 bits -------------------------------
--- Entitat del decodificador (2 bits d'entrada i 4 de sortida) que ens servira com a component per al circuit. ---

ENTITY dec2a4 IS
PORT(d: IN BIT_VECTOR(1 DOWNTO 0); f: OUT BIT_VECTOR (3 DOWNTO 0)); 
END dec2a4;

ARCHITECTURE ifthen OF dec2a4 IS
BEGIN
 PROCESS (d)
        BEGIN
       	IF d(0) = '0' AND d(1) = '0' THEN f <= "1110";
       	ELSIF d(0) = '0' AND d(1) = '1' THEN f <= "1101";
 	     	ELSIF d(0) = '1' AND d(1) = '0' THEN f <= "1011";
	     	ELSIF d(0) = '1' AND d(1) = '1' THEN f <= "0111";
		END IF;
 END PROCESS;
END ifthen;

--3) Circuit -------------------------------
ENTITY circuit IS
PORT(dada: IN BIT_VECTOR(1 DOWNTO 0); control: IN BIT; funcio: OUT BIT_VECTOR (3 DOWNTO 0)); 
END circuit;


ARCHITECTURE estructural OF circuit IS
--- Inlcuim els components que ens faran falta: portes nand (ens caldràn 4) i el decodificador (ens caldrà 1). ---	
COMPONENT portanand2 IS
PORT(a,b: IN BIT; z: OUT BIT);
END COMPONENT;


COMPONENT dec_2a4 IS
PORT(d: IN BIT_VECTOR(1 DOWNTO 0); f: OUT BIT_VECTOR (3 DOWNTO 0)); 
END COMPONENT;
--- Estableixo les senyals i DUTs necessaris. ---

SIGNAL c: BIT; 
SIGNAL d: BIT_VECTOR(1 DOWNTO 0);
SIGNAL z: BIT_VECTOR(3 DOWNTO 0);


FOR DUT1: portanand2 USE ENTITY WORK.nand2(logicaretard);
FOR DUT2: portanand2 USE ENTITY WORK.nand2(logicaretard);
FOR DUT3: portanand2 USE ENTITY WORK.nand2(logicaretard);
FOR DUT4: portanand2 USE ENTITY WORK.nand2(logicaretard);
FOR DUT5: dec_2a4 USE ENTITY WORK.dec2a4(ifthen);

--- Estableixo el circuit intern que seguira l'entitat 'circuit'. ---

BEGIN
d(0) <= dada(0);
d(1) <= dada(1);
c <= control;

DUT5: dec_2a4 PORT MAP (d, z);

DUT1: portanand2 PORT MAP (z(0), c, funcio(0));
DUT2: portanand2 PORT MAP (z(1), c, funcio(1));
DUT3: portanand2 PORT MAP (z(2), c, funcio(2));
DUT4: portanand2 PORT MAP (z(3), c, funcio(3));


END estructural;

--4) Banc de proves -------------------------------

ENTITY banc_proves IS

END banc_proves;

 

ARCHITECTURE test OF banc_proves IS

 

Component circuit IS

PORT (dada: IN BIT_VECTOR(1 DOWNTO 0); control: IN BIT; funcio: OUT BIT_VECTOR (3 DOWNTO 0));

END component;

 

SIGNAL dada: BIT_VECTOR(1 DOWNTO 0);

SIGNAL control: BIT;

SIGNAL funcio: BIT_VECTOR (3 DOWNTO 0);

 

FOR DUT1: circuit USE ENTITY WORK.circuit(estructural);

 

BEGIN

DUT1:circuit PORT MAP(dada, control, funcio);

 

PROCESS (dada, control)

BEGIN

dada(0) <= NOT dada(0) AFTER 100 ns;

dada(1) <= NOT dada(1) AFTER 200 ns;

control<= NOT control AFTER 413 ns;

 

END PROCESS;

END test;

--- La simulacio mostra els diferents casos que es poden donar en el circuit:
 -- *cada 100 ns les senyals  d'entrada ('dada0' i 'dada1') canvien i cada 413 ns, canvia la senyal 'contorl'.
 -- *el circuit només funciona si la variable 'control' = 1 (l'entrada de 'control' = 0, la 'funcio' = 0).
 -- *les sortides tenen el valor d'acord a la seva taula de la veritat (recordo, només en el cas 'control' = 0),
 -- si 'dada0' = 0 i 'dada1' = 0, 'funcio' = 1110, etc... ---









