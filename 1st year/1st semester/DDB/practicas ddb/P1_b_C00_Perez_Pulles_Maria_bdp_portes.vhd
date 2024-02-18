--Maria Perez Pulles
--29/10/19

-------------------------------banc de proves portes----------------------------
ENTITY bdp_portes IS
END bdp_portes;

ARCHITECTURE test OF bdp_portes IS

COMPONENT la_porta_inversor
PORT (a: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT la_porta_and2
PORT (a,b: IN BIT; z: OUT BIT);
END COMPONENT;  

COMPONENT la_porta_and3
PORT (a,b,c: IN BIT; z: OUT BIT);
END COMPONENT; 

COMPONENT la_porta_and4
PORT (a,b,c,d: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT la_porta_or2
PORT (a,b: IN BIT; z: OUT BIT);
END COMPONENT;  

COMPONENT la_porta_or3
PORT (a,b,c: IN BIT; z: OUT BIT);
END COMPONENT; 

COMPONENT la_porta_or4
PORT (a,b,c,d: IN BIT; z: OUT BIT);
END COMPONENT; 

COMPONENT la_porta_xor2
PORT (a,b: IN BIT; z: OUT BIT);
END COMPONENT;

SIGNAL ent1, ent2, ent3, ent4, sort_inversor_logica, sort_and2_logica, sort_and3_logica, sort_and4_logica, sort_or2_logica, sort_or3_logica, 
sort_or4_logica, sort_xor2_logica, sort_inversor_logicaretard, sort_and2_logicaretard, sort_and3_logicaretard,
sort_and4_logicaretard, sort_or2_logicaretard, sort_or3_logicaretard, sort_or4_logicaretard, sort_xor2_logicaretard: BIT; 

---sense retard---
FOR DUT0: la_porta_inversor USE ENTITY WORK.inversor(logica);
FOR DUT1: la_porta_and2 USE ENTITY WORK.and2(logica);
FOR DUT2: la_porta_and3 USE ENTITY WORK.and3(logica);
FOR DUT3: la_porta_and4 USE ENTITY WORK.and4(logica);
FOR DUT4: la_porta_or2 USE ENTITY WORK.or2(logica);
FOR DUT5: la_porta_or3 USE ENTITY WORK.or3(logica);
FOR DUT6: la_porta_or4 USE ENTITY WORK.or4(logica);
FOR DUT7: la_porta_xor2 USE ENTITY WORK.xor2(logica);

---amb retard---
FOR DUT8: la_porta_inversor USE ENTITY WORK.inversor(logica);
FOR DUT9: la_porta_and2 USE ENTITY WORK.and2(logicaretard);
FOR DUT10: la_porta_and3 USE ENTITY WORK.and3(logicaretard);
FOR DUT11: la_porta_and4 USE ENTITY WORK.and4(logicaretard);
FOR DUT12: la_porta_or2 USE ENTITY WORK.or2(logicaretard);
FOR DUT13: la_porta_or3 USE ENTITY WORK.or3(logicaretard);
FOR DUT14: la_porta_or4 USE ENTITY WORK.or4(logicaretard);
FOR DUT15: la_porta_xor2 USE ENTITY WORK.xor2(logicaretard);

BEGIN

---sense retard---
DUT0: la_porta_inversor PORT MAP (ent1,sort_inversor_logica);
DUT1: la_porta_and2 PORT MAP (ent1,ent2,sort_and2_logica);
DUT2: la_porta_and3 PORT MAP (ent1,ent2,ent3,sort_and3_logica);
DUT3: la_porta_and4 PORT MAP (ent1,ent2,ent3,ent4,sort_and4_logica);
DUT4: la_porta_or2 PORT MAP (ent1,ent2,sort_or2_logica);
DUT5: la_porta_or3 PORT MAP (ent1,ent2,ent3,sort_or3_logica);
DUT6: la_porta_or4 PORT MAP (ent1,ent2,ent3,ent4,sort_or4_logica);
DUT7: la_porta_xor2 PORT MAP (ent1,ent2,sort_xor2_logica);

---amb retard---
DUT8: la_porta_inversor PORT MAP (ent1,sort_inversor_logicaretard);
DUT9: la_porta_and2 PORT MAP (ent1,ent2,sort_and2_logicaretard);
DUT10: la_porta_and3 PORT MAP (ent1,ent2,ent3,sort_and3_logicaretard);
DUT11: la_porta_and4 PORT MAP (ent1,ent2,ent3,ent4,sort_and4_logicaretard);
DUT12: la_porta_or2 PORT MAP (ent1,ent2,sort_or2_logicaretard);
DUT13: la_porta_or3 PORT MAP (ent1,ent2,ent3,sort_or3_logicaretard);
DUT14: la_porta_or4 PORT MAP (ent1,ent2,ent3,ent4,sort_or4_logicaretard);
DUT15: la_porta_xor2 PORT MAP (ent1,ent2,sort_xor2_logicaretard);

PROCESS (ent1, ent2, ent3, ent4)
BEGIN
ent1<=NOT ent1 AFTER 50 ns;
ent2<=NOT ent2 AFTER 100 ns;
ent3<=NOT ent3 AFTER 200 ns;
ent4<=NOT ent4 AFTER 400 ns;
END PROCESS;
END test;

