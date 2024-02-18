ENTITY inversor1 IS
PORT (a : IN BIT; f: OUT BIT);
END inversor1;




ARCHITECTURE logica OF inversor1 IS
BEGIN
f <= not a;
END logica;

