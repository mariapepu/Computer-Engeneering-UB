-------------------OR PORTS-------------------
--ENTITY OR2:
ENTITY or2 is
  PORT (a,b: IN BIT; z: OUT BIT);
END or2;

--ARCHITECTURE OF OR2:
--Without delay.
ARCHITECTURE logica OF or2 IS
BEGIN
	z <= a OR b;
END logica;

--With 3 ns of delay.
ARCHITECTURE logicaretard OF or2 IS
BEGIN
  z <= a OR b AFTER 3 ns; 
END logicaretard;

--ENTITY OR3:
ENTITY or3 IS
	PORT( a, b, c: IN BIT; z: OUT BIT);
END or3;

--ARCHITECTURES OF OR3:
--Without delay.
ARCHITECTURE logica OF or3 IS
BEGIN
 	z <= a OR b OR c;
END logica;
--With 3 ns of delay.
ARCHITECTURE logicaretard OF or3 IS
BEGIN
	z <= a OR b OR c after 2 ns;
END logicaretard;

--ENTITY OR4:
ENTITY or4 is
	PORT (a, b, c, d: IN BIT; z: OUT BIT);
END or4;

--ARCHITECTURES OF OR4:
--Without delay.
ARCHITECTURE logica of or4 IS
BEGIN
	z <= a OR b OR c OR d;
END logica;

--With 3 ns of delay.
ARCHITECTURE logicaretard OF or4 IS
BEGIN
	z <= a OR b OR c OR d AFTER 3 ns;
END logicaretard;



-------------------AND PORTS-------------------


--ENTITY AND2:
ENTITY and2 IS
  PORT (a,b: IN BIT; z: OUT BIT);
END and2;

--ARCHITECTURES OF AND2:
--Without delay.
ARCHITECTURE logica OF and2 IS
BEGIN
	z <= a AND b;
END logica;

--With 3 ns of delay.
ARCHITECTURE logicaretard OF and2 IS
BEGIN
  z <= a AND b AFTER 3 ns;
END logicaretard;

--ENTITY AND3:
ENTITY and3 IS
	PORT( a, b, c: IN BIT; z: OUT BIT);
END and3;

--ARCHITECTURES OF AND3:
--Without delay.
ARCHITECTURE logica of and3 IS
BEGIN
 	z <= a AND b AND c;
END logica;

--With 3 ns of delay.
ARCHITECTURE logicaretard of and3 IS
BEGIN
 	z <= a AND b AND c after 3 ns;
END logicaretard;

--ENTITY AND4:
ENTITY and4 IS
	PORT (a, b, c, d: IN BIT; z: OUT BIT);
END and4;

--ARCHITECTURE OF AND4:
--Without delay.
ARCHITECTURE logica OF and4 IS
BEGIN
	z <= a AND b AND c AND d;
END logica;

--With 3 ns of delay.
ARCHITECTURE logicaretard OF and4 IS
BEGIN
	z <= a AND b AND c AND d;
END logicaretard;


-------------------XOR PORTS-------------------

--ENTITY XOR2:
ENTITY xor2 IS
	PORT (a, b: IN BIT; z: OUT BIT);
END xor2;

--ARCHITECTURE OF XOR2:
ARCHITECTURE logicaretard of xor2 IS
BEGIN
	z <= a XOR b after 2 ns;
END logicaretard;

-------------------INVERSOR PORTS-------------------


--ENTITY INV:
ENTITY inv IS
  PORT (a: IN BIT; z: OUT BIT);
END inv;

--ARCHITECTURES OF INV:
--Without delay.
ARCHITECTURE logica OF inv IS
BEGIN
	z <= NOT a;
END logica;

--With 3 ns of delay.
ARCHITECTURE logicaretard OF inv IS
BEGIN
  z <= NOT a AFTER 3 ns;
END logicaretard;



-------------------END OF PORTS-------------------
ENTITY funcio_2 IS
	PORT(a, b, c, d: IN BIT; f: OUT BIT);
END funcio_2;

--This is our logical structure.
ARCHITECTURE logica of funcio_2 IS
BEGIN
	f <= (((NOT a) AND b AND (NOT c)) OR (b AND (NOT d)) OR (a AND c AND d) OR (a AND (NOT d))) XOR (a OR (NOT d));
END logica;

--Now we must define our structural function, using the already defined ports.
ARCHITECTURE estructural of funcio_2 IS
--Which components do we need?:
	--Inversor
	--And2
	--And3
	--Or2
	--Or4
	--Xor2
--Let's define them here as components:
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

--Now we have to define input, output and internal signals.
--Let's analyze how many components we need:
	--First of all, we will need 1 inversor for a, 1 inversor for b and 1 inversor for d.
	--We will need 2 ports of AND2 (b and not d, a and d).
	--Then, 2 ports of AND3 (not a and b and not c, a and c and d).
	--Then, one port of OR4.
	--Then, one port of OR2.
	--Last port is a XOR.
	SIGNAL inva, invc, invd, andnabnc, andbnd, andacd, andand, auxor4, auxor2: BIT;

	--Declaring which logic we will use.
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
		--Pairing signals and dispositives.
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

--Let's test this out:
ENTITY bancdeproves IS
END bancdeproves;

ARCHITECTURE test OF bancdeproves IS
	--Defininf the wanting-to-test component.
	COMPONENT bloc_simulat IS
		PORT (a, b, c, d: IN BIT; f: OUT BIT);
	END COMPONENT;

	SIGNAL ent3, ent2, ent1, ent0, sort_logica, sort_estructural: BIT;
		--We will try out both architectures.
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
 
------------------Comentaris sobre les preguntes:
--Podem observar com la funcio te rebots en alguns temps determinats com del 209 al 212, es a dir,
--en alguns punts la funcio dona uns valors incorrectes que no estan lligats nomes
--al desplaçament de 3 ns al fer el calcul. A que es deu aixo?
--Com que els calculs es fan, cada un, amb un interval de 3 ns, durant aquest moment, 
--la funcio esta rebent unes dades teoricament "incorrectes".
--Es a dir, si necessitam tenir a la funcio 0, 0, 0, 0, 0 perque la funcio ens doni un 1 i les variables canvien
--i passen a ser totes 0, teoricament (!) la funcio hauria de donar un 1. Pero, en aquest moment, la funcio encara
--no ha calculat els inversos (perque duen 3 ns de retras), aixi que, practicament, no tenim el resultat que hauriem de tenir
--(per exemple, fer la AND de 1, 0, 1) perque l'inversor encara no ho ha calculat.
--A mes, aquest error es veu repetit perque tenim 4 nivells on generam retrasos.
--Resumint, en la funcio, degut als retrassos, hi ha moments on teoricament la funcio ens hauria de donar be
--pero a la practica estam calculant amb nombres que son incorrectes i aixo ens resulta un error.

--Per contestar l'altra pregunta, hem de veure que passa. Com que tenim 4 nivells on hi ha retras i
--tenim un retras de 3 ns, tendrem un total de 12 ns on la funcio calcula amb errors.
--Quan posam que variin les senyals d'entrada en 5 ns, no li donam temps a la funcio
--a que hi hagi un valor calculat practicament be (tot i que en la teoria, haurien de ser correctes).
--Haurien de passar, 12 ns entre canvi de variable i canvi de variable per, despres, tenir al menys 1 ns
--on segur que tendriem un valor de la funcio be (aquest es el minim). 
--Si varien en menys de 13 ns, la funcio no te temps a ser calculada practicament be
--i si algun valor coincideix (en la practica i en la teoria) no es degut a la logica que intentam que la nostra funcio segueixi.