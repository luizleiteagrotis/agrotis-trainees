
package exercicios.aulas14e15;

import java.util.Scanner;

class Exercicio25{
	public static void main (String args[]) {
		Scanner scan = new Scanner(System.in);
		
		int count = 0;
		String resposta = "";
		String[] perguntas = {"Telefonou para a vítima?", "Esteve no local do crime?", "Mora perto da vítima?", "Devia para a vítima?", "Já trabalhou com a vítima?"};
		
		for(int i = 0; i < perguntas.length; i++) {
			
			System.out.println(perguntas[i]);
			resposta = scan.next().toLowerCase();
			
			if(resposta.equals("sim")) {
				count ++;
			}
		}
		
		switch(count) {
		case 2: System.out.println("Suspeita"); break;
		case 3: System.out.println("Cúmplice");	break;
		case 4: System.out.println("Cúmplice");	break;
		case 5: System.out.println("Assassino"); break;
		default: System.out.println("Inocente"); break;
		}
	}
}
