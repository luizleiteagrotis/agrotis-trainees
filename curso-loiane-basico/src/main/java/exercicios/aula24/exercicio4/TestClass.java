package exercicios.aula24.exercicio4;

public class TestClass {

	public static void main(String[] args) {

		LivroDeBiblioteca livro = new LivroDeBiblioteca();

		livro.setTitulo("O Mundo de sofia");
		livro.setAutor("Jostein Garder");
		livro.setEditora("Mundo Livre");
		livro.setLeitor(new Leitor("Anderson", "1234567"));

		livro.realizaEmprestimoDeLivro(livro, livro.getLeitor());

	}

}
