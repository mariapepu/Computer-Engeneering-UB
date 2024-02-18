--I write down the ports here in case there's some error with them in the other file.
-----------------OR PORT USED:
--ENTITY or2 is
--  PORT (a,b: IN BIT; z: OUT BIT);
--END or2;
--With 3 ns of delay.
--ARCHITECTURE logicaretard OF or2 IS
--BEGIN
--  z <= a OR b AFTER 3 ns;
--END logicaretard;
--------------------AND PORT USED:
--ENTITY and2 IS
--  PORT (a,b: IN BIT; z: OUT BIT);
--END and2;

--ARCHITECTURE logicaretard OF and2 IS
--BEGIN
--  z <= a AND b AFTER 3 ns;
--END logicaretard;
--We will build our 4 bits comparator with two 2-bits comparator.
--Even though I have this defined in another file on the project, I write it down here just in
--case something goes wrong with the other file.

ENTITY comparador_2bits IS
PORT (enable: IN BIT; a, b: IN BIT_VECTOR(1 DOWNTO 0); z: OUT BIT_VECTOR(2 DOWNTO 0));
END comparador_2bits;

ARCHITECTURE ifthen OF comparador_2bits IS
BEGIN
        PROCESS (enable,a, b)
        BEGIN
		IF enable='1' THEN z <= "000";
		ELSE
			IF ((a = "10" OR a = "01") AND (b = "01" OR b = "00")) THEN z <= "100" ;
			ELSIF ((a = "01" OR a = "00") AND (b = "10" OR b = "11")) THEN z <= "001" ;
			ELSIF (((a = "00" OR a = "01") AND (b = "00" OR b = "01")) OR ((a = "10" OR a = "11") AND (b = "10" OR b = "11"))) THEN
                  		IF ((a = "11" OR a = "10") AND (b = "01" OR b = "00")) THEN z <= "100" ;
                  		ELSIF ((a = "00" OR a = "10") AND (b = "01" OR b = "11")) THEN z <= "001" ;
                  		ELSE z <= "010";
                  		END IF;
              		END IF;
		END IF;
        END PROCESS;
END ifthen;
--Starting our entity and architecture of 4-bits comparator:
ENTITY comparador_4bits IS
	PORT
		(enable: IN BIT; a,b: IN BIT_VECTOR(3 DOWNTO 0); z: OUT BIT_VECTOR (2 DOWNTO 0));
END comparador_4bits;

ARCHITECTURE estructural OF comparador_4bits IS
--We will need:
	--two 2-bits comparators.
	--2 or2 ports.
	--3 and2 ports.
COMPONENT comparador_2bits IS
	PORT (enable: IN BIT; a,b: IN BIT_VECTOR(1 DOWNTO 0); z: OUT BIT_VECTOR (2 DOWNTO 0));
END COMPONENT;
	
COMPONENT portaor2 IS
	PORT (a, b: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portaand2 IS
	PORT (a, b: IN BIT; z: OUT BIT);
END COMPONENT;
--Since our entity of 2-bits comparator needs two entry vectors and one output vector
--we need to define vector signals and use them properly.
--These are the entry ones.
SIGNAL alfa1,alfa2,beta1,beta2: BIT_VECTOR (1 DOWNTO 0);
--And these the output ones.
SIGNAL z1,z2: BIT_VECTOR(2 DOWNTO 0);
--These are the internal signal of the ands ports. Still needed.
SIGNAL andz1z2, andz1z0: BIT;

	FOR DUT1: comparador_2bits USE ENTITY WORK.comparador_2bits(ifthen);
	FOR DUT2: comparador_2bits USE ENTITY WORK.comparador_2bits(ifthen);
	FOR DUT3: portaand2 USE ENTITY WORK.and2(logicaretard);
	FOR DUT4: portaand2 USE ENTITY WORK.and2(logicaretard);
	FOR DUT5: portaand2 USE ENTITY WORK.and2(logicaretard);
	FOR DUT6: portaor2 USE ENTITY WORK.or2(logicaretard);
	FOR DUT7: portaor2 USE ENTITY WORK.or2(logicaretard);

	BEGIN
		--First word signals:
		alfa1(1)<=a(3);
		alfa1(0)<=a(2);
		alfa2(1)<=a(1);
		alfa2(0)<=a(0);
		--Second word signals:
		beta1(1)<=b(3);
		beta1(0)<=b(2);
		beta2(1)<=b(1);
		beta2(0)<=b(0);

		--Z1 vector is for comparator1 results.
		--Z2 vector is for comparator2 results.
		--Z vector contains final results.
		DUT1: comparador_2bits PORT MAP (enable, alfa1, beta1, z1);
		DUT2: comparador_2bits PORT MAP (enable, alfa2, beta2, z2);
		DUT3: portaand2 PORT MAP (z1(1), z2(1), andz1z0);
		DUT4: portaand2 PORT MAP (z1(1), z2(2), z(1));
		DUT5: portaand2 PORT MAP (z1(1), z2(2), andz1z2);
		DUT6: portaor2 PORT MAP (andz1z2, z1(2), z(2));
		DUT7: portaor2 PORT MAP (z1(0), andz1z0, z(0));
	END estructural;

--Let's check if this works.
ENTITY bdp_comp8 IS
END bdp_comp8;

ARCHITECTURE test OF bdp_comp8 IS
	COMPONENT comparador4bits IS
		PORT (enable: IN BIT; a, b: IN BIT_VECTOR(3 DOWNTO 0); z: OUT BIT_VECTOR(2 DOWNTO 0));
	END COMPONENT;

	SIGNAL enable: BIT;
	SIGNAL ent_a, ent_b : BIT_VECTOR(3 DOWNTO 0);
	SIGNAL ent_z: BIT_VECTOR(2 DOWNTO 0);

	FOR DUTA: comparador4bits USE ENTITY WORK.comparador_4bits(estructural);

	BEGIN
		DUTA: comparador4bits PORT MAP(enable, ent_a, ent_b, ent_z);

		PROCESS (enable, ent_a, ent_b)
			BEGIN
				enable <= '0';
				ent_a(3) <= NOT ent_a(3) AFTER 1220 ns;
				ent_a(2) <= NOT ent_a(2) AFTER 640 ns;
				ent_a(1) <= NOT ent_a(1) AFTER 320 ns;
				ent_a(0) <= NOT ent_a(0) AFTER 160 ns;
				ent_b(3) <= NOT ent_b(3) AFTER 80 ns;
				ent_b(2) <= NOT ent_b(2) AFTER 40 ns;
				ent_b(1) <= NOT ent_b(1) AFTER 20 ns;
				ent_b(0) <= NOT ent_b(0) AFTER 10 ns;
	END PROCESS;
END test;
