package exercicios.aula34a36;

public class Recursividade2 {

	public static int soma(int somatorioNum) {
		if (somatorioNum <= 0) {
			return 0;
		} else {
			return somatorioNum + soma (somatorioNum - 1);
		}
	}
	
}
