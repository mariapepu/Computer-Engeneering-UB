ENTITY or2 IS
PORT (a,b: IN BIT; z: OUT BIT);
END or2;

ARCHITECTURE logica OF or2 IS 
BEGIN
z <= a OR b;
END logica;

ARCHITECTURE logicaretard OF or2 IS 
BEGIN
z <= a OR b AFTER 3 ns;
END logicaretard;