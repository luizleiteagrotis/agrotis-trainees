package ExerciciosAula15IfElse;

import java.util.Scanner;

public class Ex26 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner(System.in);
		
        System.out.println("Entre com a qtd de litros vendidos");
        double litros = scan.nextDouble();
        
        System.out.println("Entre com o tipode combustível");
        String tipo = scan.next();
        
        double precoGasolina = 2.5;
        double precoAlcool = 1.9;
        double total = 0;
        int percentualDesconto = 0;
        double totalDesconto = 0;
        
        if (tipo.equalsIgnoreCase("a")){
            
            if (litros <= 20){
                percentualDesconto = 3;
            } else {
                percentualDesconto = 5;
            }
            
            total = litros * precoAlcool;
            
        } else if (tipo.equalsIgnoreCase("g")){
            
            if (litros <= 20){
                percentualDesconto = 4;
            } else {
                percentualDesconto = 6;
            }
            
            total = litros * precoGasolina;
        }
        
        totalDesconto = (total / 100) * percentualDesconto;
        
        double totalGeral= total - totalDesconto;
        
        System.out.println("Valor total: " + totalGeral);
    }
}