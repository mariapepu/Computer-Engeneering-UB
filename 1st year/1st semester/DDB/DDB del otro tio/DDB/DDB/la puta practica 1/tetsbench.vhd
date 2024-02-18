ENTITY testbench IS
END testbench;

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
