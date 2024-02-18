 .dw 4,5 ; es un exemple

 .begin inici

suma:
ADD R1,R2,R3 
BR salt 

inici:

LOAD 0(R0), R1
LOAD 1(R0), R2
SUB R2,R1,R3 
BL suma

salt:

STORE R3, 34(R0)
.end