package ExerciciosAula15IfElse;

import java.util.Scanner;

public class Ex4 {

	public static void main(String[] args) {
			
		Scanner scan = new Scanner(System.in);
		
		System.out.println("Entre com uma letra: ");
		String letraVC = scan.next();
	
		
		/*if (letraVC.equalsIgnoreCase("a") || letraVC.equalsIgnoreCase("e") || 
			letraVC.equalsIgnoreCase("i") || letraVC.equalsIgnoreCase("o") || 
			letraVC.equalsIgnoreCase("u")){
			System.out.println("Vogal");
		} else {
			System.out.println("Consoante");*/
			
		//Método da correção usando SWITCH
		 
		if (letraVC.length() > 1){
			System.out.println("Não é uma letra");
		} else {
		 switch(letraVC){
		 case "a":
		 case "e":
		 case "i":
		 case "o":
		 case "u":
		 case "A":
		 case "E":
		 case "I":
		 case "O":
		 case "U":System.out.println("Vogal"); break;
		 default:System.out.println("Consoante");
		
		 }		
		}
	}
}