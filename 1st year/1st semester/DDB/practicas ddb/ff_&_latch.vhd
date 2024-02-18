--Maria Perez Pulles
--20/11/19

----------- FLIP FLOP D -------------
ENTITY FlipFlop_D IS
PORT (D,Clk,Pre,clr: IN BIT; Q,NO_Q: OUT BIT);
END FlipFlop_D;

ARCHITECTURE ifthen OF FlipFlop_D IS
SIGNAL qint:BIT;
BEGIN
PROCESS (D,Clk,Pre,clr)
BEGIN 
IF clr='0' THEN qint<='0' AFTER 2 ns;	---PRIORITAT CLR
      ELSIF Pre='0' THEN qint<='1' AFTER 2 ns;	
      ELSIF Clk'EVENT AND Clk='0' THEN qint <= D AFTER 2 ns;	---FLANC DE BAIXADA

END IF;
END PROCESS;
Q<=qint; NO_Q<=NOT qint;
END ifthen;


----------- LATCH D -------------
ENTITY latch_D IS
PORT (D,Clk,Pre,clr: IN BIT; Q,NO_Q: OUT BIT);
END latch_D;

ARCHITECTURE ifthen OF latch_D IS
SIGNAL qint: BIT;
BEGIN
PROCESS (D,Clk,Pre,clr)
BEGIN
IF clr='0' THEN qint<='0' AFTER 2 ns;	---PRIORITAT CLR   --ACTIVE HIGH
      ELSIF Pre = '0' THEN qint <='1' AFTER 2 ns;
      ELSIF Clk = '1' THEN qint <= D AFTER 2 ns;
END IF;
END PROCESS;
Q<= qint; NO_Q<= NOT qint;
END ifthen;


------------- FLIP FLOP JK --------------
ENTITY FlipFlop_JK IS
PORT (J,K,Clk,Pre,Clr: IN BIT; Q,NO_Q: OUT BIT);
END FlipFlop_JK;

ARCHITECTURE  ifthen OF FlipFlop_JK IS
SIGNAL qint: BIT;
BEGIN
PROCESS (J,K,Clk,Pre,Clr)
BEGIN
IF Clr = '0' THEN qint<='0' AFTER 2 ns;  ---PRIORITAT CLR
ELSIF Pre = '0' THEN qint <='1' AFTER 2 ns;
ELSIF Clk'EVENT AND Clk='0' THEN 	---FLANC DE BAIXADA
        IF J = '0' AND K = '0' THEN qint <= qint AFTER 2 ns;
        ELSIF J ='1' AND K = '0' THEN qint<='1' AFTER 2 ns;
        ELSIF J = '0' AND K = '1' THEN qint <= '0' AFTER 2 ns;
        ELSIF J = '1' AND K = '1' THEN qint <= NOT qint AFTER 2 ns;
        END IF;
END IF;
END PROCESS;
Q <=qint; NO_Q<= NOT qint;
END ifthen;


------------- LATCH JK --------------
ENTITY latch_JK IS
PORT (J,K,Clk,Pre,Clr: IN BIT; Q,NO_Q: OUT BIT);
END latch_JK;

ARCHITECTURE ifthen OF latch_JK IS
SIGNAL qint: BIT;
BEGIN
PROCESS (J,K,Clk,Pre,Clr)
BEGIN
IF Clr='0' THEN qint<='0' AFTER 2 ns;	---PRIORITAT CLR   --ACTIVE HIGH
ELSIF Pre='0' THEN qint<='1' AFTER 2 ns;
ELSIF Clk='1' THEN
			IF J='0' AND K='0' THEN qint<=qint AFTER 2 ns;
			ELSIF J='0' AND K='1' THEN qint<='0' AFTER 2 ns;
			ELSIF J='1' AND K='0' THEN qint<='1' AFTER 2 ns;
			ELSIF J='1' AND K='1' THEN qint<= NOT qint AFTER 2 ns;
			END IF;


END IF;

END PROCESS;
Q<=qint; NO_Q<=NOT qint;
END ifthen;



------------- FLIP FLOP T ------------
ENTITY FlipFlop_T IS
PORT (T,Clk,Pre,Clr: IN BIT; Q,NO_Q: OUT BIT);
END FlipFlop_T;

ARCHITECTURE ifthen OF FlipFlop_T IS
SIGNAL qint:BIT;
BEGIN
PROCESS (T,Clk,Pre,clr)
BEGIN 
IF clr='0' THEN qint<='0' AFTER 2 ns;	---PRIORITAT CLR
      ELSIF Pre = '0' THEN qint <='1' AFTER 2 ns;
      ELSIF Clk'EVENT AND Clk='0'THEN	---FLANC DE BAIXADA
                IF T = '0' THEN qint <= qint AFTER 2 ns;
                ELSIF T = '1'THEN qint <= NOT qint AFTER 2 ns;
                END IF; 

