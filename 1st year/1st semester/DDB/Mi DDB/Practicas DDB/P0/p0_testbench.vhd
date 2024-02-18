
ENTITY testbench IS
END testbench;

ARCHITECTURE vectors OF testbench IS

COMPONENT inversor
PORT (a: IN BIT; z: OUT BIT);
END COMPONENT

SIGNAL entrada, sortida: BIT;

FOR DUT: inversor USE ENTITY WORK.inv(logica);

BEGIN
DUT: inversor PORT MAP (entrada, sortida);

PROCESS
BEGIN
entrada<= '0';
WAIT FOR 100 ns;
entrada<= '1';
WAIT FOR 500 ns;
entrada<= '0';
WAIT FOR 1000 ns;
entrada<= '1';
END PROCESS;

END vectors;