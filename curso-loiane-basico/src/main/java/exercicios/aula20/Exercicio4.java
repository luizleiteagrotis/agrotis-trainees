
package exercicios.aula20;

import java.util.Scanner;

class Exercicio4{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		int[][] A = new int[30][24];
		
		
		int indiceMes = -1;
		int indiceDia = -1;
		int indiceHora = -1;
		
		int month = -1;
		int day = -1;
		int hour = -1;
		
		while (true) {
			System.out.print("Bem vindo! Indique se deseja marcar um compromisso (M) ou consultar sua agenda (A):");
			char option = scan.next().toUpperCase().charAt(0);
			if (option == 'M') {

				System.out.println("Insira o dia (1 a 30): ");
				do {
					indiceDia = scan.nextInt();
					if (indiceDia <= 0 || indiceDia > 30) {
						System.out.println("Dia inválido! Tente Novamente: ");
						indiceDia = -1;
					}

				} while (indiceDia <= 0);

				System.out.println("Horário: ");

				do {

					indiceHora = scan.nextInt();
					if (indiceHora <= 0 || indiceHora > 24) {
						System.out.println("Hora inválida! Tente Novamente: ");
						indiceHora = -1;
					}

				} while (indiceHora <= 0);

				A[indiceDia][indiceHora] = 1;
				System.out.println("Compromisso Agendado com sucesso!");
			} else if (option == 'A') {
				System.out.println("Insira o dia e horário que deseja consultar o compromisso:");
				
				day = scan.nextInt();
				hour = scan.nextInt();
				
				if (A[day][hour] == 1) {
					System.out.println(
							"Você de fato tem um compromisso agendado para:" + (day > 10 ? " " + day : " 0" + day) + " às " + hour + ":00");
				} else {
					System.out.println("Você não tem nenhum compromisso agendado para esse dia.");
				}

			} else {
				System.out.println("Opção inválida!");
				break;
			}
		}
		
		
		
	}
}
