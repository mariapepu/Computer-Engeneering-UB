#calculadora
def calculadora():
	i=0
	while i<1:
		s=str(input("Escriu una operacio"))
		x=s.strip('1234567890')
		if x=="+"
			x, y=s.split('+')
			a=int(x)
			b=int(y)
			print(a+b)
		elif x=="-"
			x, y=s.split('-')
			a=int(x)
			b=int(y)
			print(a-b)	
		elif x=="*"
			x, y=s.split('*')
			a=int(x)
			b=int(y)
			print(a*b)	
		elif x=="/"
			x, y=s.split('/')
			a=int(x)
			b=int(y)
			if b==0
				print("Syntax error")
			else
				print(a/b)
		else
		print ("opercaio incorrecte")
		
#Prou cerveses
def prouCerveses (numCerveses, esCapDeSetmana):
	if((esCapDeSetmana==False and (numCerveses>=50 and numCerveses<=100)) or (esCapDeSetmana==True and numCerveses>=50)):
		print ("Exit de festa")
	else
		print ("Fracas de festa")