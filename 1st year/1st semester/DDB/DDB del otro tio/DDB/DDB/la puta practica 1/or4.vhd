ENTITY or4 IS
PORT (a, b, c, d: IN BIT; z: OUT BIT);
END or4;

ARCHITECTURE logica OF or4 IS
BEGIN
z <= a OR b OR c OR d;
END logica;

ARCHITECTURE logicaretard OF or4 IS
BEGIN
z <= a OR b OR c OR d AFTER 3 ns;
END logicaretard;