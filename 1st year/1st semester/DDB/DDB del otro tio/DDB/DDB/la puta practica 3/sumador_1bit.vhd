--Entitats i arquitectures previes

ENTITY or2 IS
PORT (a,b: IN BIT; z: OUT BIT);
END or2;

ARCHITECTURE logica OF or2 IS 
BEGIN
z <= a OR b;
END logica;


ENTITY and2 IS
PORT (a,b: IN BIT; z: OUT BIT);
END and2;

ARCHITECTURE logica OF and2 IS
BEGIN
z <= a AND b;
END logica;

ENTITY xor2 IS
PORT (a,b: IN BIT; z: OUT BIT);
END xor2;

ARCHITECTURE logica OF xor2 IS 
BEGIN
z <= a XOR b;
END logica;

--SUMADOR MODIFICAT D'UN BIT
--Aquesta és la entitat del sumador modificat d'un bit que volem implementar
ENTITY sumador_modificat_1bit IS
PORT (a,b,cin: IN BIT; suma_1bit, cout_1bit, aib_1bit, aob_1bit: OUT BIT);
END sumador_modificat_1bit;

-- Aquesta és la definició del sumador modificat de forma estructural
ARCHITECTURE estructural OF sumador_modificat_1bit IS

COMPONENT portaand2 IS
PORT(a,b: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portaor2 IS
PORT(a,b: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portaxor2 IS
PORT (a,b: IN BIT; z: OUT BIT);
END COMPONENT ;


FOR DUT1: portaxor2 USE ENTITY WORK.xor2(logica);
FOR DUT2: portaxor2 USE ENTITY WORK.xor2(logica);
FOR DUT3: portaand2 USE ENTITY WORK.and2(logica);
FOR DUT4: portaor2 USE ENTITY WORK.or2(logica);
FOR DUT5: portaand2 USE ENTITY WORK.and2(logica);
FOR DUT6: portaor2 USE ENTITY WORK.or2(logica);


SIGNAL sort_xor, sort_or, sort_and1, sort_and2: BIT;

BEGIN

	DUT1: portaxor2 PORT MAP (a,b,sort_xor);
	DUT2: portaxor2 PORT MAP( sort_xor,cin,suma_1bit);
	DUT3: portaand2 PORT MAP (a,b,sort_and1);
	DUT4: portaor2 PORT MAP( a,b,sort_or);
	DUT5: portaand2 PORT MAP (sort_or,cin,sort_and2);
	DUT6: portaor2 PORT MAP (sort_and1,sort_and2,cout_1bit);

aib_1bit <= sort_and1;
aob_1bit <= sort_or;

END estructural;


