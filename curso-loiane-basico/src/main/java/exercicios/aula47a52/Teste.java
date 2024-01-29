package exercicios.aula47a52;

import java.util.Scanner;

public class Teste {

	public static void main(String[] args) {
		Scanner scan = new Scanner (System.in);
		Agenda agenda = new Agenda();
		
		int menuOpcoes = 1;
		while (menuOpcoes != 3) {
			menuOpcoes = menu(scan);
			
			if(menuOpcoes == 1) {
				consultarContato(scan, agenda);
						
			}else if (menuOpcoes == 2) {
				adicionarContato(scan, agenda);
			}else if (menuOpcoes == 3) {
				System.exit(0);
			}
		}
		
		
	}
	
	public static void adicionarContato(Scanner scan, Agenda agenda) {
	try {
		System.out.println("Insira os dados do contato a ser criado: ");
		String nome = leituraString(scan, "Insira o nome: ");
		String telefone = leituraString(scan, "Insira o telefone: ");
		String email = leituraString(scan, "Insira o email: ");
		
		Contato contato = new Contato();
		contato.setNome(nome);
		contato.setEmail(email);
		contato.setTelefone(telefone);
		
		System.out.println("Contato Criado com sucesso! ");
		System.out.println(contato);
		
		 agenda.addContato(contato);
		} catch (AgendaCheiaException e){
			System.out.print(e.getMessage());
			
			System.out.println("Contatos da agenda");
			System.out.println(agenda);
			
	}	
	}	
	
	public static void consultarContato(Scanner scan, Agenda agenda) {
		String nomeContato = leituraString(scan, "Insira o nome a ser pesquisado");
		try {
			if (agenda.consultarContato(nomeContato) >= 0) {
				System.out.println("Contato Existe.");
				
				
			}
		} catch (ContatoNaoExisteException e) {
			System.out.println(e.getMessage());
			
		}
		
	}
	
	public static String leituraString (Scanner scan, String leitura) {
		System.out.println(leitura);
		String menu = scan.nextLine();
		return menu;
	}
	
	public static int menu(Scanner scan) {
		
		boolean inseriuCerto = false;
		int menuOpcoes = 3;
		
		while(!inseriuCerto) {
			System.out.println("Bem vindo ao menu! Escolha uma opcao: ");
			System.out.println("Opcao 1: Consulta Contato: ");
			System.out.println("Opcao 2: Adicionar Contato: ");
			System.out.println("Opcao 3: --Sair do menu-- ");
			
			try {
			 String insira = scan.nextLine();
			 menuOpcoes = Integer.parseInt(insira);
			 
			if (menuOpcoes == 1 || menuOpcoes == 2 || menuOpcoes == 3) {
				inseriuCerto = true;
			} else {
				throw new Exception ("Nao permitido");
			}
		}
			 catch (Exception e){
				System.out.println("Nao permitido. Insira novamente.");
			}
		}
		return menuOpcoes;
	}
}

