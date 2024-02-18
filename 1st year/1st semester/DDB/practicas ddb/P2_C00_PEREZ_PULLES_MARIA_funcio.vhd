-----------FUNCIO 2---------------

ENTITY funcio_2 IS
PORT(a, b, c, d: IN BIT; f: OUT BIT);
END funcio_2;

--LA FUNCIO
ARCHITECTURE logica of funcio_2 IS
BEGIN
f <= (((NOT a) AND b AND (NOT c)) OR (b AND (NOT d)) OR (a AND c AND d) OR (a AND (NOT d))) XOR (a OR (NOT d));
END logica;

-----ESTRUCUTRAL------

ARCHITECTURE estructural of funcio_2 IS

COMPONENT portainv IS
	PORT (a: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portaand2 IS
	PORT (a, b: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portaand3 IS
	PORT (a, b, c: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portaor2 IS
	PORT (a, b: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portaor4 IS
	PORT (a, b, c, d: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portaxor2 IS
	PORT (a, b: IN BIT; z: OUT BIT);
END COMPONENT;

SIGNAL inva, invc, invd, andnabnc, andbnd, andacd, andand, auxor4, auxor2: BIT;
FOR DUT1: portainv USE ENTITY WORK.inv(logicaretard);
FOR DUT2: portainv USE ENTITY WORK.inv(logicaretard);
FOR DUT3: portainv USE ENTITY WORK.inv(logicaretard);
FOR DUT4: portaand2 USE ENTITY WORK.and2(logicaretard);
FOR DUT5: portaand2 USE ENTITY WORK.and2(logicaretard);
FOR DUT6: portaand3 USE ENTITY WORK.and3(logicaretard);
FOR DUT7: portaand3 USE ENTITY WORK.and3(logicaretard);
FOR DUT8: portaor4 USE ENTITY WORK.or4(logicaretard);
FOR DUT9: portaor2 USE ENTITY WORK.or2(logicaretard);
FOR DUT10: portaxor2 USE ENTITY WORK.xor2(logicaretard);

BEGIN
DUT1: portainv PORT MAP (A, inva);
DUT2: portainv PORT MAP (C, invc);
DUT3: portainv PORT MAP (D, invd);
DUT4: portaand2 PORT MAP (B, invd, andbnd);
DUT5: portaand2 PORT MAP (A, invd, andand);
DUT6: portaand3 PORT MAP (inva, B, invc, andnabnc);
DUT7: portaand3 PORT MAP (A, C, D, andacd);
DUT8: portaor4 PORT MAP (andbnd, andand, andnabnc, andacd, auxor4);
DUT9: portaor2 PORT MAP (A, invd, auxor2);
DUT10: portaxor2 PORT MAP (auxor4, auxor2, f);

END estructural;

-------BANC DE PROVES--------

ENTITY bancdeproves IS
END bancdeproves;

ARCHITECTURE test OF bancdeproves IS
COMPONENT bloc_simulat IS
PORT (a, b, c, d: IN BIT; f: OUT BIT);
END COMPONENT;

SIGNAL ent3, ent2, ent1, ent0, sort_logica, sort_estructural: BIT;
FOR DUT1: bloc_simulat USE ENTITY WORK.funcio_2(logica);
FOR DUT2: bloc_simulat USE ENTITY WORK.funcio_2(estructural);

BEGIN
DUT1: bloc_simulat PORT MAP(ent3, ent2, ent1, ent0,  sort_logica);
DUT2: bloc_simulat PORT MAP(ent3, ent2, ent1, ent0,  sort_estructural);

PROCESS (ent3, ent2, ent1, ent0)
BEGIN
ent3 <= NOT ent3 AFTER 400 ns;
ent2 <= NOT ent2 AFTER 200 ns;
ent1 <= NOT ent1 AFTER 100 ns;
ent0 <= NOT ent0 AFTER 50 ns;
END PROCESS;

END test;
 
-----------IFTHEN------------

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
f <='1' AFTER 5 ns;
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

-------BANC DE PROVES-------

ENTITY banc_de_proves_ifthen IS
END banc_de_proves_ifthen;

ARCHITECTURE test OF banc_de_proves_ifthen IS
COMPONENT portes IS
PORT (d, c, b, a: IN BIT; f: OUT BIT);
END COMPONENT;

SIGNAL ent3, ent2, ent1, ent0, sort_ifthen: BIT;
FOR DUT5: portes USE ENTITY WORK.funcio_2(ifthen);

BEGIN
DUT5: portes PORT MAP(ent3, ent2, ent1, ent0, sort_ifthen);

END test;

--Comentaris sobre les preguntes:

--PREGUNTA 1:
--Podem observar com la funcio te rebots en alguns temps determinats com del 209 al 212, es a dir,
--en alguns punts la funcio dona uns valors incorrectes que no estan lligats nomes
--al desplaçament de 3 ns al fer el calcul. A que es deu aixo?

--RESPOSTA:
--Com que els calculs es fan, cada un, amb un interval de 3 ns, durant aquest moment, 
--la funcio esta rebent unes dades teoricament "incorrectes".
--Es a dir, si necessitam tenir a la funcio 0, 0, 0, 0, 0 perque la funcio ens doni un 1 i les variables canvien
--i passen a ser totes 0, teoricament (!) la funcio hauria de donar un 1. Pero, en aquest moment, la funcio encara
--no ha calculat els inversos (perque duen 3 ns de retras), aixi que, practicament, no tenim el resultat que hauriem de tenir
--(per exemple, fer la AND de 1, 0, 1) perque l'inversor encara no ho ha calculat.
--A mes, aquest error es veu repetit perque tenim 4 nivells on generam retrasos.
--Resumint, en la funcio, degut als retrassos, hi ha moments on teoricament la funcio ens hauria de donar be
--pero a la practica estam calculant amb nombres que son incorrectes i aixo ens resulta un error.

--RESPOSTA PREGUNTA 2:
--Per contestar l'altra pregunta, hem de veure que passa. Com que tenim 4 nivells on hi ha retras i
--tenim un retras de 3 ns, tendrem un total de 12 ns on la funcio calcula amb errors.
--Quan posam que variin les senyals d'entrada en 5 ns, no li donam temps a la funcio
--a que hi hagi un valor calculat practicament be (tot i que en la teoria, haurien de ser correctes).
--Haurien de passar, 12 ns entre canvi de variable i canvi de variable per, despres, tenir al menys 1 ns
--on segur que tendriem un valor de la funcio be (aquest es el minim). 
--Si varien en menys de 13 ns, la funcio no te temps a ser calculada practicament be
--i si algun valor coincideix (en la practica i en la teoria) no es degut a la logica que intentam que la nostra funcio segueixi.
