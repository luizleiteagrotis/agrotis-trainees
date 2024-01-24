package exercicios.aula35.exercicios1;

public class Main {
	
	public static void main(String[] args) {
		
		for (int i = 0; i < 10; i++) {
			System.out.print(fibonacci(i) + " ");			
		}
		
	}
	
	public static int fibonacci(int num) {		
		if ( num < 2) {
			return num;
		}
		
		return fibonacci(num-1) + fibonacci(num-2);
		
		
	}
}
