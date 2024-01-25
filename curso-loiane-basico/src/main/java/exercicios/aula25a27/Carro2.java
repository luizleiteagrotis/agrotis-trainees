package exercicios.aula25a27;

public class Carro2 {

	public static void main(String[] args) {
		
		Carro van = new Carro();
		van.marca = "Fiat";
		van.modelo = "Ducato";
		van.numPassageiros = 10;
		van.capCombustivel = 100;
		van.consumoCombustivel = 0.2;
		
		System.out.println(van.modelo);
		System.out.println(van.marca);
		
		van.exibirAutonomia();
		
		double autonomia = van.obterAutonomia();
		System.out.println("A autonomia do carro é: " + autonomia);
		System.out.println("A autonomia do carro é: " + van.obterAutonomia());
		
		double qtdCombustivel10 = van.calcularCombustivel(10);
		double qtdCombustivel15 = van.calcularCombustivel(15);
		
		System.out.println("qtdCombustivel10 = " + qtdCombustivel10);
		System.out.println("qtdCombustivel15 = " + qtdCombustivel15);
		
	}

}