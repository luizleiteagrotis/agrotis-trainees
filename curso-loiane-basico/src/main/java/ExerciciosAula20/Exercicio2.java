package ExerciciosAula20;

import java.util.Random;
import java.util.Scanner;

public class Exercicio2 {

	public static void main(String[] args) {
		
		Scanner scan = new Scanner (System.in);
        Random numeroRandom = new Random();
		// declarando variaveis
		int linha = 0;
		int coluna = 0;
		int maiorValor = 0;
		//Instanciar
		
		int[][] valor = new int[10][10];
		
		for (int i = 0; i < valor.length; i++) {
            for (int j = 0; j < valor[i].length; j++) {
                valor[i][j] = numeroRandom.nextInt(100);
            }
        }
        
        for (int i=0; i<valor.length; i++){
            for (int j=0; j<valor[i].length; j++){
                System.out.print(valor[i][j] + " - ");
            }
            System.out.println();
        }
        
        int maiorL5 = 0;
        int menorL5 = 101;
        int linha5 = 5;
        for (int i=0; i<valor[linha5].length; i++){
            if (valor[linha5][i] > maiorL5){
                maiorL5 = valor[linha5][i];
            }
            if (valor[linha5][i] < menorL5){
                menorL5 = valor[linha5][i];
            }
        }
        
        System.out.println("Maior valor da linha 5 = " + maiorL5);
        System.out.println("Menor valor da linha 5 = " + menorL5);
        
        int maiorC7 = 0;
        int menorC7 = 101;
        int col7 = 7;
        for (int i=0; i<valor.length; i++){
            if (valor[i][col7] > maiorC7){
                maiorC7 = valor[i][col7]; 
            }
            if (valor[i][col7] < menorC7){
                menorC7 = valor[i][col7]; 
            }
        }
        
        System.out.println("Maior valor da coluna 7 = " + maiorC7);
        System.out.println("Menor valor da coluna 7 = " + menorC7);
    }

}