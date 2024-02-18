-- Entitats i arquitectures previes
  
--Portes
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

-- Sumador de 1 bit
ENTITY sumador_modificat_1bit IS
PORT (a,b,cin: IN BIT; suma_1bit, cout_1bit, aib_1bit, aob_1bit: OUT BIT);
END sumador_modificat_1bit;

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

-- Sumador de 3 bits

-- Definim l'entitat "sumador_modificat_3bits"

ENTITY sumador_modificat_3bits IS
PORT (a, b: IN BIT_VECTOR(2 DOWNTO 0); suma_smod_3bits, aib_smod_3bits, aob_smod_3bits: OUT BIT_VECTOR (2 DOWNTO 0); cin: IN BIT; cout: OUT BIT);
END sumador_modificat_3bits;

-- Definim l'arquitectura "estructura" del "sumador_modificat_3bits"

ARCHITECTURE estructura OF sumador_modificat_3bits IS

COMPONENT sumador_m_1bit IS
PORT(a,b,cin: IN BIT; suma_1bit, cout_1bit, aib_1bit, aob_1bit: OUT BIT);
END COMPONENT;

FOR DUT1: sumador_m_1bit USE ENTITY WORK.sumador_modificat_1bit(estructural);
FOR DUT2: sumador_m_1bit USE ENTITY WORK.sumador_modificat_1bit(estructural);
FOR DUT3: sumador_m_1bit USE ENTITY WORK.sumador_modificat_1bit(estructural);

SIGNAL c1, c2: BIT;

BEGIN  
  
DUT1: sumador_m_1bit PORT MAP (a(0), b(0), cin, suma_smod_3bits(0), c1, aib_smod_3bits(0), aob_smod_3bits(0));
DUT2: sumador_m_1bit PORT MAP (a(1), b(1), c1, suma_smod_3bits(2), c2, aib_smod_3bits(1), aob_smod_3bits(1));
DUT3: sumador_m_1bit PORT MAP (a(2), b(2), c2, suma_smod_3bits(2), cout, aib_smod_3bits(2), aob_smod_3bits(2));

END estructura;


-- Banc de proves del "sumador_modificat_3bits"


ENTITY banc_prov IS
END banc_prov;

ARCHITECTURE test OF banc_prov IS
COMPONENT 
PORT (a, b: IN BIT_VECTOR(2 DOWNTO 0); suma_smod_3bits, aib_smod_3bits, aob_smod_3bits: OUT BIT_VECTOR (2 DOWNTO 0); cin: IN BIT; cout: OUT BIT);
END COMPONENT;


END test;





