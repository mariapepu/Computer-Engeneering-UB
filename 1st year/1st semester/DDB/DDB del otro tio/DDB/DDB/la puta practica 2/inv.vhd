ENTITY inv IS
PORT (a: IN BIT; z: OUT BIT);
END inv;

ARCHITECTURE logica OF inv IS
BEGIN
z <= NOT a;
END logica;

ARCHITECTURE logicaretard OF inv IS
BEGIN
z <= NOT a AFTER 3 ns;
END logicaretard;
