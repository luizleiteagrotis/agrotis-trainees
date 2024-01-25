package ExerciciosAula15IfElse;

import java.util.Scanner;

public class Ex16 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
        
        System.out.println("Entre com o valor de a:");
        int a = scan.nextInt();
        
        if (a == 0){
            System.out.println("Não é equação de segundo grau");
        } else {
            
            System.out.println("Entre com o valor de b:");
            int b = scan.nextInt();
        
            System.out.println("Entre com o valor de c:");
            int c = scan.nextInt();
            
            double delta = (b*b) - (4*a*c);
            if (delta < 0){
                System.out.println("delta negativo");
            } else {
                
                System.out.println("delta: " + delta);
                
                double x1 = ((-b) + Math.sqrt(delta)) / (2*a);
                double x2 = ((-b) - Math.sqrt(delta)) / (2*a);
                
                System.out.println("x1 = " + x1);
                System.out.println("x2 = " + x2);
            }   
        }
    }
}