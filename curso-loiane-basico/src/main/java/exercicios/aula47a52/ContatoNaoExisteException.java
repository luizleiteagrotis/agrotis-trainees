package atividades47_52;

public class ContatoNaoExisteException extends Exception {

    private String nomeContato;

    public ContatoNaoExisteException(String nomeContato) {
        super();
        this.nomeContato = nomeContato;
    }

    @Override
    public String getMessage() {
        return "Contato " + nomeContato + " não cadastrado";
    }

}
