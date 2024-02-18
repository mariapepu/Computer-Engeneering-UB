--ENTITY COMPARADOR2BITS:
ENTITY comparador_2bits IS
	PORT (enable: IN BIT; paraula_a, paraula_b: IN BIT_VECTOR(1 DOWNTO 0); z: OUT BIT_VECTOR(2 DOWNTO 0));
END comparador_2bits;

--ARCHITECTURE OF COMPARADOR2BITS:
ARCHITECTURE ifthen OF comparador_2bits IS
BEGIN
        PROCESS (enable,paraula_a, paraula_b)
        	BEGIN
		--Building here all possible options.
		--I took the previous code and applied vector syntax.
		IF enable='1' THEN z <= "000" AFTER 5 ns;
		ELSE
			IF ((paraula_a = "10" OR paraula_a = "01") AND (paraula_b = "01" OR paraula_b = "00")) THEN z <= "100" AFTER 5 ns;
			ELSIF ((paraula_a = "01" OR paraula_a = "00") AND (paraula_b = "10" OR paraula_b = "11")) THEN z <= "001" AFTER 5 ns;
			ELSIF (((paraula_a = "00" OR paraula_a = "01") AND (paraula_b = "00" OR paraula_b = "01")) OR ((paraula_a = "10" OR paraula_a = "11") AND (paraula_b = "10" OR paraula_b = "11"))) THEN
                  		IF ((paraula_a = "11" OR paraula_a = "10") AND (paraula_b = "01" OR paraula_b = "00")) THEN z <= "100" AFTER 5 ns;
                  		ELSIF ((paraula_a = "00" OR paraula_a = "10") AND (paraula_b = "01" OR paraula_b = "11")) THEN z <= "001" AFTER 5 ns;
                  		ELSE z <= "010";
                  		END IF;
              		END IF;
		END IF;
        END PROCESS;
END ifthen;

--Let's test our simple comparator.
ENTITY bdp_comp4 IS
END bdp_comp4;

ARCHITECTURE test OF bdp_comp4 IS
--Defining the component:
	COMPONENT comparador_2bits IS
	PORT
		(enable: IN BIT; paraula_a, paraula_b: IN BIT_VECTOR(1 DOWNTO 0); z: OUT BIT_VECTOR (2 DOWNTO 0));
	END COMPONENT;
	--Which signals will we need?
	--Enable signal, two vectors of two components (words) and output signal, one vector of three
	--components.
	SIGNAL enable: BIT;
	SIGNAL vector_entrada_a, vector_entrada_b: BIT_VECTOR (1 DOWNTO 0);
	SIGNAL salida: BIT_VECTOR (2 DOWNTO 0);

	FOR DUT: comparador_2bits USE ENTITY work.comparador_2bits(ifthen);

	BEGIN

		DUT: comparador_2bits PORT MAP (enable, vector_entrada_a, vector_entrada_b, salida);

		PROCESS (enable, vector_entrada_a, vector_entrada_b)
			BEGIN
				enable <= '0';
				vector_entrada_a(1) <= NOT vector_entrada_a(1) AFTER 200 ns;
				vector_entrada_a(0) <= NOT vector_entrada_a(0) AFTER 100 ns;
				vector_entrada_b(1) <= NOT vector_entrada_b(1) AFTER 200 ns;
				vector_entrada_b(0) <= NOT vector_entrada_b(0) AFTER 100 ns;

	END PROCESS;
END test;
