package exercicios.aula47a52.exercicio1;

import java.util.HashMap;
import java.util.Map;

import exercicios.aula47a52.exercicio1.exceptions.AgendaCheiaException;
import exercicios.aula47a52.exercicio1.exceptions.ContatoNaoExisteException;

public class AgendaImpl implements Agenda{

	private static final int NUMERO_MAXIMO_DE_CONTATOS = 3;
	private Map<Long, Contato> contatos = new HashMap<Long, Contato>();

	public void adicionarContato(Contato contato) {
		try {
			contatos.put(contato.getId(), contato);
			
		} catch (Exception e) {
			throw new AgendaCheiaException("Agenda cheia.");
		}

	}

	public void consultarContato(Long id) {
		try {
			System.out.println(contatos.get(id));
		} catch (Exception e) {
			throw new ContatoNaoExisteException("Id não encontrado.");

		}
	}
}
