package ExerciciosAula19;

public class Ex3 {

	public static void main(String[] args) {
		
		int[] A = new int[15];
		A[0] = 23;
		A[1] = 38;
		A[2] = 12;
		A[3] = 1;
		A[4] = 260;
		A[5] = 15;
		A[6] = 8;
		A[7] = 10;
		A[8] = 506;
		A[9] = 400;
		A[10] = 201;
		A[11] = 17;
		A[12] = 21;
		A[13] = 6;
		A[14] = 40;
		


		for (int i = 0; i<A.length; i++){
			System.out.println("O valor do elemento " + (i + 1) + ("A") + " é: " + A[i]);
		}
		
		int[] B = A;
		System.out.println();
		
		for (int i = 0; i<B.length; i++){
			System.out.println("O valor do elemento " + (i + 1) + ("B") + " é: " + B[i] * B[i]);
		}
	}
}
