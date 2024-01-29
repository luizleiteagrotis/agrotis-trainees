package exercicios.aula47a52;

import java.util.ArrayList;
import java.util.List;

public class Agenda {

	private static final int CAPACIDADE_MAXIMA = 10;
    private List<Contato> contatos = new ArrayList<>();

    public void adicionarContato(Contato contato) throws AgendaCheiaException {
        if (contatos.size() >= CAPACIDADE_MAXIMA) {
            throw new AgendaCheiaException("A agenda está cheia. Não é possível adicionar mais contatos.");
        }
        contatos.add(contato);
    }

    public Contato consultarContato(int identificador) throws ContatoNaoExisteException {
        for (Contato contato : contatos) {
            if (contato.getIdentificador() == identificador) {
                return contato;
            }
        }
        throw new ContatoNaoExisteException("Contato com identificador " + identificador + " não encontrado na agenda.");
    }
    class AgendaCheiaException extends Exception {
        private static final long serialVersionUID = 1L;

		public AgendaCheiaException(String message) {
            super(message);
        }
    }

    class ContatoNaoExisteException extends Exception {
        private static final long serialVersionUID = 1L;

		public ContatoNaoExisteException(String message) {
            super(message);
        }
    }

}