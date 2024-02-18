--ENTITY INVERSOR:
ENTITY inversor IS 
 	PORT( a: IN BIT; b: OUT BIT);
END inversor;

--ARCHITECTURES OF INVERSOR:
ARCHITECTURE logica OF inversor IS
BEGIN
	b <= NOT a;
END logica;

ARCHITECTURE logicaretard OF inversor IS
BEGIN
	b <= NOT a after 2 ns;
END logicaretard;

--ENTITY AND2:
ENTITY and2 IS
 	PORT( a,b: IN BIT; z: OUT BIT);
END and2;

--ARCHITECTURES OF AND2:
ARCHITECTURE logica OF and2 IS
BEGIN
	z <= a AND b;
END logica;

ARCHITECTURE logicaretard of and2 IS
BEGIN
	z <= a AND b after 2 ns;
END logicaretard;

--ENTITY AND3:
ENTITY and3 IS
	PORT( a, b, c: IN BIT; z: OUT BIT);
END and3;

--ARCHITECTURES OF AND3:
ARCHITECTURE logica of and3 IS
BEGIN
 	z <= a AND b AND c;
END logica;

ARCHITECTURE logicaretard of and3 IS
BEGIN
	z <= a AND b AND c after 2 ns;
END logicaretard;

--ENTITY OR2:
ENTITY or2 IS
	PORT( a, b: IN BIT; z: OUT BIT);
END or2;

--ARCHITECTURES OF OR2:
ARCHITECTURE logica OF or2 IS
BEGIN
	z <= a OR b;
END logica;

ARCHITECTURE logicaretard OF or2 IS
BEGIN
	z <= a OR b after 2 ns;
END logicaretard;

--ENTITY OR3:
ENTITY or3 IS
	PORT( a, b, c: IN BIT; z: OUT BIT);
END or3;

--ARCHITECTURES OF OR3:
ARCHITECTURE logica OF or3 IS
BEGIN
 	z <= a OR b OR c;
END logica;

ARCHITECTURE logicaretard OF or3 IS
BEGIN
	z <= a OR b OR c after 2 ns;
END logicaretard;

------------------TESTBENCH----------------
ENTITY bdp_portes IS 
END bdp_portes;

ARCHITECTURE vector of bdp_portes IS
	COMPONENT inversor is
		PORT(a: IN BIT; b: OUT BIT);
	END COMPONENT;

	COMPONENT and2 
		PORT(a, b: IN BIT; z: OUT BIT);
	END COMPONENT;

	COMPONENT and3
		PORT(a, b, c: IN BIT; z: OUT BIT);
	END COMPONENT;

	COMPONENT or2
		PORT(a, b: IN BIT; z: OUT BIT);
	END COMPONENT;

	COMPONENT or3
		PORT(a, b, c: IN BIT; z: OUT BIT);
	END COMPONENT;

	--Defining input and output signals:
	SIGNAL ent1, ent2, ent3, sort_inv_logica, sort_inv_logicaretard, sort_and2_logica, sort_and2_logicaretard, sort_and3_logica, sort_and3_logicaretard, sort_or2_logica, sort_or2_logicaretard, sort_or3_logica, sort_or3_logicaretard: BIT;
		FOR DUTINV: inversor USE ENTITY WORK.inversor(logica);
		FOR DUTAND2: and2 USE ENTITY WORK.and2(logica);
		FOR DUTAND3: and3 USE ENTITY WORK.and3(logica);
		FOR DUTOR2: or2 USE ENTITY WORK.or2(logica);
		FOR DUTOR3: or3 USE ENTITY WORK.or3(logica);
		FOR DUTINVRET: inversor USE ENTITY WORK.inversor(logicaretard);
		FOR DUTAND2RET: and2 USE ENTITY WORK.and2(logicaretard);
		FOR DUTAND3RET: and3 USE ENTITY WORK.and3(logicaretard);
		FOR DUTOR2RET: or2 USE ENTITY WORK.or2(logicaretard);
		FOR DUTOR3RET: or3 USE ENTITY WORK.or3(logicaretard);

	--End of definitions, start with test procedure.
	BEGIN
		DUTINV: inversor PORT MAP (ent1, sort_inv_logica);
		DUTAND2: and2 PORT MAP (ent1, ent2, sort_and2_logica);
		DUTAND3: and3 PORT MAP (ent1, ent2, ent3, sort_and3_logica);
		DUTOR2: or2 PORT MAP (ent1, ent2, sort_or2_logica);
		DUTOR3: or3 PORT MAP (ent1, ent2, ent3, sort_or3_logica);
		DUTINVRET: inversor PORT MAP (ent1, sort_inv_logicaretard);
		DUTAND2RET: and2 PORT MAP (ent1, ent2, sort_and2_logicaretard);
		DUTAND3RET: and3 PORT MAP (ent1, ent2, ent3, sort_and3_logicaretard);
		DUTOR2RET: or2 PORT MAP (ent1, ent2, sort_or2_logicaretard);
		DUTOR3RET: or3 PORT MAP (ent1, ent2, ent3, sort_or3_logicaretard);
			stimulus: PROCESS (ent1, ent2, ent3)
			BEGIN
				ent1 <= NOT ent1 after 200 ns;
				ent2 <= NOT ent2 after 100 ns;
				ent3 <= NOT ent3 after 50 ns;
			END PROCESS;
		END vector;