package exercicios.aula47a52.exercicio1;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws AgendaCheiaException, ContatoNaoExisteException {

		Scanner scan = new Scanner(System.in);
		Agenda agenda = new Agenda();

		int opcao = 0;

		while (opcao != 3) {

			opcao = obterOpcaoMenu(scan);

			if (opcao == 1) {

				consultarContato(scan, agenda);

			} else if (opcao == 2) {

				adicionarContato(scan, agenda);

			}

		}

	}

	public static void adicionarContato(Scanner scan, Agenda agenda) {
		try {
			Contato contato = new Contato();
			System.out.println("Digite o nome e telefone do contato que deseja adicionar: ");
			contato.setNome(scan.next());
			contato.setTelefone(scan.next());
			agenda.adicionarContato(contato);
			System.out.println("Contato criado: ");
			System.out.println(contato);
		} catch (AgendaCheiaException e) {
			System.out.println(e.getMessage());
			System.out.println("Contatos da agenda");
			System.out.println(agenda);
		}

	}

	public static void consultarContato(Scanner scan, Agenda agenda) {
		System.out.println("Qual o identificador do contato que deseja consultar ??");
		try {
			agenda.consultarContato(scan.nextInt());
		} catch (InputMismatchException e1) {
			System.out.println("Você deve informar o número do identificador do seu contato!");
			scan.next();
		} catch (ContatoNaoExisteException e) {
			System.out.println(e.getMessage());
		}

	}

	public static int obterOpcaoMenu(Scanner scan) {
		int opcao = 0;
		boolean entradaValida = false;

		while (!entradaValida) {
			System.out.println(
					"Digite 1 para consultar um contato da agenda, 2 para adicionar um contato da agenda ou 3 para sair: ");
			try {
				String entrada = scan.next();
				opcao = Integer.parseInt(entrada);

				if (opcao >= 1 && opcao <= 3) {
					entradaValida = true;
				} else {
					throw new Exception("Entrada inválida");
				}
			} catch (Exception e) {
				System.out.println("Entrada inválida, digite novamente");
			}
		}
		return opcao;

	}
}
