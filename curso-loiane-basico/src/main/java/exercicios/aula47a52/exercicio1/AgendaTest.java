package exercicios.aula47a52.exercicio1;

import java.util.Scanner;

public class AgendaTest {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		Agenda agenda = new AgendaImpl();

		int operacao = programaInicial(sc);

		if (operacao == 1) {
			System.out.println("Informe o ID do contato a ser buscado.");
			Long id = sc.nextLong();
			agenda.consultarContato(id);
		} else if (operacao == 2) {
			System.out.println("Informe o nome do contato: ");
			sc.nextLine();
			String nome = sc.nextLine();
			System.out.println("Informe o telefone: ");
			String telefone = sc.nextLine();
			Contato contato = new Contato(nome, telefone);

			agenda.adicionarContato(contato);
			System.out.println("Adicionando o contato na agenda: " + contato);

			System.out.println("Deseja cadastrar mais um contato? (s/n)");
			char respostaContinuacao = sc.next().charAt(0);

			while (respostaContinuacao == 's' || respostaContinuacao == 'S') {
				System.out.println("Informe o nome do contato: ");
				sc.nextLine(); // Consumir a quebra de linha pendente
				nome = sc.nextLine();
				System.out.println("Informe o telefone: ");
				telefone = sc.nextLine();
				contato = new Contato(nome, telefone);
				agenda.adicionarContato(contato);
				System.out.println("Adicionando o contato na agenda: " + contato);

				System.out.println("Deseja cadastrar mais um contato? (s/n)");
				respostaContinuacao = sc.next().charAt(0);
			}

			if (respostaContinuacao == 'n' || respostaContinuacao == 'N') {
				System.out.println("Deseja consultar um contato? (s/n)");
				char respostaConsulta = sc.next().charAt(0);
				if (respostaConsulta == 's' || respostaConsulta == 'S') {
					System.out.println("Informe o ID do contato a ser buscado.");
					Long id = sc.nextLong();
					agenda.consultarContato(id);
				}
			}
		}

		sc.close();
	}

	private static int programaInicial(Scanner sc) {
		System.out.println("Agenda");
		System.out.println("Você tem a opção de cadastrar um novo contato.");
		System.out.println("Ou fazer a busca por um contato existente.");
		System.out.println("Digite 1 para Consultar um contato");
		System.out.println("Digite 2 para Cadastrar um novo contato");
		return sc.nextInt();
	}
}
