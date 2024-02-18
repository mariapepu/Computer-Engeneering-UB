ENTITY funcio_2 IS
PORT ( a,b,c,d: IN BIT; f: OUT BIT);
END funcio_2;

-- a)
ARCHITECTURE logica OF funcio_2 IS
BEGIN
f <= (((NOT a) AND b AND (NOT c)) OR (b AND (NOT d)) OR (a AND c AND d) OR (a AND (NOT d))) XOR (a OR (NOT d)) AFTER 5 ns;
END logica;



ARCHITECTURE ifthen OF funcio_2 IS
BEGIN

PROCESS (d, c, b, a)
BEGIN
IF d='0' AND c='0' AND b='0' AND a='0' THEN
f <='1' AFTER 5 ns;
ELSIF d='1' AND c='0' AND b='0' AND a='0' THEN
f <='0' AFTER 5 ns;
ELSIF d='0' AND c='1' AND b='0' AND a='0' THEN  
f <='1' AFTER 5 ns;
ELSIF d='1' AND c='1' AND b='0' AND a='0' THEN
f <='0' AFTER 5 ns;
ELSIF d='0' AND c='0' AND b='1' AND a='0' THEN
f <='1' AFTER 5 ns;
ELSIF d='1' AND c='0' AND b='1' AND a='0' THEN
f <='1' AFTER 5 ns;
ELSIF d='0' AND c='1' AND b='1' AND a='0' THEN
f <='0' AFTER 5 ns;
ELSIF d='1' AND c='1' AND b='1' AND a='0' THEN
f <='0' AFTER 5 ns;
ELSIF d='0' AND c='0' AND b='0' AND a='1' THEN
f <='0' AFTER 5 ns;
ELSIF d='1' AND c='0' AND b='0' AND a='1' THEN
f<='1' AFTER 5 ns;
ELSIF d='0' AND c='1' AND b='0' AND a='1' THEN  
f <='1' AFTER 5 ns;     
ELSIF d='1' AND c='1' AND b='0' AND a='1' THEN
f <='0' AFTER 5 ns;
ELSIF d='0' AND c='0' AND b='1' AND a='1' THEN
f <='0' AFTER 5 ns;
ELSIF d='1' AND c='0' AND b='1' AND a='1' THEN
f <='1' AFTER 5 ns;
ELSIF d='0' AND c='1' AND b='1' AND a='1' THEN
f <='0' AFTER 5 ns;
ELSIF d='1' AND c='1' AND b='1' AND a='1' THEN
f <='0' AFTER 5 ns;
END IF;
END PROCESS;
END ifthen;


ARCHITECTURE estructural OF funcio_2 IS

COMPONENT portaand3 IS
PORT (a, b, c: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portaand2 IS
PORT (a, b: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portaor2 IS
PORT (a, b: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portainv IS
PORT (a, b, c, d: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portaxor2 IS
PORT (a, b: IN BIT; z: OUT BIT);
END COMPONENT;

SIGNAL entA, entB, entC, entD: BIT;
SIGNAL inva, invc, invd: BIT;
SIGNAL sortA, sortB: BIT;
SIGNAL sort1, sort2, sort3, sort4: BIT;
SIGNAL alpha, beta, funcio: BIT;

FOR DUT3:portaand3 USE ENTITY WORK.and3(logicaretard);
FOR DUT1:portaand2 USE ENTITY WORK.and2(logicaretard);
FOR DUT4:portaand3 USE ENTITY WORK.and3(logicaretard);
FOR DUT2:portaand2 USE ENTITY WORK.and2(logicaretard);
FOR DUT5:portaor2 USE ENTITY WORK.or2(logicaretard);
FOR DUT6:portaor2 USE ENTITY WORK.or2(logicaretard);
FOR DUT7:portaor2 USE ENTITY WORK.or2(logicaretard);
FOR DUT8:portaor2 USE ENTITY WORK.or2(logicaretard);
FOR DUT9:portaxor2 USE ENTITY WORK.xor2(logicaretard);

BEGIN
  
DUT1:portaand2 PORT MAP (entB, invd, sort2);
DUT2:portaand2 PORT MAP (entA, invd, sort4);
DUT3:portaand3 PORT MAP (inva, entB, invc, sort1);
DUT4:portaand3 PORT MAP (entA, entC, entD, sort3);
DUT5:portaor2 PORT MAP (sort1, sort2, sortA);
DUT6:portaor2 PORT MAP (sort3, sort4, sortB);
DUT7:portaor2 PORT MAP (sortA, sortB, alpha);
DUT8:portaor2 PORT MAP (entA, invd, beta);
DUT9:portaxor2 PORT MAP (alpha, beta, funcio);


END estructural;

ENTITY banc_de_proves IS
END banc_de_proves;

-- c)
-- d)
-- e)