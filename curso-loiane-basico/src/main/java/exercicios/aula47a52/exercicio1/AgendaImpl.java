package exercicios.aula47a52.exercicio1;

import java.util.HashMap;
import java.util.Map;

import exercicios.aula47a52.exercicio1.exceptions.AgendaCheiaException;
import exercicios.aula47a52.exercicio1.exceptions.ContatoNaoExisteException;

public class AgendaImpl implements Agenda {

	private static final int NUMERO_MAXIMO_DE_CONTATOS = 3;
	private Map<Long, Contato> contatos = new HashMap<Long, Contato>();

	public void adicionarContato(Contato contato) {
		if (contatos.size() > NUMERO_MAXIMO_DE_CONTATOS) {
			throw new AgendaCheiaException("Agenda cheia.");
		}
		contatos.put(contato.getId(), contato);

	}

	public void consultarContato(Long id) {
		if (contatos.get(id) == null) {
			throw new ContatoNaoExisteException("Id não encontrado.");
		}
		System.out.println(contatos.get(id));
	}
}
