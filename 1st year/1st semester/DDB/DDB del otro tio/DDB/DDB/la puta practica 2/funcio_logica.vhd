ENTITY funcio_logica IS
PORT ( a,b,c: IN BIT; f: OUT BIT);
END Funcio_logica;

ARCHITECTURE logica OF funcio_logica IS
BEGIN
f <= ((NOT a) AND b) OR ((NOT c) AND a);  
END logica;


ARCHITECTURE estructural OF funcio_logica IS 
COMPONENT portaand2 IS
PORT(a,b: IN BIT;
                z: OUT BIT);
END COMPONENT;
 
COMPONENT portaor2 IS
PORT(a,b: IN BIT;
                z: OUT BIT);
END COMPONENT;
 
COMPONENT portainv IS
PORT(a: IN BIT;
                z: OUT BIT);
END COMPONENT;
 
SIGNAL inva, invc, alpha, beta: BIT; 
FOR DUT1: portainv USE ENTITY WORK.inv(logicaretard);
FOR DUT2: portainv USE ENTITY WORK.inv(logicaretard);
FOR DUT3: portaand2 USE ENTITY WORK.and2(logicaretard);
FOR DUT4: portaand2 USE ENTITY WORK.and2(logicaretard);
FOR DUT5: portaor2 USE ENTITY WORK.or2(logicaretard);                                             
 
BEGIN
                DUT1: portainv PORT MAP(A,inva);
                DUT2: portainv PORT MAP(C,invc);
                DUT3: portaand2 PORT MAP(inva,B,alpha);
                DUT4: portaand2 PORT MAp(A,invc,beta);
                DUT5: portaor2 PORT MAP(alpha,beta,f);
END estructural;
 
ENTITY bancdeproves IS
END bancdeproves;
 
 
ARCHITECTURE test_de_proves OF bancdeproves IS
COMPONENT bloc_que_simulem IS
PORT(A,B,C: IN BIT;
                f: OUT BIT);
END COMPONENT;

SIGNAL senyalA,senyalB,senyalC,sortida_f_logica,sortida_f_estructural: BIT;
FOR DUT1: bloc_que_simulem USE ENTITY WORK.funcio_logica(logica);
FOR DUT2: bloc_que_simulem USE ENTITY WORK.funcio_logica(estructural);
BEGIN

                DUT1: bloc_que_simulem PORT MAP(senyalA, senyalB, senyalC, sortida_f_logica);
                DUT2: bloc_que_simulem PORT MAP(senyalA, senyalB, senyalC, sortida_f_estructural);
PROCESS (senyalA, senyalB, senyalC)
                BEGIN
senyalA <= NOT senyalA AFTER 200ns;
senyalB <= NOT senyalB AFTER 100ns;
senyalC <= NOT senyalC AFTER 50ns;
                END PROCESS;
END test_de_proves;
 

ENTITY decodificador_3a8 IS
PORT (a2, a1, a0: IN BIT; z7, z6,z5,z4,z3,z2,z1,z0: OUT BIT);
END decodificador_3a8;

ARCHITECTURE ifthen OF decodificador_3a8 IS
BEGIN
       PROCESS (a2, a1, a0)
       BEGIN
             IF a2='0' AND a1='0' AND a0='0' THEN
Z7<='0' AFTER 5 ns; Z6<='1' AFTER 5 ns; Z5<='1' AFTER 5 ns; Z4<='1' AFTER 5 ns; Z3<='1'AFTER 5 ns; Z2<='1' AFTER 5 ns; Z1<='1' AFTER 5 ns; Z0<='1' AFTER 5 ns;
                    ELSIF a2='0' AND a1='0' AND a0='1' THEN
Z7<='1' AFTER 5 ns; Z6<='0' AFTER 5 ns; Z5<='1'AFTER 5 ns; Z4<='1' AFTER 5 ns; Z3<='1' AFTER 5 ns; Z2<='1' AFTER 5 ns; Z1<='1' AFTER 5 ns; Z0<='1' AFTER 5 ns;
                    ELSIF a2='0' AND a1='1' AND a0='0' THEN
Z7<='1' AFTER 5 ns; Z6<='1' AFTER 5 ns; Z5<='0' AFTER 5 ns; Z4<='1' AFTER 5 ns; Z3<='1' AFTER 5 ns; Z2<='1' AFTER 5 ns; Z1<='1' AFTER 5 ns; Z0<='1' AFTER 5 ns;
                    ELSIF a2='0' AND a1='1' AND a0='1' THEN
Z7<='1' AFTER 5 ns; Z6<='1' AFTER 5 ns; Z5<='1' AFTER 5 ns; Z4<='0' AFTER 5 ns; Z3<='1' AFTER 5 ns; Z2<='1' AFTER 5 ns; Z1<='1' AFTER 5 ns; Z0<='1' AFTER 5 ns;
                    ELSIF a2='1' AND a1='0' AND a0='0' THEN
Z7<='1' AFTER 5 ns; Z6<='1' AFTER 5 ns; Z5<='1' AFTER 5 ns; Z4<='1' AFTER 5 ns; Z3<='0' AFTER 5 ns; Z2<='1' AFTER 5 ns; Z1<='1' AFTER 5 ns; Z0<='1' AFTER 5 ns;
                    ELSIF a2='1' AND a1='0' AND a0='1' THEN 
Z7<='1' AFTER 5 ns; Z6<='1' AFTER 5 ns; Z5<='1' AFTER 5 ns; Z4<='1' AFTER 5 ns; Z3<='1' AFTER 5 ns; Z2<='0' AFTER 5 ns; Z1<='1' AFTER 5 ns; Z0<='1' AFTER 5 ns;
                    ELSIF a2='1' AND a1='1' AND a0='0' THEN
Z7<='1' AFTER 5 ns; Z6<='1' AFTER 5 ns; Z5<='1' AFTER 5 ns; Z4<='1' AFTER 5 ns; Z3<='1' AFTER 5 ns; Z2<='1' AFTER 5 ns; Z1<='0' AFTER 5 ns; Z0<='1' AFTER 5 ns;
                    ELSIF a2='1' AND a1='1' AND a0='1' THEN
Z7<='1' AFTER 5 ns; Z6<='1' AFTER 5 ns; Z5<='1' AFTER 5 ns; Z4<='1' AFTER 5 ns; Z3<='1' AFTER 5 ns; Z2<='1' AFTER 5 ns; Z1<='1' AFTER 5 ns; Z0<='0' AFTER 5 ns;

                    END IF;
             END PROCESS;
END ifthen;


ENTITY banc_de_proves IS
END banc_de_proves;

ARCHITECTURE test OF banc_de_proves IS

COMPONENT el_meu_decodificador_3a8 IS
PORT (a2, a1, a0: IN BIT; z7,z6,z5,z4,z3,z2,z1,z0: OUT BIT);

END COMPONENT;

SIGNAL ent2, ent1, ent0: BIT;
SIGNAL sort7,sort6,sort5,sort4,sort3,sort2,sort1,sort0: BIT;

FOR DUT: el_meu_decodificador_3a8 USE ENTITY WORK.decodificador_3a8(ifthen);

BEGIN

DUT: el_meu_decodificador_3a8 PORT MAP (ent2,ent1,ent0,sort7,sort6,sort5,sort4,sort3,sort2,sort1,sort0);

PROCESS (ent2,ent1,ent0)
BEGIN
ent0<= NOT ent0 AFTER 50 ns;
ent1<= NOT ent1 AFTER 100 ns;
ent2<= NOT ent2 AFTER 200 ns;

END PROCESS;
END test;