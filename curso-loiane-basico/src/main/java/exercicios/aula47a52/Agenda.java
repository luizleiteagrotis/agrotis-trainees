package atividades47_52;

public class Agenda {

    private Contato[] contatos;

    public Agenda() {
        contatos = new Contato[5];
    }

    public void adicionarContato(Contato c) throws AgendaCheiaException {

        boolean cheia = true;
        for (int i = 0; i < contatos.length; i++) {
            if (contatos[i] == null) {
                contatos[i] = c;
                cheia = false;
                break;
            }
        }

        if (cheia) {
            throw new AgendaCheiaException();
        }

    }

    public int consultarContatoNome(String nome) throws ContatoNaoExisteException {
        for (int i = 0; i < contatos.length; i++) {
            if (contatos[i] != null) {
                if (contatos[i].getNome().equalsIgnoreCase(nome)) {
                    return i;
                }
            }

        }

        throw new ContatoNaoExisteException(nome);

    }

    @Override
    public String toString() {

        String contatoConcat = "";

        for (Contato contato : contatos) {
            if (contato != null) {
                contatoConcat += contato.toString() + "\n";
            }
        }

        return contatoConcat;
    }

}
