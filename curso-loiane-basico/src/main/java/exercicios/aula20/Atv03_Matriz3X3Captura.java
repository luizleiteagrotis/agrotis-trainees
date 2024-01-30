package atividades20;

import java.util.Scanner;

public class Atv03_Matriz3X3Captura {

    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);

        int[][] numeros = new int[3][3];

        for (int i = 0; i < numeros.length; i++) {
            for (int j = 0; j < numeros[i].length; j++) {
                System.out.println("Entre com o valor do índice [" + i + "," + j + "]");
                numeros[i][j] = scan.nextInt();
            }
        }

        scan.close();

        int countPar = 0, countImpar = 0;
        for (int i = 0; i < numeros.length; i++) {
            for (int j = 0; j < numeros[i].length; j++) {
                if ((numeros[i][j] % 2) == 0) {
                    countPar++;
                } else {
                    countImpar++;
                }
            }
        }

        System.out.println();

        for (int i = 0; i < numeros.length; i++) {
            for (int j = 0; j < numeros[i].length; j++) {
                System.out.print(numeros[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Há " + countPar + " números pares e " + countImpar + " números ímpares.");

    }

}
