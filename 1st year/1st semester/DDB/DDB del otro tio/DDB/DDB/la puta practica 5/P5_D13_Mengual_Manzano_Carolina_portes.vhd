-- Latch D
ENTITY Lach_D IS
	PORT(D, Clk, Pre, Clr: IN BIT; Q, NO_Q: OUT BIT);
END Lach_D;

ARCHITECTURE ifthen OF Lach_D IS
SIGNAL qint: BIT;
BEGIN
	PROCESS (Clk, Pre, Clr, D)
	BEGIN
		IF Pre='0' THEN qint <= '1';
		ELSIF Clr='0' THEN qint <= '0';
		ELSIF Clk='1' THEN qint <= D;
		END IF;
	END PROCESS;

	Q <= qint;
	NO_Q <= NOT qint;
END ifthen;


-- Flip-Flop JK per flanc de pujada
ENTITY FF_JK IS
	PORT(J, K, Clk, Pre, Clr: IN BIT; Q, NO_Q: OUT BIT);
END FF_JK;

ARCHITECTURE ifthen OF FF_JK IS
SIGNAL qint: BIT;
BEGIN
	PROCESS (Clk, Pre, Clr, J, K)
	BEGIN
		IF Pre='0' THEN qint <= '1';
		ELSIF Clr='0' THEN qint <= '0';
		ELSIF Clk'EVENT AND Clk='1' THEN
			IF J='0' AND K='0' THEN qint <= qint;
			ELSIF J='0' AND K='1' THEN qint <= '0';
			ELSIF J='1' AND K='0' THEN qint <= '1';
			ELSIF J='1' AND K='1' THEN qint <= NOT qint;
			END IF;
		END IF;
	END PROCESS;

	Q <= qint;
	NO_Q <= NOT qint;
END ifthen;


--------------------------------------------------- PORTES ---------------------------------------------------
ENTITY inv IS 
	PORT(a:IN BIT; z:OUT BIT);
END inv;

ARCHITECTURE logica OF inv IS
BEGIN
	z<= NOT a;
END logica;


ENTITY and2 IS
	PORT(a,b:IN BIT; z: OUT BIT);
END and2;

ARCHITECTURE logica OF and2 IS
BEGIN
	z<= a AND b;
END logica;


ENTITY nor2 IS
	PORT(a,b:IN BIT; z: OUT BIT);
END nor2;

ARCHITECTURE logica OF nor2 IS
BEGIN
	z<= NOT(a OR b);
END logica;
--------------------------------------------------------------------------------------------------------------

-- Circuit
ENTITY circuit IS
	PORT(x, ck:IN BIT; z:OUT BIT);
END circuit;

ARCHITECTURE estructural OF circuit IS
-- Components
COMPONENT inv IS 
	PORT(a:IN BIT; z:OUT BIT);
END COMPONENT;

COMPONENT and2 IS
	PORT(a,b:IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT nor2 IS
	PORT(a,b:IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT Lach_D IS
	PORT(D, Clk, Pre, Clr: IN BIT; Q, NO_Q: OUT BIT);
END COMPONENT;

COMPONENT FF_JK IS
	PORT(J, K, Clk, Pre, Clr: IN BIT; Q, NO_Q: OUT BIT);
END COMPONENT;

-- Senyals internes
SIGNAL invX, q, no_q, j, k: BIT;

-- DUTs
FOR DUT1: inv USE ENTITY WORK.inv(logica);
FOR DUT2: and2 USE ENTITY WORK.and2(logica);
FOR DUT3: nor2 USE ENTITY WORK.nor2(logica);
FOR DUT4: Lach_D USE ENTITY WORK.Lach_D(ifthen);
FOR DUT5: FF_JK USE ENTITY WORK.FF_JK(ifthen);

BEGIN
	-- Mapejat dels DUTs
	DUT1: inv PORT MAP(x, invX);
	DUT2: and2 PORT MAP(x, no_q, j);
	DUT3: nor2 PORT MAP(invX, q, k);
	-- Ja que al cicuit no utilitzem clear ni preset, pero als flip-flops estan implementats, al mapejar-los
	-- possem '1' als dos casos, per tal de que clear i preset sempre siguin 1, perquè així no influeixen
	DUT4: Lach_D PORT MAP(x, ck, '1', '1', q, no_q);
	DUT5: FF_JK PORT MAP(j, k, ck, '1', '1', z);

END estructural;


-- Banc de proves
ENTITY banc_de_proves IS
END banc_de_proves;

ARCHITECTURE test OF banc_de_proves IS
-- Component a simular
COMPONENT com IS
	PORT(x, ck:IN BIT; z:OUT BIT);
END COMPONENT;

-- Senyals
SIGNAL x, ck, z: BIT;

-- DUT
FOR DUT: com USE ENTITY WORK.circuit(estructural);

BEGIN
	-- Mapejat del DUT
	DUT: com PORT MAP(x, ck, z);

	-- Variació de les entrades
	ck <= NOT ck AFTER 100 ns;
	x <= '0', '1' AFTER 150 ns, '0' AFTER 350 ns, '1' AFTER 475 ns, '0' AFTER 550 ns, '1' AFTER 710 ns, '0' AFTER 730 ns, '1' AFTER 770 ns, '0' AFTER 790 ns;
END test;