//SumaDosEnters.java
import java.util.*;
public class SumaDosEnters{
	public static void main (String[] args){
		Scanner entrada;
		int x, y, suma;
		entrada = new Scanner (System.in);
		System.out.println("Introdueix un nombre enter: ");
		x = entrada.nextInt();
		System.out.println("Introdueix un nombre enter: ");
		y = entrada.nextInt();
		suma = x + y;
		System.out.println("La suma és: " +  suma);
		}
	}
