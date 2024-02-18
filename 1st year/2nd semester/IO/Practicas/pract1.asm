array:     .DW 4,3,2,1
sum:       .RW 1
.BEGIN ini
ini:       sub R1,R1,R1
           add R0,R0,R2
loop:      subi R1,#4,R0
           beq endloop
           load array(R1),R3
           add R3,R2,R2
           addi R1,#1,R1
           br loop
endloop:   store R2,sum(R0)
.END
