ENTITY comparador_2bits IS
PORT (enable: IN BIT; paraula_a, paraula_b: IN BIT_VECTOR (1 DOWNTO 0); z: OUT BIT_VECTOR (2 DOWNTO 0));
END comparador_2bits;


ARCHITECTURE ifthen OF comparador_2bits IS
BEGIN
        PROCESS (enable, paraula_a, paraula_b)
        BEGIN
		IF enable='1' THEN Z <= "000";
		ELSE
            	IF paraula_a(1) = '1' AND paraula_b(1) = '0' THEN Z <= "100";
              	ELSIF paraula_a(1) = '0' AND paraula_b(1) = '1' THEN Z <= "001";
              	ELSIF paraula_a(1) = paraula_b(1) THEN
                  	IF paraula_a(0) = '1' AND paraula_b(0) = '0' THEN Z <= "100";
                  	ELSIF paraula_a(0) = '0' AND paraula_b(0) = '1' THEN Z <= "001";
                  	ELSE z <=  "010";
                  	END IF;
              	END IF;
		END IF;
        END PROCESS;
END ifthen;


ENTITY banc_de_proves IS
END banc_de_proves;

ARCHITECTURE test OF banc_de_proves IS

COMPONENT comparador_2bits IS
PORT (enable: IN BIT; paraula_a, paraula_b: IN BIT_VECTOR (1 DOWNTO 0); z: OUT BIT_VECTOR (2 DOWNTO 0));
END COMPONENT;

SIGNAL enable: BIT;
SIGNAL ent_a, ent_b: BIT_VECTOR (1 DOWNTO 0);
SIGNAL sort: BIT_VECTOR (2 DOWNTO 0);

FOR DUT1: comparador_2bits USE ENTITY WORK.comparador_2bits(ifthen);

BEGIN
DUT1: comparador_2bits PORT MAP (enable, ent_a, ent_b, sort);

PROCESS (enable, ent_a, ent_b)
BEGIN
enable <= NOT enable AFTER 800 ns;

ent_a(1) <= NOT ent_a(1) AFTER 50 ns;
ent_a(0) <= NOT ent_a(0) AFTER 100 ns;
ent_b(1) <= NOT ent_b(1) AFTER 200 ns;
ent_b(0) <= NOT ent_b(0) AFTER 400 ns;

END PROCESS;
END test;

 

