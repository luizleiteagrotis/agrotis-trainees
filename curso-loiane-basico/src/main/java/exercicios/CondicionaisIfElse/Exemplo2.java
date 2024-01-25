package exercicios.CondicionaisIfElse;

import java.util.Scanner;

public class Exemplo2 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Entre com a sua idade: ");
		int idade =  scan.nextInt();
		
		if (idade >= 18) {
			System.out.println("É maior de idade.");	
		} else {
			System.out.println("Não é maior de idade.");
		}
		
		//barato <= 10
		//10 < valor < 15 - desconto
		//15 <= valor < 17 - pesquisar mais
		//>= 17 - muito caro
		
		System.out.println("Entre com o preço do item: ");
		double valor = scan.nextDouble();
		
		if (valor <= 10){
			System.out.println("Está barato, pode comprar!");
		} else if (valor < 10 && valor < 15){
			System.out.println("Você pode pedir um desconto.");
		} else if (valor >=15 && valor < 17){
			System.out.println("Pode pesquisar mais.");
		} else if (valor >= 18){		
			System.out.println("Não compre, muito caro.");
		}
	}
}