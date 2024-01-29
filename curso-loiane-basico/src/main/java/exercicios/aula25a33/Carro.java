package exercicios.aula25a33;

public class Carro {
	
	String modelo;
	String marca;
	int ano;
	String cor;
	String versao;
	double motorizacao;
	int numPassageiros;
	int capCombustivel;
	double consumoCombustivel;
	
	void exibirAutonomia() {
		System.out.println("A autonomia do carro é: " + capCombustivel * consumoCombustivel + "km");
	}
	
	double obterAutonomia() {
		
		System.out.println("Método obter autonomia foi chamado.");
		
		return capCombustivel * consumoCombustivel;
	}
	
	double calcularCombustivel(double km) {
		double qtdCombustivel = km / capCombustivel;
		return qtdCombustivel;
	}
	
}
