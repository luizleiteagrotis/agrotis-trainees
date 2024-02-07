
package exercicios.aula19;


class Exercicio19{
	public static void main (String args[]) {
		
		int[] nota1 = {10,6,6,8,7,10,4,5,7,9};
		int[] nota2 = {8,4,8,9,7,9,10,6,7,10};

		int[] result = new int[10];
		int indexMaior = 0;
		int indexMenor = 0;
		
		for(int i = 0; i< nota1.length; i++) {
			result[i] = (nota1[i] + nota2[i])/2;
		}
		
		for(int i = 0; i < result.length; i++) {
			if(result[i] >= 7) System.out.println("Média: " + result[i] + " Situação: APROVADO");
			else  System.out.println("Média: " + result[i] + " Situação: REPROVADO");
		}

	}
}
