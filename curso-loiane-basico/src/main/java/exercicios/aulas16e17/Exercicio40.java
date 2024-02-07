
package exercicios.aulas16e17;

import java.util.Scanner;

class Exercicio40{
	public static void main (String args[]) {
		
		Scanner scan = new Scanner(System.in);
		
		int k = 0;
		int n = 0;
		
		Cidade[] cidadeArray = new Cidade[5];
		
		cidadeArray[0] = new Cidade();
		cidadeArray[1] = new Cidade();
		cidadeArray[2] = new Cidade();
		cidadeArray[3] = new Cidade();
		cidadeArray[4] = new Cidade();
		
		Cidade cidadeMaiorAcidentes = new Cidade(0, 0, 0);
		Cidade cidadeMenorAcidentes = new Cidade(0, 0, Integer.MAX_VALUE);
		
		double mediaVeiculos = 0;
		double mediaVeiculosParcial = 0;
		
		int sum1 = 0;
		int sum2 = 0;
		
		while(k < 5) {
			System.out.print("Código da Cidade: ");
			cidadeArray[k].setCodigo(scan.nextInt());
			
			System.out.print("Número de veículos de passeio (em 1999): ");
			cidadeArray[k].setNumVeiculosPasseio(scan.nextInt());
			
			System.out.print("Número de acidentes de trânsito com vítimas (em 1999): ");
			cidadeArray[k].setNumAcidentes(scan.nextInt());
					
			k++;
		}
		
		for(int i = 0; i < cidadeArray.length; i++ ) {
			
			sum1 += cidadeArray[i].getNumVeiculosPasseio();
			
			if(cidadeArray[i].getNumVeiculosPasseio() < 2000) {
				sum2 += cidadeArray[i].getNumAcidentes();
				n++;
			}
			
					
			if(cidadeArray[i].getNumAcidentes() > cidadeMaiorAcidentes.getNumAcidentes()) {
				cidadeMaiorAcidentes.setCodigo(cidadeArray[i].getCodigo());
				cidadeMaiorAcidentes.setNumAcidentes(cidadeArray[i].getNumAcidentes());
				cidadeMaiorAcidentes.setNumVeiculosPasseio(cidadeArray[i].getNumVeiculosPasseio());
			}
			
			if(cidadeArray[i].getNumAcidentes() < cidadeMenorAcidentes.getNumAcidentes()) {
				cidadeMenorAcidentes.setCodigo(cidadeArray[i].getCodigo());
				cidadeMenorAcidentes.setNumAcidentes(cidadeArray[i].getNumAcidentes());
				cidadeMenorAcidentes.setNumVeiculosPasseio(cidadeArray[i].getNumVeiculosPasseio());
			}
			
		}
		
		mediaVeiculos = sum1 / 5;
		mediaVeiculosParcial = sum2 / n;
		
		System.out.println("Cidade com maior índice de acidentes: " + cidadeMaiorAcidentes.getCodigo() + " Índice de acidentes: " + cidadeMaiorAcidentes.getNumAcidentes() );
		System.out.println("Cidade com menor índice de acidentes: " + cidadeMenorAcidentes.getCodigo() + " Índice de acidentes: " + cidadeMenorAcidentes.getNumAcidentes() );
		
		System.out.println("Média de veículos nas cinco cidades: " + mediaVeiculos);
		System.out.println("Média de acidentes nas cidades de menos de 2000 veículos: " + mediaVeiculosParcial);
		

		scan.close();
	}
}

class Cidade{
	
	private int codigo;
	private int numVeiculosPasseio;
	private int numAcidentes;
	
	public Cidade() {
		this.codigo = 0;
		this.numVeiculosPasseio = 0;
		this.numAcidentes = 0;
	}
	
	public Cidade(int codigo, int numVeiculosPasseio, int numAcidentes) {
		super();
		this.codigo = codigo;
		this.numVeiculosPasseio = numVeiculosPasseio;
		this.numAcidentes = numAcidentes;
	}
	
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public int getNumVeiculosPasseio() {
		return numVeiculosPasseio;
	}
	public void setNumVeiculosPasseio(int numVeiculosPasseio) {
		this.numVeiculosPasseio = numVeiculosPasseio;
	}
	public int getNumAcidentes() {
		return numAcidentes;
	}
	public void setNumAcidentes(int numAcidentes) {
		this.numAcidentes = numAcidentes;
	}

}
