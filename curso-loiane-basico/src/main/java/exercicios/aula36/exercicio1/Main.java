package exercicios.aula36.exercicio1;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
	
	private static List<Agenda> agendas = new ArrayList<>();
	
	public static void main(String[] args) {
		adicionarAgendas();
		
		String nomeAgenda = pegarNomeAgenda();
		
		Agenda agenda = procurarAgenda(nomeAgenda);
		List<Contato> contatos = agenda.getContatos();
		
		System.out.println("Agenda: " + agenda.getNome());
		contatos.forEach((contato) -> {
			System.out.println(contato);
		});
	}
	
	private static void adicionarAgendas() {
		for (int i = 0; i < 3; i++) {
			Agenda agenda = new Agenda("agenda" + i);
			for (int j = 0; j < 3; j++) {
				agenda.adicionar(new Contato("contato" + j, "telefone" + j, "email" + j));
			}
			agendas.add(agenda);
		}
	}

	private static String pegarNomeAgenda() {
		Scanner scanner = new Scanner(System.in);
		System.out.print("Digite o nome da agenda: ");
		String nomeAgenda = scanner.next() + scanner.nextLine();
		return nomeAgenda;
	}
	
	private static Agenda procurarAgenda(String nomeAgenda) {
		for (int i = 0; i < agendas.size(); i++) {
			Agenda atual = agendas.get(i);
			if (atual.getNome().equals(nomeAgenda)) {
				return atual;
			}
		}
		return new Agenda("Nenhuma");
	}
}
