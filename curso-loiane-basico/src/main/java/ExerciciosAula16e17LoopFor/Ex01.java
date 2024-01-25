package ExerciciosAula16e17LoopFor;

import java.util.Scanner;

public class Ex01 {

	public static void main(String[] args) {
		
        Scanner scan = new Scanner(System.in);
        
        boolean notaValida = false;
        
        do {
           
            System.out.println("Entre com a sua nota");
        
            double nota = scan.nextDouble();

            if (nota >=0 && nota <= 10){
                notaValida = true;
                System.out.println("Você digitoua nota: " + nota);
            } else {
                System.out.println("Nota inválida, digite de novo.");
            }
            
        } while (!notaValida);
    }
}