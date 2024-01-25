package exercicios.aula24;

import java.util.Scanner;

public class LivroDeLivraria2 {

	public static void main(String[] args) {
		
	Scanner sc = new Scanner(System.in);
	LivroDeLivraria livroLivraria = new LivroDeLivraria();
	
	livroLivraria.nomeLivro = "Vademecum";
	livroLivraria.anoDeLancamento = 2021;
	livroLivraria.autor = "Saraiva Jur";
	livroLivraria.genero = "Juridico";
	livroLivraria.preco = 129.90;
	System.out.println("O livro é de capa dura? Digite 1 para SIM, e 2 para NÃO:");
	livroLivraria.simOuNao = sc.nextInt();
	
	if (livroLivraria.simOuNao == 1){
		double percentual = 0.15;
		System.out.println("Livros com capa dura tem acréscimo de 15% no valor.");
		System.out.println();
		double valorFinal = (percentual * livroLivraria.preco) + livroLivraria.preco;
		livroLivraria.capaDura = true;
		System.out.printf("Valor: " + "%.2f%n", valorFinal);
	} else {
		livroLivraria.simOuNao = 2;
		livroLivraria.capaDura = false;
		System.out.println("Valor: " + livroLivraria.preco);
	}

	
	
	System.out.println("Nome: " + livroLivraria.nomeLivro);
	System.out.println("Ano: " + livroLivraria.anoDeLancamento);
	System.out.println("Autor: " + livroLivraria.autor);
	System.out.println("Genero: " + livroLivraria.genero);

	
		
	}
	
}