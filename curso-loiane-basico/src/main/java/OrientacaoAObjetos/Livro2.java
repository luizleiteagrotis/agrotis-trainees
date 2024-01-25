package OrientacaoAObjetos;

public class Livro2 {
	
	public static void main(String[] args) {
	
	Livro livro = new Livro();
	livro.nomeLivro = "Vademecum";
	livro.anoDeLancamento = 2021;
	livro.autor = "Nome NOme NOME";
	livro.genero = "Juridico";
	
	System.out.println("Nome: " + livro.nomeLivro);
	System.out.println("Ano: " + livro.anoDeLancamento);
	System.out.println("Autor: " + livro.autor);
	System.out.println("Genero: " + livro.genero);
		
	}
}