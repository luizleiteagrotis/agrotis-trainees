package exercicios.ExerciciosAula16e17LoopFor;

import java.util.Scanner;

public class Ex12 {

	public static void main(String[] args) {
        
		Scanner scan = new Scanner(System.in);
		
        System.out.println("Entre com o número que deseja saber a tabuada: ");
        int numeroTabuada = scan.nextInt();
        
        for (int i=1; i<=10; i++){
             int tabuada = numeroTabuada * i;
             System.out.println(numeroTabuada + " x " + i + " = " + tabuada);
        }        
	}
}