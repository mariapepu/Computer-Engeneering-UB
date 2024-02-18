--Maria Pérez
--30/12/19

----------------- FLOP FLOP JK -----------------------
---com que no posa quin tipus de ff-JK s'ha de fer servir he agafat el de la P3
ENTITY FlipFlop_JK IS
PORT (J,K,Clk,Pre,Clr: IN BIT; Q: OUT BIT);
END FlipFlop_JK;

ARCHITECTURE  ifthen OF FlipFlop_JK IS
SIGNAL qint: BIT;
BEGIN
PROCESS (J,K,Clk,Pre,Clr)
BEGIN
IF Clr = '0' THEN qint<='0' AFTER 4 ns;  ---PRIORITAT CLR
ELSIF Pre = '0' THEN qint <='1' AFTER 4 ns;
ELSIF Clk'EVENT AND Clk='0' THEN 	---FLANC DE BAIXADA
        IF J = '0' AND K = '0' THEN qint <= qint AFTER 4 ns;
        ELSIF J ='1' AND K = '0' THEN qint<='1' AFTER 4 ns;
        ELSIF J = '0' AND K = '1' THEN qint <= '0' AFTER 4 ns;
        ELSIF J = '1' AND K = '1' THEN qint <= NOT qint AFTER 4 ns;
        END IF;
END IF;
END PROCESS;
Q <=qint;
END ifthen;

------------------------- CIRCUIT ----------------------------
ENTITY circuit IS
PORT (x, clock: IN BIT; z2,z1,z0: OUT BIT);
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
	PORT(J,K,Clk,Pre,Clr: IN BIT; Q: OUT BIT);
END COMPONENT;

SIGNAL sortxor, sortinv, sortand, Q0, Q1, Q2: BIT;
FOR DUT1: FFJK USE ENTITY WORK.FF_JK(ifthen);
FOR DUT2: inversor USE ENTITY WORK.inv(logica_retard);
FOR DUT3: portaxor2 USE ENTITY WORK.xor2(logica_retard);
FOR DUT4: FFJK USE ENTITY WORK.FF_JK(ifthen);
FOR DUT5: portaand2 USE ENTITY WORK.and2(logica_retard);
FOR DUT6: FFJK USE ENTITY WORK.FF_JK(ifthen);

BEGIN

DUT1: FFJK PORT MAP (x, '1', clock, '1', '1', Q0);
DUT3: portaxor2 PORT MAP (x, Q0, sortxor);
DUT2: inversor PORT MAP (sortxor, sortinv);
DUT4: FFJK PORT MAP (sortinv, sortinv, clock, '1', '1', Q1);
DUT5: portaand2 PORT MAP (sortinv, Q1, sortand);
DUT6: FFJK PORT MAP (sortand, sortand, clock, '1', '1', Q2);

z0 <= Q0;
z1 <= Q1;
z2 <= Q2;

END estructural;

-------------------- BANC PROVES CIRCUIT --------------------
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
