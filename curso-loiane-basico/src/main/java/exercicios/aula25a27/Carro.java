package exercicios.aula25a27;

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
	
}
