--- Latch D ---
ENTITY Lach_D IS
	PORT(D, Clk, Pre, Clr: IN BIT; Q, NO_Q: OUT BIT);
END Lach_D;

ARCHITECTURE ifthen OF Lach_D IS
SIGNAL qentr BIT;
BEGIN
	PROCESS (Clk, Pre, Clr, D)
	BEGIN
		IF Pre='0' THEN qentr <= '1';
		ELSIF Clr='0' THEN qentr <= '0';
		ELSIF Clk='1' THEN qentr <= D;
		END IF;
	END PROCESS;

	Q <= qentr;
	NO_Q <= NOT qentr;
END ifthen;


--- Flip-Flop JK (flanc de pujada) ---
ENTITY FF_JK IS
	PORT(J, K, Clk, Pre, Clr: IN BIT; Q, NO_Q: OUT BIT);
END FF_JK;

ARCHITECTURE ifthen OF FF_JK IS
SIGNAL qentr: BIT;
BEGIN
	PROCESS (Clk, Pre, Clr, J, K)
	BEGIN
		IF Pre='0' THEN qentr <= '1';
		ELSIF Clr='0' THEN qentr <= '0';
		ELSIF Clk'EVENT AND Clk='1' THEN
			IF J='0' AND K='0' THEN qentr <= qentr;
			ELSIF J='0' AND K='1' THEN qentr <= '0';
			ELSIF J='1' AND K='0' THEN qentr <= '1';
			ELSIF J='1' AND K='1' THEN qentr <= NOT qentr;
			END IF;
		END IF;
	END PROCESS;

	Q <= qentr;
	NO_Q <= NOT qentr;
END ifthen;


----------------------------------- INVERSOR -----------------------------------------
ENTITY inv IS 
	PORT(a:IN BIT; z:OUT BIT);
END inv;

ARCHITECTURE logica OF inv IS
BEGIN
	z<= NOT a;
END logica;

------------------------------------- AND2 -------------------------------------------

ENTITY and2 IS
	PORT(a,b:IN BIT; z: OUT BIT);
END and2;

ARCHITECTURE logica OF and2 IS
BEGIN
	z<= a AND b;
END logica;

------------------------------------- NOR2 -------------------------------------------

ENTITY nor2 IS
	PORT(a,b:IN BIT; z: OUT BIT);
END nor2;

ARCHITECTURE logica OF nor2 IS
BEGIN
	z<= NOT(a OR b);
END logica;
--------------------------------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------------------------------

--- Circuit ---

ENTITY circuit IS
	PORT(x, ck:IN BIT; z:OUT BIT);
END circuit;

ARCHITECTURE estructural OF circuit IS

COMPONENT inversor IS 
PORT(a:IN BIT; z:OUT BIT);
END COMPONENT;

COMPONENT portaand2 IS
PORT(a,b:IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portanor2 IS
PORT(a,b:IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT Lach_D IS
PORT(D, Clk, Pre, Clr: IN BIT; Q, NO_Q: OUT BIT);
END COMPONENT;

COMPONENT FF_JK IS
PORT(J, K, Clk, Pre, Clr: IN BIT; Q, NO_Q: OUT BIT);
END COMPONENT;

SIGNAL invX, q, no_q, j, k: BIT;

FOR DUT1: inversor USE ENTITY WORK.inv(logica);
FOR DUT2: portaand2 USE ENTITY WORK.and2(logica);
FOR DUT3: portanor2 USE ENTITY WORK.nor2(logica);
FOR DUT4: Lach_D USE ENTITY WORK.Lach_D(ifthen);
FOR DUT5: FF_JK USE ENTITY WORK.FF_JK(ifthen);

BEGIN
	DUT1: inversor PORT MAP(x, invX);
	DUT2: portaand2 PORT MAP(x, no_q, j);
	DUT3: portanor2 PORT MAP(invX, q, k);
	DUT4: Lach_D PORT MAP(x, ck, '1', '1', q, no_q);
	DUT5: FF_JK PORT MAP(j, k, ck, '1', '1', z);

END estructural;


--- Banc de proves ---

ENTITY banc_de_proves IS
END banc_de_proves;

ARCHITECTURE test OF banc_de_proves IS

COMPONENT com IS
PORT(x, ck:IN BIT; z:OUT BIT);
END COMPONENT;

SIGNAL x, ck, z: BIT;

FOR DUT: com USE ENTITY WORK.circuit(estructural);

BEGIN
DUT: com PORT MAP(x, ck, z);

ck <= NOT ck AFTER 100 ns;
x <= '0', '1' AFTER 150 ns, '0' AFTER 350 ns, '1' AFTER 475 ns, '0' AFTER 550 ns, '1' AFTER 710 ns, '0' AFTER 730 ns, '1' AFTER 770 ns, '0' AFTER 790 ns;

END test;