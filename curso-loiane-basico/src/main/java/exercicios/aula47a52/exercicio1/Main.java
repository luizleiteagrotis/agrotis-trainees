package exercicios.aula47a52.exercicio1;

import java.util.Scanner;

public class Main {
	
	private static Agenda agenda;
	private static Scanner scanner;
	
	public static void main(String[] args) {
		scanner = new Scanner(System.in);
		
		int tamanhoAgenda = 2;
		agenda = new Agenda(tamanhoAgenda);
		
		while (true) {
			System.out.println("------------------");
			System.out.println("MENU");
			System.out.println("------------------");
			System.out.println("1 - Consultar um contato");
			System.out.println("2 - Adicionar um contato");
			System.out.println("3 - Sair");
			System.out.println("------------------");
			System.out.print("Escolha uma opcao: ");
			int opcao = scanner.nextInt();
			System.out.println();
			switch (opcao) {
				case 1:
					consultar();
					break;
				case 2:
					adicionar();
					break;
				case 3:
					System.exit(0);
				default:
					System.out.println("Opcao invalida!");
			}
			System.out.println();
		}
	}
	
	private static void consultar() {
		System.out.println("------------------");
		System.out.println("CONSULTAR");
		System.out.println("------------------");
		System.out.print(agenda);
		System.out.println("------------------");
		System.out.print("Informe o id do contato: ");
		long idContato = scanner.nextLong();
		try {
			Contato contato = agenda.consultar(idContato);
			System.out.println(contato);
		} catch (ContantoNaoExisteException e) {
			System.out.println(e.getMessage());
		}
	}
	
	private static void adicionar() {
		System.out.println("------------------");
		System.out.println("ADICIONAR");
		System.out.println("------------------");
		System.out.print(agenda);
		System.out.println("------------------");
		System.out.print("Informe o nome: ");
		String nomeContato = scanner.next() + scanner.nextLine();
		System.out.print("Informe o telefone: ");
		String telefoneContato = scanner.next() + scanner.nextLine();
		try {
			Contato contato = new Contato(nomeContato, telefoneContato);
			agenda.adicionar(contato);
		} catch (AgendaCheiaException e) {
			System.out.println(e.getMessage());
		}
	}
}
