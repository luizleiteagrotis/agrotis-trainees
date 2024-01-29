package exercicios.aula47a52;

import exercicios.aula47a52.Agenda.AgendaCheiaException;
import exercicios.aula47a52.Agenda.ContatoNaoExisteException;

public class Test {

	public static void main(String[] args) {
		Agenda agenda = new Agenda();

	    // Exemplo de uso
	    try {
	        Contato contato1 = new Contato("Vitor", "41999933314");
	        Contato contato2 = new Contato("Joana", "41999933315");

	        agenda.adicionarContato(contato1);
	        agenda.adicionarContato(contato2);

	        int opcao = 1; // Defina a opção conforme o menu
	        switch (opcao) {
	            case 1:
	                int identificador = 1; // Defina o identificador a ser consultado
	                Contato resultado = agenda.consultarContato(identificador);
	                System.out.println("Contato encontrado: " + resultado.getNome() + ", " + resultado.getTelefone());
	                break;
	            case 2:
	                Contato novoContato = new Contato("Carol", "48945663541");
	                agenda.adicionarContato(novoContato);
	                System.out.println("Contato adicionado com sucesso!");
	                break;
	            default:
	                System.out.println("Opção inválida");
	        }
	    } catch (AgendaCheiaException | ContatoNaoExisteException e) {
	        e.printStackTrace();
	    }
	}
}
