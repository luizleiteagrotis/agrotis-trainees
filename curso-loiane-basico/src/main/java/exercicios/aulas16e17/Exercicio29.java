
package exercicios.aulas16e17;

class Exercicio29{


	public static void main (String args[]) {
		
		System.out.println("Lojas Quase Dois - Tabela de preços ");
		
		for(int i = 0; i < 50; i++) {
			
			System.out.println((i+1) + "\t-\tR$" + String.format("%.2f",(i+1)*1.99));
				
		}

	}
}
