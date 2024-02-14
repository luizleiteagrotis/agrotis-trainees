package exercicios.aula47a52;

import java.util.Scanner;

public class Teste {

	public static void main(String[] args){
		Scanner scan = new Scanner(System.in);
		
		Agenda agenda = new Agenda();
		
		while (true) {
			System.out.println("1 - Consultar um contato da Agenda ");
			System.out.println("2 - Adicionar um contato na Agenda ");
			int opt;
			try {
				opt = scan.nextInt();
				switch (opt) {
				case 1: {
					System.out.println("Insira o nome do contato:");
					String nomeBusca = scan.next();
					try {
						Contato contatoBusca = agenda.consultarContatos(nomeBusca);
						if (contatoBusca == null) {
							throw new ContatoNaoExisteException(nomeBusca);
						}
						contatoBusca.toString();
					} catch (Exception e) {
						System.out.println("Agenda vazia!");
					}

					break;
				}
				case 2: {
					Contato newContato = new Contato();
					System.out.println("Insira os dados do contato");
					System.out.print("Nome: ");
					newContato.setNome(scan.next());
					System.out.print("Telefone: ");
					newContato.setTelefone(scan.next());
					try {
						agenda.adicionarContato(newContato);
						if(agenda.getContatos()[agenda.getContatos().length - 1 ] != null) {
							throw new AgendaCheiaException();
						}
						System.out.println("Contato registrado com sucesso");
					} catch (Exception e) {
						System.out.println(e);
					}
					
					break;
				}
				default:
					System.out.println("Opção inválida");
					break;
				}
			} catch (Exception e) {
				System.out.println("Opção inválida");
				scan.next();
			}
			
		}
	}

}
