ENTITY funcio_2 IS
PORT ( a,b,c,d: IN BIT; f: OUT BIT);
END funcio_2;

-- a)
ARCHITECTURE logica OF funcio_2 IS
BEGIN
f <= ( ( (NOT a) AND b AND (NOT c) OR b AND (NOT d) OR a AND c AND d OR a AND (NOT d)) AND (NOT (a OR (NOT d))) OR (NOT ( (NOT a) AND b AND (NOT c) OR b AND (NOT d) OR a AND c AND d OR a AND (NOT d) )) AND (a OR (NOT d))  AFTER 5 ns;
END logica;


-- b)

ARCHITECTURE ifthen OF funcio_2 IS
BEGIN

PROCESS (d, c, b, a)
BEGIN
IF d='0' AND c='0' AND b='0' AND a='0' THEN
f <='0' AFTER 5 ns;
ELSIF d='1' AND c='0' AND b='0' AND a='0' THEN
f <='1' AFTER 5 ns;
ELSIF d='0' AND c='1' AND b='0' AND a='0' THEN  
f <='1' AFTER 5 ns;
ELSIF d='1' AND c='1' AND b='0' AND a='0' THEN
f <='1' AFTER 5 ns;
ELSIF d='0' AND c='0' AND b='1' AND a='0' THEN
f <='1' AFTER 5 ns;
ELSIF d='1' AND c='0' AND b='1' AND a='0' THEN
f <='1' AFTER 5 ns;
ELSIF d='0' AND c='1' AND b='1' AND a='0' THEN
f <='1' AFTER 5 ns;
ELSIF d='1' AND c='1' AND b='1' AND a='0' THEN
f <='1' AFTER 5 ns;
ELSIF d='0' AND c='0' AND b='0' AND a='1' THEN
f <='1' AFTER 5 ns;
ELSIF d='1' AND c='0' AND b='0' AND a='1' THEN
Z7<='1' AFTER 5 ns;
ELSIF d='0' AND c='1' AND b='0' AND a='1' THEN  
f <='1' AFTER 5 ns;     
ELSIF d='1' AND c='1' AND b='0' AND a='1' THEN
f <='1' AFTER 5 ns;
ELSIF d='0' AND c='0' AND b='1' AND a='1' THEN
f <='1' AFTER 5 ns;
ELSIF d='1' AND c='0' AND b='1' AND a='1' THEN
f <='1' AFTER 5 ns;
ELSIF d='0' AND c='1' AND b='1' AND a='1' THEN
f <='1' AFTER 5 ns;
ELSIF d='1' AND c='1' AND b='1' AND a='1' THEN
f <='0' AFTER 5 ns;
END IF;
END PROCESS;
END ifthen;



ENTITY banc_de_proves IS
END banc_de_proves;

ARCHITECTURE test OF banc_de_proves IS

COMPONENT portes IS
PORT (d, c, b, a: IN BIT; f: OUT BIT);
END COMPONENT;

SIGNAL ent3, ent2, ent1, ent0: BIT;
SIGNAL sort: BIT;

FOR DUT: portes USE ENTITY WORK.funcio_2(ifthen);



END test;

-- c)

ARCHITECTURE estructural OF funcio_logica IS 
COMPONENT portaand2 IS
PORT(a,b: IN BIT; z: OUT BIT);
END COMPONENT;
 
COMPONENT portaor2 IS
PORT(a,b: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portaand3 IS
PORT(a,b,c: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portaor3 IS
PORT(a,b,c: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portaand3 IS
PORT(a,b,c,d: IN BIT; z: OUT BIT);
END COMPONENT;
 
COMPONENT portainv IS
PORT(a: IN BIT; z: OUT BIT);
END COMPONENT;

END estructural;
 

-- d)


-- e)
