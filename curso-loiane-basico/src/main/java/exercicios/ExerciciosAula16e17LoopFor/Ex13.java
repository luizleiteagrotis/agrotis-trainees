package exercicios.ExerciciosAula16e17LoopFor;

import java.util.Scanner;

public class Ex13 {

	public static void main(String[] args) {
        
		Scanner scan = new Scanner(System.in);
		
        System.out.println("Digite a base: ");
        int base = scan.nextInt();
        System.out.println("Digite o expoente: ");
        int expoente = scan.nextInt();
        int calculo = base;
        
        for (int i=1; i<expoente; i++){
            calculo *= base;
        } 
        System.out.println(calculo);
    }
}