
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio43{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);

		Produto[] produtoArray = new Produto[5];
		
		produtoArray[0] = new Produto("Cachorro Quente", 100, 1.2);
		produtoArray[1] = new Produto("Bauru Simples", 101, 1.3);
		produtoArray[2] = new Produto("Bauru com ovo", 102, 1.5);
		produtoArray[3] = new Produto("Hambúrger", 103, 1.2);
		produtoArray[4] = new Produto("Cheeseburguer", 104, 1.3);
		
		System.out.println("Especificação \t Código  Preço");
		
		for(int i = 0; i < produtoArray.length; i++) {
			System.out.println(produtoArray[i].getNome() + "\t " + produtoArray[i].getCodigo() + "\t R$ " + String.format("%.2f", produtoArray[i].getPreco()) );
		}
		
		scan.close();
	}
}

class Produto{
	
	private String nome;
	private int codigo;
	private double preco;
	
	public Produto(String nome, int codigo, double preco) {
		super();
		this.nome = nome;
		this.codigo = codigo;
		this.preco = preco;
	}

	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public double getPreco() {
		return preco;
	}
	public void setPreco(double preco) {
		this.preco = preco;
	}
	
	
	
}