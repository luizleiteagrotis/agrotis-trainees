package exercicios.aula36a43;

import java.util.Scanner;

public class ReceitaFederalEx2 {
	
	    public static void main(String[] args) {
	        Scanner scanner = new Scanner(System.in);

	        Contribuinte[] contribuintes = new Contribuinte[6];

	        // Preencher os dados para 3 Pessoa Jurídica
	        for (int i = 0; i < 3; i++) {
	            System.out.println("Digite o nome da Pessoa Jurídica:");
	            String nome = scanner.nextLine();
	            System.out.println("Digite a renda bruta da Pessoa Jurídica:");
	            double rendaBruta = scanner.nextDouble();
	            scanner.nextLine();  // Consumir a quebra de linha pendente
	            contribuintes[i] = new PessoaJuridica(nome, rendaBruta);
	        }

	        // Preencher os dados para 3 Pessoa Física
	        for (int i = 3; i < 6; i++) {
	            System.out.println("Digite o nome da Pessoa Física:");
	            String nome = scanner.nextLine();
	            System.out.println("Digite a renda bruta da Pessoa Física:");
	            double rendaBruta = scanner.nextDouble();
	            scanner.nextLine();  // Consumir a quebra de linha pendente
	            contribuintes[i] = new PessoaFisica(nome, rendaBruta);
	            
	        }

	        // Calcular e imprimir o imposto para todos os contribuintes

	        }
}