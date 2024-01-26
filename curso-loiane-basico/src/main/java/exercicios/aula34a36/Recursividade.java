package exercicios.aula34a36;

public class Recursividade {
		
	
		public static int fibonacci (int num) {
			if (num <=1) {
				return num;
			} else {
				return fibonacci (num-1) + fibonacci (num-2);
			}
		}
}
