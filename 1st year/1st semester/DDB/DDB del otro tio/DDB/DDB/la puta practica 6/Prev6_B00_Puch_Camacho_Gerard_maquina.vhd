------------------------------------ INVERSOR ----------------------------------------
ENTITY inv IS 
	PORT(a: IN BIT; z: OUT BIT);
END inv;


ARCHITECTURE logica_retard OF inv IS
BEGIN
	z <= NOT a AFTER 4 ns;
END logica_retard;


------------------------------------- AND2 -------------------------------------------
ENTITY and2 IS
	PORT(a, b: IN BIT; z: OUT BIT);
END and2;

ARCHITECTURE logica_retard OF and2 IS
BEGIN
	z <= a AND b  AFTER 4 ns;
END logica_retard;

------------------------------------- XOR2 -------------------------------------------
ENTITY xor2 IS
	PORT(a, b:IN BIT; z: OUT BIT);
END xor2;

ARCHITECTURE logica_retard OF xor2 IS
BEGIN
	z<= (NOT a AND b) OR (a AND NOT b) AFTER 4 ns;
END logica_retard;

--------------------------------------------------------------------------------------
--------------------------------------------------------------------------------------

--- Flip-Flop JK ----

ENTITY FF_JK IS
  PORT(J,K,Clk,Pre,Clr: IN BIT; Q,NO_Q: OUT BIT);
END FF_JK;

ARCHITECTURE ifthen OF FF_JK IS
  SIGNAL qint: BIT;
  
  BEGIN

  PROCESS (Clk,Pre,Clr,J,K)
  BEGIN
    IF Pre='0' THEN qint <= '1' AFTER 6 ns;
    ELSIF Clr='0' THEN qint <='0' AFTER 6 ns;
    ELSIF Clk'EVENT AND Clk='1' THEN
      IF J='0' AND K='0' THEN qint<=qint AFTER 6 ns;
			ELSIF J='0' AND K='1' THEN qint<='0' AFTER 6 ns;
			ELSIF J='1' AND K='0' THEN qint<='1' AFTER 6 ns;
			ELSIF J='1' AND K='1' THEN qint<= NOT qint AFTER 6 ns;
      END IF;
    END IF;
  END PROCESS;
  Q<=qint; NO_Q<=NOT qint;
END ifthen;



--- Circuit ---

ENTITY circuit IS
PORT (clock, X: IN BIT; z: OUT BIT_VECTOR (2 DOWNTO 0));
END circuit;

ARCHITECTURE estructural OF circuit IS

COMPONENT portaand2 IS
PORT(a,b: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portaxor2 IS
PORT(a,b: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT inversor IS
PORT(a: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT FFJK IS
PORT(J,K,Clk,Pre,Clr: IN BIT; Q,NO_Q: OUT BIT);
END COMPONENT;

SIGNAL sortxor, sortinv, sortand: BIT;
SIGNAL Qu: BIT_VECTOR (2 DOWNTO 0);

FOR DUT1: inversor USE ENTITY WORK.inv(logica_retard);
FOR DUT2: portaand2 USE ENTITY WORK.and2(logica_retard);
FOR DUT3: portaxor2 USE ENTITY WORK.xor2(logica_retard);

FOR DUT4: FFJK USE ENTITY WORK.FF_JK(ifthen);
FOR DUT5: FFJK USE ENTITY WORK.FF_JK(ifthen);
FOR DUT6: FFJK USE ENTITY WORK.FF_JK(ifthen);

BEGIN

DUT1: inversor PORT MAP (sortxor, sortinv);
DUT2: portaand2 PORT MAP (sortinv, Qu(1), sortand);
DUT3: portaxor2 PORT MAP (X, Qu(2), sortxor);

DUT4: FFJK PORT MAP (sortand, sortand, clock, '1', '1', Qu(0));
DUT5: FFJK PORT MAP (sortinv, sortinv, clock, '1', '1', Qu(1));
DUT6: FFJK PORT MAP (X, '1', clock, '1', '1', Qu(2));


Z(0) <= Qu(0);
Z(1) <= Qu(1);
Z(2) <= Qu(2);

END estructural;

ENTITY bProves IS
End bProves;

ARCHITECTURE test OF bProves IS
COMPONENT circuit_prova IS
PORT (clock, X: IN BIT; z: OUT BIT_VECTOR (2 DOWNTO 0));
END COMPONENT;

SIGNAL c, ics: BIT;
SIGNAL sortFFJK: BIT_VECTOR (2 DOWNTO 0);

FOR DUT1: circuit_prova USE ENTITY WORK.circuit(estructural);

BEGIN
DUT1: circuit_prova PORT MAP (c, ics, sortFFJK);

PROCESS (c, ics)
 BEGIN
	c <= NOT c AFTER 100 ns;
	ics <= NOT ics AFTER 
50 ns;
 END PROCESS;

END test;
