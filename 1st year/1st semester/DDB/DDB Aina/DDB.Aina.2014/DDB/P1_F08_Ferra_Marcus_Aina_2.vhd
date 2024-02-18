--ENTITY XOR2:
ENTITY xor2 IS
	PORT (a, b: IN BIT; c: OUT BIT);
END xor2;

--ARCHITECTURES OF XOR2:
ARCHITECTURE logica of xor2 IS
BEGIN
	c <= a XOR b;
END logica;

ARCHITECTURE logicaretard of xor2 IS
BEGIN
	c <= a XOR b after 2 ns;
END logicaretard;

------------TESTBENCH---------
ENTITY bdp_funcio is
END bdp_funcio;

ARCHITECTURE vector OF bdp_funcio IS
	COMPONENT xor2 IS
		PORT (a, b: IN BIT; c: OUT BIT);
	END COMPONENT;

	--Defining input and output signals: 
	SIGNAL ent1, ent2, sort_xor2_logica, sort_xor2_logicaretard: BIT;
		FOR DUTXOR2: xor2 USE ENTITY WORK.xor2(logica);
		FOR DUTXOR2RET: xor2 USE ENTITY WORK.xor2(logicaretard);

	BEGIN
		DUTXOR2: xor2 PORT MAP (ent1, ent2, sort_xor2_logica);
		DUTXOR2RET: xor2 PORT MAP (ent1, ent2, sort_xor2_logicaretard);
			--Starting process
			stimulus: PROCESS (ent1, ent2)
			BEGIN
				--Defining changing time of ent1 and end2.
				ent1 <= NOT ent1 after 200 ns;
				ent2 <= NOT ent2 after 100 ns;
			END PROCESS;
		END vector;
