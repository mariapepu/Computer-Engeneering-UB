-------------------XOR2-------------------
ENTITY xor2 IS  
	PORT(a, b: IN BIT; z: OUT BIT);
END xor2;

ARCHITECTURE logicaretard OF xor2 IS  
BEGIN    
	z <= a XOR b AFTER 4 ns;
END logicaretard;

-------------------AND2--------------------
ENTITY and2 IS  
	PORT (a, b: IN BIT; z: OUT BIT);
END and2;

ARCHITECTURE logicaretard OF and2 IS
BEGIN  
	z <= a AND b AFTER 4 ns;
END logicaretard;

------------------INVERSOR----------------
ENTITY inv IS 
	PORT(a: IN BIT; z: OUT BIT);
END inv;

ARCHITECTURE logicaretard OF inv IS
BEGIN
	z <= NOT a AFTER 4 ns;
END logicaretard;

------------------- Flip-Flop JK -----------------
ENTITY FF_JK IS
  PORT(J,K,Clk,Pre,Clr: IN BIT; Q,NO_Q: OUT BIT);
END FF_JK;

ARCHITECTURE ifthen OF FF_JK IS
  SIGNAL qint: BIT;
  
  BEGIN

  PROCESS (Clk,Pre,Clr,J,K)
  BEGIN
    IF Pre='0' THEN qint <= '1' AFTER 4 ns;
    ELSIF Clr='0' THEN qint <='0' AFTER 4 ns;
    ELSIF Clk'EVENT AND Clk='1' THEN
      IF J='0' AND K='0' THEN qint<=qint AFTER 4 ns;
			ELSIF J='0' AND K='1' THEN qint<='0' AFTER 4 ns;
			ELSIF J='1' AND K='0' THEN qint<='1' AFTER 4 ns;
			ELSIF J='1' AND K='1' THEN qint<= NOT qint AFTER 4 ns;
      END IF;
    END IF;
  END PROCESS;
  Q<=qint; NO_Q<=NOT qint;
END ifthen;

--Hasta aqui compila.
---------------------CIRCUIT---------------------

ENTITY circuit IS
	PORT (clock, X: IN BIT; Z0, Z1, Z2: OUT BIT);
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

SIGNAL sortxor, sortinv, sortand, Q0, Q1, Q2, NO_Q0, NO_Q1, NO_Q2: BIT;
FOR DUT1: inversor USE ENTITY WORK.inv(logica_retard);
FOR DUT2: portaand2 USE ENTITY WORK.and2(logica_retard);
FOR DUT3: portaxor2 USE ENTITY WORK.xor2(logica_retard);
FOR DUT4: FFJK USE ENTITY WORK.FF_JK(ifthen);
FOR DUT5: FFJK USE ENTITY WORK.FF_JK(ifthen);
FOR DUT6: FFJK USE ENTITY WORK.FF_JK(ifthen);

BEGIN

DUT1: inversor PORT MAP (sortxor, sortinv);
DUT2: portaand2 PORT MAP (sortinv, Q1, sortand);
DUT3: portaxor2 PORT MAP (X, Q0, sortxor);
DUT4: FFJK PORT MAP (sortand, sortand, clock, '1', '1', Q2, NO_Q2);
DUT5: FFJK PORT MAP (sortinv, sortinv, clock, '1', '1', Q1, NO_Q1);
DUT6: FFJK PORT MAP (X, '1', clock, '1', '1', Q0, NO_Q0);

Z0 <= Q0;
Z1 <= Q1;
Z2 <= Q2;

END estructural;
--Hasta aqui compila
-------------------- Banco Pruevas maquina :D --------------------
ENTITY bancdeproves IS
END bancdeproves;

ARCHITECTURE test OF bancdeproves IS
  
COMPONENT circuit IS
	PORT(clock, x: IN BIT; z0, z1, z2: OUT BIT);  
END COMPONENT;
  
SIGNAL ent_clock, ent_x, sort_z0, sort_z1, sort_z2: BIT;  
FOR DUT1: circuit USE ENTITY WORK.circuit(estructural);   

BEGIN
DUT1: circuit PORT MAP(ent_clock, ent_x, sort_z0, sort_z1, sort_z2);  

PROCESS(ent_clock, ent_x)
BEGIN                    
	ent_clock <= NOT ent_clock AFTER 100 ns;          
	ent_x <= NOT ent_x AFTER 50 ns;
              
END PROCESS;    
END test;

