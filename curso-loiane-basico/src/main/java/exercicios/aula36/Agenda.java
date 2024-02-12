package exercicios.aula36;


public class Agenda {
	
	private String nome;
	private Contato[] contatos;
	

	public Agenda(String nome, Contato[] contatos) {
		this.nome = nome;
		this.contatos = contatos;
	}
	
	public String getNome() {
		return nome;
	}
	
	public void setNome(String nome) {
		this.nome = nome;
	}
	
	public Contato[] getContatos() {
		return contatos;
	}
	
	public void setContatos(Contato[] contatos) {
		this.contatos = contatos;
	}
	
	public void listDetalhesContatos() {
		for(Contato contato : contatos ) {
			System.out.println(contato.toString());
		}
	}
	
	public void listContatos() {
		for(Contato contato : contatos) {
			System.out.println("Contato: " + contato.getNome());
		}
	}
}
