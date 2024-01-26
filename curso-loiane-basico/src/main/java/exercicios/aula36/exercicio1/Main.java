package exercicios.aula36.exercicio1;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		Scanner scan = new Scanner(System.in);

		System.out.println("Diga a qtn Contatos: ");
		Contato[] contatos = new Contato[scan.nextInt()];
		scan.nextLine();
		Agenda agenda = new Agenda();

		System.out.println("Entre com o nome da agenda: ");
		agenda.setNome(scan.next());
		
		scan.nextLine();

		for (int i = 0; i < 3; i++) {
			Contato contato = new Contato();
			System.out.println("Entre com o nome, telefone e email do contato: " + (i + 1));
			contato.setNome( scan.nextLine() );
			contato.setTelefone(scan.nextLine());
			contato.setEmail(scan.nextLine());

			contatos[i] = contato;
		}
		
		agenda.setContatos(contatos);
		
		System.out.println(agenda);
	}
}
