package exercicios.aula24.exercicio4;

import java.util.Date;

public class LivroDeBiblioteca {
	
	private String titulo;
	private String nomeAutor;
	private Date publicacao;
	private String nomeTitularEmprestimo;
	
	public LivroDeBiblioteca(String titulo, String nomeAutor, Date publicacao, String nomeTitularEmprestimo) {
		this.titulo = titulo;
		this.nomeAutor = nomeAutor;
		this.publicacao = publicacao;
		this.nomeTitularEmprestimo = nomeTitularEmprestimo;
	}
}
