package exercicios.aula24.exercicio2;

import java.util.Date;

public class Livro {

	private String titulo;
	private String nomeAutor;
	private double preco;
	private Date publicacao;
	
	public Livro(String titulo, String nomeAutor, double preco, Date publicacao) {
		this.titulo = titulo;
		this.nomeAutor = nomeAutor;
		this.preco = preco;
		this.publicacao = publicacao;
	}
}
