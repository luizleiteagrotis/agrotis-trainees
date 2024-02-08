
package exercicios.aula20;

import java.util.Scanner;

class Exercicio5{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		int[][][] A = new int[12][30][8];
		
		
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
				System.out.println("Insira o mês que deseja marcar o compromisso:");
				do {
					indiceMes = scan.nextInt() - 1;
					if(indiceMes < 0 || indiceMes > 12) {
						System.out.println("Mês inválido! Tente Novamente: ");
						indiceMes = -1;
					}
				} while (indiceMes < 0);

				System.out.println("Insira o dia (1 a 30): ");
				do {
					indiceDia = scan.nextInt() - 1;
					if (indiceDia < 0 || indiceDia > 30) {
						System.out.println("Dia inválido! Tente Novamente: ");
						indiceDia = -1;
					}

				} while (indiceDia < 0);

				System.out.println("Horário: ");

				do {

					indiceHora = scan.nextInt() - 1;
					if (indiceHora < 0 || indiceHora > 8) {
						System.out.println("Hora inválida! Tente Novamente: ");
						indiceHora = -1;
					}

				} while (indiceHora < 0);

				A[indiceMes][indiceDia][indiceHora] = 1;
				System.out.println("Compromisso Agendado com sucesso!");
			} else if (option == 'A') {
				System.out.println("Insira o mês, dia e horário que deseja consultar o compromisso:");
				
				month = scan.nextInt();
				day = scan.nextInt();
				hour = scan.nextInt();
				
				if (A[month-1][day-1][hour-1] == 1) {
					System.out.println(
							"Você tem um compromisso agendado para:" + (day > 10 ? " " + day : " 0" + day) + "/" + ((month) > 10 ? (month) : "0" + (month))  + " às " + hour + ":00");
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
