ENTITY xor2 IS
PORT (a,b: IN BIT; z: OUT BIT);
END xor2;

ARCHITECTURE logica OF xor2 IS
BEGIN
z <= ((NOT a) AND b) OR (a AND (NOT b));
END logica;

ARCHITECTURE logicaretard OF xor2 IS
BEGIN
z <= ((NOT a) AND b) OR (a AND (NOT b)) AFTER 3 ns;
END logicaretard;
