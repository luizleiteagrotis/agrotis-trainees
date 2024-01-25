package ExerciciosAula19;

public class Ex01 {

	public static void main(String[] args) {
		
		int[] A = new int[5];
		A[0] = 5;
		A[1] = 4;
		A[2] = 3;
		A[3] = 2;
		A[4] = 1;
		
		int[] B = A;

		for (int i = 0; i<A.length; i++){
			System.out.println("O valor do elemento " + (i + 1) + ("A") + " é: " + A[i]);
		}
		
		System.out.println();
		
		for (int i = 0; i<B.length; i++){
			System.out.println("O valor do elemento " + (i + 1) + ("B") + " é: " + B[i]);
		}
	}
}
