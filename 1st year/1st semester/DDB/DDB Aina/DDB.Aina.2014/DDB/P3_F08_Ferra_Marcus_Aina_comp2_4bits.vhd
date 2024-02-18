--He deixat les portes en comentaris per si de cas hi ha problemes al canviar-les de projecte
--I no carrega les portes que tenim a l'altre arxiu.
--En aquest cas, el comparador tambe realitza alguns calculs estranys
--Ja que les portes duen 3 ns de retras. Els calculs es fan amb un poc de retras.
--Si llevam tot el retras, podem comprovar que el comparador funciona correctament. 

--ENTITY OR2:
--ENTITY or2 is
--  PORT (a,b: IN BIT; z: OUT BIT);
--END or2;

--ARCHITECTURE OF OR2:
---ARCHITECTURE logicaretard OF or2 IS
--BEGIN
--	z <= a OR b AFTER 3 ns;
--END logicaretard;
--
--ENTITY AND2:
--ENTITY and2 IS
--  PORT (a,b: IN BIT; z: OUT BIT);
--END and2;

--ARCHITECTURE OF AND2:
--With 3 ns of delay.
--ARCHITECTURE logicaretard OF and2 IS
--BEGIN
--  z <= a AND b AFTER 3 ns;
--END logicaretard;

--ENTITY COMPARADOR
ENTITY comparador_2bits IS
PORT (enable: IN BIT; a1,a0,b1,b0: IN BIT; z2,z1,z0: OUT BIT);
END comparador_2bits;

-- Definim l'arquitectura tipus 'ifthen'
ARCHITECTURE ifthen OF comparador_2bits IS
BEGIN
        PROCESS (enable,a1,a0,b1,b0)
        BEGIN
		--If enable = 1, then we don't compare.
		IF enable='1' THEN Z2<='0'; Z1<='0'; Z0<='0';
		--Else, our dispositive is active. Procedure to compare.
		ELSE
		--If the first bit of A is greater than the first bit of B, we are done.
            	IF a1='1' AND b1='0' THEN Z2<='1'; Z1<='0'; Z0<='0';
		--Otherwise, we are done, but B is greater than A.
              	ELSIF a1='0' AND b1='1' THEN Z2<='0'; Z1<='0'; Z0<='1';
		--If the first bit of the two words are the same, then we must compare the other 2 bits.
              	ELSIF a1=b1 THEN
			--If the last bit of A is greater than B's, then we are done, A > B.
                  	IF a0='1' AND b0='0' THEN Z2<='1'; Z1<='0'; Z0<='0';
			--Otherwise, B > A.
                  	ELSIF a0='0' AND b0='1' THEN Z2<='0'; Z1<='0'; Z0<='1';
			--If both are equal, then words are equal.
                  	ELSE Z2<='0'; Z1<='1'; Z0<='0';
			END IF;
              	END IF;
		END IF;
        END PROCESS;
END ifthen;

ENTITY comparador_4bits IS
	PORT (enable: IN BIT; a3, a2, a1, a0, b3, b2, b1, b0: IN BIT; sort2, sort1, sort0: OUT BIT);
end comparador_4bits;

ARCHITECTURE estructural of comparador_4bits IS
	COMPONENT comparador_2bits IS
		PORT (enable: IN BIT; a1, a0, b1, b0: IN BIT; z2, z1, z0: OUT BIT);
	END COMPONENT;
	
	COMPONENT portaor2 IS
		PORT (a, b: IN BIT; z: OUT BIT);
	END COMPONENT;

	COMPONENT portaand2 IS
		PORT (a, b: IN BIT; z: OUT BIT);
	END COMPONENT; 

	SIGNAL z2comp1, z1comp1, z0comp1, z2comp2, z1comp2, z0comp2, andz1z2, andz1z0: BIT;

		FOR DUT1: comparador_2bits USE ENTITY WORK.comparador_2bits(ifthen);
		FOR DUT2: comparador_2bits USE ENTITY WORK.comparador_2bits(ifthen);
		FOR DUT3: portaand2 USE ENTITY WORK.and2(logicaretard);
		FOR DUT4: portaand2 USE ENTITY WORK.and2(logicaretard);
		FOR DUT5: portaand2 USE ENTITY WORK.and2(logicaretard);
		FOR DUT6: portaor2 USE ENTITY WORK.or2(logicaretard);
		FOR DUT7: portaor2 USE ENTITY WORK.or2(logicaretard);

	BEGIN

		DUT1: comparador_2bits PORT MAP (enable, a3, a2, b3, b2, z2comp1, z1comp1, z0comp1);
		DUT2: comparador_2bits PORT MAP (enable, a1, a0, b1, b0, z2comp2, z1comp2, z0comp2);
		DUT3: portaand2 PORT MAP (z1comp1, z0comp2, andz1z0);
		DUT4: portaand2 PORT MAP (z1comp1, z1comp2, sort1);
		DUT5: portaand2 PORT MAP (z1comp1, z2comp2, andz1z2);
		DUT6: portaor2 PORT MAP (andz1z2, z2comp1, sort2);
		DUT7: portaor2 PORT MAP (z0comp1, andz1z0, sort0);
END estructural;

ENTITY bdp_comp4 IS
END bdp_comp4;

ARCHITECTURE test OF bdp_comp4 IS

	COMPONENT comparador4bits IS
		PORT (enable, a3, a2, a1, a0, b3, b2, b1, b0: IN BIT; sort2, sort1, sort0: OUT BIT);
	END COMPONENT; 

	SIGNAL enable, ent_a3, ent_a2, ent_a1, ent_a0, ent_b3, ent_b2, ent_b1, ent_b0, sort2, sort1, sort0: BIT;

		FOR DUT1: comparador4bits USE ENTITY WORK.comparador_4bits(estructural);

	BEGIN
		DUT1: comparador4bits PORT MAP(enable, ent_a3, ent_a2, ent_a1, ent_a0, ent_b3, ent_b2, ent_b1, ent_b0, sort2, sort1, sort0);

		PROCESS (enable, ent_a3, ent_a2, ent_a1, ent_a0, ent_b3, ent_b2, ent_b1, ent_b0)
			BEGIN
				enable <= '0';
				ent_a3 <= NOT ent_a3 AFTER 1220 ns;
				ent_a2 <= NOT ent_a2 AFTER 640 ns;
				ent_a1 <= NOT ent_a1 AFTER 320 ns;
				ent_a0 <= NOT ent_a0 AFTER 160 ns; 
				ent_b3 <= NOT ent_b3 AFTER 80 ns; 
				ent_b2 <= NOT ent_b2 AFTER 40 ns;
				ent_b1 <= NOT ent_b1 AFTER 20 ns;
				ent_b0 <= NOT ent_b0 AFTER 10 ns;
	END PROCESS;
END test; 
				
 