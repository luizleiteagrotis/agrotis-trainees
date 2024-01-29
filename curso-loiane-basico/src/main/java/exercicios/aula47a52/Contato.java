package exercicios.aula47a52;

public class Contato extends Agenda {
    
	private static int contador = 0;
    private int identificador;
    private String nome;
    private String telefone;

    public Contato(String nome, String telefone) {
        this.identificador = ++contador;
        this.nome = nome;
        this.telefone = telefone;
    }

    public int getIdentificador() {
        return identificador;
    }

    public String getNome() {
        return nome;
    }

    public String getTelefone() {
        return telefone;
    }
}