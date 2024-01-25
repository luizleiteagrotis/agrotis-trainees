package ExerciciosAula19;

import java.util.Scanner;

public class Ex11 {
		
    public static void main(String[] args){
	        
        Scanner scan = new Scanner(System.in);
        
        int[] A = new int[10];
        
        for (int i=0; i<A.length; i++){
            System.out.println("Entre com o A" + i + ": ");
            A[i] = scan.nextInt();
        }
        int quantidadePares = 0;
        for (int i=0; i<A.length; i++){
            if (A[i] % 2 == 0){
            	quantidadePares++;
            }
        }
        
        System.out.print("A = ");
        for (int i=0; i<A.length; i++){
            System.out.print(A[i] + " ");
        }
        System.out.println();
        
        System.out.println("Quantidade de numeros pares: " + quantidadePares);
    }    
}