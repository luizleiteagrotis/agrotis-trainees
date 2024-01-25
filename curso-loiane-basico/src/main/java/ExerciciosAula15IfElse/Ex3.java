package ExerciciosAula15IfElse;

import java.util.Scanner;

public class Ex3 {

	public static void main(String[] args) {
			
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Digite a letra F ou M referente ao sexo: ");
		String letraSexo = scan.next();
		
		//tentativas // precisa usar '.equalsIgnoreCase("")' junto com a variável
		/// pois é assim que faz a comparação de strings
		/*if (letraSexo == "f"){
			System.out.println("F - Feminino");
		} else if (letraSexo == "m"){ 
			System.out.println("M - Masculino");
		} else if (letraSexo != "m" && "f") {
			System.out.println("Sexo Inválido");
		}*/
		
		if (letraSexo.equalsIgnoreCase("f")){
			System.out.println("F - Feminino");
		} else if (letraSexo.equalsIgnoreCase("m")){ 
			System.out.println("M - Masculino");
		} else {
			System.out.println("Sexo Inválido");
		}
	}
}