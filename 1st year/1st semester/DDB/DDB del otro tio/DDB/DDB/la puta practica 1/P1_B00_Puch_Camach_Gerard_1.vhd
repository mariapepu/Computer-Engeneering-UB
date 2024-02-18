-- inversor1.vhd
ENTITY inversor1 IS
PORT (a : IN BIT; f: OUT BIT);
END inversor1;

ARCHITECTURE logica OF inversor1 IS
BEGIN
f <= not a;
END logica;
-- testbench.vhd

ENTITY testbench IS
END testbench

ARCHITECTURE vectors OF testbench IS

COMPONENT inversor1
PORT( a: IN BIT; f: OUT BIT);
END COMPONENT;
SIGNAL a,f : BIT;
FOR DUT: inversor1 USE ENTITY WORK.inversor1(logica);
BEGIN
DUT: inversor1 PORT MAP (a,f);
stimulus: PROCESS
BEGIN
a <= '0';
wait for 100 ns;
a <= '1';
wait for 500 ns;
a <= '0';
wait for 1000 ns;
a <= '1';
END PROCESS;
END vectors;
-- and2.vhd

ENTITY and2 IS
 PORT (a, b: IN BIT; z: OUT BIT);
 END and2;
 
 ARCHITECTURE logica OF and2 IS
   BEGIN
     z <= a AND b;
   END logica; 
-- and3.vhd

ENTITY and3 IS
 PORT (a, b, c: IN BIT; z: OUT BIT);
 END and3;
 
 ARCHITECTURE logica OF and3 IS
   BEGIN
     z <= a AND b AND c;
   END logica;
-- and4.vhd

ENTITY and4 IS
PORT (a, b, c, d: IN BIT; z: OUT BIT);
END and4;

ARCHITECTURE logica OF and4 IS
BEGIN
z <= a AND b AND c AND d;
END logica;
-- or2.vhd

ENTITY or2 IS
 PORT (a, b: IN BIT; z: OUT BIT);
 END or2;
 
 ARCHITECTURE logica OF or2 IS
   BEGIN
     z <= a OR b;
   END logica;
-- or3.vhd

ENTITY or3 IS
 PORT (a, b, c: IN BIT; z: OUT BIT);
 END or3;
 
 ARCHITECTURE logica OF or3 IS
   BEGIN
     z <= a OR b OR c;
   END logica;
--or4.vhd

ENTITY or4 IS
PORT (a, b, c, d: IN BIT; z: OUT BIT);
END or4;

ARCHITECTURE logica OF or4 IS
BEGIN
z <= a OR b OR c OR d;
END logica;
--bdp_proves.vhd

ENTITY bdp_proves IS
END bdp_proves;
ARCHITECTURE vectors OF  bdp_proves IS

COMPONENT porta_and2
PORT (a, b: IN BIT; z: OUT BIT);
END COMPONENT;
COMPONENT porta_or2
PORT (a, b: IN BIT; z: OUT BIT);
END COMPONENT;
COMPONENT porta_and3
PORT (a, b, c: IN BIT; z: OUT BIT);
END COMPONENT;
COMPONENT porta_or3
PORT (a, b, c: IN BIT; z: OUT BIT);
END COMPONENT;
COMPONENT porta_and4
PORT (a, b, c, d: IN BIT; z: OUT BIT);
END COMPONENT;
COMPONENT porta_or4
PORT (a, b, c, d: IN BIT; z: OUT BIT);
END COMPONENT;
COMPONENT inversor1
PORT( a: IN BIT; f: OUT BIT);
END COMPONENT;

SIGNAL ent1, ent2, ent3, ent4, sort_and2_logica, sort_and3_logica, sort_and4_logica, sort_or2_logica, sort_or3_logica, sort_or4_logica, sort_inversor1_logica: BIT;

FOR DUT1: porta_and2 USE ENTITY WORK.and2(logica);
FOR DUT2: porta_and3 USE ENTITY WORK.and3(logica);
FOR DUT3: porta_and4 USE ENTITY WORK.and4(logica);

FOR DUT4: porta_or2 USE ENTITY WORK.or2(logica);
FOR DUT5: porta_or3 USE ENTITY WORK.or3(logica);
FOR DUT6: porta_or4 USE ENTITY WORK.or4(logica);

FOR DUT: inversor1 USE ENTITY WORK.inversor1(logica);

BEGIN
DUT1: porta_and2 PORT MAP (ent1, ent2, sort_and2_logica);
DUT2: porta_and3 PORT MAP (ent1, ent2, ent3, sort_and3_logica);
DUT3: porta_and4 PORT MAP (ent1, ent2, ent3, ent4, sort_and4_logica);

DUT4: porta_or2 PORT MAP (ent1, ent2, sort_or2_logica);
DUT5: porta_or3 PORT MAP (ent1, ent2, ent3, sort_or3_logica);
DUT6: porta_or4 PORT MAP (ent1, ent2, ent3, ent4, sort_or4_logica);

DUT: inversor1 PORT MAP (ent1, sort_inversor1_logica);

PROCESS (ent1, ent2, ent3, ent4)
BEGIN
ent1 <= NOT ent1 AFTER 400 ns;
ent2 <= NOT ent2 AFTER 200 ns;
ent3 <= NOT ent3 AFTER 100 ns;
ent4 <= NOT ent4 AFTER 50 ns;
END PROCESS;


END vectors;