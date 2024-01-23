package exercicios.aula24.exercicio3;

import java.util.Date;

public class LivroDeLivraria {
	
	private String titulo;
	private String nomeAutor;
	private double preco;
	private Date publicacao;
	private String nomeLivraria;
	
	public LivroDeLivraria(String titulo, String nomeAutor, 
			double preco, Date publicacao, String nomeLivraria) {
		this.titulo = titulo;
		this.nomeAutor = nomeAutor;
		this.preco = preco;
		this.publicacao = publicacao;
	}
}
