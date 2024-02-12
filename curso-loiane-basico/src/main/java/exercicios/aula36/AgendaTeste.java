package exercicios.aula36;

import java.util.Scanner;

public class AgendaTeste {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
		Contato[] contatos = new Contato[3];
		
		System.out.println("Nome da agenda: ");
		String nome = scan.next();
		
		
		
		for (int i = 0; i < contatos.length; i++) {
			contatos[i] = new Contato();
			System.out.println("Contato " + i + ": ");
			System.out.println("Nome: ");
			contatos[i].setNome(scan.next());
			System.out.println("Email: ");
			contatos[i].setEmail(scan.next());
			System.out.println("Telefone: ");
			contatos[i].setTelefone(scan.nextInt());
		}
		
		Agenda agenda = new Agenda(nome, contatos);
		
		System.out.println("Contatos na agenda " + agenda.getNome() + ": ");
		agenda.listContatos();
		System.out.println("Detalhes de cada contato: ");
		agenda.listDetalhesContatos();
		scan.close();
	}

}
