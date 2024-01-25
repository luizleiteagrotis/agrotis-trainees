package exercicios.aula24;

import java.util.Scanner;

public class LivroDeBiblioteca2 {
	
	public static void main(String[] args) {
		
	Scanner sc = new Scanner(System.in);
	
	LivroDeBiblioteca livroBiblioteca = new LivroDeBiblioteca();
	livroBiblioteca.nomeLivro = "Vademecum";
	livroBiblioteca.anoDeLancamento = 2021;
	livroBiblioteca.autor = "Nome NOme NOME";
	livroBiblioteca.genero = "Juridico";
	System.out.println("Quantas semanas você deseja ficar com o livro: ");
	livroBiblioteca.periodoEmSemanas = sc.nextInt();
	livroBiblioteca.valorSemana = 5.50;
	
	System.out.println("Nome: " + livroBiblioteca.nomeLivro);
	System.out.println("Ano: " + livroBiblioteca.anoDeLancamento);
	System.out.println("Autor: " + livroBiblioteca.autor);
	System.out.println("Genero: " + livroBiblioteca.genero);
	System.out.printf("Valor a pagar: R$" + "%.2f%n", livroBiblioteca.periodoEmSemanas * livroBiblioteca.valorSemana);
		
	}
	
}