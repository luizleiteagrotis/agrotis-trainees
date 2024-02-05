
package exercicios.aulas16e17;

class Exercicio4{
	public static void main (String args[]) {
		
		double populacaoA = 80000;
		double populacaoB = 200000;
		
		int anos = 0;
		
		while(populacaoA < populacaoB) {
			
			populacaoA = populacaoA * 1.03;
			populacaoB = populacaoB * 1.015;
			anos ++;
			
		}
		
		System.out.println("Anos necessários: " + anos);
	}
}
