package Aulas19e20;

public class Aula19Vetores {

	public static void main(String[] args) {
		
		double tempDia001 = 31.3;
		double tempDia002 = 32;
		double tempdia003 = 33.7;
		double tempdia004 = 34;
		double tempdia005 = 33.1;
		
		double[] temperaturas = new double[365];
		temperaturas[0] = 31.3;
		temperaturas[1] = 32;
		temperaturas[2] = 33.7;
		temperaturas[3] = 34;
		temperaturas[4] = 33.1;
		
		System.out.println("O valor da temperatura do dia 01 é: " + temperaturas[0]);
		System.out.println("O tamanho do Array é: " + temperaturas.length);
		System.out.println("Valores do Array: ");
		
		for (int i = 0; i<temperaturas.length; i++){
			System.out.println("O valor da temperatura do dia " + (i + 1) + " é: " + temperaturas[i]);
		}
		
		for (double temp : temperaturas){
			System.out.println(temp);
		}
	}
}