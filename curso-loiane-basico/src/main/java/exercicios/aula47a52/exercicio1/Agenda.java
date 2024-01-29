package exercicios.aula47a52.exercicio1;

import java.util.Arrays;

import exercicios.aula47a52.exercicio1.execoes.ContatoNaoExisteException;
import exercicios.aula47a52.exercicio1.execoes.ExcecaoAgendaCheia;

public class Agenda {
    private Contato[] contatos;

    Agenda() {
        this.contatos = new Contato[1];
    }

    public Contato[] getContatos() {
        return contatos;
    }

    public void setContatos(Contato[] contatos) {
        this.contatos = contatos;
    }

    @Override
    public String toString() {
        return "Agenda [contatos=" + Arrays.toString(contatos) + "]";
    }

    public void adicionarContato(Contato contato, int identificador) throws ExcecaoAgendaCheia {
        if (this.contatos.length < identificador + 1) {
            throw new ExcecaoAgendaCheia();
        } else {
            this.contatos[identificador] = contato;
        }
    };

    public void mostrarContatoAgenda(String identificador) throws ContatoNaoExisteException {
        for (int i = 0; i < this.contatos.length; i++) {
            if (this.contatos[i] != null) {
                if (identificador.equalsIgnoreCase(this.contatos[i].getNome())) {
                    System.out.println(this.contatos[i]);

                } else {
                    throw new ContatoNaoExisteException();
                }
            } else {
                System.out.println("Agenda vazia");
            }
        }

    }
}
