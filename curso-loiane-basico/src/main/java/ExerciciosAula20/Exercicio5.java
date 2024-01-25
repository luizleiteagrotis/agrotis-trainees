package ExerciciosAula20;

import java.util.Scanner;

public class Exercicio5 {
	
	public static void main(String[] args) {
		
		Scanner scan = new Scanner (System.in);
		
        String[][][] M = new String[12][31][8];
        
        boolean sair = false;
        byte opcao;
        while (!sair){
        	System.out.println("Escolha uma das opções abaixo (digite apenas o nº): ");
            System.out.println("1 - Adicionar Compromisso;");
            System.out.println("2 - Consultar Compromisso;");
            System.out.println("0 - Sair.");
            
            opcao = scan.nextByte();
            
            if (opcao == 1){ //adicionar compromisso
                
                boolean mesValido = false;
                int mes = 0;
                while (!mesValido){
                	System.out.println("Digite o mes que deseja registrar o compromisso: ");
                    mes = scan.nextInt();
                    if (mes > 0 && mes <= 12){
                        mesValido = true;
                    } else {
                    	System.out.println("Mes inválido, tente novamente: ");
                    }
                }
                
                boolean diaValido = false;
                int dia = 0;
                while (!diaValido){
                	System.out.println("Digite o dia que deseja registrar o compromisso: ");
                    dia = scan.nextInt();
                    if (dia > 0 && dia <= 31){
                        diaValido = true;
                    } else {
                        System.out.println("Dia inválido, tente novamente: ");
                    }
                }
                
                boolean horaValida = false;
                int hora = 0;
                while (!horaValida){
                	System.out.println("Digite a hora que deseja registrar o compromisso:");
                    hora = scan.nextInt();
                    if (hora >= 0 && hora <= 8){
                        horaValida = true;
                    } else {
                        System.out.println("Hora inválida, tente novamente: ");
                    }
                }
                
                mes--;
                dia--;
                System.out.println("Digite o compromisso: ");
                M[mes][dia][hora] = scan.next();
                
            } else if (opcao == 2){ //verificar compromisso
                
                boolean mesValido = false;
                int mes = 0;
                while (!mesValido){
                	System.out.println("Digite o mes que deseja registrar o compromisso:");
                    mes = scan.nextInt();
                    if (mes > 0 && mes <= 12){
                        mesValido = true;
                    } else {
                        System.out.println("Mes inválido, tente novamente: ");
                    }
                }
                
                boolean diaValido = false;
                int dia = 0;
                while (!diaValido){
                	System.out.println("Digite o dia que deseja registrar o compromisso: ");
                    dia = scan.nextInt();
                    if (dia > 0 && dia <= 31){
                        diaValido = true;
                    } else {
                        System.out.println("Dia inválido, tente novamente: ");
                    }
                }
                
                boolean horaValida = false;
                int hora = 0;
                while (!horaValida){
                	System.out.println("Digite a hora que deseja registrar o compromisso: ");
                    hora = scan.nextInt();
                    if (hora >= 0 && hora <= 24){
                        horaValida = true;
                    } else {
                        System.out.println("Hora inválida, tente novamente: ");
                    }
                }
                
                mes--;
                dia--;
                System.out.println("O compromisso agendado é: ");
                System.out.println(M[mes][dia][hora]);
                
            } else if (opcao == 0){
                sair = true;
            } else {
                System.out.println("Opção inválida, tente novamente: ");
            }
        }
    }
}