END IF;
END PROCESS;
Q<=qint; NO_Q<=NOT qint;
END ifthen;


--------------- LATCH T ------------------
ENTITY latch_T IS
PORT (T,Clk,Pre,Clr: IN BIT; Q,NO_Q: OUT BIT);
END latch_T;

ARCHITECTURE ifthen OF latch_T IS
SIGNAL qint: BIT;
BEGIN
PROCESS (T,Clk,Pre,clr)
BEGIN
IF clr='0' THEN qint<='0' AFTER 2 ns;	---PRIORITAT CLR	--ACTIVE HIGH
      ELSIF Pre = '0' THEN qint <='1' AFTER 2 ns;
      ELSIF Clk = '1' THEN
                IF T = '0' THEN qint <= qint AFTER 2 ns;
                ELSIF T = '1'THEN qint <= NOT qint AFTER 2 ns;
                END IF; 
END IF;
END PROCESS;
Q<= qint; NO_Q<= NOT qint;
END ifthen;


----------------BANC DE PROVES------------------------
ENTITY bdp IS
END bdp;

ARCHITECTURE test OF bdp IS
COMPONENT la_latch_D IS
PORT(D,Clk,Pre,Clr: IN BIT; Q,NO_Q: OUT BIT);
END COMPONENT;
COMPONENT la_FlipFlop_D IS
PORT(D,Clk,Pre,Clr: IN BIT; Q,NO_Q: OUT BIT);
END COMPONENT;
COMPONENT la_latch_JK IS
PORT(J,K,Clk,Pre,Clr: IN BIT; Q,NO_Q: OUT BIT);
END COMPONENT;
COMPONENT la_FlipFlop_JK IS
PORT(J,K,Clk,Pre,Clr: IN BIT; Q,NO_Q: OUT BIT);
END COMPONENT;
COMPONENT la_FlipFlop_T IS
PORT(T,Clk,Pre,Clr: IN BIT; Q,NO_Q: OUT BIT);
END COMPONENT;
COMPONENT la_latch_T IS
PORT(T,Clk,Pre,Clr: IN BIT; Q,NO_Q: OUT BIT);
END COMPONENT;

SIGNAL ent1,ent2,clock,preset,clear,Dsort_Q,Dsort_noQ,JKsort_Q,JKsort_noQ,Tsort_Q,Tsort_noQ,
       DsortFF_Q,DsortFF_noQ,JKsortFF_Q,JKsortFF_noQ,TsortFF_Q,TsortFF_noQ: BIT;

FOR DUT0: la_latch_D USE ENTITY WORK.latch_D(ifthen);
FOR DUT1: la_FlipFlop_D  USE ENTITY WORK.FlipFlop_D (ifthen);
FOR DUT2: la_latch_JK USE ENTITY WORK.latch_JK(ifthen);
FOR DUT3: la_FlipFlop_JK USE ENTITY WORK.FlipFlop_JK(ifthen);
FOR DUT4: la_latch_T USE ENTITY WORK.latch_T(ifthen);
FOR DUT5: la_FlipFlop_T USE ENTITY WORK.FlipFlop_T(ifthen);

BEGIN
DUT0: la_latch_D PORT MAP (ent1,clock,preset,clear,Dsort_Q,Dsort_noQ);
DUT1: la_FlipFlop_D PORT MAP (ent1,clock,preset,clear,DsortFF_Q,DsortFF_noQ);
DUT2: la_latch_JK PORT MAP (ent1,ent2,clock,preset,clear,JKsort_Q,JKsort_noQ);
DUT3: la_FlipFlop_JK PORT MAP (ent1,ent2,clock,preset,clear,JKsortFF_Q,JKsortFF_noQ);
DUT4: la_latch_T PORT MAP (ent1,clock,preset,clear,Tsort_Q,Tsort_noQ);
DUT5: la_FlipFlop_T PORT MAP (ent1,clock,preset,clear,TsortFF_Q,TsortFF_noQ);

ent1 <= NOT ent1 AFTER 800 ns;
ent2 <= NOT ent2 AFTER 400 ns;
clock <= NOT clock AFTER 500 ns;
preset <= '0', '1' AFTER 600 ns;
clear <= '1','0' AFTER 200 ns, '1' AFTER 400 ns;

END test;
