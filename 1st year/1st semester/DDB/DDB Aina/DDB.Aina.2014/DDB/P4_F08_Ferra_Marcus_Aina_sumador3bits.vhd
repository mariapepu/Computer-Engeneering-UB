--ENTITY OF SUMADOR MODIFICAT 1 BIT:
--We will use this to build a 3-bit adder.
--Also, ths code is given. We won't modify anything of this, but build based on this.
ENTITY sumador_modificat_1bit IS
	PORT (a,b,cin: IN BIT; suma_smod_1bit,cout_smod_1bit,aib_smod_1bit,aob_smod_1bit: OUT BIT);
END sumador_modificat_1bit;

-- Aquesta és la definició del sumador modificat de forma estructural
ARCHITECTURE estructural OF sumador_modificat_1bit IS

COMPONENT portaand2 IS
	PORT(a,b: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portaor2 IS
	PORT(a,b: IN BIT; z: OUT BIT);
END COMPONENT;

COMPONENT portaxor2 IS
	PORT (a,b: IN BIT; z: OUT BIT);
END COMPONENT ;	

--Ens calen 6 DUTs.
	FOR DUT1: portaxor2 USE ENTITY WORK.xor2(logica);
	FOR DUT2: portaxor2 USE ENTITY WORK.xor2(logica);
	FOR DUT3: portaand2 USE ENTITY WORK.and2(logica);
	FOR DUT4: portaor2 USE ENTITY WORK.or2(logica);
	FOR DUT5: portaand2 USE ENTITY WORK.and2(logica);
	FOR DUT6: portaor2 USE ENTITY WORK.or2(logica);

	-- Calen 4 senyals interns
	SIGNAL sort_xor,sort_or,sort_and1,sort_and2: BIT;

	-- Un cop introduïts tots els blocs i senyals, passem a realitzar les connexions
	-- i, d'aquesta forma,
	-- fer la definició de la funció lògica en funció de les variables a, b i cin.

	BEGIN

		DUT1: portaxor2 PORT MAP (a,b,sort_xor);
		DUT2: portaxor2 PORT MAP( sort_xor,cin,suma_smod_1bit);
		DUT3: portaand2 PORT MAP (a,b,sort_and1);
		DUT4: portaor2 PORT MAP( a,b,sort_or);
		DUT5: portaand2 PORT MAP (sort_or,cin,sort_and2);
		DUT6: portaor2 PORT MAP (sort_and1,sort_and2,cout_smod_1bit);

	-- ara introduïm quins senyals interns s?utilitzen, també, com externs
	aib_smod_1bit <= sort_and1;
	aob_smod_1bit <= sort_or;

END estructural;
--Building our 3-bits adder:
--It will take 2 vectors of 3 bits, a simple bit (carry in) and it will take out one solution vector (3 bits) and one simple bit called carry out.
ENTITY sumador3bits IS
	PORT (a, b: IN BIT_VECTOR(2 DOWNTO 0); cin: IN  BIT; z, aib_smod_3bits, aob_smod_3bits: OUT BIT_VECTOR(2 DOWNTO 0); cout: OUT BIT);
END sumador3bits;
--We will use here our 1-bit adder:
ARCHITECTURE estructural OF sumador3bits IS
	COMPONENT sumador_modificat_1bit IS
		PORT (a,b,cin: IN BIT; suma_smod_1bit, cout_smod_1bit, aib_smod_1bit, aob_smod_1bit: OUT BIT);
	END COMPONENT;
--We will only need to internal signals, carry out of the first adder and carry out of the second adder.
--We can use all previously defined signals for everything else.
--These are simple bits since we are defining carries.
SIGNAL cout0, cout1: BIT;

	FOR DUT1: sumador_modificat_1bit USE ENTITY WORK.sumador_modificat_1bit(estructural);
	FOR DUT2: sumador_modificat_1bit USE ENTITY WORK.sumador_modificat_1bit(estructural);
	FOR DUT3: sumador_modificat_1bit USE ENTITY WORK.sumador_modificat_1bit(estructural);

	BEGIN
		--So, basically, we are connecting the adders by their carries.
		--First carry out goes to second carry in, second carry out goes to third carry in.
		--Firs carry in will be a 0 and last carry out depends on the adding result.
		--First adder recieve the most significant bits, and so goes on.
		DUT1: sumador_modificat_1bit PORT MAP(a(0), b(0), cin, z(0), cout0, aib_smod_3bits(0), aob_smod_3bits(0));
		DUT2: sumador_modificat_1bit PORT MAP(a(1), b(1), cout0, z(1), cout1, aib_smod_3bits(1),aob_smod_3bits(1));
		DUT3: sumador_modificat_1bit PORT MAP(a(2), b(2), cout1, z(2), cout, aib_smod_3bits(2),aob_smod_3bits(2));

	END estructural;

--Benching and testing out.
ENTITY bdp_sum3 IS
END bdp_sum3;

ARCHITECTURE test OF bdp_sum3 IS
	COMPONENT sumador3bits IS
		PORT (a, b: IN BIT_VECTOR(2 DOWNTO 0); cin: IN  BIT; z, aib_smod_3bits, aob_smod_3bits: OUT BIT_VECTOR(2 DOWNTO 0); cout: OUT BIT);
	END COMPONENT;

SIGNAL ent_a, ent_b, ent_z, aib, aob: BIT_VECTOR (2 DOWNTO 0);
SIGNAL ent_cin, ent_cout: BIT;

	FOR DUTA: sumador3bits USE ENTITY WORK.sumador3bits(estructural);

	BEGIN

		DUTA: sumador3bits PORT MAP(ent_a, ent_b, ent_cin,ent_z, aib, aob, ent_cout);
		PROCESS(ent_a, ent_b, ent_cin)

			BEGIN
				ent_cin <= '0';
				ent_a(2) <= NOT ent_a(2) AFTER 640 ns;
				ent_a(1) <= NOT ent_a(1) AFTER 320 ns;
				ent_a(0) <= NOT ent_a(0) AFTER 160 ns;
				ent_b(2) <= NOT ent_b(2) AFTER 80 ns;
				ent_b(1) <= NOT ent_b(1) AFTER 40 ns;
				ent_b(0) <= NOT ent_b(0) AFTER 20 ns;

		END PROCESS;
	END test;
