package ExerciciosAula15IfElse;

import java.util.Scanner;

public class Ex12 {

	public static void main(String[] args) {
		
        Scanner scan = new Scanner(System.in);
        
        System.out.println("Quanto você recebe por hora trabalhada? ");
        double valorPorHora = scan.nextDouble();
        
        System.out.println("Quantas horas você trabalhou este mês? ");
        double horas = scan.nextDouble();
        
        double salarioBruto = valorPorHora * horas;
        
        int percentualIR = 0;
        if (salarioBruto <= 900){
            percentualIR = 0;
        } else if (salarioBruto > 900 && salarioBruto <= 1500){
            percentualIR = 5;
        } else if (salarioBruto > 1500 && salarioBruto <= 2500){
            percentualIR = 10;
        } else if (salarioBruto > 2500){
            percentualIR = 20;
        }
        
        double IR = (salarioBruto / 100) * percentualIR;
        double inss = (salarioBruto / 100) * 10;
        double fgts = (salarioBruto / 100) * 11;
        double descontos = IR + inss;
        double salarioLiquido = salarioBruto - descontos;
        
        System.out.println("Salário Bruto: (" + valorPorHora + " * " + horas + "): " + salarioBruto);
        System.out.println("(-) IR (" + percentualIR + "%):" + IR);
        System.out.println("(-) INSS ( 10%): " + inss);
        System.out.println("FGTS (11%): " + fgts);
        System.out.println("Descontos: " + descontos);
        System.out.println("Salário Liquido: " + salarioLiquido);
    }
}