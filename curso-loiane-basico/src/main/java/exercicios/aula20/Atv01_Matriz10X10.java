package atividades20;

import java.util.Random;

public class Atv01_Matriz10X10 {

    public static void main(String[] args) {

        int[][] numerosAleatorios = new int[10][10];

        Random random = new Random();

        for (int i = 0; i < numerosAleatorios.length; i++) {
            for (int j = 0; j < numerosAleatorios[i].length; j++) {
                numerosAleatorios[i][j] = random.nextInt(9);
            }
        }

        int maiorLinha5 = Integer.MIN_VALUE;
        int menorLinha5 = Integer.MAX_VALUE;
        int maiorColuna7 = Integer.MIN_VALUE;
        int menorColuna7 = Integer.MAX_VALUE;

        for (int i = 0; i < numerosAleatorios[5].length; i++) {
            if (numerosAleatorios[5][i] > maiorLinha5) {
                maiorLinha5 = numerosAleatorios[5][i];
            }
            if (numerosAleatorios[5][i] < menorLinha5) {
                menorLinha5 = numerosAleatorios[5][i];
            }

        }

        for (int i = 0; i < numerosAleatorios.length; i++) {
            if (numerosAleatorios[i][7] > maiorColuna7) {
                maiorColuna7 = numerosAleatorios[5][i];
            }
            if (numerosAleatorios[i][7] < menorColuna7) {
                menorColuna7 = numerosAleatorios[5][i];
            }

        }

        for (int i = 0; i < numerosAleatorios.length; i++) {
            for (int j = 0; j < numerosAleatorios[i].length; j++) {
                System.out.print(numerosAleatorios[i][j] + " ");
            }
            System.out.println();
        }

        System.out.println("Maior valor da linha 5 = " + maiorLinha5);
        System.out.println("Menor valor da linha 5 = " + menorLinha5);
        System.out.println("Maior valor da coluna 7 = " + maiorColuna7);
        System.out.println("Menor valor da coluna 7 = " + menorColuna7);

    }

}
