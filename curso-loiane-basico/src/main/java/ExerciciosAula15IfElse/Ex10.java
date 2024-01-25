package ExerciciosAula15IfElse;

import java.util.Scanner;

public class Ex10 {

	public static void main(String[] args) {
		
Scanner scan = new Scanner(System.in);
        
        System.out.println("Digite o seu turno: ");
        String turnos = scan.next();
        
		if (turnos.equalsIgnoreCase("m")){
			System.out.println("Bom dia");
		} else if (turnos.equalsIgnoreCase("v")){ 
			System.out.println("Boa tarde");
		} else if (turnos.equalsIgnoreCase("n")){
			System.out.println("Boa noite");
		}
        
		//fazendo com switch de acordo com a correção
        /*switch(turnos){
            case "m":
            case "M": System.out.println("Bom dia!"); break; 
            case "v":
            case "V": System.out.println("Boa tarde!"); break; 
            case "n":
            case "N": System.out.println("Boa noite!"); break;  
            default: System.out.println("Valor inválido"); 
        }*/
    }
}