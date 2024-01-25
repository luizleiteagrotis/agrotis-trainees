package ExerciciosAula19;

import java.util.Scanner;

public class Ex7 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
        int[] A = new int[10];
        int[] B = new int[A.length];
        int[] C = new int[A.length];
        
        for (int i=0; i<A.length; i++){
            System.out.println("Entre com o valor de A" + i + ": ");
            A[i] = scan.nextInt();
        }
        
        for (int i=0; i<B.length; i++){
            System.out.println("Entre com o valor de B" + i + ": ");
            B[i] = scan.nextInt();
        }
        
        for (int i=0; i<C.length; i++){
            C[i] = A[i] - B[i];
        }
        
        System.out.print("A = ");
        for (int i=0; i<A.length; i++){
            System.out.print(A[i] + " ");
        }
        System.out.println();
        
        System.out.print("B = ");
        for (int i=0; i<B.length; i++){
            System.out.print(B[i] + " ");
        }
        System.out.println();
        
        System.out.print("C = ");
        for (int i=0; i<C.length; i++){
            System.out.print(C[i] + " ");
        }
        System.out.println();
    }    
}
