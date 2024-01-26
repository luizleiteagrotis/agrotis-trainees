package exercicios.aula24;

import java.util.Date;

public class TesteLivroDeBiblioteca {

	public static void main(String[] args) {
		

        LivroDeBiblioteca especifico = new LivroDeBiblioteca ();
		
		especifico.nomeDoLivro = "A ascensao do dinheiro: A historia financeira do mundo - 3 edicao";
		especifico.autorDoLivro = "Fergunson, Niall";
		especifico.anoDaEdicao = 2020;
		especifico.quantidadePaginas = 512;
		especifico.editoraDoLivro = "Editora Planeta do Brasil ltda";
		especifico.idiomaDoLivro= "Portugues";
		especifico.isbn = "978-65-5535-217-7";
		especifico.acabamentoDoLivro = "Livro brochura";
		
		especifico.emprestado = true;
		especifico.dataEntrega = newDate();
		especifico.nomeDeQuemEmprestou = "WIllian Rafael Silva";
		
		System.out.println("Nome do livro: " + especifico.nomeDoLivro);
		System.out.println("Autor do livro: " + especifico.autorDoLivro);
		System.out.println("Ano da edicao do livro: " + especifico.anoDaEdicao);
		System.out.println("Quantidade de paginas " + especifico.quantidadePaginas);
		System.out.println("Editora do livro: " + especifico.editoraDoLivro);
		System.out.println("Idioma do Livro: " + especifico.idiomaDoLivro);
		System.out.println("ISBN do Livro: " + especifico.isbn);
		System.out.println("Tipo de acabamento do livro: " + especifico.acabamentoDoLivro);
		System.out.println("Nome de quem emprestou o Livro: " + especifico.nomeDeQuemEmprestou);
	
	}

	private static Date newDate() {
		// TODO Auto-generated method stub
		return null;
	}
}


