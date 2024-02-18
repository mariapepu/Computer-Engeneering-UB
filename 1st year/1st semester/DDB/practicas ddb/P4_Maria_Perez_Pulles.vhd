--Maria Perez Pulles
--24/11/19
------------------ MUX 4 a 1 -----------------------
ENTITY mux4a1 IS
PORT (a,b,c,d,sel0,sel1: IN BIT; f: OUT BIT);
END mux4a1;

---- IFTHEN ------
ARCHITECTURE ifthen OF mux4a1 IS
BEGIN
	PROCESS (a,b,c,d,sel0,sel1) IS
		BEGIN
  			IF (sel0 ='0' and sel1 = '0') THEN f <= a;
			ELSIF (sel0 ='1' and sel1 = '0') THEN f <= b;
			ELSIF (sel0 ='0' and sel1 = '1') THEN f <= c; 			
			ELSE f <= d;
  		END IF;
	END PROCESS;
END ifthen;

---- LOGICA RETARD ------
ARCHITECTURE logica_retard OF mux4a1 IS
BEGIN
f <= ((NOT sel0)AND(NOT sel1)AND(a)) OR ((sel0)AND(NOT sel1)AND(b)) OR ((not sel0)AND(sel1)AND(c)) OR ((sel1)AND(sel0)AND(d)) AFTER 3 ns; 
END logica_retard;


------------------------ FF_D -------------------------
ENTITY FF_D IS
PORT (D,Clk,Pre: IN BIT; Q: OUT BIT);
END FF_D;

------ IFTHEN ------
ARCHITECTURE ifthen OF FF_D IS
SIGNAL qint:BIT;
BEGIN
PROCESS (D,Clk,Pre)
BEGIN 
IF Pre='0' THEN qint<='0' AFTER 2 ns;	---PRIORITAT PRE
      ELSIF Pre='0' THEN qint<='1' AFTER 2 ns;	
      ELSIF Clk'EVENT AND Clk='1' THEN qint <= D AFTER 2 ns;	---FLANC DE PUJADA

END IF;
END PROCESS;
Q<=qint;
END ifthen;

----------------------- REGISTRE -----------------------
ENTITY registre IS
PORT (a2, a1, a0, sel1, sel0, E, clock: IN BIT; Q2, Q1, Q0: OUT BIT);
END registre;

------- ESTRUCTURAL ------
ARCHITECTURE estructural of registre IS

COMPONENT mux_4a1 IS
	PORT (a,b,c,d,sel0,sel1: IN BIT; f: OUT BIT);
END COMPONENT;

COMPONENT FlipFlop_D IS
	PORT (D,Clk,Pre: IN BIT; Q: OUT BIT);
END COMPONENT;


SIGNAL Q_2, a_2, la_e, sel_0, sel_1, f_2, a_1, a_0, d1, d2, d0, Q_1, Q_0, clk, preset: BIT;
FOR DUT1: mux_4a1 USE ENTITY WORK.mux4a1(ifthen);
FOR DUT2: FlipFlop_D USE ENTITY WORK.FF_D(ifthen);
FOR DUT3: mux_4a1 USE ENTITY WORK.mux4a1(ifthen);
FOR DUT4: FlipFlop_D USE ENTITY WORK.FF_D(ifthen);
FOR DUT5: mux_4a1 USE ENTITY WORK.mux4a1(ifthen);
FOR DUT6: FlipFlop_D USE ENTITY WORK.FF_D(ifthen);

BEGIN
DUT1: mux_4a1 PORT MAP ('1', Q_2, a_2, la_e, sel_0, sel_1, d2);
DUT2: FlipFlop_D PORT MAP (d2, clk, preset, Q_2);
DUT3: mux_4a1 PORT MAP ('1', Q_1, a_1, Q_2, sel_0, sel_1, d1);
DUT4: FlipFlop_D PORT MAP (d1, clk, preset, Q_1);
DUT5: mux_4a1 PORT MAP ('1', Q_0, a_0, Q_1, sel_0, sel_1, d0);
DUT6: FlipFlop_D PORT MAP (d0, clk, preset, Q_0);

END estructural;

----------------------- BANC DE PROVES -------------------------
ENTITY bdp IS
END bdp;

ARCHITECTURE test OF bdp IS
COMPONENT el_registre IS
PORT (a2, a1, a0, sel1, sel0, E, clock: IN BIT; Q2, Q1, Q0: OUT BIT);
END COMPONENT;

SIGNAL a_2, a_1, a_0, sel_1, sel_0, e, clk, Q_2, Q_1, Q_0: BIT;
FOR DUT1: el_registre USE ENTITY WORK.registre(estructural);

BEGIN
DUT1: el_registre PORT MAP(a_2, a_1, a_0, sel_1, sel_0, e, clk, Q_2, Q_1, Q_0);

PROCESS (a_2, a_1, a_0, sel_1, sel_0, e, clk)
BEGIN
a_2 <= NOT a_2 AFTER 3200 ns;
a_1 <= NOT a_1 AFTER 1600 ns;
a_0 <= NOT a_0 AFTER 800 ns;
sel_1 <= NOT sel_1 AFTER 400 ns;
sel_0 <= NOT sel_0 AFTER 200 ns;
e <= NOT e AFTER 100 ns;
clk <= NOT clk AFTER 50 ns;
END PROCESS;

END test;