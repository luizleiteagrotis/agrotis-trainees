package ExerciciosAula19;

import java.util.Scanner;

public class Ex13 {
		
    public static void main(String[] args){
	        
        Scanner scan = new Scanner(System.in);
        
        int[]A = new int[10];
        
        for (int i=0; i<A.length; i++){
            System.out.println("Entre com o valor de A" + i + ": ");
            A[i] = scan.nextInt();
        }
        int soma = 0;
        for (int i=0; i<A.length; i++){
            if (A[i] % 5 == 0){
                soma += A[i];
            }
        }
        System.out.print("Vetor A = ");
        for (int i=0; i<A.length; i++){
            System.out.print(A[i] + " ");
        }
        System.out.println();
        System.out.println("Soma: " + soma);
    }    
}