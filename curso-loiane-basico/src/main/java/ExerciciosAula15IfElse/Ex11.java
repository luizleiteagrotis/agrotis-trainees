package ExerciciosAula15IfElse;

import java.util.Scanner;

public class Ex11 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
			
		System.out.println("Digite o seu salário:");
	        double salario = scan.nextDouble();
	        
	        int percentual = 0;
	        if (salario <= 280){
	            percentual = 20;
	        } else if (salario > 280 && salario < 700){
	            percentual = 15;
	        } else if (salario >= 700 && salario < 1500){
	            percentual = 10;
	        } else if (salario >= 1500){
	            percentual = 5;
	        }
	        
	        double aumentoSalario = (salario / 100) * percentual;
	        double ajusteSalario = salario + aumentoSalario;
	        
	        System.out.println("Salário: " + salario);
	        System.out.println("Percentual: " + percentual);
	        System.out.println("Aumento Salário: " + aumentoSalario);
	        System.out.println("Ajuste Salário: " + ajusteSalario);
	}
}