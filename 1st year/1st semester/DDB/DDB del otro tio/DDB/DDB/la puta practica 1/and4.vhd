ENTITY and4 IS
PORT (a, b, c, d: IN BIT; z: OUT BIT);
END and4;

ARCHITECTURE logica OF and4 IS
BEGIN
z <= a AND b AND c AND d;
END logica;

ARCHITECTURE logicaretard OF and4 IS
BEGIN
z <= a AND b AND c AND d AFTER 3 ns;
END logicaretard;
