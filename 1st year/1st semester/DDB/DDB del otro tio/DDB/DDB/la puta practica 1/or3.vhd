ENTITY or3 IS
 PORT (a, b, c: IN BIT; z: OUT BIT);
 END or3;
 
ARCHITECTURE logica OF or3 IS
BEGIN
z <= a OR b OR c;
END logica;

ARCHITECTURE logicaretard OF or3 IS
BEGIN
z <= a OR b OR c AFTER 3 ns;
END logicaretard;