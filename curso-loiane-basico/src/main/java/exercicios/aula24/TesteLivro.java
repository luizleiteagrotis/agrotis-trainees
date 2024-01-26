package exercicios.aula24;

public class TesteLivro {

	public static void main(String[] args) {
	
		
        Livro especifico = new Livro ();
		
		especifico.nomeDoLivro = "A ascensao do dinheiro: A historia financeira do mundo - 3 edicao";
		especifico.autorDoLivro = "Fergunson, Niall";
		especifico.anoDaEdicao = 2020;
		especifico.quantidadePaginas = 512;
		especifico.editoraDoLivro = "Editora Planeta do Brasil ltda";
		especifico.idiomaDoLivro= "Portugues";
		especifico.isbn = "978-65-5535-217-7";
		especifico.acabamentoDoLivro = "Livro brochura";
		
		System.out.println(especifico.nomeDoLivro);
		System.out.println(especifico.autorDoLivro);
		System.out.println(especifico.anoDaEdicao);
		System.out.println(especifico.quantidadePaginas);
		System.out.println(especifico.editoraDoLivro);
		System.out.println(especifico.idiomaDoLivro);
		System.out.println(especifico.isbn);
		System.out.println(especifico.acabamentoDoLivro);
	}

}
