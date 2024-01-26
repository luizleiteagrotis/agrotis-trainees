package exercicios.aula47a52.exercicio1;

import java.util.Arrays;

public class Agenda {

	private Contato[] contatos;

	public Agenda() {
		contatos = new Contato[5];
	}

	@Override
	public String toString() {
		return "Agenda [contatos=" + Arrays.toString(contatos) + "]";
	}

	public void adicionarContato(Contato contato) throws AgendaCheiaException {
		boolean cheia = true;
		for (int i = 0; i < contatos.length; i++) {
			if (contatos[i] == null) {
				contatos[i] = contato;
				cheia = false;
				break;
			}
		}
		if (cheia) {
			throw new AgendaCheiaException();
		}
	}

	public void consultarContato(int id) throws ContatoNaoExisteException {
		boolean contatoEncontrado = false;
		for (int i = 0; i < contatos.length; i++) {
			if (contatos[i] != null) {
				if (contatos[i].getIdentificador() == id) {
					System.out.println(contatos[i]);
					contatoEncontrado = true;
				}
			}
		}
		if (!contatoEncontrado ) {
			throw new ContatoNaoExisteException();
			
		}
	}

}
