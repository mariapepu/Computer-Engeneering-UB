-- Entitats i arquitectures previes

ENTITY portaor2 IS
PORT (c,d: IN BIT; z: OUT BIT);
END portaor2;

ARCHITECTURE logicaretard OF portaor2 IS 
BEGIN
z <= c OR d AFTER 3 ns;
END logicaretard;


ENTITY portaand2 IS
PORT (a,b: IN BIT; z: OUT BIT);
END portaand2;
ENTITY portaor2 IS
PORT (c,d: IN BIT; z: OUT BIT);
END portaor2;

ARCHITECTURE logicaretard OF portaor2 IS 
BEGIN
z <= c OR d AFTER 3 ns;
END logicaretard;


ENTITY portaand2 IS
PORT (a,b: IN BIT; z: OUT BIT);
END portaand2;

ARCHITECTURE logicaretard OF portaand2 IS
BEGIN
z <= a AND b AFTER 3 ns;
END logicaretard;
ARCHITECTURE logicaretard OF portaand2 IS
BEGIN
z <= a AND b AFTER 3 ns;
END logicaretard;


ENTITY comparador_2bits IS
PORT (enable: IN BIT; paraula_1, paraula_2: IN BIT_VECTOR (1 DOWNTO 0); z: OUT BIT_VECTOR (2 DOWNTO 0));
END comparador_2bits;

ARCHITECTURE ifthen OF comparador_2bits IS
BEGIN
        PROCESS (enable, paraula_1, paraula_2)
        BEGIN
		IF enable='1' THEN Z <= "000";
		ELSE
            	IF paraula_1(1) = '1' AND paraula_2(1) = '0' THEN Z <= "100";
              	ELSIF paraula_1(1) = '0' AND paraula_2(1) = '1' THEN Z <= "001";
              	ELSIF paraula_1(1) = paraula_2(1) THEN
                  	IF paraula_1(0) = '1' AND paraula_2(0) = '0' THEN Z <= "100";
                  	ELSIF paraula_1(0) = '0' AND paraula_2(0) = '1' THEN Z <= "001";
                  	ELSE z <=  "010";
                  	END IF;
              	END IF;
		END IF;
        END PROCESS;
END ifthen;



--Comparador de 4 bits

ENTITY comparador_4bits IS
PORT (enable: IN BIT; paraula_1, paraula_2: IN BIT_VECTOR(3 DOWNTO 0); z: OUT BIT_VECTOR (2 DOWNTO 0));
END comparador_4bits;

ARCHITECTURE estructural OF comparador_4bits IS
COMPONENT comparador_2bits IS
PORT (enable: IN BIT; paraula_1, paraula_2: IN BIT_VECTOR(1 DOWNTO 0); z: OUT BIT_VECTOR (2 DOWNTO 0));
END COMPONENT;

COMPONENT portaand2 IS
PORT(a,b: IN BIT; z: OUT BIT);
END COMPONENT;
 
COMPONENT portaor2 IS
PORT(c,d: IN BIT; z: OUT BIT);
END COMPONENT;


SIGNAL co, mp : BIT_VECTOR (2 DOWNTO 0);
SIGNAL alfa1, alfa2, beta1, beta2: BIT_VECTOR(1 DOWNTO 0);
SIGNAL sort1, sort2: BIT;

FOR DUT1: comparador_2bits USE ENTITY WORK.comparador_2bits(ifthen);
FOR DUT2: comparador_2bits USE ENTITY WORK.comparador_2bits(ifthen);
FOR DUT3: portaand2 USE ENTITY WORK.portaand2(logicaretard);
FOR DUT4: portaand2 USE ENTITY WORK.portaand2(logicaretard);
FOR DUT5: portaand2 USE ENTITY WORK.portaand2(logicaretard);
FOR DUT6: portaor2 USE ENTITY WORK.portaor2(logicaretard);
FOR DUT7: portaor2 USE ENTITY WORK.portaor2(logicaretard);

BEGIN
alfa1(1) <= paraula_1(3);
alfa1(0) <= paraula_1(2);
alfa2(1) <= paraula_1(1);
alfa2(0) <= paraula_1(0);
beta1 <= paraula_2(3) & paraula_2(2);
beta2 <= paraula_2(1) & paraula_2(0);

DUT1: comparador_2bits PORT MAP (enable, alfa1, beta1, mp);
DUT2: comparador_2bits PORT MAP (enable, alfa2, beta2, co);
DUT3: portaand2 PORT MAP (co(0), mp(1), sort1);
DUT4: portaand2 PORT MAP (co(1), mp(1), z(1));
DUT5: portaand2 PORT MAP (co(2), mp(1), sort2);
DUT6: portaor2 PORT MAP (sort1, mp(0), z(0));
DUT7: portaor2 PORT MAP (sort2, mp(2), z(2));

END estructural;

ENTITY banc_prova IS
END banc_prova;

ARCHITECTURE test OF banc_prova IS
COMPONENT  comparador_4bits IS
PORT (enable: IN BIT; paraula_1, paraula_2: IN BIT_VECTOR (3 DOWNTO 0); z: OUT BIT_VECTOR(2 DOWNTO 0));
END COMPONENT;

SIGNAL enable: BIT;
SIGNAL A, B: BIT_VECTOR (3 DOWNTO 0);
SIGNAL sortz: BIT_VECTOR (2 DOWNTO 0);

FOR DUT8: comparador_4bits USE ENTITY WORK.comparador_4bits(estructural);
BEGIN
DUT8: comparador_4bits PORT MAP (enable, A, B, sortz);

PROCESS (enable, A, B)
BEGIN
enable <= NOT enable AFTER 6400 ns;
B(3) <= NOT B(3) AFTER 3200 ns;
B(2) <= NOT B(2) AFTER 1600 ns;
B(1) <= NOT B(1) AFTER 800 ns;
B(0) <= NOT B(0) AFTER 400 ns;
A(3) <= NOT A(3) AFTER 200 ns;
A(2) <= NOT A(2) AFTER 100 ns;
A(1) <= NOT A(1) AFTER 50 ns;
A(0) <= NOT A(0) AFTER 25 ns;
END PROCESS;
END test;



